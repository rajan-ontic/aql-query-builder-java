package org.threatshare.arangodb.aql;

public interface SelectorExpression extends Expression {

	SelectorExpression ref(String value);

	SelectorExpression sel(String value);

	SelectorExpression bindParam(String value);
	
	SelectorExpression array(Expression expr);

	OptionalArrayFilterStatement array();

	OptionalArrayFilterStatement array(int flatteningLevels);

}
