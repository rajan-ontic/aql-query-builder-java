package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.GraphDirection;
import org.threatshare.arangodb.aql.GraphSubject;
import org.threatshare.arangodb.aql.GraphSubjectStatement;
import org.threatshare.arangodb.aql.OptionalEdgeCollectionStatement;
import org.threatshare.arangodb.aql.ShortestPathStatement;
import org.threatshare.arangodb.aql.ShortestPathToStatement;
import org.threatshare.arangodb.aql.Statement;

public class ShortestPathStatementImpl extends AbstractOptionalEdgeCollectionStatement implements ShortestPathStatement,
		ShortestPathToStatement, GraphSubjectStatement, OptionalEdgeCollectionStatement {

	private final String vertexVarName;
	private final String edgeVarName;
	private final GraphDirection graphDir;
	private Expression startVertex;
	private Expression targetVertex;
	private GraphSubject graphName;

	public ShortestPathStatementImpl(Statement prevStatement, String vertexVarName, String edgeVarName,
			GraphDirection graphDir) {
		super(prevStatement);
		this.vertexVarName = vertexVarName;
		this.edgeVarName = edgeVarName;
		this.graphDir = graphDir;
	}

	@Override
	public ShortestPathToStatement shortestPath(Expression startVertex) {
		this.startVertex = startVertex;
		return this;
	}

	@Override
	public GraphSubjectStatement to(Expression targetVertex) {
		this.targetVertex = targetVertex;
		return this;
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
		sb.append(" IN ").append(graphDir).append(" SHORTEST_PATH ");
		startVertex.toAQL(sb);
		sb.append(" TO ");
		targetVertex.toAQL(sb);
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
