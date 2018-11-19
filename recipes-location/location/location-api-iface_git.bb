inherit autotools-brokensep qcommon pkgconfig

DESCRIPTION = "Location API Interface"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_DIR = "${WORKSPACE}/hardware/qcom/gps/location"
S = "${WORKDIR}/hardware/qcom/gps/location"

do_configure() {
}

do_compile() {
}

do_install() {
    install -d ${D}${includedir}
    install -m 644 ${S}/*.h ${D}${includedir}
}
