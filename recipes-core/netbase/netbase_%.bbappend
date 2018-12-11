do_install_append () {
	echo "192.168.100.1 qti-modem" >> ${D}${sysconfdir}/hosts
}
