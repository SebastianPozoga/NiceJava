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
public class FieldFilter implements TypeFilter{

    protected Class fieldType;
    protected Class fieldAnnotation;


    public FieldFilter() {
    }

    public FieldFilter(Class fieldType, Class fieldAnnotation) {
        this.fieldType = fieldType;
        this.fieldAnnotation = fieldAnnotation;
    }
    
    public FieldFilter byAnn(Class fieldAnnotation){
        this.fieldAnnotation = fieldAnnotation;
        return this;
    }
    
    public FieldFilter byType(Class fieldType){
        this.fieldType = fieldType;
        return this;
    }

    @Override
    public boolean filter(Method m) {
        return true;
    }

    @Override
    public boolean filter(Field f) {
        if(fieldType != null && f.getType()!=fieldType){
            return false;
        }
        if(fieldAnnotation!=null && f.getAnnotation(fieldAnnotation)==null){
            return false;
        }
        return true;
    }


}
