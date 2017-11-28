package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.ElseStatement;

public class TernaryExpression extends AbstractExpression implements ElseStatement {

	private final Expression ifExpr;
	private final Expression thenExpr;
	private Expression elseExpr;

	public TernaryExpression(Expression ifExpr, Expression thenExpr) {
		this.ifExpr = ifExpr;
		this.thenExpr = thenExpr;
	}

	public TernaryExpression(Expression ifExpr, Expression thenExpr, Expression elseExpr) {
		this.ifExpr = ifExpr;
		this.thenExpr = thenExpr;
		this.elseExpr = elseExpr;
	}

	@Override
	public Expression _else(Expression expr) {
		elseExpr = expr;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		ifExpr.toAQL(sb);
		sb.append(" ?");
		if (thenExpr != null) {
			thenExpr.toAQL(sb);
		}
		sb.append(" : ");
		elseExpr.toAQL(sb);
		return sb;
	}

}
