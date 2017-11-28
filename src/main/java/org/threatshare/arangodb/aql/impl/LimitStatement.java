package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.SimpleValue;
import org.threatshare.arangodb.aql.Statement;

public class LimitStatement extends AbstractBlockStatement {

	private final SimpleValue offsetValue;
	private final SimpleValue countValue;

	public LimitStatement(Statement prevStatement, SimpleValue countValue) {
		this(prevStatement, null, countValue);
	}
	
	public LimitStatement(Statement prevStatement, SimpleValue offsetValue, SimpleValue countValue) {
		super(prevStatement);
		this.offsetValue = offsetValue;
		this.countValue = countValue;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("LIMIT ");
		if (offsetValue != null) {
			offsetValue.toAQL(sb);
			sb.append(", ");
		}
		countValue.toAQL(sb);
		return sb;
	}

}
