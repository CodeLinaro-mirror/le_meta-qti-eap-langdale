PACKAGECONFIG_append += "networkd resolved coredump"

FILES_${PN} += "${sysconfdir}/resolv-conf.systemd"

ALTERNATIVE_${PN} += "resolv-conf"

ALTERNATIVE_TARGET[resolv-conf] ??= "${sysconfdir}/resolv-conf.systemd"
ALTERNATIVE_LINK_NAME[resolv-conf] ??= "${sysconfdir}/resolv.conf"
ALTERNATIVE_PRIORITY[resolv-conf] ??= "50"

do_install_append() {
	if ${@bb.utils.contains('PACKAGECONFIG', 'resolved', 'true', 'false', d)}; then
		ln -sf ../run/systemd/resolve/resolv.conf ${D}${sysconfdir}/resolv-conf.systemd
	fi
}
