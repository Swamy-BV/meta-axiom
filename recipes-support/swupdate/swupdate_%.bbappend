FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://axiom-handlers.cfg"

# Upstream derives dependencies from fragments while parsing the recipe.
python () {
    for fragment in find_cfgs(d):
        bb.parse.mark_dependency(d, fragment)
}
