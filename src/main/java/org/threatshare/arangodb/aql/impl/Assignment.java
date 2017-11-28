package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.ToAQL;

public class Assignment implements ToAQL {

	private final String varName;
	private final Expression expr;

	public Assignment(String varName, Expression expr) {
		this.varName = varName;
		this.expr = expr;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("`").append(varName).append("` = ");
		expr.toAQL(sb);
		return sb;
	}

}
