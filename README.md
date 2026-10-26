# Axiom

Embedded AI and robotics, built with Yocto.

From the workspace containing `sources/`:

```sh
TEMPLATECONF=../meta-axiom/conf/templates/default \
  source sources/openembedded-core/oe-init-build-env build
bitbake core-image-base
```

Defaults: Orin Nano developer kit with microSD, systemd, headless Linux and SSH.
Build artifacts are under `build/tmp/deploy/images/jetson-orin-nano-devkit/`.
