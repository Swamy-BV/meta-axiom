FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append:jetson-orin-nano-devkit = " file://flash_t234_axiom_qspi_sd_rootfs_ab.xml"
PARTITION_FILE:jetson-orin-nano-devkit = "${UNPACKDIR}/flash_t234_axiom_qspi_sd_rootfs_ab.xml"
