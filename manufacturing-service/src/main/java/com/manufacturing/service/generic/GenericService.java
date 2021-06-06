package com.manufacturing.service.generic;


public interface GenericService<T, K> {

    T create(T entity);

    T update(T entity);

    void deleteById(K id);

    T findById(K id);
}
