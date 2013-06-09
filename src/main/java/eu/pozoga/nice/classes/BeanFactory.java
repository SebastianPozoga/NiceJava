package eu.pozoga.nice.classes;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class BeanFactory {
    
    private static Map<Class, Bean> beans = new HashMap();
    
    public static Bean getInstance(Class c) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        Bean bean = beans.get(c);
        if(bean == null){
            bean = new Bean(c);
            beans.put(c, bean);
        }
        return bean;
    }
    
}
