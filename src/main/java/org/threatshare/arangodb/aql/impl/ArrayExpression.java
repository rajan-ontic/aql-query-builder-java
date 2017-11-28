package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.SelectorExpression;

public class ArrayExpression extends AbstractSelectorExpression {

	private final Expression expr;

	public ArrayExpression(SelectorExpression prevSelector, Expression expr) {
		super(prevSelector);
		this.expr = expr;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevSelector(sb);
		sb.append("[");
		expr.toAQL(sb);
		sb.append("]");
		return sb;
	}

}
