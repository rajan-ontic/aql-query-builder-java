package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.CollectionName;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.InOrIntoCollectionStatement;
import org.threatshare.arangodb.aql.Statement;

public class RemoveStatement extends AbstractBlockStatement implements InOrIntoCollectionStatement {

	private final Expression removeExpr;
	private CollectionName collectionName;

	public RemoveStatement(Statement prevStatement, Expression removeExpr) {
		super(prevStatement);
		this.removeExpr = removeExpr;
	}
	
	@Override
	public BlockStatement in(CollectionName collectionName) {
		this.collectionName = collectionName;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("REMOVE ");
		removeExpr.toAQL(sb);
		sb.append(" IN ");
		collectionName.toAQL(sb);
		return sb;
	}

}
