package org.threatshare.arangodb.aql.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class StringBuilderJoiner {

	public static <K, V> void joinMap(StringBuilder sb, Map<K, V> map, BiConsumer<K, V> consumer) {
		joinMap(", ", sb, map, consumer);
	}

	public static <K, V> void joinMap(String delimiter, StringBuilder sb, Map<K, V> map, BiConsumer<K, V> consumer) {
		boolean first = true;
		for (Entry<K, V> entry : map.entrySet()) {
			if (first) {
				first = false;
			} else {
				sb.append(delimiter);
			}
			consumer.accept(entry.getKey(), entry.getValue());
		}
	}

	public static <V> void joinList(StringBuilder sb, List<V> list, Consumer<V> consumer) {
		joinList(", ", sb, list, consumer);
	}

	public static <V> void joinList(String delimiter, StringBuilder sb, List<V> list, Consumer<V> consumer) {
		boolean first = true;
		for (V value : list) {
			if (first) {
				first = false;
			} else {
				sb.append(delimiter);
			}
			consumer.accept(value);
		}
	}
	
	public static <V> void joinArray(StringBuilder sb, V[] array, Consumer<V> consumer) {
		joinArray(", ", sb, array, consumer);
	}
	
	public static <V> void joinArray(String delimiter, StringBuilder sb, V[] array, Consumer<V> consumer) {
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			consumer.accept(array[i]);
		}
	}

}