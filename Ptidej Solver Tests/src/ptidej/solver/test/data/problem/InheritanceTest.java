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
package ptidej.solver.test.data.problem;

import java.util.List;
import ptidej.solver.Problem;
import ptidej.solver.Variable;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.constraint.repository.InheritanceConstraint;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2004/09/19
 */
public final class InheritanceTest {
	public static final char[] SUB_ENTITY = "SubEntity".toCharArray();
	public static final char[] SUPER_ENTITY = "SuperEntity".toCharArray();

	public static Problem getProblem(final List allEntities) {
		final Problem pb = new Problem(90, "Composition Test", allEntities);

		final Variable subEntity =
			new Variable(pb, InheritanceTest.SUB_ENTITY, true);
		final Variable superEntity =
			new Variable(pb, InheritanceTest.SUPER_ENTITY, true);

		pb.addVar(subEntity);
		pb.addVar(superEntity);

		final InheritanceConstraint c1 =
			new InheritanceConstraint(
				"Sub-entity -|>- Super-entity OR Sub-entity == Super-entity",
				"SubEntity, SuperEntity |\n\t\t\tjavaXL.XClass c1, javaXL.XClass c2 |\n\t\t\tc1.setSuperclass(c2.getName());",
				subEntity,
				superEntity,
				100,
				DefaultNoApproximations.getDefaultApproximations());

		pb.post(c1);

		return pb;
	}
}
