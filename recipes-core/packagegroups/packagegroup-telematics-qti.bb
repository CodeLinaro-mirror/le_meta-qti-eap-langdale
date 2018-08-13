SUMMARY = "Telematics QTI packages"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Open source packages

RDEPENDS_${PN} += "data-oss"
RDEPENDS_${PN} += "telux-services-ril"
RDEPENDS_${PN} += "telux-loc"
RDEPENDS_${PN} += "telux-lib"
RDEPENDS_${PN} += "telux-samples"
