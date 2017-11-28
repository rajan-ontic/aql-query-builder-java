package org.threatshare.arangodb.aql;

public enum GraphDirection {

	OUTBOUND, INBOUND, ANY;

	public static GraphDirection any() {
		return GraphDirection.ANY;
	}

	public static GraphDirection inbound() {
		return GraphDirection.INBOUND;
	}

	public static GraphDirection outbound() {
		return GraphDirection.OUTBOUND;
	}

}
