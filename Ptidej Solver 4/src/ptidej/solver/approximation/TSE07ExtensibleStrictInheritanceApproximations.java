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
package ptidej.solver.approximation;

/**
 * @author	Yann-Ga�l Gu�h�neuc
 * @since	2006/08/16
 */
public class TSE07ExtensibleStrictInheritanceApproximations implements
		IApproximations {

	private static final String[] APPROXIMATIONS =
		{ "ptidej.solver.constraint.repository.StrictInheritanceConstraint",
				"ptidej.solver.constraint.repository.StrictInheritancePathConstraint" };

	private static TSE07ExtensibleStrictInheritanceApproximations UniqueInstance;
	public static TSE07ExtensibleStrictInheritanceApproximations getDefaultApproximations() {
		if (TSE07ExtensibleStrictInheritanceApproximations.UniqueInstance == null) {
			TSE07ExtensibleStrictInheritanceApproximations.UniqueInstance =
				new TSE07ExtensibleStrictInheritanceApproximations();
		}
		return TSE07ExtensibleStrictInheritanceApproximations.UniqueInstance;
	}

	private TSE07ExtensibleStrictInheritanceApproximations() {
	}
	public String[] getApproximations() {
		return TSE07ExtensibleStrictInheritanceApproximations.APPROXIMATIONS;
	}
}
