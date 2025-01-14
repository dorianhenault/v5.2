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
package padl.creator.aolfile.util;

import java.io.IOException;
import java.io.Writer;
import padl.kernel.IAbstractModel;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IComposition;
import padl.kernel.IConstituent;
import padl.kernel.IConstructor;
import padl.kernel.IContainerAggregation;
import padl.kernel.IContainerComposition;
import padl.kernel.ICreation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IGetter;
import padl.kernel.IGhost;
import padl.kernel.IInterface;
import padl.kernel.IMemberClass;
import padl.kernel.IMemberGhost;
import padl.kernel.IMemberInterface;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.IPackage;
import padl.kernel.IPackageDefault;
import padl.kernel.IParameter;
import padl.kernel.IPrimitiveEntity;
import padl.kernel.IRelationship;
import padl.kernel.ISetter;
import padl.kernel.IUseRelationship;
import padl.visitor.IWalker;
import util.io.ProxyConsole;
import util.io.ProxyDisk;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2007/02/04
 */
public class AggregationVisitor implements IWalker {
	private IFirstClassEntity enclosingEntity;
	private Writer writer;

	public AggregationVisitor(final String anOutputFile) {
		this.writer = ProxyDisk.getInstance().fileAbsoluteOutput(anOutputFile);
	}
	public void close(final IAbstractModel anAbstractModel) {
		try {
			this.writer.close();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	public void close(final IClass aClass) {
	}
	public void close(final IConstructor aConstructor) {
	}
	public void close(final IDelegatingMethod aDelegatingMethod) {
	}
	public void close(final IGetter aGetter) {
	}
	public void close(final IGhost aGhost) {
	}
	public void close(final IInterface anInterface) {
	}
	public void close(final IMemberClass aMemberClass) {
	}
	public void close(final IMemberGhost aMemberGhost) {
	}
	public void close(final IMemberInterface aMemberInterface) {
	}
	public void close(final IMethod aMethod) {
	}
	public void close(final IPackage aPackage) {
	}
	public void close(final IPackageDefault aPackage) {
	}
	public void close(final ISetter aSetter) {
	}

	public String getName() {
		return null;
	}
	public Object getResult() {
		return null;
	}
	public void open(final IAbstractModel anAbstractModel) {
	}
	public void open(final IClass aClass) {
		this.enclosingEntity = aClass;
	}
	public void open(final IConstructor aConstructor) {
	}
	public void open(final IDelegatingMethod aDelegatingMethod) {
	}
	public void open(final IGetter aGetter) {
	}
	public void open(final IGhost aGhost) {
		this.enclosingEntity = aGhost;
	}
	public void open(final IInterface anInterface) {
		this.enclosingEntity = anInterface;
	}
	public void open(final IMemberClass aMemberClass) {
		this.enclosingEntity = aMemberClass;
	}
	public void open(final IMemberGhost aMemberGhost) {
		this.enclosingEntity = aMemberGhost;
	}
	public void open(final IMemberInterface aMemberInterface) {
		this.enclosingEntity = aMemberInterface;
	}
	public void open(final IMethod aMethod) {
	}
	public void open(final IPackage aPackage) {
	}
	public void open(final IPackageDefault aPackage) {
	}
	public void open(final ISetter aSetter) {
	}
	public void reset() {
	}
	public final void unknownConstituentHandler(
		final String aCalledMethodName,
		final IConstituent aConstituent) {

		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(this.getClass().getName());
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(" does not know what to do for \"");
		ProxyConsole.getInstance().debugOutput().print(aCalledMethodName);
		ProxyConsole.getInstance().debugOutput().print("\" (");
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(aConstituent.getDisplayID());
		ProxyConsole.getInstance().debugOutput().println(')');
	}
	public void visit(final IAggregation anAggregation) {
		this.visit((IRelationship) anAggregation);
	}
	public void visit(final IAssociation anAssociation) {
	}
	public void visit(final IComposition aComposition) {
		this.visit((IRelationship) aComposition);
	}
	public void visit(final IContainerAggregation aContainerAggregation) {
		this.visit((IRelationship) aContainerAggregation);
	}
	public void visit(final IContainerComposition aContainerComposition) {
		this.visit((IRelationship) aContainerComposition);
	}
	public void visit(final ICreation aCreation) {
	}
	public void visit(final IField aField) {
	}
	public void visit(final IMethodInvocation aMethodInvocation) {
	}
	public void visit(final IParameter aParameter) {
	}
	public void visit(final IPrimitiveEntity aPrimitiveEntity) {
		// Do nothing for uninteresting primitive types.
	}
	private void visit(final IRelationship aRelationship) {
		try {
			this.writer.write(this.enclosingEntity.getName());
			this.writer.write(" aggregates ");
			this.writer.write(aRelationship.getTargetEntity().getName());
			this.writer.write('\n');
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	public void visit(final IUseRelationship aUse) {
	}
}
