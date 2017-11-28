package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.OptionalArrayFilterStatement;
import org.threatshare.arangodb.aql.OptionalArrayLimitStatement;
import org.threatshare.arangodb.aql.OptionalArrayReturnStatement;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.SelectorExpression;

public class ArrayOperatorExpression extends AbstractSelectorExpression
		implements OptionalArrayFilterStatement, OptionalArrayLimitStatement, OptionalArrayReturnStatement {

	private final int flatteningLevels;
	private Expression filterExpr;
	private Expression offsetExpr;
	private Expression countExpr;
	private Expression returnExpr;

	public ArrayOperatorExpression(SelectorExpression prevSelectorExpr, int flatteningLevels) {
		super(prevSelectorExpr);
		this.flatteningLevels = flatteningLevels;
	}

	@Override
	public SelectorExpression _return(Expression returnExpr) {
		this.returnExpr = returnExpr;
		return this;
	}

	@Override
	public OptionalArrayReturnStatement limit(Expression offsetExpr) {
		this.offsetExpr = offsetExpr;
		return this;
	}

	@Override
	public OptionalArrayReturnStatement limit(Expression offsetExpr, Expression countExpr) {
		this.offsetExpr = offsetExpr;
		this.countExpr = countExpr;
		return this;
	}

	@Override
	public OptionalArrayLimitStatement filter(Expression filterExpr) {
		this.filterExpr = filterExpr;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevSelector(sb);
		sb.append("[");
		for (int i = 0; i < flatteningLevels; i++) {
			sb.append("*");
		}
		if (filterExpr != null) {
			sb.append(" FILTER ");
			filterExpr.toAQL(sb);
		}
		if (offsetExpr != null) {
			sb.append(" LIMIT ");
			offsetExpr.toAQL(sb);
			if (countExpr != null) {
				sb.append(", ");
				countExpr.toAQL(sb);
			}
		}
		if (returnExpr != null) {
			sb.append(" RETURN ");
			returnExpr.toAQL(sb);
		}
		sb.append("]");
		return sb;
	}

}
