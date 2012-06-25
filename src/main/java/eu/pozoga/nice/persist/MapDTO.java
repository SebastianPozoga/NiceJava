package eu.pozoga.nice.persist;

import eu.pozoga.nice.code.helper.LoadHelper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sebastian Pożoga
 */
public class MapDTO implements DTO{
    
    Map<String, Object> map = new HashMap<String, Object>();
    
    @Override
    public void set(String name, Object value){
        map.put(name, value);
    }
    
    @Override
    public Object get(String name){
        return map.get(name);
    }

    @Override
    public void load(Object o) throws Exception {
        LoadHelper.load(map, o);
    }

    @Override
    public void load(Map<String, Object> m) throws Exception {
        map.putAll(m);
    }
}
