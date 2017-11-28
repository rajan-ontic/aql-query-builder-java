package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.quote;

public interface ShortestPathToStatement {
	
	GraphSubjectStatement to(Expression targetVertex);
	
	default GraphSubjectStatement to(String targetVertex) {
		return to(quote(targetVertex));
	}

}
