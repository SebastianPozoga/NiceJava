package eu.pozoga.nice.classes;

import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

public class AbstractCloud<T extends Object> {

    protected Map<String, T> objects;
    protected ClassPack classPack;
    Integer i = new Integer(1);
    

    public AbstractCloud() throws Exception {
        classPack = C.getInstance().getPack();
        initMap();
    }

    private AbstractCloud(Map<String, T> objects, ClassPack classPack) {
        this.objects = objects;
        this.classPack = classPack;
    }
    
    void initMap(){
        objects = new WeakHashMap();
    }
    
    
    public Map<String, T> getObjects() {
        return objects;
    }

    public Object get(String name) {
        return objects.get(name);
    }

    public void put(String name, T object) {
        objects.put(name, object);
    }

    public void add(T object) {
        i++;
        objects.put(object.toString() + i.toString(), object);
    }
    
    public AbstractCloud<T> select(PackFilter filter) {
        return new AbstractCloud<T>(objects, classPack.select(filter));
    }
    
    
}
