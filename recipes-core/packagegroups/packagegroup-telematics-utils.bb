SUMMARY = "Telematics Utils packages"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} += "iperf3"
RDEPENDS_${PN} += "iproute2"
RDEPENDS_${PN} += "iptables"
RDEPENDS_${PN} += "kernel-modules"
RDEPENDS_${PN} += "network-conf"
RDEPENDS_${PN} += "pciutils"
RDEPENDS_${PN} += "tcpdump"
