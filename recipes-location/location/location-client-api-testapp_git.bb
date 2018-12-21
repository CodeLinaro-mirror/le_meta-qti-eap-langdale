inherit autotools qcommon qlicense

DESCRIPTION = "location client api test application "
PR = "r1"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/location/client_api_testapp/"
S = "${WORKDIR}/vendor/qcom/opensource/location/client_api_testapp"

DEPENDS = "location-client-api"

EXTRA_OECONF += "${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', '--with-external_ap', '', d)}"
EXTRA_OECONF += "--with-glib"
