package eu.pozoga.nice.classes;

import java.util.Collection;

public interface Cloud<T extends Object> {

    public Collection<T> getObjects();
    public Object get(String name);
    public void put(String name, T object);
    public void add(T object);
    
}
