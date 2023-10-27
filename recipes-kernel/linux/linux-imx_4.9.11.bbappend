FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

require ${WORKSPACE}/sources/eap-qti-kernel/linux-qti-addon.inc

SRC_URI += "file://defconfig_append"
SRC_URI += "file://0001-pci-msi-Enable-multi-MSI-teardown-for-pci-designware.patch"
SRC_URI += "file://0002-pci-imx-Increase-number-of-retries-for-link-up.patch"
SRC_URI += "file://0003-xhci-Don-t-show-incorrect-WARN-message-about-events-.patch"
SRC_URI += "file://0004-ARM-dts-Change-COL4-ROW4-to-the-GPIO-function.patch"

do_install_append() {
    oe_runmake_call -C ${STAGING_KERNEL_DIR} ARCH=${ARCH} CC="${KERNEL_CC}" \
        LD="${KERNEL_LD}" headers_install O=${STAGING_KERNEL_BUILDDIR}
}

do_preconfigure_prepend () {
    cat ${WORKDIR}/defconfig_append >> ${WORKDIR}/defconfig
}
