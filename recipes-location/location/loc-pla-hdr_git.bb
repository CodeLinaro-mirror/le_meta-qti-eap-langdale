inherit pkgconfig

DESCRIPTION = "GPS Loc Platform Library Abstraction"
PR = "r1"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_DIR = "${WORKSPACE}/hardware/qcom/gps/pla/oe/"
S = "${WORKDIR}/hardware/qcom/gps/pla/oe"

do_compile() {
}

do_install() {
    install -d ${D}${includedir}
    install -m 644 ${S}/*.h ${D}${includedir}
}

PACKAGES = "${PN}"
FILES:${PN} += "/usr/include/*"
