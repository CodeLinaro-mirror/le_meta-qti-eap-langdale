require ${WORKSPACE}/sources/eap-qti-kernel/linux-qti-addon.inc

SRC_URI += "file://0001-pci-msi-Enable-multi-MSI-teardown-for-pci-designware.patch"
SRC_URI += "file://0002-pci-imx-Increase-number-of-retries-for-link-up.patch"
SRC_URI += "file://0003-xhci-Don-t-show-incorrect-WARN-message-about-events-.patch"

do_install_append() {
    oe_runmake_call -C ${STAGING_KERNEL_DIR} ARCH=${ARCH} CC="${KERNEL_CC}" \
        LD="${KERNEL_LD}" headers_install O=${STAGING_KERNEL_BUILDDIR}
}
