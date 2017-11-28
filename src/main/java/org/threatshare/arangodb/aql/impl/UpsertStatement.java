package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.CollectionName;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.InOrIntoCollectionStatement;
import org.threatshare.arangodb.aql.Statement;

public class UpsertStatement extends AbstractBlockStatement implements InOrIntoCollectionStatement {

	private final Expression upsertExpr;
	private final Expression insertExpr;
	private final Expression updateOrReplaceExpr;
	private final boolean isUpdateExpr;
	private CollectionName collectionName;

	public UpsertStatement(Statement prevStatement, Expression upsertExpr, Expression insertExpr, Expression updateOrReplaceExpr,
			boolean isUpdateExpr) {
		super(prevStatement);
		this.upsertExpr = upsertExpr;
		this.insertExpr = insertExpr;
		this.updateOrReplaceExpr = updateOrReplaceExpr;
		this.isUpdateExpr = isUpdateExpr;
	}

	@Override
	public BlockStatement in(CollectionName collectionName) {
		this.collectionName = collectionName;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("UPSERT ");
		upsertExpr.toAQL(sb);
		sb.append(" INSERT ");
		insertExpr.toAQL(sb);
		if (isUpdateExpr) {
			sb.append(" UPDATE ");
		} else {
			sb.append(" REPLACE ");
		}
		updateOrReplaceExpr.toAQL(sb);
		sb.append(" IN ");
		collectionName.toAQL(sb);
		return sb;
	}

}
