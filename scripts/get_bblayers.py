#!/usr/bin/python
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
# Generate the bblayers.conf file for the current build workspace
# emitted to stdout for simplicity.

import os, sys, fnmatch, re
from operator import itemgetter

# Trawl the OEROOT as passed to us and find all the layer files that meet our
# metadata directory criteria...
def getLayerPaths(target,  fnexpr) :
    retList = []
    for file in os.listdir(target) :
        if (fnmatch.fnmatch(file, fnexpr) and not
            (fnmatch.fnmatch(file, "meta-hob") or
             fnmatch.fnmatch(file, "meta-skeleton") or
             fnmatch.fnmatch(file, "meta-selftest") or
             fnmatch.fnmatch(file, "meta-openembedded"))):
            # Found what might be a metadata layer...
            layerPath = target + "/" + file
            layerConfPath = layerPath + "/conf/layer.conf"
            if os.path.exists(layerConfPath) :
                # Found a layer.  Find the priority for it...
                confFile = open(layerConfPath, "r")
                if (confFile != None) :
                    for line in confFile :
                        fields = line.split()
                        if (len(fields) > 0 and re.match("BBFILE_PRIORITY",  fields[0])) :
                            # Add the path, priority as a tuple to the list for the layer.
                            retList += [( layerPath,  int(fields[2].strip("\"")) )]
                            break
                    confFile.close()
    # In order to avoid potential namespace conflicts, between recipes on layers
    # we sort the list in descending order of priority.
    return sorted(retList,  key=itemgetter(1), reverse=True)


# Just spool the tuple list's paths out in order to a string...
def generatePathString ( pathList ):
    retString = ""
    for path, priority in pathList:
        retString = retString + path + " "
    return retString.strip()


# Emit our config file...
print "# This configuration file is dynamically generated every time" 
print "# set_bb_env.sh is sourced to set up a workspace.  DO NOT EDIT."
print "#--------------------------------------------------------------"
print "LCONF_VERSION = \"7\""
print 
print "export WORKSPACE := \"${@os.path.abspath(os.path.join(os.path.dirname(d.getVar('FILE', True)), '../..'))}\""
print
print "BBPATH = \"${TOPDIR}\""
print "BBFILES ?= \"\""
print "BBLAYERS = \"" + generatePathString(getLayerPaths(sys.argv[1].strip("\""), sys.argv[2].strip("\""))) + "\""
