cmake_minimum_required(VERSION 3.7)
project(enet)
set(CMAKE_BUILD_TYPE Release)

find_package(JNI REQUIRED)
if (JNI_FOUND)
    message (STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
    message (STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")
endif()

include_directories(${JNI_INCLUDE_DIRS})

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(SOURCE_FILES org_bespin_enet_Event.c)

add_library(enet SHARED ${SOURCE_FILES})

if (CMAKE_SIZEOF_VOID_P EQUAL 8)
    build(64)
else (CMAKE_SIZEOF_VOID_P EQUAL 8)
    build(32)
endif (CMAKE_SIZEOF_VOID_P EQUAL 8)
