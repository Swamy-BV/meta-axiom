#!/bin/bash
# Source from the workspace containing sources/.

if [[ ${BASH_SOURCE[0]} == "$0" ]]; then
    printf 'Run: source axiom-setup.sh [-b build-directory]\n' >&2
    exit 1
fi

axiom_setup() {
    local build_dir=build OPTIND=1 option
    while getopts ':b:h' option; do
        case "$option" in
            b) build_dir=$OPTARG ;;
            h) printf 'Usage: source axiom-setup.sh [-b build-directory]\n'; return 0 ;;
            *) printf 'Invalid option. Use -b build-directory or -h.\n' >&2; return 1 ;;
        esac
    done
    shift "$((OPTIND - 1))"
    if [[ $# -ne 0 || -z $build_dir ]]; then
        printf 'Usage: source axiom-setup.sh [-b build-directory]\n' >&2
        return 1
    fi
    if [[ ! -r sources/openembedded-core/oe-init-build-env ]]; then
        printf 'Run this command from the workspace containing sources/.\n' >&2
        return 1
    fi
    TEMPLATECONF=../meta-axiom/conf/templates/default \
        source sources/openembedded-core/oe-init-build-env "$build_dir"
}

if axiom_setup "$@"; then
    unset -f axiom_setup
else
    unset -f axiom_setup
    return 1
fi
