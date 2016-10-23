package org.moita.keep.keeper;

public interface IKeeper<T> {

    public T restore();

    public void persist(T data);

    public boolean hasPreviousData();

}
