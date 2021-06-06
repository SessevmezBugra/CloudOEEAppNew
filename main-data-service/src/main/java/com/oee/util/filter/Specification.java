package com.oee.util.filter;

public interface Specification<T> {
    boolean isSatisfied(T item);
}
