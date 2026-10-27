require recipes-core/images/core-image-base.bb

SUMMARY = "Axiom Linux image with ROS 2"
DESCRIPTION = "Headless Linux with ROS 2 Jazzy and SWUpdate for Axiom"
LICENSE = "MIT"

inherit ros_distro_jazzy ros2_image
ROS_IMAGE_BASENAME_APPEND = ""

IMAGE_FEATURES:remove = "splash"
IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_INSTALL:append = " \
    ros-base \
    rmw-cyclonedds-cpp \
    swupdate \
    swupdate-client \
    swupdate-www \
"
