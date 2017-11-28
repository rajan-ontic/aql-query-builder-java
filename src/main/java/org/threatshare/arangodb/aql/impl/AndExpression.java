package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Operator;

public class AndExpression extends NAryExpression {

	public AndExpression(Expression leftExpr, Expression rightExpr, Expression...expressions) {
		super(Operator.AND, leftExpr, rightExpr, expressions);
	}

	@Override
	public Expression and(Expression expr) {
		expressions.add(expr);
		return this;
	}

}
