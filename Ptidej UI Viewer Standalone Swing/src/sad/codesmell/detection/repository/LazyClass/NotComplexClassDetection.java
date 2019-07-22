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
package sad.codesmell.detection.repository.LazyClass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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
import sad.util.BoxPlot;
import util.io.ProxyConsole;

/**
 * This class represents the detection of the code smell <CODESMELL>
 * 
 * @author Auto generated
 *
 */


public class NotComplexClassDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	
	
	public String getName() {
		return "NotComplexClassDetection";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		final Set NotComplexClassClassesFound = new HashSet();

		final HashMap mapOfNotComplexClassValues = new HashMap();
		boolean thereIsNotComplexClass = false;

		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IEntity entity = (IEntity) iter.next();
			if (entity instanceof IClass) {
				final IClass aClass = (IClass) entity;
				thereIsNotComplexClass = true;

				
	final double WMC = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("WMC")).compute(anAbstractLevelModel, aClass);
	mapOfNotComplexClassValues.put(aClass, new Double[] {new Double(WMC), new Double(0)});
				//final double WMC = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("WMC")).compute(anAbstractLevelModel, aClass);
				//mapOfNotComplexClassValues.put(aClass, new Double(WMC));
			}
		}

		if (thereIsNotComplexClass == true) {

			BoxPlot boxPlot = new BoxPlot(mapOfNotComplexClassValues, 20.0);
			setBoxPlot(boxPlot);

			final Map mapOfNotComplexClassClassesFromBoxPlot = boxPlot.getLowOutliers();
			final Iterator iter3 = mapOfNotComplexClassClassesFromBoxPlot
				.keySet()
				.iterator();

			while (iter3.hasNext()) {
				final IClass aNotComplexClassClass = (IClass) iter3.next();
				try {
					ClassProperty classProp = new ClassProperty(aNotComplexClassClass);
					
					
	final double WMC = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("WMC")).compute(anAbstractLevelModel, aNotComplexClassClass);

HashMap thresholdMap = new HashMap();
thresholdMap.put("WMC_MinBound", new Double(boxPlot.getMinBound()));
thresholdMap.put("WMC_LowerQuartile", new Double(boxPlot.getLowerQuartile()));
					final Double fuzziness = ((Double[])mapOfNotComplexClassClassesFromBoxPlot.get(aNotComplexClassClass))[1];
					classProp.addProperty(new MetricProperty("WMC", 
						WMC, 
						thresholdMap, fuzziness.doubleValue()));
					
					NotComplexClassClassesFound.add(new CodeSmell("NotComplexClass", "", classProp));
				} catch (final Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
			}
		}

		this.setSetOfSmells(NotComplexClassClassesFound);

	}
	
	
}