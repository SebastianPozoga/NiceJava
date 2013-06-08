/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.pozoga.nice.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author Sebastian Pożoga
 */
public class MethodFilter implements TypeFilterInterface{

    protected Class type;
    protected Class annotation;


    public MethodFilter() {
    }

    public MethodFilter(Class type, Class annotation) {
        this.type = type;
        this.annotation = annotation;
    }
    
    public MethodFilter byAnn(Class fieldAnnotation){
        this.annotation = fieldAnnotation;
        return this;
    }
    
    public MethodFilter byType(Class fieldType){
        this.type = fieldType;
        return this;
    }

    @Override
    public boolean filter(Method m) {
        if(type != null && m.getReturnType()!=type){
            return false;
        }
        if(annotation!=null && m.getAnnotation(annotation)==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean filter(Field f) {
        return true;
    }


}
