package com.sasha.grodno.website.service;

import java.util.List;

public interface CrudService<T> {

    T getById(Integer id);

    List<T> getAll();

    T save(T t);

    void delete(T t);
}
