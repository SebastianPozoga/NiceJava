package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.ex.AbstractClass;
import eu.pozoga.nice.classes.ex.TestInterface;
import eu.pozoga.nice.classes.ex.TypeAnn;
import eu.pozoga.nice.classes.test.C1;
import eu.pozoga.nice.classes.test.C3;
import org.junit.*;
import static org.junit.Assert.*;

public class SimplePackFilterTest {
    
    @Test
    public void testByAnn_allow() {
        PackFilter filter = new SimplePackFilter(null, TypeAnn.class);
        assertTrue(filter.filter(C1.class));
    }
    
    @Test
    public void testByAnn_denied() {
        PackFilter filter = new SimplePackFilter(null, TypeAnn.class);
        assertFalse(filter.filter(C3.class));
    }

    @Test
    public void testByType_allow() {
        PackFilter filter = new SimplePackFilter(C1.class, null);
        assertTrue(filter.filter(C1.class));
    }
    
    @Test
    public void testByType_denied() {
        PackFilter filter = new SimplePackFilter(C1.class, null);
        assertFalse(filter.filter(C3.class));
    }

    @Test
    public void testAbstract_allow() {
        SimplePackFilter filter = new SimplePackFilter(null, null);
        filter.setAllowAbstract(true);
        assertTrue(filter.filter(AbstractClass.class));
    }
    
    @Test
    public void testAbstract_denied() {
        SimplePackFilter filter = new SimplePackFilter(null, null);
        filter.setAllowAbstract(false);
        assertFalse(filter.filter(AbstractClass.class));
    }

    @Test
    public void testAllowInterface_allow() {
        SimplePackFilter filter = new SimplePackFilter(null, null);
        filter.setAllowAbstract(true);
        boolean result = filter.filter(TestInterface.class);
        assertTrue(result);
    }
    
    @Test
    public void testAllowInterface_denied() {
        SimplePackFilter filter = new SimplePackFilter(null, null);
        filter.setAllowAbstract(false);
        assertFalse(filter.filter(TestInterface.class));
    }

}
