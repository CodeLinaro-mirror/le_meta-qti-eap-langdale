inherit pkgconfig cmake

DESCRIPTION = "CV2X Compensator daemon"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

PACKAGE_ARCH ?= "${MACHINE_ARCH}"

FILESPATH =+ "${WORKSPACE}:"
SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/rf-compensator/"
SRC_URI = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
S = "${WORKDIR}/vendor/qcom/opensource/rf-compensator/"

FILES_${PN} += "${systemd_unitdir}"

DEPENDS = "telux-lib"

SYSTEMD_SERVICE_${PN} = "compensator.service"
SYSTEMD_AUTO_ENABLE = "enable"

EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DWITH_SYSTEMD:BOOL=ON', '', d)}"
