package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.threatshare.arangodb.aql.ArrayCompoundValue;
import org.threatshare.arangodb.aql.Expression;

public class ArrayCompoundValueImpl extends AbstractSelectorExpression implements ArrayCompoundValue {

	private final List<Expression> entries = new LinkedList<>();

	public ArrayCompoundValueImpl(Expression... entries) {
		Collections.addAll(this.entries, entries);
	}

	@Override
	public ArrayCompoundValue entry(Expression expr) {
		entries.add(expr);
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("[");
		joinList(sb, entries, elem -> elem.toAQL(sb));
		sb.append("]");
		return sb;
	}

}
