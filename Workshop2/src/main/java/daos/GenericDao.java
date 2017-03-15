package main.java.daos;

import java.io.Serializable;

public interface GenericDao<T, PK extends Serializable> {
    T create(T t);
    T read(PK id);
    T saveOrUpdate(T t);
    void delete(T t);
}