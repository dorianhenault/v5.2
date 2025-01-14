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
package padl.micropatterns.test.cases;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.impl.Factory;
import padl.micropatterns.helper.MicroPatternDetector;

/**
 * @author tanterij
 */
public class TestImmutable extends TestCase {

	private static MicroPatternDetector detector;
	private MicroPatternDetector currentDetector;

	public TestImmutable(String arg0) {
		super(arg0);
	}

	protected void setUp() {

		if (TestImmutable.detector == null) {

			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					"ptidej.example.innerclasses");
			try {
				codeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../PADL Micro-pattern Analysis Tests/bin/padl/micropatterns/examples/Immutable.class", }));
			}
			catch (Exception e) {
				// TODO: handle exception
			}

			this.currentDetector = new MicroPatternDetector(codeLevelModel);
		}
	}

	public void testImmutable() {
		// TODO: Joiner does not currently produce the
		// right results due to limitation in the PADL 
		// meta-model.
		// Assert.assertEquals("Immutable", 1, this.currentDetector.getNumberOfImmutable());
		Assert.assertEquals(
			"Immutable",
			0,
			this.currentDetector.getNumberOfImmutable());
	}
}
