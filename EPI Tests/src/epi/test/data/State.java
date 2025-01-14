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
package epi.test.data;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  08/01/15
 */
public final class State implements BitVectorPattern {
	private static State UniqueInstance;
	public static State getInstance() {
		if (State.UniqueInstance == null) {
			State.UniqueInstance = new State();
		}
		return State.UniqueInstance;
	}

	private static final String STATE_NAME = "BridgeStateStrategy";
	private static final String STATE_STRING_NONE =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_1 =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_2 =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_4 = "";
	// Same as NONE because all roles must be played.
	private static final String STATE_STRING_1_AND_2 =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_1_AND_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_1_AND_4 = ""; // Same as 1
	private static final String STATE_STRING_2_AND_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_2_AND_4 = "";
	// Same as 2 and 4
	private static final String STATE_STRING_3_AND_4 = "";
	// Same as 3 and 4
	private static final String STATE_STRING_1_AND_2_AND_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_ALL = "";
	// Same as 1 and 2 and 3
	private static final String[] STATE_STRINGS =
		new String[] {
			STATE_STRING_NONE,
			STATE_STRING_1,
			STATE_STRING_2,
			STATE_STRING_3,
			STATE_STRING_4,
			STATE_STRING_1_AND_2,
			STATE_STRING_1_AND_3,
			STATE_STRING_1_AND_4,
			STATE_STRING_2_AND_3,
			STATE_STRING_2_AND_4,
			STATE_STRING_3_AND_4,
			STATE_STRING_1_AND_2_AND_3,
			STATE_STRING_ALL };

	public String getName(){
		return STATE_NAME;
	}
	public String[] getStrings(){
		return STATE_STRINGS;
	}
}
