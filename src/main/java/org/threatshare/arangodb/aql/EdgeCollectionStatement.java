package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.ref;

public interface EdgeCollectionStatement {

	OptionalEdgeCollectionStatement edgeCollection(GraphCollectionName edgeCollection);

	default OptionalEdgeCollectionStatement edgeCollection(String edgeCollection) {
		return edgeCollection(ref(edgeCollection));
	}

	OptionalEdgeCollectionStatement edgeCollection(GraphDirection graphDir, GraphCollectionName edgeCollection);

	default OptionalEdgeCollectionStatement edgeCollection(GraphDirection graphDir, String edgeCollection) {
		return edgeCollection(graphDir, ref(edgeCollection));
	}

}
