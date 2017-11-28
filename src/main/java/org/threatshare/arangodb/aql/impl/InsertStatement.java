package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.CollectionName;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.InOrIntoCollectionStatement;
import org.threatshare.arangodb.aql.Statement;

public class InsertStatement extends AbstractBlockStatement implements InOrIntoCollectionStatement {

	private final Expression insertExpr;
	private CollectionName collectionName;

	public InsertStatement(Statement prevStatement, Expression insertExpr) {
		super(prevStatement);
		this.insertExpr = insertExpr;
	}

	@Override
	public BlockStatement in(CollectionName collectionName) {
		this.collectionName = collectionName;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("INSERT ");
		insertExpr.toAQL(sb);
		sb.append(" IN ");
		collectionName.toAQL(sb);
		return sb;
	}

}
