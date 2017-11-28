package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.GraphCollectionName;
import org.threatshare.arangodb.aql.GraphDirection;
import org.threatshare.arangodb.aql.ToAQL;

public class EdgeCollection implements ToAQL {

	private final GraphDirection graphDir;
	private final GraphCollectionName collectionName;

	public EdgeCollection(GraphCollectionName collectionName) {
		this(null, collectionName);
	}

	public EdgeCollection( GraphDirection graphDir, GraphCollectionName collectionName) {
		this.graphDir = graphDir;
		this.collectionName = collectionName;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		if (graphDir != null) {
			sb.append(graphDir).append(" ");
		}
		collectionName.toAQL(sb);
		return sb;
	}

}
