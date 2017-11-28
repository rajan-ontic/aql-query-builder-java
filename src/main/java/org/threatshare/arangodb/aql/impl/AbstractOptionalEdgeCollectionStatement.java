package org.threatshare.arangodb.aql.impl;

import java.util.LinkedList;
import java.util.List;

import org.threatshare.arangodb.aql.GraphCollectionName;
import org.threatshare.arangodb.aql.GraphDirection;
import org.threatshare.arangodb.aql.OptionalEdgeCollectionStatement;
import org.threatshare.arangodb.aql.Statement;

abstract class AbstractOptionalEdgeCollectionStatement extends AbstractBlockStatement
		implements OptionalEdgeCollectionStatement {

	protected List<EdgeCollection> edgeCollections;
	
	protected AbstractOptionalEdgeCollectionStatement(Statement prevStatement) {
		super(prevStatement);
	}

	@Override
	public OptionalEdgeCollectionStatement edgeCollection(GraphCollectionName edgeCollection) {
		prepareEdgeCollection().add(new EdgeCollection(edgeCollection));
		return this;
	}

	@Override
	public OptionalEdgeCollectionStatement edgeCollection(GraphDirection graphDir, GraphCollectionName edgeCollection) {
		prepareEdgeCollection().add(new EdgeCollection(graphDir, edgeCollection));
		return this;
	}

	protected List<EdgeCollection> prepareEdgeCollection() {
		if (edgeCollections == null) {
			edgeCollections = new LinkedList<>();
		}
		return edgeCollections;
	}

}
