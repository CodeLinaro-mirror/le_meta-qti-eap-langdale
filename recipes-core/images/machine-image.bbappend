IMAGE_INSTALL += "packagegroup-telematics-utils"
IMAGE_INSTALL += "packagegroup-telematics-qti"
IMAGE_INSTALL += "${@bb.utils.contains_any('MACHINE_FEATURES', 'wwan-plus-cv2x cv2x-only', 'packagegroup-telematics-cv2x-qti', '', d)}"
