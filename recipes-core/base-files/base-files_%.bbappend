FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI:append:jetson-orin-nano-devkit = " file://axiom-fstab"

do_install:append:jetson-orin-nano-devkit() {
    cat ${UNPACKDIR}/axiom-fstab >> ${D}${sysconfdir}/fstab
}
