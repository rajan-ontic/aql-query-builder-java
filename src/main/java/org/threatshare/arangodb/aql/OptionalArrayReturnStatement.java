package org.threatshare.arangodb.aql;

public interface OptionalArrayReturnStatement extends SelectorExpression {

	SelectorExpression _return(Expression expr); 
	
}
