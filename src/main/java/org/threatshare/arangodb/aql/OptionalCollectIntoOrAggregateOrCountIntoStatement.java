package org.threatshare.arangodb.aql;

public interface OptionalCollectIntoOrAggregateOrCountIntoStatement
		extends OptionalKeepStatement, AggregateOrCountIntoStatement, BlockStatement {

	OptionalKeepStatement into(String varName);

	OptionalKeepStatement into(String varName, Expression expression);

}
