/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.nice.classes;

import eu.pozoga.nice.classes.test.C1;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author spozoga
 */
public class BeanTest {
    
    public BeanTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetBaseClass() throws Exception {
        System.out.println("getBaseClass");
        Bean instance = new Bean(C1.class);
        Class expResult = null;
        Class result = instance.getBaseClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
