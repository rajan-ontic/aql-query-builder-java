package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.CollectionName;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.InOrIntoCollectionStatement;
import org.threatshare.arangodb.aql.Statement;
import org.threatshare.arangodb.aql.UpdateOrReplaceWithStatement;

public class UpdateStatement extends AbstractBlockStatement
		implements UpdateOrReplaceWithStatement, InOrIntoCollectionStatement {

	private final Expression updateExpr;
	private Expression withExpr;
	private CollectionName collectionName;

	public UpdateStatement(Statement prevStatement, Expression updateExpr) {
		super(prevStatement);
		this.updateExpr = updateExpr;
	}

	@Override
	public BlockStatement in(CollectionName collectionName) {
		this.collectionName = collectionName;
		return this;
	}

	@Override
	public InOrIntoCollectionStatement with(Expression withExpr) {
		this.withExpr = withExpr;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("UPDATE ");
		updateExpr.toAQL(sb);
		if (withExpr != null) {
			sb.append(" WITH ");
			withExpr.toAQL(sb);
		}
		sb.append(" IN ");
		collectionName.toAQL(sb);
		return sb;
	}

}
