package org.threatshare.arangodb.aql;

public interface OptionalArrayLimitStatement extends OptionalArrayReturnStatement {
	
	OptionalArrayReturnStatement limit(Expression offsetExpr);
	
	OptionalArrayReturnStatement limit(Expression offsetExpr, Expression countExpr);

}
