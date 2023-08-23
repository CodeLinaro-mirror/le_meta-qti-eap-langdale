do_install:append() {
	if [ -f ${D}/${sysconfdir}/hosts ]; then
		rm ${D}/${sysconfdir}/hosts
	fi
}
