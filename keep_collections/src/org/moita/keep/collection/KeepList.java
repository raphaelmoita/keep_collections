package org.moita.keep.collection;

import java.util.ArrayList;
import java.util.List;

import org.moita.keep.collection.internal.KeepListProxy;
import org.moita.keep.config.KeepConfiguration;
import org.moita.keep.keeper.IKeeper;
import org.moita.keep.keeper.KeeperSerializer;

public class KeepList {

    public static <E extends Object> List<E> create(List<E> list, IKeeper<List<E>> keeper) {
        return create(list, keeper, null);
    }
}
