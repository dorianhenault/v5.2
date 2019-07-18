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
package sad.codesmell.detection.repository.FunctionalDecomposition;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IEntity;
import sad.codesmell.property.impl.SemanticProperty;
import sad.codesmell.property.impl.ClassProperty;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.kernel.impl.CodeSmell;

/**
 * This class represents the detection of the code smell <CODESMELL>
 * 
 * @author Auto generated
 *
 */


public class FunctionClassDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	
	
	public String getName() {
		return "FunctionClassDetection";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		
String[] CTRL_NAME = new String[]{"Calculate","Display","Compute","Exec","Execute","Creator","Create","Make"};
final Set FunctionClasssFound = new HashSet();
final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
while (iter.hasNext()) {
	final IEntity entity = (IEntity) iter.next();
	if (entity instanceof IClass) {
	final IClass aClass = (IClass) entity;
	boolean isFunctionClass = false;
	// we check the names of classes

String detectedKeyword = "";
	for (int i = 0; i < CTRL_NAME.length
			&& !isFunctionClass; i++) {
		if (aClass.getDisplayName().indexOf(CTRL_NAME[i]) > -1) {
		isFunctionClass = true;
		detectedKeyword = CTRL_NAME[i];
		}
	}
	if (isFunctionClass) {


ClassProperty classProp = new ClassProperty(aClass);
try {
classProp.addProperty(new SemanticProperty(
detectedKeyword));
}
catch (Exception e) {
// TODO: Auto generated
}
FunctionClasssFound.add(new CodeSmell("FunctionClass", "", classProp));

	}
	}
}
this.setSetOfSmells(FunctionClasssFound);
	}
	
	
}
