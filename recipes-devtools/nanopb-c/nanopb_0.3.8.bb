inherit cmake python3native

SUMMARY = "Nanopb - Protocol Buffers for Embedded Systems"
DESCRIPTION = "Nanopb is a small code-size Protocol Buffers implementation \
in ansi C. It is especially suitable for use in microcontrollers, but fits \
any memory restricted system."
HOMEPAGE = "https://github.com/nanopb/nanopb"
SECTION = "console/tools"

LICENSE = "Zlib"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9db4b73a55a3994384112efcdb37c01f"

DEPENDS = "protobuf-native nanopb-native"
DEPENDS:append_class-native = " python-protobuf-native"

SRCREV = "cc74b9f200176edc5524aa00ba45fa90a5e87d27"
PV = "0.3.8_git_${SRCREV}"

SRC_URI = "git://git.codelinaro.org/clo/le/nanopb.git;protocol=https;branch=caf_migration/nanopb/master"
SRC_URI += "${@bb.utils.contains_any('LAYERSERIES_COMPAT_core', 'nanbield scarthgap styhead walnascar',\
           'file://0002-compilation-fixes-for-nanbield-scarthgap.patch','', d)}"
SRC_URI += "file://0001-extra-nanopb-config.cmake-remove-unwanted-symbol.patch"
SRC_URI += "file://0001-bitbake-using-cmake.patch"

S = "${WORKDIR}/git"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*"

# Add support for tag numbers > 255 and fields larger than 255 bytes or 255 array entries
CFLAGS += "-DPB_FIELD_16BIT=1"

# need to export these variables for python-config to work
export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

EXTRA_OECMAKE:append_class-native = "-Dnanopb_BUILD_GENERATOR=ON -Dnanopb_BUILD_RUNTIME=OFF -Dnanopb_MSVC_STATIC_RUNTIME=OFF"
EXTRA_OECMAKE += "-DCMAKE_INSTALL_CMAKEDIR:STRING=${libdir}/cmake/nanopb"

BBCLASSEXTEND = "native nativesdk"
