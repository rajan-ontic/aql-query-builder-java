package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.AggregateOrCountIntoStatement;
import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.OptionalCollectIntoOrAggregateOrCountIntoStatement;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.InOrIntoCollectionStatement;
import org.threatshare.arangodb.aql.InStatement;
import org.threatshare.arangodb.aql.ShortestPathOrTraversalStatement;
import org.threatshare.arangodb.aql.SimpleValue;
import org.threatshare.arangodb.aql.Statement;
import org.threatshare.arangodb.aql.TraversalStatement;
import org.threatshare.arangodb.aql.UpdateOrReplaceWithStatement;
import org.threatshare.arangodb.aql.UpsertInsertStatement;
import org.threatshare.arangodb.aql.UpsertUpdateOrReplaceStatement;

abstract class AbstractBlockStatement extends AbstractStatement implements BlockStatement {

	public AbstractBlockStatement() {
		this(null);
	}

	public AbstractBlockStatement(Statement prevStatement) {
		super(prevStatement);
	}

	@Override
	public InStatement _for(String varName) {
		return new ForStatement(this, varName);
	}

	@Override
	public ShortestPathOrTraversalStatement _for(String vertexVarName, String edgeVarName) {
		return new TraversalStatementImpl(this, vertexVarName, edgeVarName);
	}

	@Override
	public TraversalStatement _for(String vertexVarName, String edgeVarName, String pathVarName) {
		return new TraversalStatementImpl(this, vertexVarName, edgeVarName, pathVarName);
	}

	@Override
	public ReturnStatement _return(Expression expr) {
		return new ReturnStatement(this, expr);
	}

	@Override
	public ReturnStatement returnDistinct(Expression expr) {
		return new ReturnStatement(this, true, expr);
	}

	@Override
	public InOrIntoCollectionStatement remove(Expression expr) {
		return new RemoveStatement(AbstractBlockStatement.this, expr);
	}

	@Override
	public InOrIntoCollectionStatement insert(Expression expr) {
		return new InsertStatement(AbstractBlockStatement.this, expr);
	}

	@Override
	public UpdateOrReplaceWithStatement update(Expression updateEexpr) {
		return new UpdateStatement(AbstractBlockStatement.this, updateEexpr);
	}

	@Override
	public UpdateOrReplaceWithStatement replace(Expression replaceExpr) {
		return new ReplaceStatement(AbstractBlockStatement.this, replaceExpr);
	}

	@Override
	public UpsertInsertStatement upsert(Expression upsertExpr) {
		return insertExpr -> new UpsertUpdateOrReplaceStatement() {

			@Override
			public InOrIntoCollectionStatement update(Expression updateExpr) {
				return new UpsertStatement(AbstractBlockStatement.this, upsertExpr, insertExpr, updateExpr, true);
			}

			@Override
			public InOrIntoCollectionStatement replace(Expression replaceExpr) {
				return new UpsertStatement(AbstractBlockStatement.this, upsertExpr, insertExpr, replaceExpr, false);
			}
		};
	}
	
	@Override
	public BlockStatement let(String varName, Expression expr) {
		return new LetStatement(this, varName, expr);
	}

	@Override
	public BlockStatement filter(Expression expr) {
		return new FilterStatement(this, expr);
	}

	@Override
	public AggregateOrCountIntoStatement collect() {
		return new CollectStatement(this);
	}

	@Override
	public OptionalCollectIntoOrAggregateOrCountIntoStatement collect(String varName, Expression expr) {
		return new CollectStatement(this, varName, expr);
	}

	@Override
	public BlockStatement sort(Expression expr) {
		return new SortStatement(this, expr);
	}
	
	@Override
	public BlockStatement sort(Expression expr, SimpleValue sortDir) {
		return new SortStatement(this, expr, sortDir);
	}

	@Override
	public BlockStatement limit(SimpleValue count) {
		return new LimitStatement(this, count);
	}

	@Override
	public BlockStatement limit(SimpleValue offset, SimpleValue count) {
		return new LimitStatement(this, offset, count);
	}

}
