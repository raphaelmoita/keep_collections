package org.moita.keep.collection;

import java.util.Map;

import org.moita.keep.collection.internal.KeepMapProxy;
import org.moita.keep.keeper.IKeeper;

public class KeepMap {

	public static <K extends Object, V extends Object> Map<K, V> create(Map<K, V> map, IKeeper<Map<K, V>> keeper) {
		KeepMapProxy<K, V> mapProxy = new KeepMapProxy<K, V>(map);
		mapProxy.setKeeper(keeper);
		if (keeper.hasPreviousData()) {
			mapProxy.putAll(keeper.restore());
		}
		return mapProxy;
	}
}
