/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_ogre4j_InputEvent */

#ifndef _Included_org_ogre4j_InputEvent
#define _Included_org_ogre4j_InputEvent
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: table */
#undef org_ogre4j_InputEvent_ALT_MASK
#define org_ogre4j_InputEvent_ALT_MASK 8L
#undef org_ogre4j_InputEvent_BUTTON_ANY_MASK
#define org_ogre4j_InputEvent_BUTTON_ANY_MASK 240L
#undef org_ogre4j_InputEvent_BUTTON0_MASK
#define org_ogre4j_InputEvent_BUTTON0_MASK 16L
#undef org_ogre4j_InputEvent_BUTTON1_MASK
#define org_ogre4j_InputEvent_BUTTON1_MASK 32L
#undef org_ogre4j_InputEvent_BUTTON2_MASK
#define org_ogre4j_InputEvent_BUTTON2_MASK 64L
#undef org_ogre4j_InputEvent_BUTTON3_MASK
#define org_ogre4j_InputEvent_BUTTON3_MASK 128L
#undef org_ogre4j_InputEvent_CTRL_MASK
#define org_ogre4j_InputEvent_CTRL_MASK 2L
#undef org_ogre4j_InputEvent_META_MASK
#define org_ogre4j_InputEvent_META_MASK 4L
#undef org_ogre4j_InputEvent_SHIFT_MASK
#define org_ogre4j_InputEvent_SHIFT_MASK 1L
/*
 * Class:     org_ogre4j_InputEvent
 * Method:    consume
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_InputEvent_consume
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    getID
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_InputEvent_getID
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    getModifiers
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_InputEvent_getModifiers
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    getSource
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_InputEvent_getSource
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    getWhen
 * Signature: (I)F
 */
JNIEXPORT jfloat JNICALL Java_org_ogre4j_InputEvent_getWhen
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    isAltDown
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_InputEvent_isAltDown
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    isConsumed
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_InputEvent_isConsumed
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    isControlDown
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_InputEvent_isControlDown
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    isEventBetween
 * Signature: (III)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_InputEvent_isEventBetween
  (JNIEnv *, jclass, jint, jint, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    isMetaDown
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_InputEvent_isMetaDown
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_InputEvent
 * Method:    isShiftDown
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_InputEvent_isShiftDown
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif