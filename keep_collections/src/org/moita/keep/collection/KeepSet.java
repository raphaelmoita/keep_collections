package org.moita.keep.collection;

import java.util.Set;

import org.moita.keep.collection.internal.KeepSetProxy;
import org.moita.keep.keeper.IKeeper;

public class KeepSet {

	public static <K extends Object> Set<K> create(Set<K> set, IKeeper<Set<K>> keeper) {
		KeepSetProxy<K> setProxy = new KeepSetProxy<K>(set);
		setProxy.setKeeper(keeper);
		if (keeper.hasPreviousData()) {
			setProxy.addAll(keeper.restore());
		}
		return setProxy;
	}
}
