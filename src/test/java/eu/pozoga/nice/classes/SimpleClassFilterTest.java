package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.ex.MethodAnn;
import eu.pozoga.nice.classes.test.C1;
import eu.pozoga.nice.classes.test.C2;
import org.junit.*;
import static org.junit.Assert.*;

public class SimpleClassFilterTest {

    /*
     * filterMethod
     */
    
    @Test
    public void testFilter_Method_byAnn_allow() throws NoSuchMethodException {
        ClassFilter instance = new SimpleClassFilter(null, MethodAnn.class);
        assertTrue( instance.filter(C2.class.getMethod("methodWithAnnotation", new Class[]{})) );
    }
    
    @Test
    public void testFilter_Method_byAnn_denied() throws NoSuchMethodException {
        ClassFilter instance = new SimpleClassFilter(null, MethodAnn.class);
        assertFalse( instance.filter(C2.class.getMethod("methodWithoutAnnotation", new Class[]{})) );
    }
    
    @Test
    public void testFilter_Method_byType_allow() throws NoSuchMethodException {
        ClassFilter instance = new SimpleClassFilter(String.class, null);
        assertTrue( instance.filter(C2.class.getMethod("myText", new Class[]{})) );
    }
    
    @Test
    public void testFilter_Method_byType_denied() throws NoSuchMethodException {
        ClassFilter instance = new SimpleClassFilter(Integer.class, null);
        assertFalse( instance.filter(C2.class.getMethod("myText", new Class[]{})) );
    }

    /*
     * filterProperty
     */
    
    @Test
    public void testFilter_filterProperty_byAnn_allow() {
        ClassFilter instance = new SimpleClassFilter(null, MethodAnn.class);
        assertTrue( instance.filter(C1.class, "integer1") );
    }
    
    @Test
    public void testFilter_filterProperty_byAnn_denied() {
        ClassFilter instance = new SimpleClassFilter(null, MethodAnn.class);
        assertFalse( instance.filter(C1.class, "string1") );
    }
    
    @Test
    public void testFilter_filterProperty_byType_allow() {
        ClassFilter instance = new SimpleClassFilter(Integer.class, null);
        assertTrue( instance.filter(C1.class, "integer1") );
    }
    
    @Test
    public void testFilter_filterProperty_byType_denied() {
        ClassFilter instance = new SimpleClassFilter(Integer.class, null);
        assertFalse( instance.filter(C1.class, "string1") );
    }
    
    /*
     * Herlpers
     */

    @Test
    public void testUpperFirstChar() {
        assertEquals("Aaa", SimpleClassFilter.upperFirstChar("aaa"));
    }
}
