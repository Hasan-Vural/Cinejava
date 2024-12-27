package com.cinejava.infrastructure;

import com.cinejava.common.BaseModel;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class DataStoreContext<T extends BaseModel>{
    private final String fileLocation = System.getProperty("user.dir").concat("/src/main/resources/data/");
    private final String json = ".json";
    private final String storeFilePath;

    private final Class<T> type;
    private List<T> items;
    private ObjectMapper objectMapper;

    public DataStoreContext(Class<T> type, String storeName) {
        this.storeFilePath = fileLocation.concat(storeName).concat(json);
        
        this.type= type;       
        this.objectMapper = new ObjectMapper();
        this.items = loadFromFile();
    }

    public Optional<T> get(long id) {
        return items.stream().filter(x -> x.id == id).findFirst();
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public boolean insert(T item) {
        item.id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        items.add(item);

        return trySaveToFile();
    }

    public boolean update(long id, T item) {

        Optional<T> existedItem = get(id);

        if (!existedItem.isPresent()) {
            return false;
        }
    
        int index = items.indexOf(existedItem.get());
        
        items.set(index, item);
        return trySaveToFile();
    }

    public boolean delete(long id) {
        Optional<T> existedItem = get(id);

        if (existedItem.isPresent()) {
            return false;
        }
    
        int index = items.indexOf(existedItem.get());
        
        items.remove(index);
        return trySaveToFile();
    }

    // #region private methods
    private List<T> loadFromFile() {
        try {
            File file = new File(storeFilePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, type);

            return objectMapper.readValue(file, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private boolean trySaveToFile() {
        try {
            objectMapper.writeValue(new File(storeFilePath), items);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    // #endregion
}
