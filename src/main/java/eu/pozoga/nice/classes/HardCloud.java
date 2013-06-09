package eu.pozoga.nice.classes;

import java.util.HashMap;


public class HardCloud<T> extends AbstractCloud<T> {

    public HardCloud() throws Exception {
    }

    @Override
    void initMap() {
        objects = new HashMap<String, T>();
    }
    
}
