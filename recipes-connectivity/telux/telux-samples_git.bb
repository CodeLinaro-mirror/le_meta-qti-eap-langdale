inherit pkgconfig cmake systemd

SUMMARY = "Telematics SDK Samples"
DESCRIPTION = "Telematics SDK Samples"

LICENSE = "BSD-3-Clause & BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
   file://${WORKDIR}/telux/public/asn1c/LICENSE;md5=ee8bfaaa7d71cf3edb079475e6716d4b"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "\
    git://github.com/vlm/asn1c;name=asn1c;protocol=https;nobranch=1;tag=94f0b645d401f75b5b1aa8e5440dc2df0f916517;destsuffix=telux/public/asn1c\
    file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_DIR = "${WORKSPACE}/telux/public/"
S = "${WORKDIR}/telux/public/samples"

FILES_${PN} += "${systemd_unitdir}"
EXTRA_OECMAKE += "-DASN1C_PATH=${WORKDIR}/telux/public/asn1c"
EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DWITH_SYSTEMD:BOOL=ON', '', d)}"
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'cv2x-only', '-DMACHINE_HAS_CV2X_ONLY=ON', '', d)} "
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'wwan-plus-cv2x', '-DMACHINE_HAS_CV2X=ON', '', d)} "
EXTRA_OECMAKE += "-DAUDIO_ENABLED=ON"
# Flag to identify an External Application Processor(EAP)
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', '-DTELUX_FOR_EXTERNAL_AP=ON', '', d)}"
# Flag to identify an External Application Processor(EAP) of Qualcomm Technologies, Inc.
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'qti-external-ap', '-DTELUX_QTI_EXTERNAL_AP=ON', '', d)}"
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'qti-external-ap', '-DWITH_AEROLINK=ON', '', d)}"

SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('MACHINE_FEATURES', 'pps', 'chrony-sock.service', '', d)}"

DEPENDS += "telux telux-lib systemd"
DEPENDS += "${@bb.utils.contains('MACHINE_FEATURES', 'qti-external-ap', 'aerolink', '',d)}"
DEPENDS += "${@bb.utils.contains('MACHINE_FEATURES', 'qti-external-ap', 'aerolink-headers', '',d)}"

do_install_append() {
    install -m 0644 ${WORKDIR}/telux/public/apps/tests/telsdk_console_app/config_files/telsdk_app.conf -D ${D}${sysconfdir}/telsdk_app.conf
}
