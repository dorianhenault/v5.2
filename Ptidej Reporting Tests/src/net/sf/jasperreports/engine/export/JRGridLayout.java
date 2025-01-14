/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */

/*
 * Contributors:
 * Greg Hilton 
 */

package net.sf.jasperreports.engine.export;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.base.JRBasePrintFrame;
import net.sf.jasperreports.engine.util.JRBoxUtil;

/**
 * Utility class used by grid exporters to create a grid for page layout.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRGridLayout.java,v 1.1 2008/09/29 16:20:46 guehene Exp $
 */
public class JRGridLayout
{
	private final ExporterNature nature;
	
	private int width;
	private int height;
	private int offsetX;
	private int offsetY;
	private final String address;
	
	private CutsInfo xCuts;
	private CutsInfo yCuts;
	private JRExporterGridCell[][] grid;
	
	private Map boxesCache;

	private int virtualFrameIndex = 0;

	private boolean hasTopMargin = true;
	private boolean hasBottomMargin = true;
	private boolean hasLeftMargin = true;
	private boolean hasRightMargin = true;
	
	private boolean isNested;
	
	/**
	 * Constructor.
	 * 
	 * @param elements the elements that should arranged in a grid
	 * @param width the width available for the grid
	 * @param height the height available for the grid
	 * @param offsetX horizontal element position offset
	 * @param offsetY vertical element position offset
	 */
	public JRGridLayout(
		ExporterNature nature,
		List elements, 
		int width, 
		int height, 
		int offsetX, 
		int offsetY
		)
	{
		this(
			nature,
			elements,
			width, 
			height, 
			offsetX, 
			offsetY,
			null //xCuts
			);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param elements the elements that should arranged in a grid
	 * @param width the width available for the grid
	 * @param height the height available for the grid
	 * @param offsetX horizontal element position offset
	 * @param offsetY vertical element position offset
	 * @param xCuts An optional list of pre-calculated X cuts.
	 */
	public JRGridLayout(
		ExporterNature nature,
		List elements, 
		int width, 
		int height, 
		int offsetX, 
		int offsetY, 
		CutsInfo xCuts
		)
	{
		this.nature = nature;
		this.height = height;
		this.width = width;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.address = null;
		this.xCuts = xCuts;
		
		this.boxesCache = new HashMap();
		
		this.virtualFrameIndex = elements.size();

		layoutGrid(createWrappers(elements, this.address));
		
		if (nature.isSplitSharedRowSpan())
		{
			splitSharedRowSpanIntoNestedGrids();
		}
	}

	/**
	 * Constructor.
	 * 
	 * @param wrappers the element wrappers that should arranged in a grid
	 * @param width the width available for the grid
	 * @param height the height available for the grid
	 * @param offsetX horizontal element position offset
	 * @param offsetY vertical element position offset
	 * @param address element address
	 */
	protected JRGridLayout(
		ExporterNature nature,
		ElementWrapper[] wrappers, 
		int width, 
		int height, 
		int offsetX, 
		int offsetY, 
		String address
		)
	{
		this.nature = nature;
		this.height = height;
		this.width = width;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.address = address;
		
		//this constructor is called only in nested grids:
		this.isNested = true;
		
		this.boxesCache = new HashMap();
		
		layoutGrid(wrappers);
		
		if (nature.isSplitSharedRowSpan())
		{
			splitSharedRowSpanIntoNestedGrids();
		}
	}


	/**
	 * 
	 */
	private void createNestedGrid(
		int row1,
		int col1,
		int row2,
		int col2
		)
	{
		JRBasePrintFrame frame = new JRBasePrintFrame(null);
		List wrappers = new ArrayList();
		
		for(int row = row1; row < row2; row++)
		{
			for(int col = col1; col < col2; col++)
			{
				JRExporterGridCell gridCell = this.grid[row][col];
				this.grid[row][col] = JRExporterGridCell.OCCUPIED_CELL;
				ElementWrapper wrapper = gridCell.getWrapper();
				if (gridCell != JRExporterGridCell.OCCUPIED_CELL && wrapper != null)
				{
					wrappers.add(wrapper);
					frame.addElement(wrapper.getElement());//FIXMEODT do we need this?
				}
			}
		}
		
		frame.setWidth(this.xCuts.getCut(col2) - this.xCuts.getCut(col1));
		frame.setHeight(this.yCuts.getCut(row2) - this.yCuts.getCut(row1));
		
		String virtualAddress = (this.address == null ? "" : this.address + "_") + getNextVirtualFrameIndex(); 
		
		JRExporterGridCell gridCell = 
			new JRExporterGridCell(
				new ElementWrapper(frame, virtualAddress, null),
				frame.getWidth(), 
				frame.getHeight(), 
				col2 - col1, 
				row2 - row1
				);
		
		gridCell.setLayout(
			new JRGridLayout(
				this.nature,
				(ElementWrapper[]) wrappers.toArray(new ElementWrapper[wrappers.size()]), 
				frame.getWidth(), 
				frame.getHeight(), 
				this.offsetX -this.xCuts.getCut(col1),
				this.offsetY -this.yCuts.getCut(row1),
				virtualAddress
				)
			);
		this.grid[row1][col1] = gridCell; 
	}


	/**
	 * Constructs the element grid.
	 */
	protected void layoutGrid(ElementWrapper[] wrappers)
	{
		
		boolean createXCuts = (this.xCuts == null);

		this.xCuts = createXCuts ? new CutsInfo(this.width) : this.xCuts;
		this.yCuts = this.nature.isIgnoreLastRow() ? new CutsInfo(0) : new CutsInfo(this.height);
		
		if(!this.isNested && this.nature.isIgnorePageMargins())
		{
		    setMargins(wrappers);
		    
		    if(createXCuts)
		    {
    		    List xCutsList = this.xCuts.getCuts();
    
    			if(this.hasLeftMargin)
    			{
    				xCutsList.remove(new Integer(0));
    			}
    			if(this.hasRightMargin)
    			{
    				xCutsList.remove(new Integer(this.width));
    			}
		    }
			
			List yCutsList = this.yCuts.getCuts();

			if(this.hasTopMargin)
			{
				yCutsList.remove(new Integer(0));
			}
			if(this.hasBottomMargin)
			{
				yCutsList.remove(new Integer(this.height));
			}
		}

		createCuts(wrappers, this.offsetX, this.offsetY, createXCuts);
		
		this.xCuts.use();
		this.yCuts.use();

		int colCount = Math.max(this.xCuts.size() - 1, 0);
		int rowCount = Math.max(this.yCuts.size() - 1, 0);

		this.grid = new JRExporterGridCell[rowCount][colCount];
				
		for(int row = 0; row < rowCount; row++)
		{ 
			for(int col = 0; col < colCount; col++)
			{
				this.grid[row][col] = 
					new JRExporterGridCell(
						null,
						this.xCuts.getCut(col + 1) - this.xCuts.getCut(col),
						this.yCuts.getCut(row + 1) - this.yCuts.getCut(row),
						1,
						1
						);
			}
		}

		setGridElements(wrappers,
				this.offsetX, this.offsetY,
				0, 0, rowCount, colCount);
		
		if(!this.isNested && this.nature.isIgnorePageMargins())
		{
			int newWidth = 0;
			int newHeight = 0;
			
			for(int x = 1; x < this.xCuts.size(); x++)
			{
				newWidth += this.xCuts.getCut(x) - this.xCuts.getCut(x - 1);
			}

			for(int y = 1; y < this.yCuts.size(); y++)
			{
				newHeight += this.yCuts.getCut(y) - this.yCuts.getCut(y - 1);
			}

			this.width = newWidth;
			this.height = newHeight;
		}
	}

	protected void createCuts(ElementWrapper[] wrappers, int elementOffsetX, int elementOffsetY, boolean createXCuts)
	{
		for(int elementIndex = 0; elementIndex < wrappers.length; elementIndex++)
		{
			ElementWrapper wrapper = wrappers[elementIndex];
			JRPrintElement element = wrapper.getElement();
			
			if (this.nature.isToExport(element))
			{
				if (createXCuts)
				{
					this.xCuts.addXCuts(element, elementOffsetX);
				}
				
				this.yCuts.addYCuts(element, elementOffsetY);
			}
			
			JRPrintFrame frame = element instanceof JRPrintFrame ? (JRPrintFrame) element : null;
			
			if (this.nature.isDeep() && frame != null)
			{
				createCuts(
					wrapper.getWrappers(), 
					element.getX() + elementOffsetX + frame.getLineBox().getLeftPadding().intValue(), 
					element.getY() + elementOffsetY + frame.getLineBox().getTopPadding().intValue(), 
					createXCuts
					);
			}
		}
	}

	protected void setMargins(ElementWrapper[] wrappers)
	{
		for(int elementIndex = 0; elementIndex < wrappers.length; elementIndex++)
		{
			ElementWrapper wrapper = wrappers[elementIndex];
			JRPrintElement element = wrapper.getElement();
			
			if (this.nature.isToExport(element))
			{
				if(this.hasLeftMargin && element.getX() <= 0)
				{
					this.hasLeftMargin = false;
				}
				
				if(this.hasRightMargin && element.getX() >= this.width - element.getWidth())
				{
					this.hasRightMargin = false;
				}
				
				if(this.hasTopMargin && element.getY() <= 0)
				{
					this.hasTopMargin = false;
				}
				
				if(this.hasBottomMargin && element.getY() >= this.height - element.getHeight())
				{
					this.hasBottomMargin = false;
				}
				
			}
		}
	}


	protected void setGridElements(ElementWrapper[] wrappers, 
			int elementOffsetX, int elementOffsetY,
			int startRow, int startCol, int endRow, int endCol)
	{
		for(int elementIndex = wrappers.length - 1; elementIndex >= 0; elementIndex--)
		{
			ElementWrapper wrapper = wrappers[elementIndex];
			JRPrintElement element = wrapper.getElement();

			boolean toExport = this.nature.isToExport(element);
			//JRPrintFrame frame = deep && element instanceof JRPrintFrame ? (JRPrintFrame) element : null;
			JRPrintFrame frame = element instanceof JRPrintFrame ? (JRPrintFrame) element : null;
			
			if (toExport || frame != null)
			{
				int x = element.getX() + elementOffsetX;
				int y = element.getY() + elementOffsetY;
				
				int col1 = this.xCuts.indexOfCut(x);
				int row1 = this.yCuts.indexOfCut(y);
				int col2 = this.xCuts.indexOfCut(x + element.getWidth());
				int row2 = this.yCuts.indexOfCut(y + element.getHeight());
				
				if (!(toExport && isOverlap(row1, col1, row2, col2)))
				{
					if (this.nature.isDeep() && frame != null)
					{
						setGridElements(
							wrapper.getWrappers(), 
							x + frame.getLineBox().getLeftPadding().intValue(), 
							y + frame.getLineBox().getTopPadding().intValue(),
							row1, col1, row2, col2
							);
					}

					if (toExport)
					{
						if (this.nature.isDeep() && frame != null)
						{
							setFrameCellsStyle(frame, row1, col1, row2, col2);
						}
						else
						{
							setGridElement(wrapper, row1, col1, row2, col2);
						}
					}
				}
			}
		}
		
		if (this.nature.isHorizontallyMergeEmptyCells())
		{
			horizontallyMergeEmptyCells(startRow, startCol, endRow, endCol);
		}
	}

	protected void horizontallyMergeEmptyCells(int startRow, int startCol, int endRow, int endCol)
	{
		for (int row = startRow; row < endRow; ++row)
		{
			int startSpan = -1;
			int spanWidth = 0;
			int col = startCol;
			for (; col < endCol; ++col)
			{
				JRExporterGridCell cell = this.grid[row][col];
				if (cell.isEmpty())
				{
					if (startSpan == -1)
					{
						startSpan = col;
					}
					spanWidth += cell.getWidth();
				}
				else
				{
					if (startSpan != -1 && col - startSpan > 1)
					{
						JRExporterGridCell spanCell = this.grid[row][startSpan];
						spanCell.setColSpan(col - startSpan);
						spanCell.setWidth(spanWidth);
						//TODO set OCCUPIED_CELL?
					}
					startSpan = -1;
					spanWidth = 0;
				}
			}
			if (startSpan != -1 && col - startSpan > 1)
			{
				JRExporterGridCell spanCell = this.grid[row][startSpan];
				spanCell.setColSpan(col - startSpan);
				spanCell.setWidth(spanWidth);
			}
		}
	}

	protected boolean isOverlap(int row1, int col1, int row2, int col2)
	{
		boolean isOverlap = false;
		if (this.nature.isSpanCells())
		{
			is_overlap_out:
			for (int row = row1; row < row2; row++)
			{
				for (int col = col1; col < col2; col++)
				{
					if (!this.grid[row][col].isEmpty())
					{
						isOverlap = true;
						break is_overlap_out;
					}
				}
			}
		}
		else
		{
			isOverlap = this.grid[row1][col1].getWrapper() != null;
		}
		return isOverlap;
	}


	protected void setGridElement(ElementWrapper wrapper, int row1, int col1, int row2, int col2)
	{
		this.yCuts.addUsage(row1, CutsInfo.USAGE_NOT_EMPTY);
		this.xCuts.addUsage(col1, CutsInfo.USAGE_NOT_EMPTY);
		
		if (this.nature.isSpanCells())
		{
			for (int row = row1; row < row2; row++)
			{
				for (int col = col1; col < col2; col++)
				{
					this.grid[row][col] = JRExporterGridCell.OCCUPIED_CELL;
				}
				this.yCuts.addUsage(row, CutsInfo.USAGE_SPANNED);
			}

			for (int col = col1; col < col2; col++)
			{
				this.xCuts.addUsage(col, CutsInfo.USAGE_SPANNED);
			}
		}

		if (col2 - col1 != 0 && row2 - row1 != 0)
		{
			JRPrintElement element = wrapper.getElement();
			JRPrintFrame frame = element instanceof JRPrintFrame ? (JRPrintFrame) element : null;
			
			int rowSpan = this.nature.isSpanCells() ? row2 - row1 : 1;
			int colSpan = this.nature.isSpanCells() ? col2 - col1 : 1;
			JRExporterGridCell gridCell = 
				new JRExporterGridCell(
					wrapper,
					element.getWidth(), 
					element.getHeight(), 
					colSpan, 
					rowSpan
					);
			
			if (frame != null)//FIXMEODT if deep, does this make sense?
			{
				gridCell.setLayout(
					new JRGridLayout(
						this.nature,
						wrapper.getWrappers(), 
						frame.getWidth(), 
						frame.getHeight(), 
						0, //offsetX
						0, //offsetY
						wrapper.getAddress()
						)
					);
			}
			
			gridCell.setBox((element instanceof JRBoxContainer)?((JRBoxContainer)element).getLineBox():null);

			if (this.nature.isBreakBeforeRow(element))
			{
				this.yCuts.addUsage(row1,  CutsInfo.USAGE_BREAK);
			}
			if (this.nature.isBreakAfterRow(element))
			{
				this.yCuts.addUsage(row1 + rowSpan,  CutsInfo.USAGE_BREAK);
			}

			this.grid[row1][col1] = gridCell;
		}
	}


	protected void setFrameCellsStyle(JRPrintFrame frame, int row1, int col1, int row2, int col2)
	{
		Color backcolor = frame.getMode() == JRElement.MODE_OPAQUE ? frame.getBackcolor() : null;
		
		for (int row = row1; row < row2; row++)
		{	
			for (int col = col1; col < col2; col++)
			{
				JRExporterGridCell cell = this.grid[row][col];
				
				if (cell.getBackcolor() == null)
				{
					if (frame.getMode() == JRElement.MODE_OPAQUE)
					{
						cell.setBackcolor(backcolor);
					}
				}
				
				if (cell.getForecolor() == null)
				{
					cell.setForecolor(frame.getForecolor());
				}
				
				boolean keepLeft = col == col1;
				boolean keepRight = col == col2 - cell.getColSpan();
				boolean keepTop = row == row1;
				boolean keepBottom = row == row2 - cell.getRowSpan();
					
				if (keepLeft || keepRight || keepTop || keepBottom)
				{
					JRLineBox cellBox = cell.getBox();
					Object key = new BoxKey(frame.getLineBox(), cellBox, keepLeft, keepRight, keepTop, keepBottom);
					JRLineBox modBox = (JRLineBox) this.boxesCache.get(key);
					if (modBox == null)
					{
						modBox = JRBoxUtil.clone(frame.getLineBox(), keepLeft, keepRight, keepTop, keepBottom, cellBox);
						this.boxesCache.put(key, modBox);
					}
					
					cell.setBox(modBox);
				}
			}
		}
	}
	
	private void splitSharedRowSpanIntoNestedGrids()
	{
		for(int row = 0; row < this.grid.length;)
		{
			int rowSpan = getSharedRowSpan(row);
			//negative row span means it is not shared row span
			if (rowSpan > 0)
			{
				splitSharedColSpanIntoNestedGrids(row, row + rowSpan);
			}
			row += Math.abs(rowSpan);
		}
	}
	
	private void splitSharedColSpanIntoNestedGrids(int row1, int row2)
	{
		for(int col = 0; col < this.grid[row1].length;)
		{
			int colSpan = getSharedColSpan(row1, row2, col);
			//negative col span means it is not shared col span
			if (colSpan > 0)
			{
				if (
					!(row1 == 0
					&& row2 == this.grid.length
					&& col == 0
					&& colSpan == this.grid[0].length)
					)
				{
					this.createNestedGrid(
						row1,
						col,
						row2,
						col + colSpan
						);
				}
			}
			col += Math.abs(colSpan);
		}
	}
	
	private int getSharedRowSpan(int row1)
	{
		int rowSpan = 1;
		int sharedSpanCount = 0;

		for(int row = 0; row < rowSpan; row++)
		{
			for(int col = 0; col < this.grid[0].length; col++)
			{
				JRExporterGridCell gridCell = this.grid[row1 + row][col];
				if (row + gridCell.getRowSpan() > rowSpan)
				{
					sharedSpanCount++;
					rowSpan = row + gridCell.getRowSpan();
				}
			}
		}
		
		// we have "shared row span" only if at least two merged cells share at least one row;
		// negative row span is used to skip row span that is not shared 
		return sharedSpanCount > 1 ? rowSpan : -rowSpan;
	}
	
	private int getSharedColSpan(int row1, int row2, int col1)
	{
		int colSpan = 1;
		boolean isSharedSpan = false;

		for(int col = 0; col < colSpan; col++)
		{
			for(int row = row1; row < row2; row++)
			{
				JRExporterGridCell gridCell = this.grid[row][col1 + col];
				if (col + gridCell.getColSpan() > colSpan)
				{
					isSharedSpan = true;
					colSpan = col + gridCell.getColSpan();
				}
				else if (gridCell.getRowSpan() > 1)
				{
					isSharedSpan = true;
				}
			}
		}
		
		// we have "shared col span" only if at least two merged cells share at least one col;
		// negative col span is used to skip col span that is not shared 
		return isSharedSpan ? colSpan : -colSpan;
	}
	
	/**
	 * Returns the constructed element grid.
	 * 
	 * @return the constructed element grid
	 */
	public JRExporterGridCell[][] getGrid()
	{
		return this.grid;
	}


	/**
	 * Returns the list of cut points on the X axis for the grid.
	 * 
	 * @return the list of cut points on the X axis for the grid
	 */
	public CutsInfo getXCuts()
	{
		return this.xCuts;
	}


	/**
	 * Returns the list of cut points on the Y axis for the grid.
	 * 
	 * @return the list of cut points on the Y axis for the grid
	 */
	public CutsInfo getYCuts()
	{
		return this.yCuts;
	}


	/**
	 * Returns the width available for the grid.
	 * 
	 * @return the width available for the grid
	 */
	public int getWidth()
	{
		return this.width;
	}
	
	
	public int getRowHeight(int row)
	{
		return this.yCuts.getCut(row + 1) - this.yCuts.getCut(row);
	}
	
	
	public static int getMaxRowHeight(JRExporterGridCell[] row)
	{
		int maxRowHeight = row[0].getHeight();
		for (int col = 0; col < row.length; col++)
		{
			JRExporterGridCell cell = row[col];
			
			if (cell != JRExporterGridCell.OCCUPIED_CELL)
			{
				if (maxRowHeight < cell.getHeight())
				{
					maxRowHeight = cell.getHeight();
				}
			}
		}
		return maxRowHeight;
	}
	
	
	public static int getRowHeight(JRExporterGridCell[] row)//FIXMEODT are we still using this?
	{
		if (row[0].getRowSpan() == 1 && row[0] != JRExporterGridCell.OCCUPIED_CELL) //quick exit
		{
			return row[0].getHeight();
		}
		
		int rowHeight = 0;
		int minSpanIdx = 0;
		
		int colCount = row.length;
		
		int col;
		for (col = 0; col < colCount; col++)
		{
			JRExporterGridCell cell = row[col];
			
			if (cell != JRExporterGridCell.OCCUPIED_CELL)
			{
				if (cell.getRowSpan() == 1)
				{
					rowHeight = cell.getHeight();
					break;
				}

				if (cell.getRowSpan() < row[minSpanIdx].getRowSpan())
				{
					minSpanIdx = col;
				}
			}
		}
		
		if (col >= colCount) //no cell with rowSpan = 1 was found, getting the height of the cell with min rowSpan
		{
			rowHeight = row[minSpanIdx].getHeight();
		}
		
		return rowHeight;
	}
	
	
	/**
	 * This static method calculates all the X cuts for a list of pages.
	 * 
	 * @param pages
	 *            The list of pages.
	 * @param startPageIndex
	 *            The first page to consider.
	 * @param endPageIndex
	 *            The last page to consider.
	 * @param width
	 *            The page width
	 * @param offsetX
	 *            horizontal element position offset
	 */
	public static CutsInfo calculateXCuts(ExporterNature nature, List pages, int startPageIndex, int endPageIndex, int width, int offsetX)
	{
		CutsInfo xCuts = new CutsInfo(width);

		for (int pageIndex = startPageIndex; pageIndex <= endPageIndex; pageIndex++)
		{
			JRPrintPage page = (JRPrintPage) pages.get(pageIndex);
			addXCuts(nature, page.getElements(), offsetX, xCuts);
		}

		return xCuts;
	}

	/**
	 * This static method calculates the X cuts for a list of print elements and
	 * stores them in the list indicated by the xCuts parameter.
	 * 
	 * @param elementsList
	 *            The list of elements to be used to determine the X cuts.
	 * @param elementOffsetX
	 *            horizontal element position offset
	 * @param xCuts
	 *            The list to which the X cuts are to be added.
	 */
	protected static void addXCuts(ExporterNature nature, List elementsList, int elementOffsetX, CutsInfo xCuts)
	{
		for (Iterator it = elementsList.iterator(); it.hasNext();)
		{
			JRPrintElement element = ((JRPrintElement) it.next());

			if (nature.isToExport(element))
			{
				xCuts.addXCuts(element, elementOffsetX);
			}

			if (element instanceof JRPrintFrame)
			{
				JRPrintFrame frame = (JRPrintFrame) element;
				addXCuts(
					nature, 
					frame.getElements(), 
					element.getX() + elementOffsetX + frame.getLineBox().getLeftPadding().intValue(), 
					xCuts
					);
			}
		}
	}
	
	/**
	 *
	 */
	protected int getNextVirtualFrameIndex()
	{
		return this.virtualFrameIndex++;
	}

		
	/**
	 * 
	 */
	private static ElementWrapper[] createWrappers(List elementsList, String parentAddress)
	{
		ElementWrapper[] wrappers = new ElementWrapper[elementsList.size()];

		for (int elementIndex = 0; elementIndex < elementsList.size(); elementIndex++)
		{
			JRPrintElement element = ((JRPrintElement) elementsList.get(elementIndex));
			
			String address = (parentAddress == null ? "" : parentAddress + "_") + elementIndex;

			wrappers[elementIndex] = 
				new ElementWrapper(
					element, 
					address,
					element instanceof JRPrintFrame
						? createWrappers(((JRPrintFrame)element).getElements(), address)
						: null
					);
		}
		
		return wrappers;
	}
	
	/**
	 * 
	 */
	protected static class BoxKey
	{
		final JRLineBox box;
		final JRLineBox cellBox;
		final boolean left;
		final boolean right;
		final boolean top;
		final boolean bottom;
		final int hashCode;
		
		BoxKey(JRLineBox box, JRLineBox cellBox, boolean left, boolean right, boolean top, boolean bottom)
		{
			this.box = box;
			this.cellBox = cellBox;
			this.left = left;
			this.right = right;
			this.top = top;
			this.bottom = bottom;
			
			int hash = box.hashCode();
			if (cellBox != null)
			{
				hash = 31*hash + cellBox.hashCode();
			}
			hash = 31*hash + (left ? 1231 : 1237);
			hash = 31*hash + (right ? 1231 : 1237);
			hash = 31*hash + (top ? 1231 : 1237);
			hash = 31*hash + (bottom ? 1231 : 1237);
			this.hashCode = hash;
		}

		public boolean equals(Object obj)
		{
			if (obj == this)
			{
				return true;
			}
			
			BoxKey b = (BoxKey) obj;
			
			return b.box.equals(this.box) &&
				(b.cellBox == null ? this.cellBox == null : (this.cellBox != null && b.cellBox.equals(this.cellBox))) &&
				b.left == this.left && b.right == this.right && b.top == this.top && b.bottom == this.bottom;
		}

		public int hashCode()
		{
			return this.hashCode;
		}
	}


}