/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.nice.classes;

import java.lang.reflect.Modifier;

/**
 *
 * @author Sebastian Pożoga
 */
public class PFilter implements PackFilter{

    protected Class type = null;
    protected Class annotation = null;
    protected boolean allowAbstract = false;
    protected boolean allowInterface = false;

    public PFilter() {
    }
    
    public PFilter(Class type, Class ann) {
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
    
    public PackFilter allowAbstract(boolean allow){
        this.allowAbstract = allow;
        return this;
    }
    
    public PackFilter allowInterface(boolean allow){
        this.allowInterface = allow;
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
        if(!allowInterface && c.isInterface()){
            return false;
        }
        return true;
    }

}
