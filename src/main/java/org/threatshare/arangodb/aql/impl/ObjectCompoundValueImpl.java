package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.ObjectCompoundValue;
import org.threatshare.arangodb.aql.ObjectKey;
import org.threatshare.arangodb.aql.ReferenceExpression;

public class ObjectCompoundValueImpl extends AbstractSelectorExpression implements ObjectCompoundValue {

	private final List<ObjectEntry> entries = new LinkedList<>();

	public ObjectCompoundValueImpl(ObjectEntry... entries) {
		Collections.addAll(this.entries, entries);
	}

	@Override
	public ObjectCompoundValue entry(ObjectKey key, Expression expr) {
		entries.add(new ObjectEntry(key, expr));
		return this;
	}

	@Override
	public ObjectCompoundValue entry(ReferenceExpression expr) {
		entries.add(new ObjectEntry(expr));
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("{");
		joinList(sb, entries, value -> value.toAQL(sb));
		sb.append("}");
		return sb;
	}

}
