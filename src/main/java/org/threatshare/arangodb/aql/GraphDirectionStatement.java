package org.threatshare.arangodb.aql;

public interface GraphDirectionStatement extends GraphDirectionVertexStatement {

	ShortestPathStatement any();

	ShortestPathStatement inbound();

	ShortestPathStatement outbound();
	
}
