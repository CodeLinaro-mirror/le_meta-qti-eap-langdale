#!/bin/bash
#
# Copyright (c) 2018, The Linux Foundation. All rights reserved.
#
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are
# met:
#     * Redistributions of source code must retain the above copyright
#       notice, this list of conditions and the following disclaimer.
#     * Redistributions in binary form must reproduce the above
#       copyright notice, this list of conditions and the following
#       disclaimer in the documentation and/or other materials provided
#       with the distribution.
#     * Neither the name of The Linux Foundation nor the names of its
#       contributors may be used to endorse or promote products derived
#       from this software without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
# WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
# MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
# ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
# BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
# CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
# SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
# BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
# WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
# OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
# IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#
# set_bb_env.sh
# Generate bblayers.conf from get_bblayers.py.
# Some convenience macros are defined to save some typing.
# Set the build environement

if [[ ! $(readlink $(which sh)) =~ bash ]]
then
  echo ""
  echo "### ERROR: Please Change your /bin/sh symlink to point to bash. ### "
  echo ""
  echo "### sudo ln -sf /bin/bash /bin/sh ### "
  echo ""
  return 1
fi

# The SHELL variable also needs to be set to /bin/bash otherwise the build
# will fail, use chsh to change it to bash.
if [[ ! $SHELL =~ bash ]]
then
  echo ""
  echo "### ERROR: Please Change your shell to bash using chsh. ### "
  echo ""
  echo "### Make sure that the SHELL variable points to /bin/bash ### "
  echo ""
  return 1
fi

umask 022
unset DISTRO MACHINE PRODUCT VARIANT

# Find where this script is...
scriptdir="$( cd "$(dirname "${BASH_SOURCE}")" ; pwd -P )"

# Find where the workspace is...
WS=$(readlink -f $scriptdir/../../..)

export MACHINE=$1

if [ "$MACHINE" = "" ]; then
  export MACHINE=imx6sxsabresd
fi

function build-imx6sxsabresd-image() {
  unset_bb_env
  export MACHINE=imx6sxsabresd
  cdbitbake telematics-image
}

function build-imx6qsabrelite-image() {
  unset_bb_env
  export MACHINE=imx6qsabrelite
  cdbitbake telematics-image
}

function build-qemuarm64-image() {
  unset_bb_env
  export MACHINE=qemuarm64
  cdbitbake telematics-image
}

function build-all-eap-images() {
  build-imx6sxsabresd-image
  build-imx6qsabrelite-image
  build-qemuarm64-image
}

# Utility commands
buildclean() {
  set -x
  rm -rf ${WS}/build/tmp
  set +x
}

# Lists only those build commands that are:
#   * prefixed with function keyword
#   * name starts with build-
list-build-commands()
{
    echo
    echo "Convenience commands for building images:"
    local script_file="$scriptdir/set_bb_env.sh"

    while IFS= read line; do
        if echo $line | grep -q "^function[[:blank:]][[:blank:]]*build-"; then
            local delim_string=$(echo $line | cut -d'(' -f1)
            echo "   $(echo $delim_string|awk -F "[[:blank:]]*" '{print $2}')"
        fi
    done < $script_file

    echo
    echo "Use 'list-build-commands' to see this list again."
    echo
}

cdbitbake() {
  local ret=0
  cd ${WS}/build
  bitbake $@ && cd - || ret=$? && cd -
  return $ret
}

rebake() {
  cdbitbake -c cleanall $@ && \
  cdbitbake $@
}

unset_bb_env() {
  unset DISTRO MACHINE PRODUCT VARIANT
}

# Find build templates from qti meta layer.
export TEMPLATECONF="${WS}/sources/meta-qti-eap/conf"

BBLAYERS_CONF="${WS}/sources/meta-qti-eap/conf/bblayers.conf.sample"

# Dynamically generate our bblayers.conf since we effectively can't whitelist
# BBLAYERS (by OE-Core class policy...Bitbake understands it...) to support
# dynamic workspace layer functionality.
python $scriptdir/get_bblayers.py ${WS}/sources \"meta*\" > ${BBLAYERS_CONF}
echo "BBLAYERS += \"${WS}/sources/meta-openembedded/meta-networking\"" >> ${BBLAYERS_CONF}
echo "BBLAYERS += \"${WS}/sources/meta-openembedded/meta-oe\"" >> ${BBLAYERS_CONF}
echo "BBLAYERS += \"${WS}/sources/meta-openembedded/meta-python\"" >> ${BBLAYERS_CONF}
echo "BBLAYERS += \"${WS}/sources/poky/meta\"" >> ${BBLAYERS_CONF}
echo "BBLAYERS += \"${WS}/sources/poky/meta-poky\"" >> ${BBLAYERS_CONF}

#Fix KW build
KW_PATCH=$scriptdir/files/0001-poky-fix-KW-build-issue.patch
patch -p 1 -d ${WS}/sources/poky/ -N < ${KW_PATCH} > /dev/null 2>&1

# Find build templates from qti meta layer.
export TEMPLATECONF="${WS}/sources/meta-qti-eap/conf"

# Let bitbake use the following env-vars as if they were pre-set bitbake ones.
# (BBLAYERS is explicitly blocked from this within OE-Core itself, though...)
# oe-init-build-env calls oe-buildenv-internal which sets
# BB_ENV_EXTRAWHITE, append our vars to the list
export BB_ENV_EXTRAWHITE="${BB_ENV_EXTRAWHITE} DL_DIR PRODUCT VARIANT HASROS"

. ${WS}/sources/poky/oe-init-build-env ${WS}/build

list-build-commands
