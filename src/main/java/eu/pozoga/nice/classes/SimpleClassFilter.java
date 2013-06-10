package eu.pozoga.nice.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleClassFilter implements ClassFilter{

    protected Class fieldType;
    protected Class fieldAnnotation;
    protected Class methodType;
    protected Class methodAnnotation;

    public SimpleClassFilter() {
    }

    public SimpleClassFilter(Class type, Class annotation) {
        properties(type, annotation);
        methods(type, annotation);
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
        Class type = null;
        Object annotation = null;
        try {
            Field field = c.getField(property);
            type = field.getType();
            annotation = field.getAnnotation(fieldAnnotation);
        } catch (Exception ex) {
            String getterName = "get"+upperFirstChar(property);
            try {
                Method getter = c.getMethod(getterName, new Class[]{});
                type = getter.getReturnType();
                try {
                    annotation = getter.getAnnotation(fieldAnnotation);
                } catch (Exception e) {
                }
            } catch (Exception ex1) {
                Logger.getLogger(SimpleClassFilter.class.getName()).log(Level.SEVERE, null, ex1);
                return false;
            }
        }
        if(fieldType != null && type!=fieldType){
            return false;
        }
        if(fieldAnnotation!=null && annotation==null){
            return false;
        }
        return true;
    }

    protected static String upperFirstChar(String in){
        return in.substring(0, 1).toUpperCase()+in.substring(1);
    }

}
