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
package ptidej_ui_viewer.ptidej.viewer.event;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2014/03/28
 */
public interface IGraphModelListener {
	void graphModelAvailable(final SourceAndGraphModelEvent aSourceModelEvent);
	void graphModelChanged(final SourceAndGraphModelEvent aSourceModelEvent);
	void graphModelUnavailable();
}