DESCRIPTION = "Different utilities from Android"
SECTION = "console/utils"
LICENSE = "Apache-2.0 & BSD-2-Clause & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10 \
    file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=8bef8e6712b1be5aa76af1ebde9d6378 \
    file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9 \
"
DEPENDS = "libbsd libpcre zlib libcap"
DEPENDS:append_class-target = " openssl"

ANDROID_MIRROR = "android.googlesource.com"
OPENEMBEDDED_FILES = "https://github.com/openembedded/meta-openembedded/raw/thud/meta-oe/recipes-devtools/android-tools/android-tools"
BUILDROOT_PATCHES = "https://github.com/rancher/buildroot/raw/master/package/android-tools"
LEDE_PATCHES = "https://github.com/lede-project/source/raw/master/package/utils/adb/patches"

# matches with android-5.1.1_r37
SRCREV_core = "2314b110bdebdbfd2d94c502282f9e57c849897e"
SRCREV_extras = "3ecbe8d841df96127d7855661293e5ab6ba6c205"
SRCREV_libhardware = "be55eb1f4d840c82ffaf7c47460df17ff5bc4d9b"
SRCREV_libselinux = "07e9e1339ad1ba608acfba9dce2d0f474b252feb"
SRCREV_build = "16e987def3d7d8f7d30805eb95cef69e52a87dbc"
# patches
SRC_URI[0001.md5sum] = "40ebbf61237dea665b0fcfd77686bf54"
SRC_URI[0002.md5sum] = "2e269b47729f8475c7b0035ad54ceff6"
SRC_URI[0003.md5sum] = "3255c056f95afe18d3f8582ffbb9383b"
SRC_URI[0004.md5sum] = "6ca9911f670908b1933613cf1eee2c61"
SRC_URI[0005.md5sum] = "4da2a3585d80bc9f62b2f2f3375b9791"
SRC_URI[0006.md5sum] = "bbd08bc41a0a746f5a2573723f683bdc"
SRC_URI[0007.md5sum] = "25af02f619bdfbefd1793aa1311050e6"
SRC_URI[0008.md5sum] = "558569af9de12d061e43a4b2821756df"
SRC_URI[0010.md5sum] = "d0cf445306345459bd5b57376c591671"
SRC_URI[020.md5sum] = "c2b27a49b2f93276080d22d085743602"
# makefiles
SRC_URI[adb.md5sum] = "c366eaf4e5793ae53e7bbc7ffc49f97e"
SRC_URI[fastboot.md5sum] = "bdb98c0f3d87a6e0c68688c890c8e97a"

SRC_URI = " \
    git://${ANDROID_MIRROR}/platform/system/core;name=core;protocol=https;nobranch=1;destsuffix=git/system/core \
    git://${ANDROID_MIRROR}/platform/system/extras;name=extras;protocol=https;nobranch=1;destsuffix=git/system/extras \
    git://${ANDROID_MIRROR}/platform/hardware/libhardware;name=libhardware;protocol=https;nobranch=1;destsuffix=git/hardware/libhardware \
    git://${ANDROID_MIRROR}/platform/external/libselinux;name=libselinux;protocol=https;nobranch=1;destsuffix=git/external/libselinux \
    git://${ANDROID_MIRROR}/platform/build;name=build;protocol=https;nobranch=1;destsuffix=git/build \
    ${OPENEMBEDDED_FILES}/core/0001-adb-remove-selinux-extensions.patch;name=0001;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/core/0002-adb-Use-local-sockets-where-appropriate.patch;name=0002;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/core/0003-adb-define-shell-command.patch;name=0003;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/core/0004-adb-Fix-build-on-big-endian-systems.patch;name=0004;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/core/0005-adb-add-base64-implementation.patch;name=0005;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/core/0006-adb-Musl-fixes.patch;name=0006;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/core/0007-adb-usb_linux.c-fix-build-with-glibc-2.28.patch;name=0007;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/core/0008-adb-Allow-adbd-to-be-ran-as-root.patch;name=0008;patchdir=system/core \
    ${BUILDROOT_PATCHES}/0010-adb-added-patch-for-openssl-1.1.0-compatibility.patch;name=0010;patchdir=system \
    ${LEDE_PATCHES}/020-cherry-picked-superspeed-fix.patch;name=020;patchdir=system/core \
    ${OPENEMBEDDED_FILES}/adb.mk;name=adb;subdir=${BPN} \
    ${OPENEMBEDDED_FILES}/fastboot.mk;name=fastboot;subdir=${BPN} \
"


