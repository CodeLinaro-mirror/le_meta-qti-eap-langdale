SRC_URI += "file://defconfig_append"

do_defconfig_patch_append () {
    cat ${WORKDIR}/defconfig_append >> ${WORKDIR}/defconfig
}

do_install_append() {
    oe_runmake_call -C ${STAGING_KERNEL_DIR} ARCH=${ARCH} CC="${KERNEL_CC}" LD="${KERNEL_LD}" headers_install O=${STAGING_KERNEL_BUILDDIR}
}
