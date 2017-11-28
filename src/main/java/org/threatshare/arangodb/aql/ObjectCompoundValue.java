package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.bool;
import static org.threatshare.arangodb.aql.Expressions.num;
import static org.threatshare.arangodb.aql.Expressions.quote;

public interface ObjectCompoundValue extends CompoundValue {

	ObjectCompoundValue entry(ObjectKey key, Expression expr);
	
	ObjectCompoundValue entry(ReferenceExpression expr);

	default ObjectCompoundValue entry(String key, Expression expr) {
		return entry(quote(key), expr);
	}

	default ObjectCompoundValue entry(String key, String value) {
		return entry(quote(key), quote(value));
	}

	default ObjectCompoundValue entry(String key, Number value) {
		return entry(quote(key), num(value));
	}

	default ObjectCompoundValue entry(String key, boolean value) {
		return entry(quote(key), bool(value));
	}

	default ObjectCompoundValue entry(ObjectKey key, String value) {
		return entry(key, quote(value));
	}

	default ObjectCompoundValue entry(ObjectKey key, Number value) {
		return entry(key, num(value));
	}

	default ObjectCompoundValue entry(ObjectKey key, boolean value) {
		return entry(key, bool(value));
	}

	default ObjectCompoundValue entry(String ref) {
		return entry(Expressions.ref(ref));
	}

}
