inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "location integration api library"
PR = "r1"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_URI = "file://vendor/qcom/opensource/location/integration_api/"
S = "${WORKDIR}/vendor/qcom/opensource/location/integration_api"

DEPENDS = "loc-socket location-hal-daemon-hdr"
