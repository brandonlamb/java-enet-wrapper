cmake_minimum_required(VERSION 3.7)
project(enet)
set(CMAKE_BUILD_TYPE Release)

find_package(JNI REQUIRED)
if (JNI_FOUND)
    message (STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
    message (STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")
endif()

include_directories(
        ${JNI_INCLUDE_DIRS}
        src/main/native/include
        /System/Library/Frameworks/JavaVM.framework/Versions/Current/Headers
        enet-1.3.13/installed/include

)
#include_directories( enet-1.3.13/build/enet-1.3.13/installed/include )

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -g -O2 -Iinclude -fPIC -Wall -arch x86_64 -arch i386 -dynamiclib -lSystem -lenet -Lenet-1.3.13/installed/lib")
set(SOURCE_FILES
        "src/main/native/org_bespin_enet_Event.c"
        "src/main/native/org_bespin_enet_Host.c"
        "src/main/native/org_bespin_enet_Packet.c"
        "src/main/native/org_bespin_enet_Peer.c"
)

add_library(enet SHARED ${SOURCE_FILES})

#if (CMAKE_SIZEOF_VOID_P EQUAL 8)
#    build(64)
#else (CMAKE_SIZEOF_VOID_P EQUAL 8)
#    build(32)
#endif (CMAKE_SIZEOF_VOID_P EQUAL 8)
