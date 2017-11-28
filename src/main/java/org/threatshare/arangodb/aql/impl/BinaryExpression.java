package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Operator;

public class BinaryExpression extends AbstractExpression {

	protected final Operator operator;
	protected final Expression leftExpr;
	protected final Expression rightExpr;

	public BinaryExpression(Operator operator, Expression leftExpr, Expression rightExpr) {
		this.operator = operator;
		this.leftExpr = leftExpr;
		this.rightExpr = rightExpr;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("(");
		leftExpr.toAQL(sb);
		sb.append(" ").append(operator.getValue()).append(" ");
		rightExpr.toAQL(sb);
		sb.append(")");
		return sb;
	}

}
