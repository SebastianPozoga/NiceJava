package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.test.C1;
import org.junit.*;
import static org.junit.Assert.*;

public class BeanFactoryTest {
    
    @Test
    public void testGetInstance_BaseClass() throws Exception {
        System.out.println("getInstance");
        Bean result = BeanFactory.getInstance(C1.class);
        assertTrue(result.getBaseClass().equals(C1.class));
    }
    
    @Test
    public void testGetInstance_NotNull() throws Exception {
        System.out.println("getInstance");
        Bean result = BeanFactory.getInstance(C1.class);
        assertNotNull(result);
    }

}
