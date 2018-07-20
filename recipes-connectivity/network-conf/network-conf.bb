DESCRIPTION = "Network configuration files for systemd-networkd"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI += "file://network"

FILES_${PN} += "${sysconfdir}"

do_install() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/network/*.network ${D}${sysconfdir}/systemd/network/
}
