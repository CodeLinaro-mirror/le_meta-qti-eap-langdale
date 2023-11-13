FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

FILESEXTRAPATHS:append := ":${OEBASE}/sources"
SRC_URI += "file://eap-qti-kernel"

DEPENDS += "rsync-native"
do_install:append(){
   oe_runmake_call -C ${STAGING_KERNEL_DIR} ARCH=${ARCH} CC="${KERNEL_CC}" \
       LD="${KERNEL_LD}" headers_install O=${STAGING_KERNEL_BUILDDIR}
}

LINUX_VERSION ??= "${PV}"

do_unpack_extra () {
    rm -rf ${WORKDIR}/eap-qti-kernel/drivers
    mv ${WORKDIR}/eap-qti-kernel/drivers-5.15 ${WORKDIR}/eap-qti-kernel/drivers
    cp -r ${WORKDIR}/eap-qti-kernel/* ${S}/
    cp -r ${S}/eap-qc-omap.dtsi ${S}/arch/arm64/boot/dts/ti/
    echo "#include \"eap-qc-omap.dtsi\"" >> ${S}/arch/arm64/boot/dts/ti/k3-j7200-common-proc-board.dts
    echo "source \"drivers/qti/Kconfig\"" >> ${S}/drivers/Kconfig
    echo "obj-y += qti/" >> ${S}/drivers/Makefile
    echo "header-y += qrtr.h" >> ${S}/include/uapi/linux/Kbuild
    echo "header-y += msm_rmnet.h" >> ${S}/include/uapi/linux/Kbuild
    echo "header-y += rmnet_data.h" >> ${S}/include/uapi/linux/Kbuild
    cd ${S}
    git apply ${S}/patches-5.10/008-Add-support-for-kernel-API-used-in-daig-and-mhi.patch
    git apply ${S}/patches-5.10/010-net-cdc_ether-Ensure-that-default-MTU-is-minimum-204.patch
    git apply ${S}/patches-5.10/012-net-usbnet-Update-net_device-stats-for-RmNet-device-.patch
    git apply ${S}/patches-5.10/0001-New-eap-cfg-file.patch
    cd -

}

addtask unpack_extra after do_unpack before do_patch
