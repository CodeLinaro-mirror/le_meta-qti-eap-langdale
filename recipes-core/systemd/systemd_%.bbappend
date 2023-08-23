FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append_sa2150p += "file://mask-failed-to-connect-to-journal-render-kvm-groups-and-srv-folder.patch"

PACKAGECONFIG:append += "networkd resolved coredump"

FILES:${PN} += "${sysconfdir}/resolv-conf.systemd"

ALTERNATIVE_${PN} += "resolv-conf"

ALTERNATIVE_TARGET[resolv-conf] ??= "${sysconfdir}/resolv-conf.systemd"
ALTERNATIVE_LINK_NAME[resolv-conf] ??= "${sysconfdir}/resolv.conf"
ALTERNATIVE_PRIORITY[resolv-conf] ??= "50"

do_install:append() {
	if ${@bb.utils.contains('PACKAGECONFIG', 'resolved', 'true', 'false', d)}; then
		ln -sf ../run/systemd/resolve/resolv.conf ${D}${sysconfdir}/resolv-conf.systemd
        fi

        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
	        rm -f ${D}${sysconfdir}/udev/rules.d/mtpserver.rules

                #Use legacy naming for network interfaces
                ln -sf /dev/null ${D}${sysconfdir}/udev/rules.d/80-net-setup-link.rules
        fi
}
