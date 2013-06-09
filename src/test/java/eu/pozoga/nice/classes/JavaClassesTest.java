package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.test.C1;
import eu.pozoga.nice.classes.test.C2;
import eu.pozoga.nice.classes.test.C3;
import java.util.Collection;
import java.util.HashSet;
import org.junit.*;
import static org.junit.Assert.*;

public class JavaClassesTest {
    
    @Test
    public void testGetClasses() throws Exception {
        System.out.println("getClasses");
        final String packageName = "eu.pozoga.nice.classes.test";
        Collection expResult = new HashSet();
        expResult.add(C1.class);
        expResult.add(C2.class);
        expResult.add(C3.class);
        JavaClasses instance = new JavaClasses();
        Collection result = instance.getClasses(packageName);
        assertEquals(expResult, result);
    }

}
