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
package mendel.metric.branch;

import mendel.metric.DispatchMetric;
import mendel.model.JClassEntity;

public class SiblingsCount extends DispatchMetric {

	public String compute(JClassEntity entity) {
		if( entity.isRootClass() )
			return new Integer(0).toString();
		else {
			int nbSiblings = entity.getSuperClass().getChildren().size() - 1;
			return new Integer( nbSiblings ).toString();
		}
	}

	public String getName() {
		return "Siblings Count";
	}

}
