package org.moita.keep.collection;

import java.util.List;

import org.moita.keep.collection.internal.KeepListProxy;
import org.moita.keep.keeper.IKeeper;

public class KeepList {

	public static <E extends Object> List<E> create(List<E> list, IKeeper<List<E>> keeper) {
		KeepListProxy<E> listProxy = new KeepListProxy<E>(list);
		listProxy.setKeeper(keeper);
		if (keeper.hasPreviousData()) {
			listProxy.addAll(keeper.restore());
		}
		return listProxy;
	}
}
