package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.CollectionName;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.InOrIntoCollectionStatement;
import org.threatshare.arangodb.aql.Statement;
import org.threatshare.arangodb.aql.UpdateOrReplaceWithStatement;

public class ReplaceStatement extends AbstractBlockStatement
		implements UpdateOrReplaceWithStatement, InOrIntoCollectionStatement {

	private final Expression replaceExpr;
	private Expression withExpr;
	private CollectionName collectionName;

	public ReplaceStatement(Statement prevStatement, Expression replaceExpr) {
		super(prevStatement);
		this.replaceExpr = replaceExpr;
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
		sb.append("REPLACE ");
		replaceExpr.toAQL(sb);
		if (withExpr != null) {
			sb.append(" WITH ");
			withExpr.toAQL(sb);
		}
		sb.append(" IN ");
		collectionName.toAQL(sb);
		return sb;
	}

}
