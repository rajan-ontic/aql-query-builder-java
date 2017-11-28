package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.QueryEnd;
import org.threatshare.arangodb.aql.Statement;

public class ReturnStatement extends AbstractStatement implements QueryEnd {

	private final boolean distinct;
	private final Expression expression;

	public ReturnStatement(Statement prevStatement, Expression expression) {
		this(prevStatement, false, expression);
	}
	
	public ReturnStatement(Statement prevStatement, boolean distinct, Expression expression) {
		super(prevStatement);
		this.distinct = distinct;
		this.expression = expression;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("RETURN ");
		if (distinct) {
			sb.append("DISTINCT ");
		}
		expression.toAQL(sb);
		return sb;
	}

}
