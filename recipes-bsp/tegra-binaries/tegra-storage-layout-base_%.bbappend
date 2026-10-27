# This shared-BSP recipe has no fetch/unpack tasks; track the local XML explicitly.
AXIOM_PARTITION_FILE := "${THISDIR}/files/flash_t234_axiom_qspi_sd_rootfs_ab.xml"
PARTITION_FILE:jetson-orin-nano-devkit = "${AXIOM_PARTITION_FILE}"
do_install[file-checksums] += "${AXIOM_PARTITION_FILE}:True"
