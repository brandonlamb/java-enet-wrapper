/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_bespin_enet_Packet */

#ifndef _Included_org_bespin_enet_Packet
#define _Included_org_bespin_enet_Packet
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_bespin_enet_Packet
 * Method:    create
 * Signature: (Ljava/nio/ByteBuffer;I)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_bespin_enet_Packet_create
  (JNIEnv *, jclass, jobject, jint);

/*
 * Class:     org_bespin_enet_Packet
 * Method:    get_bytes
 * Signature: (Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
 */
JNIEXPORT jobject JNICALL Java_org_bespin_enet_Packet_get_1bytes
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_bespin_enet_Packet
 * Method:    get_flags
 * Signature: (Ljava/nio/ByteBuffer;)I
 */
JNIEXPORT jint JNICALL Java_org_bespin_enet_Packet_get_1flags
  (JNIEnv *, jclass, jobject);

/*
 * Class:     org_bespin_enet_Packet
 * Method:    destroy
 * Signature: (Ljava/nio/ByteBuffer;)V
 */
JNIEXPORT void JNICALL Java_org_bespin_enet_Packet_destroy
  (JNIEnv *, jclass, jobject);

#ifdef __cplusplus
}
#endif
#endif