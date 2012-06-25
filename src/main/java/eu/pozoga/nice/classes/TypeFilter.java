package eu.pozoga.nice.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Classes filter use type and type annotation to select classes
 *
 * @author Sebastian Pożoga
 */
public interface TypeFilter {

    public boolean filter(Method m);
    public boolean filter(Field f);
    
}
