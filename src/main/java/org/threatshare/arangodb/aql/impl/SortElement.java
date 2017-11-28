package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.SimpleValue;
import org.threatshare.arangodb.aql.ToAQL;

public class SortElement implements ToAQL {

	private final Expression expr;
	private final SimpleValue sortDir;

	public SortElement(Expression expr) {
		this(expr, null);
	}

	public SortElement(Expression expr, SimpleValue sortDir) {
		this.expr = expr;
		this.sortDir = sortDir;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		expr.toAQL(sb);
		if (sortDir != null) {
			sb.append(" ");
			sortDir.toAQL(sb);
		}
		return sb;
	}

}
