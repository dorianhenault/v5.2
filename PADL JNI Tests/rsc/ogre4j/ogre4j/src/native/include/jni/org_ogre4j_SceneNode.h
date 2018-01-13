/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_ogre4j_SceneNode */

#ifndef _Included_org_ogre4j_SceneNode
#define _Included_org_ogre4j_SceneNode
#ifdef __cplusplus
extern "C" {
#endif
/* Inaccessible static: table */
#undef org_ogre4j_SceneNode_TS_LOCAL
#define org_ogre4j_SceneNode_TS_LOCAL 0L
#undef org_ogre4j_SceneNode_TS_PARENT
#define org_ogre4j_SceneNode_TS_PARENT 1L
#undef org_ogre4j_SceneNode_TS_WORLD
#define org_ogre4j_SceneNode_TS_WORLD 2L
/*
 * Class:     org_ogre4j_SceneNode
 * Method:    _getWorldAABB
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_SceneNode__1getWorldAABB
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    attachObject
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_attachObject
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    createChildSceneNode
 * Signature: (IFFFFFFF)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_SceneNode_createChildSceneNode__IFFFFFFF
  (JNIEnv *, jclass, jint, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    createChildSceneNode
 * Signature: (ILjava/lang/String;FFFFFFF)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_SceneNode_createChildSceneNode__ILjava_lang_String_2FFFFFFF
  (JNIEnv *, jclass, jint, jstring, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat, jfloat);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    detachAllObjects
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_detachAllObjects
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    detachObject
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_detachObject__II
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    detachObject
 * Signature: (IS)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_SceneNode_detachObject__IS
  (JNIEnv *, jclass, jint, jshort);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    detachObject
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_SceneNode_detachObject__ILjava_lang_String_2
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    dispose
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_dispose
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    getAttachedObject
 * Signature: (IS)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_SceneNode_getAttachedObject__IS
  (JNIEnv *, jclass, jint, jshort);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    getAttachedObject
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_org_ogre4j_SceneNode_getAttachedObject__ILjava_lang_String_2
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    getShowBoundingBox
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_SceneNode_getShowBoundingBox
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    isInSceneGraph
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_ogre4j_SceneNode_isInSceneGraph
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    lookAt
 * Signature: (IFFFIFFF)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_lookAt
  (JNIEnv *, jclass, jint, jfloat, jfloat, jfloat, jint, jfloat, jfloat, jfloat);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    numAttachedObjects
 * Signature: (I)S
 */
JNIEXPORT jshort JNICALL Java_org_ogre4j_SceneNode_numAttachedObjects
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    removeAndDestroyAllChildren
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_removeAndDestroyAllChildren
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    removeAndDestroyChild
 * Signature: (IS)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_removeAndDestroyChild__IS
  (JNIEnv *, jclass, jint, jshort);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    removeAndDestroyChild
 * Signature: (ILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_removeAndDestroyChild__ILjava_lang_String_2
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    setDirection
 * Signature: (IFFF)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_setDirection
  (JNIEnv *, jclass, jint, jfloat, jfloat, jfloat);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    setFixedYawAxis
 * Signature: (IZ)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_setFixedYawAxis__IZ
  (JNIEnv *, jclass, jint, jboolean);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    setFixedYawAxis
 * Signature: (IZFFF)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_setFixedYawAxis__IZFFF
  (JNIEnv *, jclass, jint, jboolean, jfloat, jfloat, jfloat);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    setVisible
 * Signature: (IZZ)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_setVisible
  (JNIEnv *, jclass, jint, jboolean, jboolean);

/*
 * Class:     org_ogre4j_SceneNode
 * Method:    showBoundingBox
 * Signature: (IZ)V
 */
JNIEXPORT void JNICALL Java_org_ogre4j_SceneNode_showBoundingBox
  (JNIEnv *, jclass, jint, jboolean);

#ifdef __cplusplus
}
#endif
#endif
