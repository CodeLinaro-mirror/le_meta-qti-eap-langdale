# External AP add-on for non-QTI hosts

MACHINE_FEATURES += "external-ap"
MACHINE_FEATURES += "wwan-plus-cv2x"

DISTRO_FEATURES_remove = "sysvinit"

# Use systemd init manager.
DISTRO_FEATURES_append = " systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_dev_manager = "udev"
VIRTUAL-RUNTIME_init_manager = "systemd"

# Change Image features for systemd.
IMAGE_DEV_MANAGER = "udev"
IMAGE_INIT_MANAGER = "systemd"
IMAGE_INITSCRIPTS = ""
