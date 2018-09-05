DESCRIPTION = "Configuration files for the system"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI += "file://network"
SRC_URI += "file://system"
SRC_URI += "file://udev"

FILES_${PN} += "${sysconfdir}"

do_install() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/network/*.network ${D}${sysconfdir}/systemd/network/
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/udev/* ${D}${sysconfdir}/udev/rules.d/
    install -d ${D}${sysconfdir}/systemd/system
    install -m 0644 ${WORKDIR}/system/* ${D}${sysconfdir}/systemd/system/
}
