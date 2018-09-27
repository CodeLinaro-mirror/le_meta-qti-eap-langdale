inherit qcommon cmake pythonnative systemd

SUMMARY = "Telephony service for QTI's Modem"
DESCRIPTION = "Telephony service for QTI's Modem"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_DIR = "${WORKSPACE}/telux/services/ril"
S = "${WORKDIR}/telux/services/ril"

EXTRA_OECMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', '-DRIL_FOR_EXTERNAL_AP=ON', '', d)}"

DEPENDS += "glib-2.0 nanopb python-protobuf-native python-six-native"

SYSTEMD_SERVICE_${PN} = "rild.service"

do_install_append() {
# '@LIBDIR@' is a placeholder in rild.service for the lib directory path.
# Replace this with ${libdir} to get the correct absolute path for the machine.
    sed -i -e 's#@LIBDIR@#${libdir}#g' ${D}${systemd_unitdir}/system/rild.service
}
