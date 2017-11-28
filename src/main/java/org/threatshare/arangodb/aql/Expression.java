package org.threatshare.arangodb.aql;

public interface Expression extends ToAQL {

	Expression or(Expression expr);

	Expression and(Expression expr);

	Expression plus(Expression expr);

	Expression minus(Expression expr);

	Expression mul(Expression expr);

	Expression div(Expression expr);

	Expression mod(Expression expr);

	Expression eq(Expression expr);
	
	Expression allEq(Expression expr);
	
	Expression anyEq(Expression expr);
	
	Expression noneEq(Expression expr);

	Expression neq(Expression expr);
	
	Expression allNeq(Expression expr);
	
	Expression anyNeq(Expression expr);
	
	Expression noneNeq(Expression expr);

	Expression lt(Expression expr);
	
	Expression allLt(Expression expr);
	
	Expression anyLt(Expression expr);
	
	Expression noneLt(Expression expr);

	Expression lte(Expression expr);
	
	Expression allLte(Expression expr);
	
	Expression anyLte(Expression expr);
	
	Expression noneLte(Expression expr);

	Expression gt(Expression expr);
	
	Expression allGt(Expression expr);
	
	Expression anyGt(Expression expr);
	
	Expression noneGt(Expression expr);

	Expression gte(Expression expr);
	
	Expression allGte(Expression expr);
	
	Expression anyGte(Expression expr);
	
	Expression noneGte(Expression expr);

	Expression in(Expression expr);
	
	Expression allIn(Expression expr);
	
	Expression anyIn(Expression expr);
	
	Expression noneIn(Expression expr);

	Expression notIn(Expression expr);
	
	Expression allNotIn(Expression expr);
	
	Expression anyNotIn(Expression expr);
	
	Expression noneNotIn(Expression expr);

	Expression like(Expression expr);

	Expression regexMatch(Expression expr);

	Expression regexNonMatch(Expression expr);
	
	Expression range(Expression expr);
	
	ElseStatement then(Expression expr);

}
