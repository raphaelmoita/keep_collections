package org.moita.keep.collection.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.moita.keep.keeper.IKeeper;

/**
 * 
 * @author Raphael Moita
 *
 * @see {@link java.util.ArrayList}
 * @version 1.0
 * @requires java8
 */
public class KeepSetProxy<E> implements Set<E> {

    private transient IKeeper<Set<E>> keeper = null;

    private Set<E> set = null;

    public KeepSetProxy(Set<E> set) {
        this.set = set;
    }

    /**
     * Allows to set a different method to store and recover data from disk/database/network ...
     * 
     * @param keeper
     *            instance of IPersistence that contains the implementation to persist and restore data to and from PArrayList.
     */
    public void setKeeper(IKeeper<Set<E>> keeper) {
        this.keeper = keeper;
    }

    @Override
    public int size() {
        return this.set.size();
    }

    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.set.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        boolean addRet = this.set.add(e);
        if (addRet) {
            this.keeper.persist(this.set);
        }
        return addRet;
    }

    @Override
    public boolean remove(Object o) {
        boolean removeRet = this.set.remove(o);
        if (removeRet) {
            this.keeper.persist(this.set);
        }
        return removeRet;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean addAllRet = this.set.addAll(c);
        if (addAllRet) {
            this.keeper.persist(this.set);
        }
        return addAllRet;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removeAllRet = this.set.removeAll(c);
        if (removeAllRet) {
            this.keeper.persist(this.set);
        }
        return removeAllRet;
    }

    @Override
    public void clear() {
        this.set.clear();
    }
}
