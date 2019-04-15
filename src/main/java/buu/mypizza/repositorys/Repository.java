package buu.mypizza.repositorys;

import java.util.List;

public interface Repository<T> {
    
    
    T getByStringKey(String key);
    
    void add(T t);
    
    boolean isExist(T t);
    
    List<T> getAll();
    
    void update(T t, String[] fields);
    
    void delete(T t);
    
    int getIdByStringKey(String key);
    
}
