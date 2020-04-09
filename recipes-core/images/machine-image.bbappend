IMAGE_INSTALL += "packagegroup-telematics-utils"
IMAGE_INSTALL += "packagegroup-telematics-qti"
IMAGE_INSTALL += "${@bb.utils.contains_any('MACHINE_FEATURES', 'wwan-plus-cv2x cv2x-only', 'packagegroup-telematics-cv2x-qti', '', d)}"

do_generate_rootfs_fsconfig_append() {
    if ${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', 'true', 'false', d)}; then
        if [ -e ${COREBASE}/meta-qti-eap/recipes-core/images/${MACHINE}/${MACHINE}-rootfs-fsconfig.conf ]; then
            rm -rf ${WORKDIR}/rootfs-fsconfig.conf
            cp -f ${COREBASE}/meta-qti-eap/recipes-core/images/${MACHINE}/${MACHINE}-rootfs-fsconfig.conf ${WORKDIR}/rootfs-fsconfig.conf
        fi
    fi
}
