package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BooleanExpression;

public class BooleanExpressionImpl extends AbstractExpression implements BooleanExpression {

	private final boolean value;
	
	public BooleanExpressionImpl(boolean value) {
		this.value = value;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append(value);
		return sb;
	}

}
