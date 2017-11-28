package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.ObjectKey;
import org.threatshare.arangodb.aql.ToAQL;

public class ObjectEntry implements ToAQL {

	private final ObjectKey key;
	private final Expression value;

	public ObjectEntry(Expression value) {
		this(null, value);
	}

	public ObjectEntry(ObjectKey key, Expression value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		if (key != null) {
			key.toAQL(sb);
			sb.append(": ");
		}
		value.toAQL(sb);
		return sb;
	}

}
