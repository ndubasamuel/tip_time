

cmake_minimum_required(VERSION 3.10.2)
project(TipTimeNative)

add_library(
        #setting the name of the library
        native-lib

        #setting the library as shared
        SHARED

        #providing a relative file to the source files
        native-lib.cpp

)

set_target_properties(
        native-lib
        PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}"
)

#target_link_libraries(
#        native-lib
#)