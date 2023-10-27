SUMMARY = "Telematics QTI packages"
LICENSE = "BSD-3-Clause"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Open source packages

##### Data component #####
RDEPENDS_${PN} += "data-oss"
##### Location component #####
#RDEPENDS_${PN} += "gps-utils"
##### TelSDK component #####
RDEPENDS_${PN} += "telux"
RDEPENDS_${PN} += "telux-lib"
#RDEPENDS_${PN} += "telux-samples"
##### Misc packages #####
RDEPENDS_${PN} += "telematics-conf"

##### Packages with QTI dependencies #####
#RDEPENDS_${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'loc-socket', '', d)}"
#RDEPENDS_${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'location-client-api', '', d)}"
#RDEPENDS_${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'location-client-api-testapp', '', d)}"
#RDEPENDS_${PN} += "${@oe.utils.conditional('WITH_PROP_LAYER', 'yes', 'location-integration-api', '', d)}"

# Target SDK Packages
#TOOLCHAIN_TARGET_TASK_append_sa2150p += "telux"
#TOOLCHAIN_TARGET_TASK_append_sa2150p += "loc-pla-hdr"
