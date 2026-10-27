# Project storage policy for the upstream Orin Nano microSD target.
# Two 8 GiB rootfs slots; the local XML defines shared persist and UDA partitions.
USE_REDUNDANT_FLASH_LAYOUT_DEFAULT:jetson-orin-nano-devkit = "1"
ROOTFSPART_SIZE_DEFAULT:jetson-orin-nano-devkit = "17179869184"
