# External AP add-on to SA2150P

MACHINE_FEATURES += "external-ap"
MACHINE_FEATURES += "wwan-plus-cv2x"
MACHINE_FEATURES += "diag"
MACHINE_FEATURES += "pps"
MACHINE_FEATURES += "qcmap"

BBMASK += "meta-qti-bsp/recipes-devtools/nanopb-c/nanopb_0.3.8.bb"
BBMASK += "meta-qti-bsp/recipes-devtools/python/python-protobuf_3.3.0.bb"
BBMASK += "meta-qti-bsp/recipes-devtools/protobuf/protobuf_3.3.0.bb"
BBMASK += "meta-qti-data/recipes/data/data_git.bbappend"
BBMASK += "meta-qti-data/recipes/data-oss/data-oss_git.bb"
BBMASK += "meta-qti-eap/recipes-kernel/linux/linux-imx_4.9.11.bb"
BBMASK += "meta-qti-eap/recipes-kernel/linux/linux-yocto_%.bbappend"
BBMASK += "meta-qti-eap/recipes-core/base-passwd/base-passwd_3.5.29.bbappend"
