require ${WORKSPACE}/sources/eap-qti-kernel/linux-qti-addon.inc

PREMIRRORS_remove = "git://.*/.*   http://downloads.yoctoproject.org/mirror/sources/ \n \"

deltask unpack_extra patch_extra
addtask unpack_extra after do_kernel_metadata before do_patch

do_install_append() {
    oe_runmake_call -C ${STAGING_KERNEL_DIR} ARCH=${ARCH} CC="${KERNEL_CC}" \
        LD="${KERNEL_LD}" headers_install O=${STAGING_KERNEL_BUILDDIR}
}

do_kernel_configme_append() {
    cat ${WORKDIR}/eap-qti-kernel/defconfig >> ${B}/.config
    echo "CONFIG_PCI=y" >> ${B}/.config
    echo "CONFIG_USB=y" >> ${B}/.config
    echo "CONFIG_USB_XHCI_HCD=y" >> ${B}/.config
}
