package org.threatshare.arangodb.aql;

public interface AggregateOrCountIntoStatement {

	BlockStatement withCountInto(String varName);

	OptionalCollectIntoStatement aggregate(String varName, Expression expr);

}
