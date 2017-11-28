package org.threatshare.arangodb.aql.impl;

import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.OptionalArrayFilterStatement;
import org.threatshare.arangodb.aql.SelectorExpression;

abstract class AbstractSelectorExpression extends AbstractExpression implements SelectorExpression {

	private final SelectorExpression prevSelector;
	
	protected AbstractSelectorExpression() {
		this(null);
	}
	
	protected AbstractSelectorExpression(SelectorExpression prevSelector) {
		this.prevSelector = prevSelector;
	}

	@Override
	public SelectorExpression ref(String value) {
		return new ReferenceExpressionImpl(this, value);
	}

	@Override
	public SelectorExpression sel(String value) {
		return new SelectorExpressionImpl(this, value);
	}

	@Override
	public SelectorExpression bindParam(String value) {
		return new BindParameterExpressionImpl(this, value);
	}
	
	@Override
	public SelectorExpression array(Expression expr) {
		return new ArrayExpression(this, expr);
	}

	@Override
	public OptionalArrayFilterStatement array() {
		return array(1);
	}

	@Override
	public OptionalArrayFilterStatement array(int flatteningLevels) {
		return new ArrayOperatorExpression(this, flatteningLevels);
	}
	
	protected boolean hasPrevSelector() {
		return prevSelector != null;
	}

	protected void renderPrevSelector(StringBuilder sb) {
		if (hasPrevSelector()) {
			prevSelector.toAQL(sb);
		}
	}

}
