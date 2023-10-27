do_configure_prepend () {
    # Allow build paths with containing AU.
    if [ -e ${S}/configure.ac ]; then
        sed 's|AC_CANONICAL_SYSTEM|m4_pattern_allow([^AU_])\nAC_CANONICAL_SYSTEM|' -i ${S}/configure.ac
    else
        sed 's|AC_CANONICAL_SYSTEM|m4_pattern_allow([^AU_])\nAC_CANONICAL_SYSTEM|' -i ${S}/configure.in
    fi
}
