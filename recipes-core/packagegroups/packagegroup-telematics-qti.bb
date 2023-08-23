SUMMARY = "Telematics QTI packages"
LICENSE = "BSD-3-Clause"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Open source packages

##### Data component #####
RDEPENDS:${PN} += "data-oss"
##### Location component #####
#RDEPENDS:${PN} += "gps-utils"
##### TelSDK component #####
RDEPENDS:${PN} += "telux"
RDEPENDS:${PN} += "telux-lib"
RDEPENDS:${PN} += "telux-samples"
##### Misc packages #####
RDEPENDS:${PN} += "telematics-conf"

RDEPENDS:${PN} += "rsync"

##### Packages with QTI dependencies #####
#RDEPENDS:${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'loc-socket', '', d)}"
#RDEPENDS:${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'location-client-api', '', d)}"
#RDEPENDS:${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'location-client-api-testapp', '', d)}"
#RDEPENDS:${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'location-integration-api', '', d)}"

# Target SDK Packages
#TOOLCHAIN_TARGET_TASK:append_sa2150p += "telux"
#TOOLCHAIN_TARGET_TASK:append_sa2150p += "loc-pla-hdr"
