do_defconfig_patch () {
  cat >> ${S}/arch/${ARCH}/configs/${KERNEL_CONFIG} <<KERNEL_EXTRACONFIGS
CONFIG_QTI_MHI_BUS_ARCH=y
CONFIG_QTI_PCIE_SSR=y
KERNEL_EXTRACONFIGS
}

addtask do_defconfig_patch after do_unpack_extra before do_kernel_metadata

