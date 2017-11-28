package org.threatshare.arangodb.aql;

public interface UpsertInsertStatement {

	UpsertUpdateOrReplaceStatement insert(Expression expr);
	
}
