inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "Location client api hdr"
PR = "r1"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_URI = "file://vendor/qcom/opensource/location/client_api/inc/"
S = "${WORKDIR}/vendor/qcom/opensource/location/client_api/inc/"
FILES_${PN} += "${libdir}/*"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${includedir}
    install -m 644 ${S}/*.h ${D}${includedir}
}
