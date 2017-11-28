package org.threatshare.arangodb.aql;

public interface OptionalWithStatement extends BlockStatement {

	BlockStatement with(WithCollectionName... collections);
	
}
