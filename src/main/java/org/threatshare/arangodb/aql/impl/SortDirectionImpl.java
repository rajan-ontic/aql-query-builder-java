package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.SortDirection;

public enum SortDirectionImpl implements SortDirection {
	
	ASC, DESC;

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append(this.name());
		return sb;
	}
	
}
