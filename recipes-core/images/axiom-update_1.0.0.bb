SUMMARY = "Axiom A/B Linux and NVIDIA firmware update bundle"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://sw-description \
    file://axiom-update.sh \
"

inherit swupdate image_types_tegra

COMPATIBLE_MACHINE = "^jetson-orin-nano-devkit$"
IMAGE_DEPENDS = "axiom-image tegra-espimage"

AXIOM_UPDATE_ROOTFS = "axiom-image-${MACHINE}.rootfs.ext4.gz"
AXIOM_UPDATE_KERNEL = "${@os.path.basename(d.getVar('IMAGE_TEGRAFLASH_KERNEL'))}"
AXIOM_UPDATE_DTB = "${KERNEL_DEVICETREE}"
AXIOM_UPDATE_ESP = "tegra-espimage-${MACHINE}.tar.gz"

SWUPDATE_IMAGES = " \
    ${AXIOM_UPDATE_ROOTFS} \
    ${AXIOM_UPDATE_KERNEL} \
    devicetree/${AXIOM_UPDATE_DTB} \
    ${AXIOM_UPDATE_ESP} \
    tegra-bl.cap \
"

do_swuimage[depends] += " \
    ${INITRD_IMAGE}:do_image_complete \
    nvidia-kernel-oot-dtb:do_deploy \
    tegra-uefi-capsules:do_deploy \
"
