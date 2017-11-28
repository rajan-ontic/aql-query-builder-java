package org.threatshare.arangodb.aql;

public enum Operator {

	OR("||"),
	AND("&&"),
	NOT("!"),
	EQ("=="), 
	ALL_EQ("ALL =="), 
	ANY_EQ("ANY =="), 
	NONE_EQ("NONE =="), 
	NEQ("!="), 
	ALL_NEQ("ALL !="), 
	ANY_NEQ("ANY !="), 
	NONE_NEQ("NONE !="), 
	GT(">"), 
	ALL_GT("ALL >"), 
	ANY_GT("ANY >"), 
	NONE_GT("NONE >"), 
	GTE(">="), 
	ALL_GTE("ALL >="), 
	ANY_GTE("ANY >="), 
	NONE_GTE("NONE >="), 
	LT("<"), 
	ALL_LT("ALL <"), 
	ANY_LT("ANY <"), 
	NONE_LT("NONE <"), 
	LTE("<="), 
	ALL_LTE("ALL <="), 
	ANY_LTE("ANY <="), 
	NONE_LTE("NONE <="), 
	PLUS("+"), 
	MINUS("-"), 
	MUL("*"), 
	DIV("/"), 
	MOD("%"), 
	LIKE("LIKE"), 
	REGEX_MATCH("=~"), 
	REGEX_NON_MATCH("!~"), 
	IN("IN"), 
	ALL_IN("ALL IN"), 
	ANY_IN("ANY IN"), 
	NONE_IN("NONE IN"), 
	NOT_IN("NOT IN"), 
	ALL_NOT_IN("ALL NOT IN"), 
	ANY_NOT_IN("ANY NOT IN"),
	NONE_NOT_IN("NONE NOT IN"),
	RANGE("..");

	private final String value;

	Operator(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
