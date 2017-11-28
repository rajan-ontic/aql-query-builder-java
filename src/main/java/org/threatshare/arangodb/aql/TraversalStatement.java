package org.threatshare.arangodb.aql;

public interface TraversalStatement {

	GraphDirectionVertexStatement in();

	GraphDirectionVertexStatement in(Expression rangeExpr);
	
	default GraphDirectionVertexStatement in(int min, int max) {
		return in(Expressions.range(min, max));
	}

}
