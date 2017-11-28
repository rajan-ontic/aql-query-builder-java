package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Operator;

public class OrExpression extends NAryExpression {

	public OrExpression(Expression leftExpr, Expression rightExpr, Expression...expressions) {
		super(Operator.OR, leftExpr, rightExpr, expressions);
	}

	@Override
	public Expression or(Expression expr) {
		expressions.add(expr);
		return this;
	}

}
