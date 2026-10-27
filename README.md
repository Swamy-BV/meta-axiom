# Axiom

Embedded AI and robotics, built with Yocto.

```sh
source axiom-setup.sh -b build
bitbake axiom-image
```

Run from the workspace root after syncing `jetson-manifest`.

| Location | Contents |
|---|---|
| `conf/` | Distribution and build defaults |
| `recipes-core/images/` | `axiom-image` recipe |
| `recipes-bsp/tegra-binaries/files/` | Partition-layout XML |
| `tools/` | Setup script |
| `build/tmp/deploy/images/jetson-orin-nano-devkit/` | Generated images, relative to workspace root |

```mermaid
flowchart TB
    Q["QSPI NOR · 64 MiB<br/>Firmware A / B<br/>Boot configuration and UEFI variables"]
    subgraph SD["microSD partition layout"]
        BOOT["NVIDIA kernel / DTB / EFI / recovery / reserved"]
        A["APP · rootfs A · 8 GiB"]
        B["APP_b · rootfs B · 8 GiB"]
        P["persist · shared state · 512 MiB"]
        D["UDA · shared data · remaining capacity"]
        BOOT --- A --- B --- P --- D
    end
    Q ~~~ BOOT
```

Layout defined; hardware validation, persistent mounts, overlays and SWUpdate bundles pending.
