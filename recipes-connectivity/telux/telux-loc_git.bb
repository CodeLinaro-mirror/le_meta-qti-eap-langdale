inherit qcommon cmake

SUMMARY = "Location headers for Telematics SDK"
DESCRIPTION = "Location qmi library required for Telematics SDK"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=550794465ba0ec5312d6919e203a55f9"


SRC_DIR = "${WORKSPACE}/vendor/qcom/opensource/location/loc_api/loc_api_v02"
S = "${WORKDIR}/vendor/qcom/opensource/location/loc_api/loc_api_v02"

SRC_URI  += "file://CMakeLists.txt;subdir=${S}"

DEPENDS += "qmi-framework"