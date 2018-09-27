SUMMARY = "Telematics QTI packages"
LICENSE = "BSD-3-Clause"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Open source packages

RDEPENDS_${PN} += "data-oss"
RDEPENDS_${PN} += "telematics-conf"
RDEPENDS_${PN} += "telux-loc"
RDEPENDS_${PN} += "telux-lib"
RDEPENDS_${PN} += "telux-samples"
