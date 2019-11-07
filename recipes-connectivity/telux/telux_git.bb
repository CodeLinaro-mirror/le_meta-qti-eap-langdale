inherit cmake

SUMMARY = "Telematics SDK headers"
DESCRIPTION = "Telematics SDK headers"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_DIR = "${WORKSPACE}/telux/public/include"
S = "${WORKDIR}/telux/public/include"

SRCREV = "${AUTOREV}"

ALLOW_EMPTY_${PN} = "1"
