inherit autotools-brokensep qcommon pkgconfig

DESCRIPTION = "GPS Loc Platform Library Abstraction"
PR = "r1"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_DIR = "${WORKSPACE}/hardware/qcom/gps/pla/oe/"
S = "${WORKDIR}/hardware/qcom/gps/pla/oe"

do_compile() {
}

do_install() {
    install -d ${D}${includedir}
    install -m 644 ${S}/*.h ${D}${includedir}
}
