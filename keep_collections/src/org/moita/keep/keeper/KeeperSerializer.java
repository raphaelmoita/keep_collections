package org.moita.keep.keeper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.moita.keep.exception.KeepException;

public final class KeeperSerializer<T> implements IKeeper<T> {

    private String filePath = null;

    private boolean hasPreviousData = false;

    private static final Map<String, Object> LOCKS = new ConcurrentHashMap<String, Object>();

    /**
     * Constructor.
     * 
     * @param filePath - file used to store the collection data.
     */
    public KeeperSerializer(final String filePath) {
        this.setFilePath(filePath);
    }

    private Object getLocker() {
        return LOCKS.get(this.getFilePath().toString());
    }

    @Override
    public void persist(T data) {
        synchronized (this.getLocker()) {
            try (OutputStream file = new FileOutputStream(this.getFilePath().toFile());
                    OutputStream buffer = new BufferedOutputStream(file);
                    ObjectOutput output = new ObjectOutputStream(buffer);) {
                output.writeObject(data);
            } catch (IOException e) {
                throw new KeepException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T restore() {
        synchronized (this.getLocker()) {
            try (InputStream file = new FileInputStream(this.getFilePath().toFile());
                    InputStream buffer = new BufferedInputStream(file);
                    ObjectInput input = new ObjectInputStream(buffer);) {
                return (T) input.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new KeepException(e);
            }
        }
    }

    public final Path getFilePath() {
        return Paths.get(this.filePath);
    }

    public void setFilePath(String filePath) {
        this.setFilePath(filePath, false);
    }

    public void setFilePath(String filePath, boolean createParentIfNotExists) {
        this.filePath = filePath;
        
        this.addLock(this.getFilePath().toString());

        if (!Files.exists(Paths.get(filePath).getParent(), LinkOption.NOFOLLOW_LINKS)) {
            if (createParentIfNotExists) {
                Path directories = Paths.get(filePath).getParent();
                try {
                    Files.createDirectories(directories);
                } catch (IOException e) {
                    throw new KeepException(e);
                }
            } else {
                throw new KeepException(new FileNotFoundException(filePath.toString()));
            }
        } else {
            this.hasPreviousData = true;
        }
    }
    
    private void addLock(String key) {
        if (!LOCKS.containsKey(key)) {
            LOCKS.put(key, this);
        }
    }

    @Override
    public boolean hasPreviousData() {
        return hasPreviousData && Files.exists(this.getFilePath(), LinkOption.NOFOLLOW_LINKS);
    }
}
