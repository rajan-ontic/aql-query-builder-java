package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.quote;

public interface ShortestPathStatement {
	
	ShortestPathToStatement shortestPath(Expression startVertex);
	
	default ShortestPathToStatement shortestPath(String startVertex) {
		return shortestPath(quote(startVertex));
	}

}