S = "${WORKDIR}/git"
B = "${WORKDIR}/${BPN}"

# http://errors.yoctoproject.org/Errors/Details/133881/
ARM_INSTRUCTION_SET_armv4 = "arm"
ARM_INSTRUCTION_SET_armv5 = "arm"

# Find libbsd headers during native builds
CC:append_class-native = " -I${STAGING_INCDIR}"
CC:append_class-nativesdk = " -I${STAGING_INCDIR}"

TOOLS = "adb fastboot"

# Adb needs sys/capability.h, which is not available for native*
TOOLS_class-native = "fastboot ext4_utils mkbootimg"
TOOLS_class-nativesdk = "fastboot ext4_utils mkbootimg"

do_compile() {
    # Setting both variables below causing our makefiles to not work with
    # implicit make rules
    unset CFLAGS
    unset CPPFLAGS

    export SRCDIR=${S}

    case "${HOST_ARCH}" in
      arm)
        export android_arch=linux-arm
      ;;
      aarch64)
        export android_arch=linux-arm64
      ;;
      mips|mipsel)
        export android_arch=linux-mips
      ;;
      powerpc|powerpc64)
        export android_arch=linux-ppc
      ;;
      i586|x86_64)
        export android_arch=linux-x86
      ;;
    esac

    for tool in ${TOOLS}; do
      mkdir -p ${B}/${tool}
      oe_runmake -f ${B}/${tool}.mk -C ${B}/${tool}
    done
}

do_install() {
    if echo ${TOOLS} | grep -q "ext4_utils" ; then
        install -D -p -m0755 ${S}/system/core/libsparse/simg_dump.py ${D}${bindir}/simg_dump
        install -D -p -m0755 ${S}/system/extras/ext4_utils/mkuserimg.sh ${D}${bindir}/mkuserimg

        install -m0755 ${B}/ext4_utils/ext2simg ${D}${bindir}
        install -m0755 ${B}/ext4_utils/ext4fixup ${D}${bindir}
        install -m0755 ${B}/ext4_utils/img2simg ${D}${bindir}
        install -m0755 ${B}/ext4_utils/make_ext4fs ${D}${bindir}
        install -m0755 ${B}/ext4_utils/simg2img ${D}${bindir}
        install -m0755 ${B}/ext4_utils/simg2simg ${D}${bindir}
    fi

    if echo ${TOOLS} | grep -q "adb " ; then
        install -d ${D}${bindir}
        install -m0755 ${B}/adb/adb ${D}${bindir}
    fi

    if echo ${TOOLS} | grep -q "adbd" ; then
        install -d ${D}${bindir}
        install -m0755 ${B}/adbd/adbd ${D}${bindir}
    fi

    if echo ${TOOLS} | grep -q "fastboot" ; then
        install -d ${D}${bindir}
        install -m0755 ${B}/fastboot/fastboot ${D}${bindir}
    fi

    if echo ${TOOLS} | grep -q "mkbootimg" ; then
        install -d ${D}${bindir}
        install -m0755 ${B}/mkbootimg/mkbootimg ${D}${bindir}
    fi
}

PACKAGES += "${PN}-fstools"

FILES:${PN}-fstools = "\
    ${bindir}/ext2simg \
    ${bindir}/ext4fixup \
    ${bindir}/img2simg \
    ${bindir}/make_ext4fs \
    ${bindir}/simg2img \
    ${bindir}/simg2simg \
    ${bindir}/simg_dump \
    ${bindir}/mkuserimg \
"

BBCLASSEXTEND = "native"
