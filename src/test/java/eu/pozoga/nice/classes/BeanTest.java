package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.ex.MethodAnn;
import eu.pozoga.nice.classes.ex.TypeAnn;
import eu.pozoga.nice.classes.test.C1;
import eu.pozoga.nice.classes.test.C2;
import eu.pozoga.nice.classes.test.C3;
import java.util.Collection;
import java.util.HashSet;
import org.junit.*;
import static org.junit.Assert.*;

public class BeanTest {
    
    @Test
    public void testGetBaseClass() throws Exception {
        Bean instance = new Bean(C1.class);
        Class result = instance.getBaseClass();
        assertEquals(C1.class, result);
    }

    @Test
    public void testGetProperties() throws Exception {
        Bean instance = new Bean(C1.class);
        Collection expResult = new HashSet();
        expResult.add("string1");
        expResult.add("string2");
        expResult.add("string3");
        expResult.add("integer1");
        expResult.add("integer2");
        Collection result = instance.getProperties();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProperty_3args() throws Exception {
        C1 object = new C1();
        String propertyName = "string1";
        Object value = "myStringValue";
        Bean instance = new Bean(C1.class);
        instance.setProperty(object, propertyName, value);
        assertEquals(value, object.getString1());
    }
    
    @Test
    public void testSetProperty_field() throws Exception {
        C1 object = new C1();
        String propertyName = "string2";
        Object value = "myStringValue";
        Bean instance = new Bean(C1.class);
        instance.setProperty(object, propertyName, value);
        assertEquals(value, object.string2);
    }

    @Test
    public void testSetProperty_4args_correctSet() throws Exception {
        C1 object = new C1();
        String propertyName = "string1";
        Object value = "myStringValue";
        Bean instance = new Bean(C1.class);
        ClassFilter filter = new SimpleClassFilter();
        instance.setProperty(object, propertyName, value, filter);
        assertNotNull(object.getString1());
    }
    
    @Test
    public void testSetProperty_4args_deniedByFilter() throws Exception {
        C1 object = new C1();
        object.setString1(null);
        String propertyName = "string1";
        Object value = "myStringValue";
        Bean instance = new Bean(C1.class);
        ClassFilter filter = new SimpleClassFilter(C3.class, TypeAnn.class);
        try{
            instance.setProperty(object, propertyName, value, filter);
        }catch(IllegalAccessException ex) {
            if(ex.getMessage().equals("Property no found")){
                //IS OK - it is correct
                return;
            }
        }
        //Error
        throw new Exception("No correct - Inncorect result");
    }

    @Test
    public void testGetProperty_string1() throws Exception {
        System.out.println("getProperty");
        C1 object = new C1();
        String propertyName = "string1";
        Bean instance = new Bean(C1.class);
        String expResult = "myExpResult";
        object.setString1(expResult);
        Object result = instance.getProperty(object, propertyName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetProperty_field() throws Exception {
        C1 object = new C1();
        String propertyName = "string2";
        String value = "myStringValue";
        object.string2 = value;
        Bean instance = new Bean(C1.class);
        Object result = instance.getProperty(object, propertyName);
        assertEquals(value, result);
    }

    @Test
    public void testGetProperty_3args_correct() throws Exception {
        C1 object = new C1();
        String propertyName = "string1";
        ClassFilter filter = new SimpleClassFilter();
        Bean instance = new Bean(C1.class);
        String expResult = "mystring";
        object.setString1(expResult);
        Object result = instance.getProperty(object, propertyName, filter);
        assertEquals(expResult, result);
    }

    @Test
    public void testHasProperty() throws Exception {
        Bean instance = new Bean(C1.class);
        assertTrue( instance.hasProperty("string1") );
        assertTrue( instance.hasProperty("string2") );
        assertTrue( instance.hasProperty("integer1") );
        assertTrue( instance.hasProperty("integer2") );
    }

    @Test
    public void testInvoke_3args() throws Exception {
        Collection expResult = new HashSet();
        expResult.add("myTextResult");
        C2 object = new C2();
        Bean instance = new Bean(C2.class);
        Object result = instance.invoke(object, "myText", new Object[]{});
        assertEquals(expResult, result);
    }

    @Test
    public void testInvoke_4args_correct() throws Exception {
        Collection expResult = new HashSet();
        expResult.add("myTextResult");
        C2 object = new C2();
        Bean instance = new Bean(C2.class);
        Object result = instance.invoke(object, "myText", new Object[]{}, new SimpleClassFilter());
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInvoke_4args_noFiltred() throws Exception {
        Collection expResult = new HashSet();
        C2 object = new C2();
        Bean instance = new Bean(C2.class);
        ClassFilter filter = new SimpleClassFilter(C1.class, MethodAnn.class);
        Object result = instance.invoke(object, "myText", new Object[]{}, filter);
        assertEquals(expResult, result);
    }
}
