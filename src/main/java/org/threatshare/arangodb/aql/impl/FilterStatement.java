package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Statement;

public class FilterStatement extends AbstractBlockStatement {

	private final Expression expression;

	public FilterStatement(Statement prevStatement, Expression expression) {
		super(prevStatement);
		this.expression = expression;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("FILTER ");
		expression.toAQL(sb);
		return sb;
	}

}
