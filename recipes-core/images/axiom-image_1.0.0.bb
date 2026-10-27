require recipes-core/images/core-image-base.bb
require conf/distro/include/axiom-storage.inc

SUMMARY = "Axiom Linux image with ROS 2"
DESCRIPTION = "Headless Linux with ROS 2 Jazzy and SWUpdate for Axiom"
LICENSE = "MIT"

inherit ros_distro_jazzy ros2_image
ROS_IMAGE_BASENAME_APPEND = ""
IMAGE_FSTYPES:append = " ext4.gz"
IMAGE_ROOTFS_MAXSIZE:jetson-orin-nano-devkit = "${@int(d.getVar('ROOTFSPART_SIZE')) // 1024}"

IMAGE_FEATURES:remove = "splash"
IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_INSTALL:append = " \
    axiom-storage \
    ros-base \
    rmw-cyclonedds-cpp \
    swupdate \
    swupdate-client \
    swupdate-www \
    axiom-swupdate-config \
"
