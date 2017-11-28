package org.threatshare.arangodb.aql;

import static org.threatshare.arangodb.aql.Expressions.ref;

public interface GraphDirectionVertexStatement {

	GraphSubjectStatement any(Expression startVertex);
	
	default GraphSubjectStatement any(String startVertex) {
		return any(ref(startVertex));
	}

	GraphSubjectStatement inbound(Expression startVertex);
	
	default GraphSubjectStatement inbound(String startVertex) {
		return inbound(ref(startVertex));
	}

	GraphSubjectStatement outbound(Expression startVertex);
	
	default GraphSubjectStatement outbound(String startVertex) {
		return outbound(ref(startVertex));
	}

}
