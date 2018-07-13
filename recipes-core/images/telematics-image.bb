require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "package-management ssh-server-dropbear"

IMAGE_INSTALL += "iperf3"
IMAGE_INSTALL += "iproute2"
IMAGE_INSTALL += "iptables"
IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "network-conf"
IMAGE_INSTALL += "pciutils"
IMAGE_INSTALL += "tcpdump"

IMAGE_INSTALL += "data-oss"
