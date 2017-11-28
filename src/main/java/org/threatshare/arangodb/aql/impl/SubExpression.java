package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;

public class SubExpression extends AbstractSelectorExpression {

	private final Expression expr;

	public SubExpression(Expression expr) {
		this.expr = expr;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("(");
		expr.toAQL(sb);
		sb.append(")");
		return sb;
	}

}
