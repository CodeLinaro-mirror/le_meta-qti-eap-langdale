# External AP add-on for non-QTI hosts

require ${COREBASE}/meta/conf/distro/include/security_flags.inc

MACHINE_FEATURES += "external-ap"
MACHINE_FEATURES += "wwan-plus-cv2x"
MACHINE_FEATURES += "diag"
MACHINE_FEATURES += "qti-location"

DISTRO_FEATURES:remove = "sysvinit"

# Use systemd init manager.
DISTRO_FEATURES:append = " systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_dev_manager = "udev"
VIRTUAL-RUNTIME_init_manager = "systemd"

# Change Image features for systemd.
IMAGE_DEV_MANAGER = "udev"
IMAGE_INIT_MANAGER = "systemd"
IMAGE_INITSCRIPTS = ""

# Add QTI users and groups
USERADDEXTENSION = "eap-qpermissions"
