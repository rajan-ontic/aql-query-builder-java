package org.threatshare.arangodb.aql.impl;

import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinArray;
import static org.threatshare.arangodb.aql.util.StringBuilderJoiner.joinList;

import java.util.LinkedList;
import java.util.List;

import org.threatshare.arangodb.aql.BlockStatement;
import org.threatshare.arangodb.aql.Expression;
import org.threatshare.arangodb.aql.OptionalCollectIntoOrAggregateOrCountIntoStatement;
import org.threatshare.arangodb.aql.OptionalCollectIntoStatement;
import org.threatshare.arangodb.aql.OptionalKeepStatement;
import org.threatshare.arangodb.aql.Statement;

public class CollectStatement extends AbstractBlockStatement
		implements OptionalCollectIntoOrAggregateOrCountIntoStatement, OptionalCollectIntoStatement {

	private final List<Assignment> collectAssignments = new LinkedList<>();
	private String countIntoVarName;
	private String collectIntoVarName;
	private Expression collectIntoExpr;
	private List<Assignment> aggregateAssignments;
	private String[] keepVarNames;

	public CollectStatement(Statement prevStatement) {
		this(prevStatement, null, null);
	}

	public CollectStatement(Statement prevStatement, String collectVarName, Expression collectExpr) {
		super(prevStatement);
		collect(collectVarName, collectExpr);
	}

	@Override
	public OptionalCollectIntoOrAggregateOrCountIntoStatement collect(String varName, Expression expr) {
		collectAssignments.add(new Assignment(varName, expr));
		return this;
	}

	@Override
	public BlockStatement keep(String... varNames) {
		keepVarNames = varNames;
		return this;
	}

	@Override
	public BlockStatement withCountInto(String varName) {
		countIntoVarName = varName;
		return this;
	}

	@Override
	public OptionalCollectIntoStatement aggregate(String varName, Expression expr) {
		prepareAggregateAssignments().add(new Assignment(varName, expr));
		return this;
	}

	@Override
	public OptionalKeepStatement into(String varName) {
		collectIntoVarName = varName;
		return this;
	}

	@Override
	public OptionalKeepStatement into(String varName, Expression expr) {
		collectIntoVarName = varName;
		collectIntoExpr = expr;
		return this;
	}

	@Override
	public StringBuilder toAQL(StringBuilder sb) {
		renderPrevStatement(sb);
		sb.append("COLLECT ");

		joinList(sb, collectAssignments, ass -> ass.toAQL(sb));

		if (aggregateAssignments != null) {
			sb.append(" AGGREGATE ");
			joinList(sb, aggregateAssignments, ass -> ass.toAQL(sb));
		}

		if (countIntoVarName != null) {
			sb.append(" WITH COUNT INTO `").append(countIntoVarName).append("`");
		}

		if (collectIntoVarName != null) {
			sb.append(" INTO `").append(collectIntoVarName).append("`");

			if (collectIntoExpr != null) {
				sb.append(" = ");
				collectIntoExpr.toAQL(sb);
			}
		}

		if (keepVarNames != null) {
			sb.append(" KEEP ");
			joinArray(sb, keepVarNames, value -> sb.append("`").append(value).append("`"));
		}

		return sb;
	}

	private List<Assignment> prepareAggregateAssignments() {
		if (aggregateAssignments == null) {
			aggregateAssignments = new LinkedList<>();
		}
		return aggregateAssignments;
	}

}
