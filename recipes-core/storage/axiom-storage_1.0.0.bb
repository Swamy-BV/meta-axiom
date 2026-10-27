SUMMARY = "Axiom persistent storage and shared var/lib overlay"
require conf/distro/include/axiom-storage.inc
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://axiom-storage-directories.service \
    file://axiom-storage.conf \
    file://storage.conf \
"

S = "${UNPACKDIR}"
COMPATIBLE_MACHINE = "^jetson-orin-nano-devkit$"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SYSTEMD_SERVICE:${PN} = ""
OVERLAYFS_WRITABLE_PATHS[persist] = "/var/lib"

inherit overlayfs

RDEPENDS:${PN} = " \
    base-files \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    systemd \
"

do_install() {
    install -d ${D}/persist ${D}/data ${D}${localstatedir}/lib
    install -d ${D}${nonarch_libdir}/tmpfiles.d ${D}${systemd_system_unitdir}/var-lib.mount.d
    install -m 0644 ${S}/axiom-storage.conf ${D}${nonarch_libdir}/tmpfiles.d/
    install -m 0644 ${S}/axiom-storage-directories.service ${D}${systemd_system_unitdir}/
    install -m 0644 ${S}/storage.conf ${D}${systemd_system_unitdir}/var-lib.mount.d/
}

FILES:${PN} += " \
    /persist \
    /data \
    ${localstatedir}/lib \
    ${nonarch_libdir}/tmpfiles.d/axiom-storage.conf \
    ${systemd_system_unitdir}/axiom-storage-directories.service \
    ${systemd_system_unitdir}/var-lib.mount.d \
"
