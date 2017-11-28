package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.ref;

public interface InStatement extends ShortestPathOrTraversalStatement {

	OptionalGraphDirectionVertexStatement in(Expression expr);
	
	default OptionalGraphDirectionVertexStatement in(String collection) {
		return in(ref(collection));
	}

}
