package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import java.util.LinkedList;
import java.util.List;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Statement;

public class LetStatement extends AbstractBlockStatement {

	private final List<Assignment> assignments = new LinkedList<>();

	public LetStatement(Statement prevStatement, String varName, Expression expr) {
		super(prevStatement);
		let(varName, expr);
	}

	@Override
	public BlockStatement let(String varName, Expression expr) {
		this.assignments.add(new Assignment(varName, expr));
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("LET ");
		joinList(sb, assignments, ass -> ass.toAQL(sb));
		return sb;
	}

}
