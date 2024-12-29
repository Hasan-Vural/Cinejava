package com.cinejava.services.implementations;

import java.util.List;
import java.util.Optional;

import com.cinejava.common.BaseModel;
import com.cinejava.infrastructure.DataStoreContext;
import com.cinejava.services.interfaces.IGenericService;

public class GenericService<T extends BaseModel> implements IGenericService<T> {
    protected final DataStoreContext<T> dataStoreContext;

    public GenericService(Class<T> type, String storeName) {
        dataStoreContext = new DataStoreContext<T>(type, storeName);
    }

    @Override
    public Optional<T> get(long id) {
        return dataStoreContext.get(id);
    }

    @Override
    public T getByIndex(int index) {
        return dataStoreContext.getByIndex(index);
    }

    @Override
    public List<T> getAll() {
        return dataStoreContext.getAll();
    }

    @Override
    public boolean insert(T item) {
        return dataStoreContext.insert(item);
    }

    @Override
    public boolean update(long id, T item) {
        return dataStoreContext.update(id, item);
    }

    @Override
    public boolean delete(long id) {
        return dataStoreContext.delete(id);
    }
}
