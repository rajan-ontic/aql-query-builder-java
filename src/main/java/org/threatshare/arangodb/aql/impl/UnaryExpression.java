package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Operator;

public class UnaryExpression extends AbstractExpression {

	protected final Operator operator;
	protected final Expression expr;

	public UnaryExpression(Operator operator, Expression expr) {
		this.operator = operator;
		this.expr = expr;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append(operator.getValue());
		sb.append("(");
		expr.toAQL(sb);
		sb.append(")");
		return sb;
	}

}
