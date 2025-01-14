/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Ga�l Gu�h�neuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Ga�l Gu�h�neuc and others, see in file; API and its implementation
 ******************************************************************************/
package mendel.metric.entity;

import mendel.metric.IEntityMetric;
import mendel.model.IEntity;

public class BaseName implements IEntityMetric {

	public String compute(IEntity entity) {
		String qName = entity.getEntityName();
		return  qName.substring(qName.lastIndexOf('.') + 1, qName.length());
	}

	public String getName() {
		return "Base name";
	}

}
