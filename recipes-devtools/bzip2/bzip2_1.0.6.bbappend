#SRC_URI is pointing to CAF
SRC_URI:remove = "http://downloads.yoctoproject.org/mirror/sources/${BP}.tar.gz"

SRC_URI += "https://mirrors.edge.kernel.org/caf_mirrored_source/quic/le/bzip2-1.0.6.tar.gz"
