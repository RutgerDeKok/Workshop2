package main.java.daos;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
    List<T> findAll(Class<T> entityClass);
    T create(T t);
    T read(Class<T> entityClass, PK id);
    T saveOrUpdate(T t);
    void delete(T t);
}