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
package net.sf.jasperreports.engine.fill;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRRuntimeException;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRIncrementerFactoryCache.java,v 1.1 2008/09/29 16:20:00 guehene Exp $
 */
public class JRIncrementerFactoryCache
{


	/**
	 *
	 */
	private static Map factoriesMap = null;


	/**
	 *
	 */
	public static synchronized JRIncrementerFactory getInstance(Class factoryClass)
	{
		if (factoriesMap == null)
		{
			factoriesMap = new HashMap();
		}
		
		JRIncrementerFactory incrementerFactory = (JRIncrementerFactory)factoriesMap.get(factoryClass.getName());

		if (incrementerFactory == null)
		{
			try
			{
				incrementerFactory = (JRIncrementerFactory)factoryClass.newInstance();
			}
			catch (InstantiationException e)
			{
				throw new JRRuntimeException(e);
			}
			catch (IllegalAccessException e)
			{
				throw new JRRuntimeException(e);
			}

			factoriesMap.put(factoryClass.getName(), incrementerFactory);
		}
		
		return incrementerFactory;
	}


}
