FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://chrony-pps.conf"

do_install_append() {
    if [ ${@bb.utils.contains('MACHINE_FEATURES', 'pps', 'True', 'False', d)} = True ]; then
        rm ${D}${sysconfdir}/chrony.conf
        install -m 0644 ${WORKDIR}/chrony-pps.conf ${D}${sysconfdir}/chrony.conf
    fi
}
