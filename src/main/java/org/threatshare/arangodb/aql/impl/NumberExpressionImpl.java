package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.NumberExpression;

public class NumberExpressionImpl extends AbstractExpression implements NumberExpression {

	private final Number value;
	
	public NumberExpressionImpl(Number value) {
		this.value = value;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append(value);
		return sb;
	}

}
