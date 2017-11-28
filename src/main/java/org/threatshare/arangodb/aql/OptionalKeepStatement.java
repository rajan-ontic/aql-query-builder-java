package org.threatshare.arangodb.aql;

public interface OptionalKeepStatement extends BlockStatement {

	BlockStatement keep(String... varNames);
	
}
