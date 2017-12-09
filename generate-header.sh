#!/bin/sh

#CURR_DIR=`dirname $0`
CURR_DIR=`pwd`

echo '### Generating JNI Header Files ###'
#cd ${CURR_DIR}
#cd src/main/java/org/bespin/enet
#${JAVA_HOME}/bin/javah -verbose -jni -d ../native/include com.example.JniWrapper

cd ${CURR_DIR}/target/classes/org/bespin/enet && \
javah -verbose -jni -d ${CURR_DIR}/src/main/native/include \
org.bespin.enet.Event \
org.bespin.enet.Host
#org.bespin.enet.MutableInteger \
#org.bespin.enet.Packet \
#org.bespin.enet.Peer

#javah -verbose -jni -d ${CURR_DIR}/src/main/native/include org.bespin.enet.Event && \
#javah -verbose -jni -d ${CURR_DIR}/src/main/native/include org.bespin.enet.Host && \
#javah -verbose -jni -d ${CURR_DIR}/src/main/native/include org.bespin.enet.MutableInteger && \
#javah -verbose -jni -d ${CURR_DIR}/src/main/native/include org.bespin.enet.Packet && \
#javah -verbose -jni -d ${CURR_DIR}/src/main/native/include org.bespin.enet.Peer

#cd ${CURR_DIR}
