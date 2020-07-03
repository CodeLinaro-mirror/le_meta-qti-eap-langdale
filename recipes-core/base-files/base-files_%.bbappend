do_install_append() {
	if [ -f ${D}/${sysconfdir}/hosts ]; then
		rm ${D}/${sysconfdir}/hosts
	fi
}
