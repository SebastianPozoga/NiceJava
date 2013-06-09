package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.test.C1;
import eu.pozoga.nice.classes.test.C2;
import eu.pozoga.nice.classes.test.C3;
import eu.pozoga.nice.classes.testAnn.testAnn;
import java.util.Collection;
import java.util.HashSet;
import org.junit.*;
import static org.junit.Assert.*;

public class CTest {

    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        C instance = new C();
        instance.init();
        assertNotNull(instance.mainPack);
    }

    @Test
    public void testGetPack_instanceof() throws Exception {
        System.out.println("getPack");
        C instance = new C();
        ClassPack result = instance.getPack();
        assertTrue(result instanceof ClassPack);
    }

    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        C instance = new C();        
        Collection expResult = new HashSet();
        expResult.add(C1.class);
        expResult.add(C2.class);
        SimplePackFilter filter = new SimplePackFilter(null, testAnn.class);
        ClassPack result = instance.select(filter);
        assertEquals(expResult, result.getClasses());
    }

    @Test
    public void testGetInstance_notNull() throws Exception {
        System.out.println("getInstance");
        C result = C.getInstance();
        assertNotNull(result);
    }
    
    @Test
    public void testGetInstance_instanceof() throws Exception {
        System.out.println("getInstance");
        C result = C.getInstance();
        assertTrue(result instanceof C);
    }
}
