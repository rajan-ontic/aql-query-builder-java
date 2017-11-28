package org.threatshare.arangodb.aql;

public interface OptionalCollectIntoStatement extends BlockStatement {

	BlockStatement into(String varName);
	
	BlockStatement into(String varName, Expression expression);
	
}
