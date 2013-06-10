package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.ex.TestInterface;
import eu.pozoga.nice.classes.ex.TypeAnn;
import eu.pozoga.nice.classes.test.C1;
import eu.pozoga.nice.classes.test.C2;
import eu.pozoga.nice.classes.test.C3;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;

public class ClassPackTest {
    
    @Test
    public void testGetClasses() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        classes.add(C3.class);
        ClassPack instance = new ClassPack(classes);
        Collection result = instance.getClasses();
        assertEquals(result, classes);
    }

    @Test
    //curiosity - DON'T Remember
    public void testAdd_curiosity() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        ClassPack instance = new ClassPack(classes);
        instance.add(C2.class);
        //(curiosity)
        // - Use the some object. don't add the object twice
        //classes.add(C2.class);
        assertEquals(classes, instance.getClasses());
    }
    
    @Test
    public void testAdd_contains() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        Set<Class> result = new HashSet(classes);
        ClassPack instance = new ClassPack(classes);
        instance.add(C2.class);
        result.add(C2.class);
        assertEquals(result, instance.getClasses());
    }

    @Test
    public void testRemove() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        Set<Class> expResult = new HashSet();
        expResult.add(C1.class);
        ClassPack instance = new ClassPack(classes);
        instance.remove(C2.class);
        assertEquals(expResult, instance.getClasses());
    }

    @Test
    public void testSelect_byAnnotation() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        classes.add(C3.class);
        Set<Class> expResult = new HashSet();
        expResult.add(C1.class);
        expResult.add(C2.class);
        PackFilter filter = new SimplePackFilter(null, TypeAnn.class);
        ClassPack instance = new ClassPack(classes);
        assertEquals(expResult, instance.select(filter).getClasses());
    }

    @Test
    public void testSelect_byClass() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        classes.add(C3.class);
        Set<Class> expResult = new HashSet();
        expResult.add(C1.class);
        PackFilter filter = new SimplePackFilter(C1.class, null);
        ClassPack instance = new ClassPack(classes);
        assertEquals(expResult, instance.select(filter).getClasses());
    }
    
    @Test
    public void testSelect_bySuperClass() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        classes.add(C3.class);
        Set<Class> expResult = new HashSet();
        expResult.add(C1.class);
        expResult.add(C3.class);
        PackFilter filter = new SimplePackFilter(C3.class, null);
        ClassPack instance = new ClassPack(classes);
        assertEquals(expResult, instance.select(filter).getClasses());
    }
    
    @Test
    public void testSelect_byInterface() {
        Set<Class> classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        classes.add(C3.class);
        Set<Class> expResult = new HashSet();
        expResult.add(C2.class);
        PackFilter filter = new SimplePackFilter(TestInterface.class, null);
        ClassPack instance = new ClassPack(classes);
        assertEquals(expResult, instance.select(filter).getClasses());
    }

    @Test
    public void testInvoke_C1C2Only() {
        //Object
        C1 o1 = new C1();
        C2 o2 = new C2();
        C3 o3 = new C3();
        //Objects
        Collection objects = new HashSet();
        objects.add(o1);
        objects.add(o2);
        objects.add(o3);
        //Result
        Collection expResult = new HashSet();
        expResult.add( o2.myText() );
        expResult.add( o1.myText() ); //extend C3
        //Classes
        Collection classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        //classes.add(C3.class); - No added.
        //Don't run method in C3 class.
        //Test
        ClassPack instance = new ClassPack(classes);
        Collection result = instance.invoke(objects, "myText", new Object[]{}, null);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInvoke_C1C2C3C3() {
        //Object
        C1 o1 = new C1();
        C2 o2 = new C2();
        C3 o3 = new C3();
        C3 o32 = new C3();
        //Objects
        Collection objects = new HashSet();
        objects.add(o1);
        objects.add(o2);
        objects.add(o3);
        objects.add(o32);
        //Result
        Collection expResult = new HashSet();
        expResult.add( o1.myText() );
        expResult.add( o2.myText() );
        expResult.add( o3.myText() );
        expResult.add( o32.myText() );
        //Classes
        Collection classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        classes.add(C3.class);
        //Test
        ClassPack instance = new ClassPack(classes);
        Collection result = instance.invoke(objects, "myText", new Object[]{}, null);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProperty() {
        //values
        String oldValue = "oldValue";
        String newValue = "newValue";
        //Object
        C1 o1 = new C1();
        C2 o2 = new C2();
        C3 o3 = new C3();
        o1.setString3(oldValue);
        o3.setString3(oldValue);
        o2.string3 = oldValue;
        //Objects
        Collection objects = new HashSet();
        objects.add(o1);
        objects.add(o2);
        objects.add(o3);
        //Classes
        Collection classes = new HashSet();
        classes.add(C1.class);
        classes.add(C2.class);
        //Test
        ClassPack instance = new ClassPack(classes);
        instance.setProperty(objects, "string3", newValue, null);
        assertTrue(o1.getString3().equals(newValue));
        assertTrue(o2.string3.equals(newValue));
        assertTrue(o3.getString3().equals(oldValue)); //no in class list
    }

    /*@Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Collection objects = null;
        String methodName = "";
        ClassFilter filter = null;
        ClassPack instance = null;
        Collection expResult = null;
        Collection result = instance.getProperty(objects, methodName, filter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFilter() {
        System.out.println("filter");
        Collection objects = null;
        ClassPack instance = null;
        Collection expResult = null;
        Collection result = instance.filter(objects);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
