package org.moita.keep.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.moita.keep.collection.KeepList;
import org.moita.keep.keeper.IKeeper;
import org.moita.keep.keeper.KeeperSerializer;

public class KeepListTest {
    private final String value = "Keep Collections is great!";
    private final String fileKeep1 = "/tmp/teste-1.keep";
    private final String fileKeep2 = "/tmp/teste-2.keep";

    @Before
    public void start() throws IOException {
        Files.deleteIfExists(Paths.get(fileKeep1));
        Files.deleteIfExists(Paths.get(fileKeep2));
    }
    
    @Test
    public void recoverList() {
        KeepList.create(new ArrayList<String>(), new KeeperSerializer<List<String>>(fileKeep1)).add(this.value);
        
        List<String> arrayList = new ArrayList<String>();
        IKeeper<List<String>> keeper = new KeeperSerializer<List<String>>(fileKeep1);
        List<String> keepList = KeepList.create(arrayList, keeper);

        assertEquals(value, keepList.get(0));
    }

    @Test
    public void createList() {
        List<String> keepList = KeepList.create(new ArrayList<String>(), new KeeperSerializer<List<String>>(fileKeep2));
        
        for (int i = 0; i < 100; i++) {
            keepList.add("something ...");
        }

        assertEquals(keepList.size(), 100);
    }

}
