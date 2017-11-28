package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.BindParameterExpression;
import org.threatshare.arangodb.aql.SelectorExpression;

public class BindParameterExpressionImpl extends AbstractSelectorExpression implements BindParameterExpression {

	private final String value;

	public BindParameterExpressionImpl(String value) {
		this(null, value);
	}
	
	public BindParameterExpressionImpl(SelectorExpression prevSelectorExpr, String value) {
		super(prevSelectorExpr);
		this.value = value;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		if (hasPrevSelector()) {
			renderPrevSelector(sb);
			sb.append(".");
		}
		return sb.append("@").append(value);
	}

}
