package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.Operator;

public class NAryExpression extends AbstractExpression {

	protected final List<Expression> expressions = new LinkedList<>();
	private final Operator operator;

	public NAryExpression(Operator op, Expression firstExpr, Expression secondExpr,
			Expression... expressions) {
		this.operator = op;
		this.expressions.add(firstExpr);
		this.expressions.add(secondExpr);
		Collections.addAll(this.expressions, expressions);
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		sb.append("(");
		String delimiter = " " + operator.getValue() + " ";
		joinList(delimiter, sb, expressions, expr -> expr.toAQL(sb));
		sb.append(")");
		return sb;
	}

}
