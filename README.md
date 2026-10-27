# Axiom

Embedded AI and robotics, built with Yocto.

From the workspace containing `sources/`:

```sh
source sources/meta-axiom/tools/axiom-setup.sh -b build
bitbake axiom-image
```

Orin Nano developer kit with microSD, systemd, SSH, ROS 2 Jazzy and SWUpdate.
SWUpdate includes its client and web interface; A/B update bundles are not configured.
Build artifacts are under `build/tmp/deploy/images/jetson-orin-nano-devkit/`.
