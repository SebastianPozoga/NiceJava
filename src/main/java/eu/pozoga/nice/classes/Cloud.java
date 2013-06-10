package eu.pozoga.nice.classes;

import java.util.Map;

public interface Cloud<T extends Object> {

    public Map<String, T> getObjects();
    public T get(String name);
    public void put(String name, T object);
    public void add(T object);
    
    public AbstractCloud<T> select(PackFilter filter);
    
}
