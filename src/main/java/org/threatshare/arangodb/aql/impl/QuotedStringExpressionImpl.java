package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.QuotedStringExpression;

public class QuotedStringExpressionImpl extends AbstractExpression implements QuotedStringExpression {

	private final String value;
	
	public QuotedStringExpressionImpl(String value) {
		this.value = value;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append('"').append(value).append('"');
		return sb;
	}

}
