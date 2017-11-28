package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.bool;
import static org.threatshare.arangodb.aql.Expressions.num;
import static org.threatshare.arangodb.aql.Expressions.quote;

public interface ArrayCompoundValue extends CompoundValue {
	
	ArrayCompoundValue entry(Expression expr);
	
	default ArrayCompoundValue entry(String value) {
		return entry(quote(value));
	}
	
	default ArrayCompoundValue entry(Number value) {
		return entry(num(value));
	}
	
	default ArrayCompoundValue entry(boolean value) {
		return entry(bool(value));
	}
	
}
