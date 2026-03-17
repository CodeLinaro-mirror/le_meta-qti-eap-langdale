inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "loc socket library"
PR = "r1"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/location/utils/loc_socket"
S = "${WORKDIR}/vendor/qcom/opensource/location/utils/loc_socket"

DEPENDS = "virtual/kernel gps-utils qmi-framework"
