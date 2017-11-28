package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;

abstract class AbstractHasPrevExpression extends AbstractExpression {

	protected final Expression prevExpr;

	public AbstractHasPrevExpression() {
		this(null);
	}
	
	public AbstractHasPrevExpression(Expression prevExpr) {
		this.prevExpr = prevExpr;
	}

	protected boolean hasPrevExpr() {
		return prevExpr != null;
	}

	protected void renderPrevExpr(StringBuilder sb) {
		if (hasPrevExpr()) {
			prevExpr.toAQL(sb);
		}
	}

}
