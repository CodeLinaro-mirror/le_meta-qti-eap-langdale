inherit autotools-brokensep qcommon pkgconfig

DESCRIPTION = "location client api library"
PR = "r1"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/location/client_api"
S = "${WORKDIR}/vendor/qcom/opensource/location/client_api"

DEPENDS = "gps-utils location-hal-daemon-hdr"
EXTRA_OECONF = "--with-glib"

EXTRA_OECONF += "${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', '--with-external_ap', '', d)}"
EXTRA_OECONF += "--with-glib"
