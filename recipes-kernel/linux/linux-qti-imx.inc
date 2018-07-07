require ${COREBASE}/../eap-qti-kernel/linux-qti-addon.inc

SRC_URI += "file://defconfig_append"

do_patch_extra_append () {
    cat ${WORKDIR}/defconfig_append >> ${WORKDIR}/defconfig
}

do_install_append() {
    oe_runmake_call -C ${STAGING_KERNEL_DIR} ARCH=${ARCH} CC="${KERNEL_CC}" \
        LD="${KERNEL_LD}" headers_install O=${STAGING_KERNEL_BUILDDIR}
}
