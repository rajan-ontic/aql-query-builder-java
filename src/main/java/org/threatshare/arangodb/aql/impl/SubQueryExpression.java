package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Statement;

public class SubQueryExpression extends AbstractSelectorExpression {

	private final Statement stmt;

	public SubQueryExpression(Statement stmt) {
		this.stmt = stmt;
	}
	
	public SubQueryExpression(ReturnStatement stmt) {
		this.stmt = stmt;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("(");
		stmt.toAQL(sb);
		sb.append(")");
		return sb;
	}

}
