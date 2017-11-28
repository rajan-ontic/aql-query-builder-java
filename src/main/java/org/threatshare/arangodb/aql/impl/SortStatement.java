package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import java.util.LinkedList;
import java.util.List;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.SimpleValue;
import org.threatshare.arangodb.aql.Statement;

public class SortStatement extends AbstractBlockStatement {

	private final List<SortElement> sorts = new LinkedList<>();

	public SortStatement(Statement prevStatement, Expression expr) {
		this(prevStatement, expr, null);
	}

	public SortStatement(Statement prevStatement, Expression expr, SimpleValue sortDir) {
		super(prevStatement);
		sort(expr, sortDir);
	}

	@Override
	public BlockStatement sort(Expression expr) {
		sorts.add(new SortElement(expr));
		return this;
	}

	@Override
	public BlockStatement sort(Expression expr, SimpleValue sortDir) {
		sorts.add(new SortElement(expr, sortDir));
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("SORT ");
		joinList(sb, sorts, elem -> elem.toAQL(sb));
		return sb;
	}

}
