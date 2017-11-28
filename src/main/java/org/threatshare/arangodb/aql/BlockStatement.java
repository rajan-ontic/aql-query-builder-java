package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.num;
import static org.threatshare.arangodb.aql.Expressions.ref;

public interface BlockStatement extends Statement {

	InStatement _for(String varName);

	ShortestPathOrTraversalStatement _for(String varName, String edgeVarName);

	TraversalStatement _for(String varName, String edgeVarName, String pathVarName);

	BlockStatement let(String varName, Expression expr);
	
	default BlockStatement let(String varName, String ref) {
		return let(varName, ref(ref));
	}

	default BlockStatement let(String varName, Number value) {
		return let(varName, num(value));
	}
	
	BlockStatement filter(Expression expression);

	AggregateOrCountIntoStatement collect();

	OptionalCollectIntoOrAggregateOrCountIntoStatement collect(String varName, Expression expr);
	
	default OptionalCollectIntoOrAggregateOrCountIntoStatement collect(String varName, String expr) {
		return collect(varName, ref(expr));
	}

	BlockStatement sort(Expression expr);
	
	default BlockStatement sort(String ref) {
		return sort(ref(ref));
	}

	BlockStatement sort(Expression expr, SimpleValue sortDir);

	default BlockStatement sort(String ref, SimpleValue sortDir) {
		return sort(ref(ref), sortDir);
	}

	BlockStatement limit(SimpleValue count);

	default BlockStatement limit(int count) {
		return limit(num(count));
	}

	BlockStatement limit(SimpleValue offset, SimpleValue count);

	default BlockStatement limit(int offset, int count) {
		return limit(num(offset), num(count));
	}

	InOrIntoCollectionStatement remove(Expression expr);

	InOrIntoCollectionStatement insert(Expression expr);

	UpdateOrReplaceWithStatement update(Expression expr);

	UpdateOrReplaceWithStatement replace(Expression expr);

	UpsertInsertStatement upsert(Expression expr);

	QueryEnd _return(Expression expression);

	default QueryEnd _return(String varName) {
		return _return(ref(varName));
	}

	QueryEnd returnDistinct(Expression expression);

	default QueryEnd returnDistinct(String varName) {
		return returnDistinct(ref(varName));
	}

}
