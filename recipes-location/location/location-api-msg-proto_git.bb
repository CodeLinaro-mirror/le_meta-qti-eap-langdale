inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "location api msg protobuf library"
PR = "r1"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI          = "file://vendor/qcom/opensource/location/location_api_msg_proto"
S = "${WORKDIR}/vendor/qcom/opensource/location/location_api_msg_proto"

DEPENDS = "glib-2.0 protobuf protobuf-native loc-pla-hdr gps-utils"

FILES:${PN} += "${libdir}/*"

do_compile:prepend () {
    echo "Running location_api_msg_protobuf_gen.sh"
    cd ${S}
    ./location_api_msg_protobuf_gen.sh
    cd -
}
