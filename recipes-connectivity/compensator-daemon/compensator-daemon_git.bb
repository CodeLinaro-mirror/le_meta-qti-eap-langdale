inherit pkgconfig cmake

DESCRIPTION = "CV2X Compensator daemon"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

PACKAGE_ARCH ?= "${MACHINE_ARCH}"

FILESPATH =+ "${WORKSPACE}:"
SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/rf-compensator/"
SRC_URI = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
SRC_URI += "file://compensator-sa2150p.conf"
SRC_URI += "file://compensator-daemon.conf"

S = "${WORKDIR}/vendor/qcom/opensource/rf-compensator/"

FILES_${PN} += "${systemd_unitdir}"

DEPENDS = "telux-lib"

SYSTEMD_SERVICE_${PN} = "compensator.service"
SYSTEMD_AUTO_ENABLE = "enable"

EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '-DWITH_SYSTEMD:BOOL=ON', '', d)}"

do_install_append_sa2150p() {
   if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
       #Install compensator config file for sa2150p
       install -d ${D}${sysconfdir}/
       install -m 0644 ${WORKDIR}/compensator-sa2150p.conf ${D}${sysconfdir}/compensator-sa2150p.conf
       install -d ${D}${systemd_unitdir}/system/compensator.service.d
       install -m 0644 ${WORKDIR}/compensator-daemon.conf ${D}${systemd_unitdir}/system/compensator.service.d/compensator.conf
   fi
}
