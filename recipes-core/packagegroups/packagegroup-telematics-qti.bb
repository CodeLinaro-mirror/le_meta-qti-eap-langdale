SUMMARY = "Telematics QTI packages"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Open source packages

RDEPENDS_${PN} += "data-oss"
