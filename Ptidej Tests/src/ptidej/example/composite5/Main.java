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
package ptidej.example.composite5;

public class Main {
	public static void main(final String[] args) {
		final Document document = new Document();
		document.addElement(new Title());
		document.addElement(new Paragraph());
		document.addElement(new Title());
		document.addElement(new IndentedParagraph());
		document.addElement(new IndentedParagraph());
		document.addElement(new SpecialIndentedParagraph(document));

		final int sw = 5;
		switch (sw) {
			case 1 :
				document.printAll();
				break;
			case 2 :
				document.getClass();
				break;
			case 3 :
				document.printAll();

				break;
			case 4 :
				document.hashCode();

				break;
			case 5 :
				document.print();
				break;
			default :
				System.out.println("");
				break;
		}

		document.printAll();
	}
}
