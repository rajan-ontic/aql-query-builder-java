package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.bool;
import static org.threatshare.arangodb.aql.Expressions.num;
import static org.threatshare.arangodb.aql.Expressions.quote;

import org.threatshare.arangodb.aql.impl.ObjectEntry;

public interface CompoundValue extends SelectorExpression {

	static ObjectEntry entry(ObjectKey key, Expression expr) {
		return new ObjectEntry(key, expr);
	}

	static ObjectEntry entry(ReferenceExpression ref) {
		return new ObjectEntry(ref);
	}

	static ObjectEntry entry(ObjectKey key, String value) {
		return entry(key, quote(value));
	}

	static ObjectEntry entry(ObjectKey key, Number value) {
		return entry(key, num(value));
	}

	static ObjectEntry entry(ObjectKey key, boolean value) {
		return entry(key, bool(value));
	}

	static ObjectEntry entry(String key, Expression expr) {
		return entry(quote(key), expr);
	}

	static ObjectEntry entry(String key, String value) {
		return entry(quote(key), quote(value));
	}

	static ObjectEntry entry(String key, Number value) {
		return entry(quote(key), num(value));
	}

	static ObjectEntry entry(String key, boolean value) {
		return entry(quote(key), bool(value));
	}

	static ObjectEntry entry(String ref) {
		return entry(Expressions.ref(ref));
	}
	
}
