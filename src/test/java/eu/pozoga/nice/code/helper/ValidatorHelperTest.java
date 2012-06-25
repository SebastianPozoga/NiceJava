/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.nice.code.helper;

import java.beans.IntrospectionException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.xml.crypto.Data;
import org.junit.*;
import static org.junit.Assert.*;
import testedObject.MyEntity;
import testedObject.MyEntityOptions;

/**
 *
 * @author spozoga
 */
public class ValidatorHelperTest {

    @Test
    public void testValid() throws Exception {
        //Object 
        MyEntityOptions opt = new MyEntityOptions();
        opt.setAddDate(new Date());
        //valid
        try {
            ValidatorHelper.valid(opt);
        } catch (ValidatorHelper.ValidException validException) {
            fail("Test: should be validate.");
        }
    }
    
    @Test
    public void testNoValid() throws Exception {
        //Object 
        MyEntityOptions opt = new MyEntityOptions();
        //valid
        try {
            ValidatorHelper.valid(opt);
        } catch (ValidatorHelper.ValidException validException) {
            return;
        }
        fail("Test: should not be validate.");
    }

}
