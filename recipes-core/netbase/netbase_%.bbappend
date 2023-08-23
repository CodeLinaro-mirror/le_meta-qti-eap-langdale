do_install:append () {
	echo "192.168.225.1 qti-modem" >> ${D}${sysconfdir}/hosts
}
