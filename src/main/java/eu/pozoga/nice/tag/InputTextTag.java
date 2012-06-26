/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.nice.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author spozoga
 */
public class InputTextTag extends SimpleTagSupport {
    private String property;
    private Object bean;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print( toCode(property) );
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
    public static String toCode(String propertyName){
        return "<input type='text' "
                + "name='"+propertyName+"'"
                //+ "type='"++"'";
                + "/>";
    }
}
