FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI_append_sa2150p += "file://0001-Modify-the-wakeup-APIs-in-compatible-with-msm-4.14.patch"

do_patch_sa2150p () {
      cd ${S}/
      patch -p1 < ${WORKDIR}/0001-Modify-the-wakeup-APIs-in-compatible-with-msm-4.14.patch
      cd -
}

do_unpack_extra () {
    cp -r ${WORKDIR}/kernel/eap-qti-kernel/* ${S}/
    echo "source \"drivers/qti/Kconfig\"" >> ${S}/drivers/Kconfig
    echo "obj-y += qti/" >> ${S}/drivers/Makefile
    echo "header-y += qrtr.h" >> ${S}/include/uapi/linux/Kbuild
    echo "header-y += msm_rmnet.h" >> ${S}/include/uapi/linux/Kbuild
    echo "header-y += rmnet_data.h" >> ${S}/include/uapi/linux/Kbuild

    cat ${WORKDIR}/kernel/eap-qti-kernel/defconfig >> ${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}
    echo "# CONFIG_MHI_BUS is not set" >> ${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}
    echo "# CONFIG_QRTR is not set" >> ${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}
    echo "# CONFIG_RMNET_USB is not set" >> ${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}
    echo "# CONFIG_QTI_QMI_HELPERS is not set" >> ${S}/arch/${ARCH}/configs/${KERNEL_CONFIG}

    cp ${WORKDIR}/kernel/eap-qti-kernel/devicetree/* ${S}/arch/${ARCH}/boot/dts/qcom/
    echo "#include \"mdm9150-mhi.dtsi\"" >> ${S}/arch/${ARCH}/boot/dts/qcom/qcs405.dtsi
    echo "#include \"sa415m-mhi.dtsi\"" >> ${S}/arch/${ARCH}/boot/dts/qcom/qcs405.dtsi
    echo "#include \"sa515m-mhi.dtsi\"" >> ${S}/arch/${ARCH}/boot/dts/qcom/qcs405.dtsi
}

addtask do_unpack_extra after do_unpack before do_kernel_metadata
