# Copyright (c) 2019, The Linux Foundation. All rights reserved.
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

require conf/machine/include/eap-qpermissions.conf

QPERM_SERVICE ?= ""

do_update_service () {
    set +e
    export SERVICES="${QPERM_SERVICE}"
    if [ "${SERVICES}" != "" ] ; then
        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
            for service in ${SERVICES}; do
                sed -i "/User=/d;/Group=/d;/CapabilityBoundingSet=/d" ${service}
            done
        fi
    fi
}


do_update_files() {
    set +e
    export FILE_PERMISSIONS="${QPERM_FILE}"
    if [ "$FILE_PERMISSIONS" != "" ] ; then
        for each_file in ${FILE_PERMISSIONS};    do
            path="$(cut -d ":" -f 1 <<< $each_file)"
            user="$(cut -d ":" -f 2 <<< $each_file)"
            group="$(cut -d ":" -f 3 <<< $each_file)"
            chown $user:$group ${D}$path
        done
    fi
}

do_install[postfuncs] += "${@['','do_update_files'][(d.getVar('QPERMISSIONS_ENABLE', True) == '1')]}"

python __anonymous() {
# If QPERMISSIONS are not Enabled, add update service premissions task
    if (d.getVar('QPERMISSIONS_ENABLE', True) == '0'):
        bb.build.addtask('do_update_service', 'do_install', 'do_compile', d)
}
