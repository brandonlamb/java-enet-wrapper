#!/bin/sh

ENET_VERSION=1.3.13

tar xzf enet-${ENET_VERSION}.tar.gz
cd enet-1.3.13
mkdir build
cd build
CFLAGS=-fPIC ../configure --enable-static --disable-shared --prefix=${PWD}/enet-${ENET_VERSION}/installed
make -C enet-${ENET_VERSION}/build install
