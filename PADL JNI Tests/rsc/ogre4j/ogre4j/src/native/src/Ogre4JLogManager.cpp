///////////////////////////////////////////////////////////////////////////////
/// This source file is part of ogre4j
///     (The JNI bindings for OGRE)
/// For the latest info, see http://www.ogre4j.org/
/// 
/// Copyright (c) 2005 netAllied GmbH, Tettnang
/// Also see acknowledgements in Readme.html
/// 
/// This program is free software; you can redistribute it and/or modify it under
/// the terms of the GNU Lesser General Public License as published by the Free Software
/// Foundation; either version 2 of the License, or (at your option) any later
/// version.
/// 
/// This program is distributed in the hope that it will be useful, but WITHOUT
/// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
/// FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
/// 
/// You should have received a copy of the GNU Lesser General Public License along with
/// this program; if not, write to the Free Software Foundation, Inc., 59 Temple
/// Place - Suite 330, Boston, MA 02111-1307, USA, or go to
/// http://www.gnu.org/copyleft/lesser.txt.
///
///
/// @file Ogre4JLogManager.cpp
/// Implementation of the JNI bindings for the usage of the Ogre::Mesh class
///
/// @author   Gerhard Maier <shinobi@ogre4j.org>
/// @date     31.05.2005
///
/// $Revision: 1.1 $
/// $Date: 2005/07/29 09:58:55 $
/// $Author: yavin02 $
///////////////////////////////////////////////////////////////////////////////
#include <Ogre4J.h>
#include <org_ogre4j_LogManager.h>

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
JNIEXPORT jint JNICALL Java_org_ogre4j_LogManager_createInstance
  (JNIEnv *env, jclass)
{
    try
    {
        Ogre::LogManager * pLogMgr = new Ogre::LogManager();
        RETURN_PTR(pLogMgr);
    }
    catch( Ogre::Exception & e )
    {
        jclass OgreException = env->FindClass("org/ogre4j/OgreException");
        env->ThrowNew( OgreException, e.getFullDescription().c_str() );
    }
    catch( ... )
    {
        jclass OgreException = env->FindClass("org/ogre4j/OgreException");
        env->ThrowNew( OgreException, "Unknown Exception" );
    }
}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
JNIEXPORT jint JNICALL Java_org_ogre4j_LogManager__1getSingleton
  (JNIEnv *env, jclass)
{
    try
    {
        Ogre::LogManager * pLogMgr = Ogre::LogManager::getSingletonPtr();
        RETURN_PTR(pLogMgr);
    }
    catch( Ogre::Exception & e )
    {
        jclass OgreException = env->FindClass("org/ogre4j/OgreException");
        env->ThrowNew( OgreException, e.getFullDescription().c_str() );
    }
    catch( ... )
    {
        jclass OgreException = env->FindClass("org/ogre4j/OgreException");
        env->ThrowNew( OgreException, "Unknown Exception" );
    }
}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
JNIEXPORT void JNICALL Java_org_ogre4j_LogManager_dispose
  (JNIEnv *, jclass, jint pInstance)
{
    Ogre::LogManager * pLogMgr = GET_PTR(Ogre::LogManager, pInstance);
    delete pLogMgr;
}


///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

JNIEXPORT void JNICALL Java_org_ogre4j_LogManager__1logMessage
  (JNIEnv *env, jclass that, jstring jsMessage)
{
    Ogre::String sMessage;
    Ogre4JHelper::CopyJString(env, jsMessage, sMessage);

	Ogre::LogManager::getSingleton().logMessage( sMessage );
}