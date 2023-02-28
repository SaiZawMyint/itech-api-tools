package com.itech.api.v1.tools.interfaces;
@FunctionalInterface
public interface Functions<T,N,R> {
    public R apply(T t,N n);
}
