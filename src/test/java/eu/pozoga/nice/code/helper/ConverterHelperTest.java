/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.nice.code.helper;

import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author spozoga
 */
public class ConverterHelperTest {
    

    @Test
    public void testStringToInteger() throws Exception {
        String from = "122";
        Integer to = (Integer) ConverterHelper.convert(from, Integer.class);
        assertEquals(to, new Integer(122));
    }
    
    @Test
    public void testStringToInt() throws Exception {
        String from = "122";
        int to = (Integer) ConverterHelper.convert(from, int.class);
        assertEquals(to, 122);
    }

}
