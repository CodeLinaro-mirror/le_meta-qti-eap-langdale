inherit pkgconfig cmake pythonnative systemd

SUMMARY = "Telephony service for QTI's Modem"
DESCRIPTION = "Telephony service for QTI's Modem"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

FILESPATH        =+ "${WORKSPACE}:"
SRC_URI          = "file://${@d.getVar('SRC_DIR', True).replace('${WORKSPACE}/', '')}"
PACKAGE_ARCH    ?= "${MACHINE_ARCH}"
SRC_DIR = "${WORKSPACE}/telux/services/ril"
S = "${WORKDIR}/telux/services/ril"

EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', '-DRIL_FOR_EXTERNAL_AP=ON', '', d)}"
EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'cv2x-only', '-DMACHINE_HAS_CV2X_ONLY=ON', '', d)}"
EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'deprivileged-user', '-DTELUX_DEPRIVILEGE_ENABLED=ON', '', d)}"
DEPENDS += "glib-2.0 nanopb protobuf-native python-protobuf-native python-six-native qmi-framework"

SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', '', 'rild.service rild2.service', d)}"

do_install_append() {
# '@LIBDIR@' is a placeholder in rild.service for the lib directory path.
# Replace this with ${libdir} to get the correct absolute path for the machine.
    if ${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', 'false', 'true', d)}; then
      if [ -f ${D}${systemd_unitdir}/system/rild.service ]; then
         sed -i -e 's#@LIBDIR@#${libdir}#g' ${D}${systemd_unitdir}/system/rild.service
      fi
    fi
}

FILES_${PN} += "/etc/initscripts/rild_launcher.sh"
