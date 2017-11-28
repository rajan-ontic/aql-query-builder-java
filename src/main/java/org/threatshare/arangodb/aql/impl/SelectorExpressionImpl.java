package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.SelectorExpression;

public class SelectorExpressionImpl extends AbstractSelectorExpression {

	private final String value;

	public SelectorExpressionImpl(String value) {
		this(null, value);
	}
	
	public SelectorExpressionImpl(SelectorExpression prevSelector, String value) {
		super(prevSelector);
		this.value = value;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		if (hasPrevSelector()) {
			renderPrevSelector(sb);
			sb.append(".");
		}
		return sb.append(value);
	}

}
