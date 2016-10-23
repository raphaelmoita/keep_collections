package org.moita.keep.collection;

import java.util.Set;

import org.moita.keep.collection.internal.KeepSetProxy;
import org.moita.keep.config.KeepConfiguration;
import org.moita.keep.keeper.IKeeper;

public class KeepSet {

    public static <K extends Object> Set<K> create(Set<K> set, IKeeper<Set<K>> keeper) {
        return create(set, keeper, null);
    }

    public static <K extends Object> Set<K> create(Set<K> set, IKeeper<Set<K>> keeper, KeepConfiguration configuration) {
        KeepSetProxy<K> setProxy = new KeepSetProxy<K>(set);
        setProxy.setKeeper(keeper);
        if (keeper.hasPreviousData()) {
            setProxy.addAll(keeper.restore());
        }
        return setProxy;
    }
}
