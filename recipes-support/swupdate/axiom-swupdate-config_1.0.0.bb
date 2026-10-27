SUMMARY = "Axiom SWUpdate slot selection and capsule activation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://axiom-swupdate-setup \
    file://20-axiom-args \
    file://axiom.conf \
"

S = "${UNPACKDIR}"
COMPATIBLE_MACHINE = "^jetson-orin-nano-devkit$"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} = " \
    axiom-storage \
    coreutils \
    efivar \
    setup-nv-boot-control-service \
    tegra-redundant-boot \
    util-linux-findmnt \
    util-linux-lsblk \
    util-linux-mountpoint \
"

do_install() {
    install -d ${D}${libexecdir}/swupdate ${D}${libdir}/swupdate/conf.d
    install -m 0755 ${S}/axiom-swupdate-setup ${D}${libexecdir}/swupdate/
    install -m 0644 ${S}/20-axiom-args ${D}${libdir}/swupdate/conf.d/
    install -d ${D}${systemd_system_unitdir}/swupdate.service.d
    sed -e 's,@LIBEXECDIR@,${libexecdir},g' \
        ${S}/axiom.conf > ${D}${systemd_system_unitdir}/swupdate.service.d/axiom.conf
    install -d ${D}${sysconfdir}
    printf '%s 1.0.0\n' '${MACHINE}' > ${D}${sysconfdir}/hwrevision
}

FILES:${PN} += "${libdir}/swupdate ${systemd_system_unitdir}/swupdate.service.d"
