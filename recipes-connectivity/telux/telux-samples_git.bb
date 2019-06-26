inherit qcommon cmake systemd

SUMMARY = "Telematics SDK Samples"
DESCRIPTION = "Telematics SDK Samples"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_DIR = "${WORKSPACE}/telux/public/"
S = "${WORKDIR}/telux/public/samples"

FILES_${PN} += "${systemd_unitdir}"

EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DWITH_SYSTEMD:BOOL=ON', '', d)}"
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'cv2x-only', '-DMACHINE_HAS_CV2X_ONLY=ON', '', d)} "
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'wwan-plus-cv2x', '-DMACHINE_HAS_CV2X=ON', '', d)} "

SRCREV = "${AUTOREV}"

DEPENDS += "telux telux-lib systemd"
