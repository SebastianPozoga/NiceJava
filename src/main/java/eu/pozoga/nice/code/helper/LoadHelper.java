package eu.pozoga.nice.code.helper;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.classes.ClassController;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Pożoga
 */
public class LoadHelper {
    
    public static void load(Map<String, Object> object, Object data) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, Exception {
        ClassController dDesc = C.get(data.getClass());
        Collection<String> props =  dDesc.getProperties();
        for(String name : props){
            try {
                Object val = dDesc.getProperty(data, name);
                object.put(name, val);
            } catch (Exception ex) {
                Logger.getLogger(LoadHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void load(Object object, Map<String, Object> data) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, Exception {
        ClassController oDesc = C.get(object.getClass());
        Collection<String> props =  oDesc.getProperties();
        for(String name : props){
            try {
                Object val = data.get(name);
                if(val==null){
                    continue;
                }
                Class propertyType = oDesc.getPropertyType(name);
                val = ConverterHelper.convert(val, propertyType);
                oDesc.setProperty(object, name, val);
            } catch (Exception ex) {
                Logger.getLogger(LoadHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void load(Object object, Object[] datas) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, Exception {
        for (Object data : datas) {
            load(object, data);
        }
    }

    public static void load(Object object, Object data) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, Exception {
        ClassController oDesc = C.get(object.getClass());
        ClassController dDesc = C.get(data.getClass());
        Collection<String> props =  dDesc.getProperties();
        for(String name : props){
            try {
                Class propertyType = oDesc.getPropertyType(name);
                Object val = dDesc.getProperty(data, name);
                val = ConverterHelper.convert(val, propertyType);
                oDesc.setProperty(object, name, val);
            } catch (Exception ex) {
                Logger.getLogger(LoadHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
