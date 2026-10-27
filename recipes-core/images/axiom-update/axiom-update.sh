#!/bin/sh
# NVIDIA capsule flow, following the Tegra SWUpdate reference integration.
set -eu

phase=${1:?Missing SWUpdate phase}
selection=${2:?Missing current-slot selection}
capsule=/boot/efi/EFI/UpdateCapsule/TEGRA_BL.Cap
staged_capsule=/run/swupdate/axiom-tegra-bl.cap

case "$phase" in
    preinst)
        /usr/libexec/swupdate/axiom-swupdate-setup "$selection"
        [ ! -e "$capsule" ] || {
            echo "A firmware capsule is already pending; reboot before updating again." >&2
            exit 1
        }
        rm -f "$staged_capsule"
        ;;
    postinst)
        /usr/libexec/swupdate/axiom-swupdate-setup "$selection"
        [ -s "$staged_capsule" ]
        case "$selection" in
            slot_a) target_status=RootfsStatusSlotB ;;
            slot_b) target_status=RootfsStatusSlotA ;;
            *) exit 1 ;;
        esac

        # Keep the capsule outside UpdateCapsule until every payload has succeeded.
        status_file=$(mktemp /run/swupdate/efi-value.XXXXXX)
        armed=false
        cleanup() {
            rm -f "$status_file" "$capsule.tmp"
            if [ "$armed" = false ]; then
                rm -f "$capsule"
                sync
            fi
        }
        trap cleanup EXIT
        mkdir -p /boot/efi/EFI/UpdateCapsule
        cp "$staged_capsule" "$capsule.tmp"
        sync
        mv "$capsule.tmp" "$capsule"

        # Reset only the destination rootfs status, as in the Tegra reference.
        printf '\000\000\000\000' > "$status_file"
        efivar -w -f "$status_file" -n "781e084c-a330-417c-b678-38e696380cb9-$target_status"
        sync

        # Request firmware installation last. UEFI applies the capsule and changes slots.
        printf '\004\000\000\000\000\000\000\000' > "$status_file"
        efivar -w -f "$status_file" -n '8be4df61-93ca-11d2-aa0d-00e098032b8c-OsIndications'
        armed=true
        rm -f "$staged_capsule"
        ;;
    postfailure)
        rm -f "$staged_capsule"
        ;;
    *) exit 1 ;;
esac
