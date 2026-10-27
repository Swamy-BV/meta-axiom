# Axiom

Embedded AI and robotics, built with Yocto.

For checkout and build instructions, see [jetson-manifest](https://github.com/Swamy-BV/jetson-manifest).

```mermaid
flowchart TB
    Q["QSPI NOR · 64 MiB<br/>Firmware A / B<br/>Boot configuration and UEFI variables"]
    subgraph SD["microSD partition layout"]
        BOOT["NVIDIA kernel / DTB / EFI / recovery / reserved"]
        A["APP · rootfs A · 8 GiB"]
        B["APP_b · rootfs B · 8 GiB"]
        P["persist · shared state · 512 MiB"]
        D["UDA · shared data · remaining capacity"]
        BOOT --- A --- B --- P --- D
    end
    Q ~~~ BOOT
```

Hardware validation, persistent mounts and overlays pending.
