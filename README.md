# ArangoDB AQL Query Builder

A prototype version of an AQL Query Builder for Java 8. This project is not affiliated or supported by ArangoDB.

This project is a byproduct of another project, so any help to improve the query builder is welcomed. If you have any improvements, suggestions, ideas or want to contribute, just open an issue or send a PR.

## Todo
- add options
- add more default methods to reduce typing effort (`plus("x", 5)` instead of `plus(ref("x"), num(5))`),
  though not always possible due to static typing of Java
- better documentation
- add tests

## Getting Started
Download the project from this repository and import as Maven project in your IDE. At this time there is no binary package or Maven repository integration.

With the following static imports you're able to write all sorts of AQL queries:
```java
import static org.threatshare.arangodb.aql.ArangoQueryBuilder.*;
import static org.threatshare.arangodb.aql.Expressions.*;
import static org.threatshare.arangodb.aql.SortDirection.*;
import static org.threatshare.arangodb.aql.GraphDirection.*;
import static org.threatshare.arangodb.aql.CompoundValue.*;
```

## API Overview
The API offers an internal DSL that is as close as possible to the original query language.

## Expressions
We have different expressions available in AQL. They are located in `org.threatshare.arangodb.aql.Expressions`
- unary operators: `plus(expr), minus(expr), not(expr)`
- logical operators: `and(expr[, ...]), or(expr[, ...]), eq(expr, expr), etc.`
- mathematical operators: `plus(expr[, ...]), minus(expr[, ...]), etc.`
- ternary operator: `_if(conditionExpr, thenExpr, elseExpr)`
- value literals: `num(nr), bool(true|false), _null(), quote(str), etc.`
- selectors: `sel(selector), ref(selector)`
- compound values: `object([entry("key", "value")[, ...]])[.entry("key2", "value2")[...]], array([expressions])[.entry(expr)[...]]`
- bind parameter: `bindParam(name)`
- function call: `func(name[, args])`
- sub expression: `subExpr(expr)`
- sub query: `subQuery(query())`
- range: `range(from, to)`

Expressions can be created by using the static methods from the `Expressions` class, or through chaining:
```java
// with static methods:
and(gt(ref("x"), num(5)), lte(ref("y"), ref("z")));

// with chaining:
ref("x").gt(num(5)).and(ref("y").lte(ref("z")));

// result of both:
// ((`x` > 5) && (`y` <= `z`))
```

## Statements
Statements can be converted to String by using the `toAQL()` method.

### FOR Statement
AQL:
```
FOR variableName IN expression
````
Query Builder:
```java
query()._for(variableName).in(expression);
```

### Traversal Statement
##### Working With named graphs
AQL:
```
FOR vertex[, edge[, path]]
  IN [min[..max]]
  OUTBOUND|INBOUND|ANY startVertex
  GRAPH graphName
````
Query Builder:
```java
query()._for(vertex[, edge[, path]])
  .in([min[,max]])
  .outbound(startVertex)|inbound(startVertex)|any(startVertex)
  .graph(graphName);
```

##### Working with collection sets
AQL:
```
FOR vertex[, edge[, path]]
  IN [min[..max]]
  OUTBOUND|INBOUND|ANY startVertex
  [OUTBOUND|INBOUND|ANY] edgeCollection1[, ...]
````
Query Builder:
```java
query()._for(vertex[, edge[, path]])
  .in([min[,max]])
  .outbound(startVertex)|inbound(startVertex)|any(startVertex)
  .edgeCollection([outbound(),|inbound(),|any(),] edgeCollection1)[...];
```

### Shortest Path
##### Working with named graphs
AQL:
```
FOR vertex[, edge]
  IN OUTBOUND|INBOUND|ANY SHORTEST_PATH
  startVertex TO targetVertex
  GRAPH graphName
````
Query Builder:
```java
query()._for(vertex[, edge])
  .in().outbound(startVertex)|inbound(startVertex)|any(startVertex)
  .shortestPath(startVertex).to(targetVertex)
  .graph(graphName);
```
##### Working with collection sets
AQL:
```
FOR vertex[, edge]
  IN OUTBOUND|INBOUND|ANY SHORTEST_PATH
  startVertex TO targetVertex
  [OUTBOUND|INBOUND|ANY] edgeCollection1, ...
````
Query Builder:
```java
query()._for(vertex[, edge])
  .in().outbound(startVertex)|inbound(startVertex)|any(startVertex)
  .shortestPath(startVertex).to(targetVertex)
  .edgeCollection([outbound(),|inbound(),|any(),] edgeCollection1)...;
```

### RETURN Statement
AQL:
```
RETURN [DISTINCT] expression
````
Query Builder:
```java
query()._return[Distinct](expression);
```

### FILTER Statement
AQL:
```
FILTER condition
````
Query Builder:
```java
query().filter(condition);
```

### SORT Statement
AQL:
```
SORT expression direction
````
Query Builder:
```java
query().sort(expression[, direction]);
```

### LIMIT Statement
AQL:
```
LIMIT [offset, ]count
````
Query Builder:
```java
query().limit([offset, ]count);
```

### LET Statement
AQL:
```
LET variableName = expression
````
Query Builder:
```java
query().let(variableName, expression);
```

### COLLECT Statement
AQL:
```
COLLECT variableName = expression
COLLECT variableName = expression INTO groupsVariable
COLLECT variableName = expression INTO groupsVariable = projectionExpression
COLLECT variableName = expression INTO groupsVariable KEEP keepVariable
COLLECT variableName = expression WITH COUNT INTO countVariable
COLLECT variableName = expression AGGREGATE variableName = aggregateExpression
COLLECT AGGREGATE variableName = aggregateExpression
COLLECT WITH COUNT INTO countVariable
````
Query Builder:
```java
query().collect(variableName, expression);
query().collect(variableName, expression).into(groupsVariable);
query().collect(variableName, expression).into(groupsVariable, projectionExpression);
query().collect(variableName, expression).into(groupsVariable).keep(keepVariable);
query().collect(variableName, expression).withCountInto(countVariable);
query().collect(variableName, expression).aggregate(variableName, aggregateExpression);
query().collect().aggregate(variableName, aggregateExpression);
query().collect().withCountInto(countVariable);
```

### REMOVE Statement
AQL:
```
REMOVE keyExpression IN collection
````
Query Builder:
```java
query().remove(keyExpression).in(collection);
```

### UPDATE Statement
AQL:
```
UPDATE document IN collection
UPDATE keyExpression WITH document IN collection
````
Query Builder:
```java
query().update(document).in(collection);
query().update(keyExpression).with(document).in(collection);
```

### REPLACE Statement
AQL:
```
REPLACE document IN collection
REPLACE keyExpression WITH document IN collection
````
Query Builder:
```java
query().replace(document).in(collection);
query().replace(keyExpression).with(document).in(collection);
```

### INSERT Statement
AQL:
```
INSERT document IN collection
````
Query Builder:
```java
query().insert(document).in(collection);
```

### UPSERT Statement
AQL:
```
UPSERT searchExpression INSERT insertExpression UPDATE updateExpression IN collection
UPSERT searchExpression INSERT insertExpression REPLACE replaceExpression IN collection
````
Query Builder:
```java
query().upsert(searchExpression).insert(insertExpression).update(updateExpression).in(collection)
query().upsert(searchExpression).insert(insertExpression).replace(replaceExpression).in(collection)
```

### WITH Statement
AQL:
```
WITH collection1[, ...]
````
Query Builder:
```java
query().with(collection1[, ...])
```
