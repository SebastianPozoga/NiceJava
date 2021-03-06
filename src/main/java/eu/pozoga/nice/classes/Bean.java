package eu.pozoga.nice.classes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import org.apache.commons.beanutils.BeanUtils;

public class Bean<T> {

    protected Class<T> c;
    protected ClassFilter filter = null;
    protected Collection<String> properties = null;
    protected Collection<String> fieldProperties = null;

    public Bean(Class<T> c) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        this.c = c;
        initProperties();
    }

    protected void initProperties(){
        properties = new HashSet();
        //scan methods
        for (Method method : c.getMethods()) {
            String name = method.getName();
            if (Modifier.isPublic(method.getModifiers()) && name.startsWith("get")) {
                name = lowerFirstChar(name.substring(3));
                if (!properties.contains(name) && !name.equals("class")) {
                    properties.add(name);
                }
            }
        }
        fieldProperties = new HashSet();
        //scan fields
        for (Field field : c.getFields()) {
            String name = field.getName();
            if (Modifier.isPublic(field.getModifiers()) && !properties.contains(name) ) {
                properties.add(name);
                fieldProperties.add(name);
            }
        }
    }
    /*protected Bean(Class<T> c, Collection<String> properties, ClassFilter filter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Set<String> copied = new HashSet();
        for (String property : properties) {
            if (filter.filter(c, property)) {
                copied.add(property);
            }
        }
        this.properties = copied;
        this.filter = filter;
        this.c = c;
    }*/

    public Class<T> getBaseClass() {
        return this.c;
    }

    public Collection<String> getProperties() {
        return properties;
    }

    private static String lowerFirstChar(String in) {
        return in.substring(0, 1).toLowerCase() + in.substring(1);
    }
    
    public void setProperty(T object, String propertyName, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        setProperty(object, propertyName, value, null);
    }

    public void setProperty(T object, String propertyName, Object value, ClassFilter filter) throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        if (filter!=null && !filter.filter(c, propertyName)) {
            throw new IllegalAccessException("Property no found");
        }
        if(fieldProperties.contains(propertyName)){
            c.getField(propertyName).set(object, value);
            return;
        }
        BeanUtils.setProperty(object, propertyName, value);
    }

    public Object getProperty(T object, String propertyName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        return getProperty(object, propertyName, null);
    }
    
    public Object getProperty(T object, String propertyName, ClassFilter filter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        if (filter!=null && !filter.filter(c, propertyName)) {
            throw new IllegalAccessException("Property no found");
        }
        if(fieldProperties.contains(propertyName)){
            return c.getField(propertyName).get(object);
        }
        return BeanUtils.getProperty(object, propertyName);
    }

    public boolean hasProperty(String propertyName) {
        return getProperties().contains(propertyName);
    }

    public Object invoke(T object, String methodName, Object[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return invoke(object, methodName, args, null);
    }

    public Collection<Object> invoke(T object, String methodName, Object[] args, ClassFilter exFilter) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class[] types = {};
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Collection<Object> results = new HashSet<Object>();
        if(methodName!=null){
            Method method = c.getMethod(methodName, types);
            if (exFilter == null || exFilter.filter(method)) {
                results.add(method.invoke(object, args));
            }
            return results;
        }
        for(Method method : c.getMethods()){
            if (exFilter == null || exFilter.filter(method)) {
                results.add(method.invoke(object, args));
            }
        }
        return results;
    }

//    public Bean select(ClassFilter filter) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//        return new Bean(c, properties, filter);
//    }

}
