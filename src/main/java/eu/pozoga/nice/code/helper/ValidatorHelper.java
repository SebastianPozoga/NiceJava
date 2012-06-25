package eu.pozoga.nice.code.helper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Sebastian Pożoga
 */
public class ValidatorHelper {

    public static void valid(Object entity) throws ValidException, IntrospectionException {
        valid(new Object[]{entity});
    }
    
    public static void valid(Object[] entities) throws ValidException, IntrospectionException {
        Map<String, Collection<String>> messages = new HashMap<String, Collection<String>>();
        for(Object entity : entities){
            valid(entity, messages);
        }
        //return entityMessages;
        if(!messages.isEmpty()){
            throw new ValidException(messages);
        }
    }
    
    public static void valid(
            Object entity,
            Map<String, Collection<String>> entityMessages) throws IntrospectionException {
        //get property list
        ArrayList<String> propertyList = new ArrayList();
        BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            propertyList.add(propertyName);
        }
        //valid
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        for (String propertyName : propertyList) {
            Set<ConstraintViolation<Object>> validate = validator.validate(entity);
            if (!validate.isEmpty()) {
                Collection<String> propertyMessages = entityMessages.get(propertyName);
                if(propertyMessages==null){
                    propertyMessages = new HashSet<String>();
                    entityMessages.put(propertyName, propertyMessages);
                }
                for (ConstraintViolation v : validate) {
                    propertyMessages.add(v.getMessage());
                }
            }
        }
    }
    
    public static class ValidException extends Exception{
        Map<String, Collection<String>> messages;

        public ValidException(Map<String, Collection<String>> messages, Throwable thrwbl) {
            super(thrwbl);
            this.messages = messages;
        }

        public ValidException(Map<String, Collection<String>> messages, String string, Throwable thrwbl) {
            super(string, thrwbl);
            this.messages = messages;
        }

        public ValidException(Map<String, Collection<String>> messages, String string) {
            super(string);
            this.messages = messages;
        }

        public ValidException(Map<String, Collection<String>> messages) {
            this.messages = messages;
        }

        public Map<String, Collection<String>> getMessages() {
            return messages;
        }

    }
}
