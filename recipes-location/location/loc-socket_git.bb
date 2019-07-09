inherit autotools-brokensep qcommon pkgconfig

DESCRIPTION = "loc socket library"
PR = "r1"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=3775480a712fc46a69647678acb234cb"

SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/location/utils/loc_socket"
S = "${WORKDIR}/vendor/qcom/opensource/location/utils/loc_socket"

DEPENDS = "virtual/kernel gps-utils qmi-framework"

EXTRA_OECONF += " --with-glib --enable-kernel-qrtr=yes"
CPPFLAGS += "-I${STAGING_KERNEL_BUILDDIR}/usr/include"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
