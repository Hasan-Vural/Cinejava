package com.cinejava.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.cinejava.common.BaseModel;

public interface IGenericService<T extends BaseModel> {
    Optional<T> get(long id);
    T getByIndex(int index);
    List<T> getAll();
    boolean insert(T item);
    boolean update(long id, T item);
    boolean delete(long id);
}
