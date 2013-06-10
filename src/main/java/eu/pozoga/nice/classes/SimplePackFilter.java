package eu.pozoga.nice.classes;

import java.lang.reflect.Modifier;

public class SimplePackFilter implements PackFilter{

    protected Class type = null;
    protected Class annotation = null;
    protected boolean allowAbstract = false;

    public SimplePackFilter() {
    }
    
    public SimplePackFilter(Class type, Class ann) {
        this.type = type;
        this.annotation = ann;
    }
    
    public PackFilter byAnn(Class ann){
        this.annotation = ann;
        return this;
    }
    
    public PackFilter byType(Class type){
        this.type = type;
        return this;
    }
    
    public PackFilter setAllowAbstract(boolean allow){
        this.allowAbstract = allow;
        return this;
    }

    @Override
    public boolean filter(Class c) {
        if(type != null && !type.isAssignableFrom(c)){
            return false;
        }
        if(annotation!=null && c.getAnnotation(annotation)==null){
            return false;
        }
        if(!allowAbstract && Modifier.isAbstract(c.getModifiers())){
            return false;
        }
        return true;
    }

}
