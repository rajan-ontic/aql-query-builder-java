package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.NullExpression;

public class NullExpressionImpl extends AbstractExpression implements NullExpression {

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("null");
		return sb;
	}

}
