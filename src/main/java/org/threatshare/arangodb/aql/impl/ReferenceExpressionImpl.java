package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.ReferenceExpression;
import org.threatshare.arangodb.aql.SelectorExpression;

public class ReferenceExpressionImpl extends AbstractSelectorExpression implements ReferenceExpression {

	private final String value;

	public ReferenceExpressionImpl(String value) {
		this(null, value);
	}
	
	public ReferenceExpressionImpl(SelectorExpression prevSelectorExpr, String value) {
		super(prevSelectorExpr);
		this.value = value;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		if (hasPrevSelector()) {
			renderPrevSelector(sb);
			sb.append(".");
		}
		sb.append("`").append(value).append("`");
		return sb;
	}

}
