package org.threatshare.arangodb.aql;

import org.threatshare.arangodb.aql.impl.AndExpression;
import org.threatshare.arangodb.aql.impl.ArrayCompoundValueImpl;
import org.threatshare.arangodb.aql.impl.BinaryExpression;
import org.threatshare.arangodb.aql.impl.BindParameterExpressionImpl;
import org.threatshare.arangodb.aql.impl.BooleanExpressionImpl;
import org.threatshare.arangodb.aql.impl.FunctionCallExpression;
import org.threatshare.arangodb.aql.impl.NAryExpression;
import org.threatshare.arangodb.aql.impl.NullExpressionImpl;
import org.threatshare.arangodb.aql.impl.NumberExpressionImpl;
import org.threatshare.arangodb.aql.impl.ObjectCompoundValueImpl;
import org.threatshare.arangodb.aql.impl.ObjectEntry;
import org.threatshare.arangodb.aql.impl.OrExpression;
import org.threatshare.arangodb.aql.impl.QuotedStringExpressionImpl;
import org.threatshare.arangodb.aql.impl.ReferenceExpressionImpl;
import org.threatshare.arangodb.aql.impl.SelectorExpressionImpl;
import org.threatshare.arangodb.aql.impl.SubExpression;
import org.threatshare.arangodb.aql.impl.SubQueryExpression;
import org.threatshare.arangodb.aql.impl.TernaryExpression;
import org.threatshare.arangodb.aql.impl.UnaryExpression;

public interface Expressions {

	static Expression plus(Expression expr) {
		return new UnaryExpression(Operator.PLUS, expr);
	}
	
	static Expression plus(String ref) {
		return plus(ref(ref));
	}
	
	static Expression plus(Number num) {
		return plus(num(num));
	}

	static Expression minus(Expression expr) {
		return new UnaryExpression(Operator.MINUS, expr);
	}
	
	static Expression minus(String ref) {
		return minus(ref(ref));
	}
	
	static Expression minus(Number num) {
		return minus(num(num));
	}

	static Expression not(Expression expr) {
		return new UnaryExpression(Operator.NOT, expr);
	}

	static Expression _if(Expression condition, Expression then, Expression otherwise) {
		return new TernaryExpression(condition, then, otherwise);
	}

	static Expression _if(Expression condition, Expression otherwise) {
		return new TernaryExpression(condition, condition, otherwise);
	}

	static QuotedStringExpression quote(String value) {
		return new QuotedStringExpressionImpl(value);
	}

	static NumberExpression num(Number value) {
		return new NumberExpressionImpl(value);
	}

	static NullExpression _null() {
		return new NullExpressionImpl();
	}

	static BooleanExpression bool(boolean value) {
		return new BooleanExpressionImpl(value);
	}

	static BooleanExpression _true() {
		return new BooleanExpressionImpl(true);
	}

	static BooleanExpression _false() {
		return new BooleanExpressionImpl(false);
	}

	static ReferenceExpression ref(String value) {
		return new ReferenceExpressionImpl(value);
	}

	static SelectorExpression sel(String value) {
		return new SelectorExpressionImpl(value);
	}

	static BindParameterExpression bindParam(String value) {
		return new BindParameterExpressionImpl(value);
	}

	static ObjectCompoundValue object() {
		return new ObjectCompoundValueImpl();
	}

	static ObjectCompoundValue object(ObjectEntry... entries) {
		return new ObjectCompoundValueImpl(entries);
	}

	static ArrayCompoundValue array() {
		return new ArrayCompoundValueImpl();
	}

	static ArrayCompoundValue array(Expression... exprs) {
		return new ArrayCompoundValueImpl(exprs);
	}

	static SelectorExpression func(String funcName, Expression... args) {
		return new FunctionCallExpression(funcName, args);
	}

	static SelectorExpression subQuery(Statement stmt) {
		return new SubQueryExpression(stmt);
	}

