package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinArray;

import org.threatshare.arangodb.aql.WithCollectionName;

public class WithStatement extends AbstractBlockStatement {

	private final WithCollectionName[] collections;

	public WithStatement(WithCollectionName[] collections) {
		this.collections = collections;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("WITH ");
		joinArray(sb, collections, elem -> elem.toAQL(sb));
		return sb;
	}

}
