package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.ref;

public interface InOrIntoCollectionStatement {
	
	BlockStatement in(CollectionName collectionName);

	default BlockStatement into(CollectionName collectionName) {
		return in(collectionName);
	}
	
	default BlockStatement in(String collectionName) {
		return in(ref(collectionName));
	}
	
	default BlockStatement into(String collectionName) {
		return in(ref(collectionName));
	}
	
}
