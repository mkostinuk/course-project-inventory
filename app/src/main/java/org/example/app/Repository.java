package org.example.app;

public abstract class Repository<T> {
    public abstract void save(T t);
    public abstract void update(T t);
    public abstract void delete(T t);
}
