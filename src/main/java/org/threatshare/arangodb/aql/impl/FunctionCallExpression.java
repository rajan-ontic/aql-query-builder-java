package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinArray;

import org.threatshare.arangodb.aql.Expression;

public class FunctionCallExpression extends AbstractSelectorExpression {

	private final String funcName;
	private final Expression[] args;

	public FunctionCallExpression(String funcName, Expression[] args) {
		this.funcName = funcName;
		this.args = args;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append(funcName).append("(");
		joinArray(sb, args, arg -> arg.toAQL(sb));
		sb.append(")");
		return sb;
	}

}
