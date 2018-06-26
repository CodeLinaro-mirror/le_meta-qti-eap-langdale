inherit qcommon

DESCRIPTION = "Data Services Open Source"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"

SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/dataservices"
S = "${WORKDIR}/vendor/qcom/opensource/dataservices"

DEPENDS += "virtual/kernel glib-2.0"

EXTRA_OECONF = "--with-sanitized-headers=${STAGING_KERNEL_BUILDDIR}/usr/include --with-glib"
