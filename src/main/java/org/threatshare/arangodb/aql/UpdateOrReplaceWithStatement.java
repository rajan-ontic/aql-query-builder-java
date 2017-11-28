package org.threatshare.arangodb.aql;

public interface UpdateOrReplaceWithStatement extends InOrIntoCollectionStatement {

	InOrIntoCollectionStatement with(Expression expr);
	
}
