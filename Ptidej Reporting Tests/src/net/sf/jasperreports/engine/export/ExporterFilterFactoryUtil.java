/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.engine.export;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 * A {@link ExporterFilterFactory} utility class.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ExporterFilterFactoryUtil.java,v 1.1 2008/09/29 16:20:46 guehene Exp $
 */
public class ExporterFilterFactoryUtil
{
	//FIXME this would add commons collections to applet jar
	//private static final JRSingletonCache cache = new JRSingletonCache(ExporterFilterFactory.class);

	/**
	 * Returns an exporter filter factory based on a factory class name.
	 * 
	 * @param factoryClassName the factory class name
	 * @return an exporter filter factory instance
	 * @throws JRException
	 */
	public static ExporterFilterFactory getFilterFactory(String factoryClassName) throws JRException
	{
		//return (ExporterFilterFactory) cache.getCachedInstance(factoryClassName);
		try
		{
			Class clazz = JRClassLoader.loadClassForName(factoryClassName);
			return (ExporterFilterFactory)clazz.newInstance();
		}
		catch (ClassNotFoundException e)
		{
			throw new JRException("Class " + factoryClassName + " not found.", e);
		}
		catch (InstantiationException e)
		{
			throw new JRException("Error instantiating class " + factoryClassName + ".", e);
		}
		catch (IllegalAccessException e)
		{
			throw new JRException("Error instantiating class " + factoryClassName + ".", e);
		}
	}

}
