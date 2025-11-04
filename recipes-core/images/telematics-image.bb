require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES:append = " package-management ssh-server-dropbear"

IMAGE_INSTALL:append = " packagegroup-telematics-utils"
IMAGE_INSTALL:append = " packagegroup-telematics-qti"
IMAGE_INSTALL:append = "${@bb.utils.contains_any('MACHINE_FEATURES', 'wwan-plus-cv2x cv2x-only', ' packagegroup-telematics-cv2x-qti', '', d)}"
IMAGE_INSTALL:append = " libgpiod libgpiod-dev libgpiod-tools"
IMAGE_INSTALL:append = " devmem2"
