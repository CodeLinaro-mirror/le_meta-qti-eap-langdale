inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "location api msg protobuf library"
PR = "r1"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

FILESPATH =+ "${WORKSPACE}:"
SRC_URI          = "file://vendor/qcom/opensource/location/location_api_msg_proto"
S = "${WORKDIR}/vendor/qcom/opensource/location/location_api_msg_proto"

DEPENDS = "glib-2.0 protobuf protobuf-native abseil-cpp loc-pla-hdr gps-utils"

FILES:${PN} += "${libdir}/*"

# Include unversioned .so files in the main package
FILES:${PN} += "${libdir}/lib*.so.*"
FILES:${PN} += "${libdir}/lib*.so"

# Clear the default dev package pattern so it doesn't "steal" the .so
FILES_SOLIBSDEV = ""

# Ensure the library name doesn't require a version suffix
SOLIBS = ".so"

# Skip the QA check that forbids .so symlinks in non-dev packages
INSANE_SKIP:${PN} += "dev-so"

do_compile:prepend () {
    echo "Running location_api_msg_protobuf_gen.sh"
    cd ${S}
    ./location_api_msg_protobuf_gen.sh
    cd -
}
