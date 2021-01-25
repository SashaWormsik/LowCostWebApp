package com.sasha.grodno.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CrudServiceJpaImpl<T> implements CrudService<T> {

    @Autowired
    public JpaRepository<T, Integer> repo;

    @Override
    public T getById(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public List<T> getAll() {
        return repo.findAll();
    }

    @Override
    public T save(T t) {
        return repo.save(t);
    }

    @Override
    public void delete(T t) {
        repo.delete(t);
    }
}
