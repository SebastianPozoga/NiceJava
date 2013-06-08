package eu.pozoga.nice.classes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author spozoga
 */
public class Singleton {
    
    public static Map<Class, Object> instances = new HashMap();
    
    public static Object getInstanceOf(Class c) throws InstantiationException, IllegalAccessException{
        Object o = instances.get(c);
        if(o==null){
            o = c.newInstance();
            instances.put(c, o);
        }
        return o;
    }
    
}
