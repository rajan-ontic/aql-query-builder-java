package org.threatshare.arangodb.aql;

import org.threatshare.arangodb.aql.impl.SortDirectionImpl;

public interface SortDirection extends SimpleValue, ToAQL {

	static SortDirection asc() {
		return SortDirectionImpl.ASC;
	}
	
	static SortDirection desc() {
		return SortDirectionImpl.DESC;
	}
	
}
