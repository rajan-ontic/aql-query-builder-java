package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Statement;

abstract class AbstractStatement implements Statement {

	protected final Statement prevStatement;

	protected AbstractStatement() {
		this(null);
	}

	protected AbstractStatement(Statement prevStatement) {
		this.prevStatement = prevStatement;
	}

	protected boolean hasPrevStatement() {
		return prevStatement != null;
	}

	protected void renderPrevStatement(StringBuilder sb) {
		if (hasPrevStatement()) {
			prevStatement.toAQL(sb);
			if (sb.length() > 1 && !isLastCharLeftParenthesis(sb)) {
				sb.append(" ");
			}
		}
	}

	private boolean isLastCharLeftParenthesis(StringBuilder sb) {
		return sb.charAt(sb.length() > 0 ? sb.length() - 1 : 0) == '(';
	}

	@Override
	public String toAQL() {
		return toAQL(new StringBuilder()).toString();
	}

}
