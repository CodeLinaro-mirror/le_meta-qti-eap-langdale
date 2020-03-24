inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "location client api library"
PR = "r1"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/location/client_api"
S = "${WORKDIR}/vendor/qcom/opensource/location/client_api"

DEPENDS = "loc-socket location-hal-daemon-hdr"
