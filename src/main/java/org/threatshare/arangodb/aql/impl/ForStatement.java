package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.GraphDirection;
import org.threatshare.arangodb.aql.GraphDirectionStatement;
import org.threatshare.arangodb.aql.GraphSubjectStatement;
import org.threatshare.arangodb.aql.InStatement;
import org.threatshare.arangodb.aql.OptionalGraphDirectionVertexStatement;
import org.threatshare.arangodb.aql.ShortestPathStatement;
import org.threatshare.arangodb.aql.Statement;

public class ForStatement extends AbstractBlockStatement
		implements InStatement, GraphDirectionStatement, OptionalGraphDirectionVertexStatement {

	private final String varName;
	private final String edgeVarName;
	private final String pathVarName;
	private Expression expr;

	public ForStatement(Statement prevStatement, String varName) {
		this(prevStatement, varName, null, null);
	}

	public ForStatement(Statement prevStatement, String varName, String edgeVarName) {
		this(prevStatement, varName, edgeVarName, null);
	}

	public ForStatement(Statement prevStatement, String varName, String edgeVarName, String pathVarName) {
		super(prevStatement);
		this.varName = varName;
		this.edgeVarName = edgeVarName;
		this.pathVarName = pathVarName;
	}

	@Override
	public OptionalGraphDirectionVertexStatement in(Expression expr) {
		this.expr = expr;
		return this;
	}

	@Override
	public GraphDirectionStatement in() {
		return this;
	}

	@Override
	public GraphSubjectStatement any(Expression startVertex) {
		return new TraversalStatementImpl(prevStatement, varName, edgeVarName, pathVarName, expr, GraphDirection.ANY,
				startVertex);
	}

	@Override
	public GraphSubjectStatement inbound(Expression startVertex) {
		return new TraversalStatementImpl(prevStatement, varName, edgeVarName, pathVarName, expr,
				GraphDirection.INBOUND, startVertex);
	}

	@Override
	public GraphSubjectStatement outbound(Expression startVertex) {
		return new TraversalStatementImpl(prevStatement, varName, edgeVarName, pathVarName, expr,
				GraphDirection.OUTBOUND, startVertex);
	}

	@Override
	public ShortestPathStatement any() {
		return new ShortestPathStatementImpl(prevStatement, varName, edgeVarName, GraphDirection.ANY);
	}

	@Override
	public ShortestPathStatement inbound() {
		return new ShortestPathStatementImpl(prevStatement, varName, edgeVarName, GraphDirection.INBOUND);
	}

	@Override
	public ShortestPathStatement outbound() {
		return new ShortestPathStatementImpl(prevStatement, varName, edgeVarName, GraphDirection.OUTBOUND);
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("FOR `").append(varName).append("` IN ");
		expr.toAQL(sb);
		return sb;
	}

}
