package wittybox.Blockhail;

import java.util.Stack;

import wittybox.Blockhail.*;

enum Operations {
	NONE, DELETE_ROW, ROTATE_SHAPE, SET_NEW_SHAPE,
	PASTE_SHAPE, MOVE_SHAPE_LEFT, MOVE_SHAPE_RIGHT,
	MOVE_SHAPE_DOWN, CLEAR_SHAPE;
}

class Operation {
	private Operations op;
	private Object val;

	Operations getOperation() {
		return this.op;
	}

	Object getVal() {
		return this.val;
	}

	public Operation() {
		op = Operations.NONE;
		val = null;
	}

	public Operation(Operations op) {
		this.op = op;
		this.val = null;
	}

	public Operation(Operations op, Object val) {
		this.op = op;
		this.val = val;
	}
}
