FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "${@bb.utils.contains('PV', '2.23', 'file://0001-Add-IPCR-protocol-family-2.23.patch', '', d)}"
SRC_URI += "${@bb.utils.contains('PV', '2.24', 'file://0001-Add-IPCR-protocol-family-2.24.patch', '', d)}"
