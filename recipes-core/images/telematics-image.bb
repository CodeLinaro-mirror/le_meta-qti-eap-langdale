require recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "package-management ssh-server-dropbear"

IMAGE_INSTALL += "packagegroup-telematics-utils"
IMAGE_INSTALL += "packagegroup-telematics-qti"
IMAGE_INSTALL_append_cv2x += "packagegroup-telematics-cv2x-qti"
