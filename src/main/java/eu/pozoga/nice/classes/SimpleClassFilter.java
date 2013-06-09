package eu.pozoga.nice.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Pożoga
 */
public class SimpleClassFilter implements ClassFilter{

    protected Class fieldType;
    protected Class fieldAnnotation;
    protected Class methodType;
    protected Class methodAnnotation;

    public SimpleClassFilter() {
    }

    public SimpleClassFilter(Class type, Class annotation) {
        properties(type, annotation);
    }
    
    public SimpleClassFilter methods(Class type, Class annotation){
        this.methodType = type;
        this.methodAnnotation = annotation;
        return this;
    }
    
    public SimpleClassFilter properties(Class type, Class annotation) {
        this.fieldType = type;
        this.fieldAnnotation = annotation;
        return this;
    }

    @Override
    public boolean filter(Method method) {
        if(methodType != null && method.getReturnType()!=methodType){
            return false;
        }
        if(methodAnnotation!=null && method.getAnnotation(methodAnnotation)==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean filter(Class c, String property) {
        Field field;
        try {
            field = c.getField(property);
        } catch (Exception ex) {
            Logger.getLogger(SimpleClassFilter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(fieldType != null && field.getType()!=fieldType){
            return false;
        }
        if(fieldAnnotation!=null && field.getAnnotation(fieldAnnotation)==null){
            return false;
        }
        return true;
    }


}
