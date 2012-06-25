package eu.pozoga.nice.persist;

import java.util.Map;

/**
 *
 * @author Sebastian Pożoga
 */
public interface DTO {
    
    public void load(Object o) throws Exception;
    
    public void load(Map<String, Object> m) throws Exception;
    
    public void set(String name, Object value) throws Exception;
    
    public Object get(String name) throws Exception;
}
