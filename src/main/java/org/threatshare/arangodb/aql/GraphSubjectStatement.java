package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.quote;

public interface GraphSubjectStatement extends EdgeCollectionStatement {

	BlockStatement graph(GraphSubject graphName);
	
	default BlockStatement graph(String graphName) {
		return graph(quote(graphName));
	}

}
