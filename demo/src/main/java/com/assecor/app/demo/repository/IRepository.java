package com.assecor.app.demo.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    List<T> findAll();

    List<T> findByColor(int colorId);

    Optional<T> findById(long id);

    T insert(T t);
}