	static SelectorExpression subQuery(QueryEnd stmt) {
		return new SubQueryExpression(stmt);
	}

	static SelectorExpression subExpr(Expression expr) {
		return new SubExpression(expr);
	}

	static Expression and(Expression expr1, Expression expr2, Expression... exprs) {
		return new AndExpression(expr1, expr2, exprs);
	}

	static Expression or(Expression expr1, Expression expr2, Expression... exprs) {
		return new OrExpression(expr1, expr2, exprs);
	}

	static Expression plus(Expression expr1, Expression expr2, Expression... exprs) {
		return new NAryExpression(Operator.PLUS, expr1, expr2, exprs);
	}

	static Expression minus(Expression expr1, Expression expr2, Expression... exprs) {
		return new NAryExpression(Operator.MINUS, expr1, expr2, exprs);
	}

	static Expression mul(Expression expr1, Expression expr2, Expression... exprs) {
		return new NAryExpression(Operator.MUL, expr1, expr2, exprs);
	}

	static Expression div(Expression expr1, Expression expr2, Expression... exprs) {
		return new NAryExpression(Operator.DIV, expr1, expr2, exprs);
	}

	static Expression mod(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.MOD, expr1, expr2);
	}

	static Expression eq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.EQ, expr1, expr2);
	}

	static Expression neq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NEQ, expr1, expr2);
	}

	static Expression lt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.LT, expr1, expr2);
	}

	static Expression lte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.LTE, expr1, expr2);
	}

	static Expression gt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.GT, expr1, expr2);
	}

	static Expression gte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.GTE, expr1, expr2);
	}

	static Expression in(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.IN, expr1, expr2);
	}

	static Expression notIn(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NOT_IN, expr1, expr2);
	}

	static Expression like(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.LIKE, expr1, expr2);
	}

	static Expression regexMatch(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.REGEX_MATCH, expr1, expr2);
	}

	static Expression regexNonMatch(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.REGEX_NON_MATCH, expr1, expr2);
	}

	static Expression range(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.RANGE, expr1, expr2);
	}
	
	static Expression range(int min, int max) {
		return range(num(min), num(max));
	}

	static Expression allEq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_EQ, expr1, expr2);
	}

	static Expression anyEq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_EQ, expr1, expr2);
	}

	static Expression noneEq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_EQ, expr1, expr2);
	}

	static Expression allNeq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_NEQ, expr1, expr2);
	}

	static Expression anyNeq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_NEQ, expr1, expr2);
	}

	static Expression noneNeq(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_NEQ, expr1, expr2);
	}

	static Expression allLt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_LT, expr1, expr2);
	}

	static Expression anyLt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_LT, expr1, expr2);
	}

	static Expression noneLt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_LT, expr1, expr2);
	}

	static Expression allLte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_LTE, expr1, expr2);
	}

	static Expression anyLte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_LTE, expr1, expr2);
	}

	static Expression noneLte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_LTE, expr1, expr2);
	}

	static Expression allGt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_GT, expr1, expr2);
	}

	static Expression anyGt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_GT, expr1, expr2);
	}

	static Expression noneGt(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_GT, expr1, expr2);
	}

	static Expression allGte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_GTE, expr1, expr2);
	}

	static Expression anyGte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_GTE, expr1, expr2);
	}

	static Expression noneGte(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_GTE, expr1, expr2);
	}

	static Expression allIn(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_IN, expr1, expr2);
	}

	static Expression anyIn(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_IN, expr1, expr2);
	}

	static Expression noneIn(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_IN, expr1, expr2);
	}

	static Expression allNotIn(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ALL_NOT_IN, expr1, expr2);
	}

	static Expression anyNotIn(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.ANY_NOT_IN, expr1, expr2);
	}

	static Expression noneNotIn(Expression expr1, Expression expr2) {
		return new BinaryExpression(Operator.NONE_NOT_IN, expr1, expr2);
	}

}
