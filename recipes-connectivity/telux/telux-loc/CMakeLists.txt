# telux_qmiloc.so:
#    Builds Location QMI library required for Telematics SDK location services

# target libtelux_data
set(TARGET_LIBTELUX_QMI_LOC telux_qmiloc)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -I =/usr/include/qmi-framework")

# sources
set(LIBTELUX_QMI_LOC_SOURCES
    location_service_v02.c
)

# build library
add_library (${TARGET_LIBTELUX_QMI_LOC} SHARED
    ${LIBTELUX_QMI_LOC_SOURCES}
)
set_target_properties(${TARGET_LIBTELUX_QMI_LOC} PROPERTIES
                      VERSION 1.0.0
                      SOVERSION 1)

# install headers
install( FILES location_service_v02.h DESTINATION ${CMAKE_INSTALL_INCLUDEDIR} )

# install to target
install ( TARGETS ${TARGET_LIBTELUX_QMI_LOC}
          ARCHIVE DESTINATION ${CMAKE_INSTALL_LIBDIR}
          LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR})
