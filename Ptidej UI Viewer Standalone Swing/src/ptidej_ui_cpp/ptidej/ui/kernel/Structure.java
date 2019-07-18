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
package ptidej_ui_cpp.ptidej.ui.kernel;

import padl.cpp.kernel.IStructure;
import ptidej.ui.kernel.Entity;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;

public final class Structure extends Entity {
	public Structure(
		final IPrimitiveFactory aPrimitiveFactory,
		final Builder aBuilder,
		final IStructure aStructure) {

		super(aPrimitiveFactory, aBuilder, aStructure);
	}
	protected String getStereotype() {
		return "<<struct>>\n";
	}
}