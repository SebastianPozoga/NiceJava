/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.nice.code.helper;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Pożoga
 */
public class StringHelperTest {
    
    @Test
    public void testUpperFirstChar() {
        String from = "first";
        String to = "First";
        assertEquals(to, StringHelper.UpperFirstChar(from));
    }

    @Test
    public void testLowerFirstChar() {
        String from = "First";
        String to = "first";
        assertEquals(to, StringHelper.LowerFirstChar(from));
    }
}
