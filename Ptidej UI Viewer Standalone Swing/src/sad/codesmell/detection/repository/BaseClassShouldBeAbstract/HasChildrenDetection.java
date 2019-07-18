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
package sad.codesmell.detection.repository.BaseClassShouldBeAbstract;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IEntity;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import sad.codesmell.property.impl.MetricProperty;
import sad.codesmell.property.impl.ClassProperty;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.kernel.impl.CodeSmell;
import util.io.ProxyConsole;

/**
 * This class represents the detection of the code smell <CODESMELL>
 * 
 * @author Auto generated
 *
 */


public class HasChildrenDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	
	
	public String getName() {
		return "HasChildrenDetection";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		final Set classesHasChildren = new HashSet();
		
		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IEntity entity = (IEntity) iter.next();
			if (entity instanceof IClass) {
				final IClass aClass = (IClass) entity;
				final double NOC = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("NOC")).compute(anAbstractLevelModel, aClass);
				
				if (NOC >= 1.0) {
					try {
						CodeSmell dc = new CodeSmell(
							"HasChildren", "", 
							new ClassProperty(aClass));
						
						HashMap thresholdMap = new HashMap();
						thresholdMap.put("NOC", new Double(1.0));
						
						dc.getClassProperty().addProperty(new MetricProperty("NOC", 
							((IUnaryMetric) MetricsRepository.getInstance().getMetric("NOC")).compute(anAbstractLevelModel, aClass), thresholdMap));
						
						classesHasChildren.add(dc);
					}
					catch (final Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace(ProxyConsole.getInstance().errorOutput());
					}
				}
			}
		}

		this.setSetOfSmells(classesHasChildren);
		// return classesHasChildren;

	}
	
	
}
