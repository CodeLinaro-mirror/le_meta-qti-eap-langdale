SUMMARY = "Telematics Utils packages"
LICENSE = "BSD-3-Clause"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN}:append_sa2150p += "android-tools"
RDEPENDS:${PN} += "chrony"
RDEPENDS:${PN} += "chronyc"
RDEPENDS:${PN} += "dnsmasq"
RDEPENDS:${PN} += "e2fsprogs-resize2fs"
RDEPENDS:${PN} += "iperf3"
RDEPENDS:${PN} += "iproute2"
RDEPENDS:${PN} += "iptables"
RDEPENDS:${PN} += "kernel-modules"
RDEPENDS:${PN} += "nbd-server"
RDEPENDS:${PN} += "pciutils"
RDEPENDS:${PN} += "pps-tools"
RDEPENDS:${PN} += "rng-tools"
RDEPENDS:${PN} += "tcpdump"
RDEPENDS:${PN} += "usbutils"
