require recipes-core/images/core-image-base.bb

SUMMARY = "Axiom base Linux image"
DESCRIPTION = "Headless Linux image for the Axiom embedded AI and robotics project"
LICENSE = "MIT"

IMAGE_FEATURES:remove = "splash"
IMAGE_FEATURES += "ssh-server-openssh"
