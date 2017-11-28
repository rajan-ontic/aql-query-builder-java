package org.threatshare.arangodb.aql;

public interface UpsertUpdateOrReplaceStatement {

	InOrIntoCollectionStatement update(Expression expr);
	
	InOrIntoCollectionStatement replace(Expression expr);
	
}
