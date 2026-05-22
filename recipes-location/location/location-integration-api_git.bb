inherit autotools-brokensep pkgconfig
require common-location-defines.inc

DESCRIPTION = "location integration api library"
PR = "r1"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=7a434440b651f4a472ca93716d01033a"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_URI = "file://vendor/qcom/opensource/location/integration_api/"
S = "${WORKDIR}/vendor/qcom/opensource/location/integration_api"

DEPENDS = "loc-socket location-api-msg-proto location-client-api-hdr abseil-cpp loc-diag-iface-hdr"

# Include unversioned .so files in the main package
FILES:${PN} += "${libdir}/lib*.so.*"
FILES:${PN} += "${libdir}/lib*.so"

# Clear the default dev package pattern so it doesn't "steal" the .so
FILES_SOLIBSDEV = ""

# Ensure the library name doesn't require a version suffix
SOLIBS = ".so"

# Skip the QA check that forbids .so symlinks in non-dev packages
INSANE_SKIP:${PN} += "dev-so"
