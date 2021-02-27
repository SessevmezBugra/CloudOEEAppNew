package com.oee.service.common.generic;


public interface GenericService <T, K> {

    T create(T entity);

    T update(T entity);

    void deleteById(K id);

    T findById(K id);
}
