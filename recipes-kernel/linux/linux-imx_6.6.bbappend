FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

require ${WORKSPACE}/sources/eap-qti-kernel/linux-qti-addon-nanbield.inc

DEPENDS += "rsync-native"

do_install:append(){
   oe_runmake_call -C ${STAGING_KERNEL_DIR} ARCH=${ARCH} CC="${KERNEL_CC}" \
       LD="${KERNEL_LD}" headers_install O=${STAGING_KERNEL_BUILDDIR}
}
