package com.cinejava.services;

import java.util.List;
import java.util.Optional;

import com.cinejava.common.BaseModel;
import com.cinejava.infrastructure.DataStoreContext;

public class GenericService<T extends BaseModel> {
    protected final DataStoreContext<T> dataStoreContext;

    public GenericService(Class<T> type, String storeName) {
        dataStoreContext = new DataStoreContext<T>(type, storeName);
    }

    public Optional<T> get(long id) {
        return dataStoreContext.get(id);
    }

    public List<T> getAll() {
        return dataStoreContext.getAll();
    }

    public boolean insert(T item) {
        return dataStoreContext.insert(item);
    }

    public boolean update(long id, T item) {
        return dataStoreContext.update(id, item);
    }

    public boolean delete(long id) {
        return dataStoreContext.delete(id);
    }
}
