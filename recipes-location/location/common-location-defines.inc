# Common defines applicable for all location BB files

## enable GLIB
EXTRA_OECONF += "--with-glib"

## this is Ext AP
EXTRA_OECONF += "${@bb.utils.contains('MACHINE_FEATURES', 'external-ap', '--with-external_ap', '', d)}"
