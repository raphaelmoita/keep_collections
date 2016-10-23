package org.moita.keep.collection.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.moita.keep.keeper.IKeeper;

/**
 * 
 * @author Raphael Moita
 *
 * @see {@link java.util.ArrayList}
 * @version 1.0
 * @requires java8
 */
public class KeepListProxy<E> implements List<E> {

    private transient IKeeper<List<E>> keeper = null;

    private List<E> list = null;

    public KeepListProxy(List<E> list) {
        this.list = list;
    }

    public boolean add(E e) {
        boolean addReturn = this.list.add(e);
        if (addReturn) {
            this.keeper.persist(this.list);
        }
        return addReturn;
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean addAllReturn = this.list.addAll(c);
        if (addAllReturn) {
            this.keeper.persist(this.list);
        }
        return addAllReturn;
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        this.keeper.persist(this.list);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean addAllReturn = this.list.addAll(index, c);
        if (addAllReturn) {
            this.keeper.persist(this.list);
        }
        return addAllReturn;
    }

    /**
     * Allows to set a different method to store and recover data from disk/database/network ...
     * 
     * @param keeper
     *            instance of IPersistence that contains the implementation to persist and restore data to and from PArrayList.
     */
    public void setKeeper(IKeeper<List<E>> keeper) {
        this.keeper = keeper;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.list.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removeAllReturn = this.list.removeAll(c);
        if (removeAllReturn) {
            this.keeper.persist(this);
        }
        return removeAllReturn;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean retainAllReturn = this.list.retainAll(c);
        if (retainAllReturn) {
            this.keeper.persist(this);
        }
        return retainAllReturn;
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        return this.list.set(index, element);
    }

    @Override
    public E remove(int index) {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

}
