#!/bin/sh

CURR_DIR=`pwd`
ENET_VERSION=1.3.13

rm -rf enet-${ENET_VERSION}
tar xzf enet-${ENET_VERSION}.tar.gz
cd enet-1.3.13
mkdir build
cd build

#CFLAGS="-fPIC -m64 -Wall -Wextra -dynamiclib -lSystem -I${JAVA_HOME}/Headers -arch x86_64 -arch i386" ../configure --enable-static --disable-shared --prefix=${PWD}/enet-${ENET_VERSION}/installed
#CFLAGS="-fPIC -m64 -Wall -Wextra -lSystem -arch x86_64 -arch i386" ../configure --enable-static --disable-shared --prefix=${PWD}/enet-${ENET_VERSION}/installed
CFLAGS=-fPIC ../configure --enable-static --disable-shared --prefix=${CURR_DIR}/enet-${ENET_VERSION}/installed

cd ${CURR_DIR}
make -C enet-${ENET_VERSION}/build install
