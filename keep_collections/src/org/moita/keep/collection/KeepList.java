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

    public static <E extends Object> List<E> create(List<E> list, IKeeper<List<E>> keeper, KeepConfiguration configuration) {
        KeepListProxy<E> listProxy = new KeepListProxy<E>(list);
        listProxy.setKeeper(keeper);
        if (keeper.hasPreviousData()) {
            listProxy.addAll(keeper.restore());
        }
        return listProxy;
    }

    /**
     * Teste.
     * 
     * @param args
     */
    public static void main(String[] args) {
        List<String> l = new ArrayList<String>();
        
        KeeperSerializer<List<String>> keeperSerializer = new KeeperSerializer<List<String>>("666");
        keeperSerializer.setFilePath("/tmp/moita.ser", true);
        List<String> lDecor1 = KeepList.create(l, keeperSerializer);
        new Thread(() -> {            
            lDecor1.add("Moita1 " + System.currentTimeMillis());
            lDecor1.forEach((e)  -> System.out.println("1 " + e));
            lDecor1.add("Moita1 " + System.currentTimeMillis());
            lDecor1.forEach((e) -> System.out.println("1 " + e));
            lDecor1.add("Moita1 " + System.currentTimeMillis());
            lDecor1.forEach((e) -> System.out.println("1 " + e));
        }).start();
        
        KeeperSerializer<List<String>> keeperSerializer2 = new KeeperSerializer<List<String>>("666");
        keeperSerializer2.setFilePath("/tmp/moita.ser", true);        
        List<String> lDecor2 = KeepList.create(l, keeperSerializer2);
        new Thread(() -> {            
            lDecor2.add("Moita2 " + System.currentTimeMillis());
            lDecor2.forEach((e) -> System.out.println("2 " + e));
            lDecor2.add("Moita2 " + System.currentTimeMillis());
            lDecor2.forEach((e) -> System.out.println("2 " + e));
            lDecor2.add("Moita2 " + System.currentTimeMillis());
            lDecor2.forEach((e) -> System.out.println("2 " + e));
            
        }).start();
        
        System.out.println();
    }

}
