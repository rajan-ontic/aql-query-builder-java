package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Operator;
import org.threatshare.arangodb.aql.ElseStatement;

abstract class AbstractExpression implements Expression {

	@Override
	public Expression or(Expression expr) {
		return new OrExpression(this, expr);
	}

	@Override
	public Expression and(Expression expr) {
		return new AndExpression(this, expr);
	}

	@Override
	public Expression plus(Expression expr) {
		return new BinaryExpression(Operator.PLUS, this, expr);
	}

	@Override
	public Expression minus(Expression expr) {
		return new BinaryExpression(Operator.MINUS, this, expr);
	}

	@Override
	public Expression mul(Expression expr) {
		return new BinaryExpression(Operator.MUL, this, expr);
	}

	@Override
	public Expression div(Expression expr) {
		return new BinaryExpression(Operator.DIV, this, expr);
	}

	@Override
	public Expression mod(Expression expr) {
		return new BinaryExpression(Operator.MOD, this, expr);
	}

	@Override
	public Expression eq(Expression expr) {
		return new BinaryExpression(Operator.EQ, this, expr);
	}

	@Override
	public Expression neq(Expression expr) {
		return new BinaryExpression(Operator.NEQ, this, expr);
	}

	@Override
	public Expression lt(Expression expr) {
		return new BinaryExpression(Operator.LT, this, expr);
	}

	@Override
	public Expression lte(Expression expr) {
		return new BinaryExpression(Operator.LTE, this, expr);
	}

	@Override
	public Expression gt(Expression expr) {
		return new BinaryExpression(Operator.GT, this, expr);
	}

	@Override
	public Expression gte(Expression expr) {
		return new BinaryExpression(Operator.GTE, this, expr);
	}

	@Override
	public Expression in(Expression expr) {
		return new BinaryExpression(Operator.IN, this, expr);
	}

	@Override
	public Expression notIn(Expression expr) {
		return new BinaryExpression(Operator.NOT_IN, this, expr);
	}

	@Override
	public Expression like(Expression expr) {
		return new BinaryExpression(Operator.LIKE, this, expr);
	}

	@Override
	public Expression regexMatch(Expression expr) {
		return new BinaryExpression(Operator.REGEX_MATCH, this, expr);
	}

	@Override
	public Expression regexNonMatch(Expression expr) {
		return new BinaryExpression(Operator.REGEX_NON_MATCH, this, expr);
	}

	@Override
	public Expression range(Expression expr) {
		return new BinaryExpression(Operator.RANGE, this, expr);
	}

	@Override
	public Expression allEq(Expression expr) {
		return new BinaryExpression(Operator.ALL_EQ, this, expr);
	}

	@Override
	public Expression anyEq(Expression expr) {
		return new BinaryExpression(Operator.ANY_EQ, this, expr);
	}

	@Override
	public Expression noneEq(Expression expr) {
		return new BinaryExpression(Operator.NONE_EQ, this, expr);
	}

	@Override
	public Expression allNeq(Expression expr) {
		return new BinaryExpression(Operator.ALL_NEQ, this, expr);
	}

	@Override
	public Expression anyNeq(Expression expr) {
		return new BinaryExpression(Operator.ANY_NEQ, this, expr);
	}

	@Override
	public Expression noneNeq(Expression expr) {
		return new BinaryExpression(Operator.NONE_NEQ, this, expr);
	}

	@Override
	public Expression allLt(Expression expr) {
		return new BinaryExpression(Operator.ALL_LT, this, expr);
	}

	@Override
	public Expression anyLt(Expression expr) {
		return new BinaryExpression(Operator.ANY_LT, this, expr);
	}

	@Override
	public Expression noneLt(Expression expr) {
		return new BinaryExpression(Operator.NONE_LT, this, expr);
	}

	@Override
	public Expression allLte(Expression expr) {
		return new BinaryExpression(Operator.ALL_LTE, this, expr);
	}

	@Override
	public Expression anyLte(Expression expr) {
		return new BinaryExpression(Operator.ANY_LTE, this, expr);
	}

	@Override
	public Expression noneLte(Expression expr) {
		return new BinaryExpression(Operator.NONE_LTE, this, expr);
	}

	@Override
	public Expression allGt(Expression expr) {
		return new BinaryExpression(Operator.ALL_GT, this, expr);
	}

	@Override
	public Expression anyGt(Expression expr) {
		return new BinaryExpression(Operator.ANY_GT, this, expr);
	}

	@Override
	public Expression noneGt(Expression expr) {
		return new BinaryExpression(Operator.NONE_GT, this, expr);
	}

	@Override
	public Expression allGte(Expression expr) {
		return new BinaryExpression(Operator.ALL_GTE, this, expr);
	}

	@Override
	public Expression anyGte(Expression expr) {
		return new BinaryExpression(Operator.ANY_GTE, this, expr);
	}

	@Override
	public Expression noneGte(Expression expr) {
		return new BinaryExpression(Operator.NONE_GTE, this, expr);
	}

	@Override
	public Expression allIn(Expression expr) {
		return new BinaryExpression(Operator.ALL_IN, this, expr);
	}

	@Override
	public Expression anyIn(Expression expr) {
		return new BinaryExpression(Operator.ANY_IN, this, expr);
	}

	@Override
	public Expression noneIn(Expression expr) {
		return new BinaryExpression(Operator.NONE_IN, this, expr);
	}

	@Override
	public Expression allNotIn(Expression expr) {
		return new BinaryExpression(Operator.ALL_NOT_IN, this, expr);
	}

	@Override
	public Expression anyNotIn(Expression expr) {
		return new BinaryExpression(Operator.ANY_NOT_IN, this, expr);
	}

	@Override
	public Expression noneNotIn(Expression expr) {
		return new BinaryExpression(Operator.NONE_NOT_IN, this, expr);
	}

	@Override
	public ElseStatement then(Expression thenExpr) {
		return new TernaryExpression(this, thenExpr);
	}

}
