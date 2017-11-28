package org.threatshare.arangodb.aql;

public interface OptionalArrayFilterStatement extends OptionalArrayLimitStatement {

	OptionalArrayLimitStatement filter(Expression expr);
	
}
