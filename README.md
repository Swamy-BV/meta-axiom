# Axiom

Embedded AI and robotics, built with Yocto.

From the workspace containing `sources/`:

```sh
source sources/meta-axiom/tools/axiom-setup.sh -b build
bitbake axiom-image
```

Defaults: Orin Nano developer kit with microSD, systemd, headless Linux and SSH.
Build artifacts are under `build/tmp/deploy/images/jetson-orin-nano-devkit/`.
