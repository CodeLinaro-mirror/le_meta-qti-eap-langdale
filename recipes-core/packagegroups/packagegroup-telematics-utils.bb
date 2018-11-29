SUMMARY = "Telematics Utils packages"
LICENSE = "BSD-3-Clause"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} += "chrony"
RDEPENDS_${PN} += "chronyc"
RDEPENDS_${PN} += "dnsmasq"
RDEPENDS_${PN} += "e2fsprogs-resize2fs"
RDEPENDS_${PN} += "iperf3"
RDEPENDS_${PN} += "iproute2"
RDEPENDS_${PN} += "iptables"
RDEPENDS_${PN} += "kernel-modules"
RDEPENDS_${PN} += "pciutils"
RDEPENDS_${PN} += "tcpdump"
RDEPENDS_${PN} += "usbutils"
