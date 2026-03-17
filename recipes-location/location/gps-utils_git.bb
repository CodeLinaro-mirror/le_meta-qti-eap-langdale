inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "GPS Utils"
PR = "r1"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_DIR = "${WORKSPACE}/hardware/qcom/gps"
S = "${WORKDIR}/hardware/qcom/gps/utils"

DEPENDS = "glib-2.0 loc-pla-hdr location-api-iface"

do_install:append() {
    #Install gps.conf file
    install -m 0644 -D ${WORKDIR}/hardware/qcom/gps/etc/gps.conf ${D}${sysconfdir}/gps.conf
}

