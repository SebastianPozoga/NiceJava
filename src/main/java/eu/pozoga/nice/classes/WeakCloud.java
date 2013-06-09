package eu.pozoga.nice.classes;

import java.util.WeakHashMap;


public class WeakCloud<T> extends AbstractCloud<T> {

    public WeakCloud() throws Exception {
    }

    @Override
    void initMap() {
        objects = new WeakHashMap<String, T>();
    }
    
}
