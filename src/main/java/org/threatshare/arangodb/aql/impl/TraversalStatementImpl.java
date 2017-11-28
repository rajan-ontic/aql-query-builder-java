package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.GraphDirection;
import org.threatshare.arangodb.aql.GraphDirectionStatement;
import org.threatshare.arangodb.aql.GraphDirectionVertexStatement;
import org.threatshare.arangodb.aql.GraphSubject;
import org.threatshare.arangodb.aql.GraphSubjectStatement;
import org.threatshare.arangodb.aql.OptionalEdgeCollectionStatement;
import org.threatshare.arangodb.aql.ShortestPathOrTraversalStatement;
import org.threatshare.arangodb.aql.ShortestPathStatement;
import org.threatshare.arangodb.aql.Statement;
import org.threatshare.arangodb.aql.TraversalStatement;

public class TraversalStatementImpl extends AbstractOptionalEdgeCollectionStatement
		implements TraversalStatement, ShortestPathOrTraversalStatement, GraphDirectionStatement, GraphSubjectStatement,
		OptionalEdgeCollectionStatement {

	private final String vertexVarName;
	private final String edgeVarName;
	private final String pathVarName;
	private Expression rangeExpr;
	private GraphDirection graphDir;
	private Expression startVertex;
	private GraphSubject graphName;

	public TraversalStatementImpl(Statement prevStatement, String vertexVarName) {
		this(prevStatement, vertexVarName, null, null, null, null, null);
	}

	public TraversalStatementImpl(Statement prevStatement, String vertexVarName, String edgeVarName) {
		this(prevStatement, vertexVarName, edgeVarName, null, null, null, null);
	}

	public TraversalStatementImpl(Statement prevStatement, String vertexVarName, String edgeVarName,
			String pathVarName) {
		this(prevStatement, vertexVarName, edgeVarName, pathVarName, null, null, null);
	}

	public TraversalStatementImpl(Statement prevStatement, String vertexVarName, String edgeVarName, String pathVarName,
			Expression rangeExpr, GraphDirection graphDir, Expression startVertex) {
		super(prevStatement);
		this.vertexVarName = vertexVarName;
		this.edgeVarName = edgeVarName;
		this.pathVarName = pathVarName;
		this.rangeExpr = rangeExpr;
		this.graphDir = graphDir;
		this.startVertex = startVertex;
	}

	@Override
	public GraphDirectionStatement in() {
		return this;
	}

	@Override
	public GraphDirectionVertexStatement in(Expression rangeExpr) {
		this.rangeExpr = rangeExpr;
		return this;
	}

	@Override
	public GraphSubjectStatement any(Expression startVertex) {
		this.graphDir = GraphDirection.ANY;
		this.startVertex = startVertex;
		return this;
	}

	@Override
	public GraphSubjectStatement inbound(Expression startVertex) {
		this.graphDir = GraphDirection.INBOUND;
		this.startVertex = startVertex;
		return this;
	}

	@Override
	public GraphSubjectStatement outbound(Expression startVertex) {
		this.graphDir = GraphDirection.OUTBOUND;
		this.startVertex = startVertex;
		return this;
	}

	@Override
	public ShortestPathStatement any() {
		return new ShortestPathStatementImpl(prevStatement, vertexVarName, edgeVarName, GraphDirection.ANY);
	}

	@Override
	public ShortestPathStatement inbound() {
		return new ShortestPathStatementImpl(prevStatement, vertexVarName, edgeVarName, GraphDirection.INBOUND);
	}

	@Override
	public ShortestPathStatement outbound() {
		return new ShortestPathStatementImpl(prevStatement, vertexVarName, edgeVarName, GraphDirection.OUTBOUND);
	}
	
	@Override
	public BlockStatement graph(GraphSubject graphName) {
		this.graphName = graphName;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("FOR `").append(vertexVarName).append("`");
		if (edgeVarName != null) {
			sb.append(", `").append(edgeVarName).append("`");
		}
		if (pathVarName != null) {
			sb.append(", `").append(pathVarName).append("`");
		}
		sb.append(" IN ");
		if (rangeExpr != null) {
			rangeExpr.toAQL(sb);
			sb.append(" ");
		}
		sb.append(graphDir).append(" ");
		startVertex.toAQL(sb);
		sb.append(" ");

		if (graphName != null) {
			sb.append("GRAPH ");
			graphName.toAQL(sb);
		}

		else if (edgeCollections != null) {
			joinList(sb, edgeCollections, edge -> edge.toAQL(sb));
		}
		return sb;
	}

}
