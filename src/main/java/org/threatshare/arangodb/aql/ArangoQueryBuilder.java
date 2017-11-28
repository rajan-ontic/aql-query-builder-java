package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.ref;

import org.threatshare.arangodb.aql.impl.EdgeCollection;
import org.threatshare.arangodb.aql.impl.QueryStartImpl;

public interface ArangoQueryBuilder {

	static QueryStart query() {
		return new QueryStartImpl();
	}

	

	static EdgeCollection edgeCollection(GraphCollectionName collectionName) {
		return new EdgeCollection(collectionName);
	}
	
	static EdgeCollection edgeCollection(String collectionName) {
		return edgeCollection(ref(collectionName));
	}
	
	static EdgeCollection edgeCollection(GraphDirection graphDir, GraphCollectionName collectionName) {
		return new EdgeCollection(graphDir, collectionName);
	}
	
	static EdgeCollection edgeCollection(GraphDirection graphDir, String collectionName) {
		return edgeCollection(graphDir, ref(collectionName));
	}
	
}
