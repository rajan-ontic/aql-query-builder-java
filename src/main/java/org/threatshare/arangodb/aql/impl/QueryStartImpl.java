package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.QueryStart;
import org.threatshare.arangodb.aql.WithCollectionName;

public class QueryStartImpl extends AbstractBlockStatement implements QueryStart {

	@Override
	public BlockStatement with(WithCollectionName... collections) {
		return new WithStatement(collections);
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		return sb;
	}

}
