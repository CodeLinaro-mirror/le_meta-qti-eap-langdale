inherit autotools-brokensep qcommon pkgconfig

DESCRIPTION = "location HAL daemon headers"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/location/location_hal_daemon"
S = "${WORKDIR}/vendor/qcom/opensource/location/location_hal_daemon"

do_configure() {
}

do_compile() {
}

do_install() {
    install -d ${D}${includedir}
    install -m 644 ${S}/*.h ${D}${includedir}
}
