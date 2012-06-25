package eu.pozoga.nice.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Sebastian Pożoga
 */
public class InputTag extends SimpleTagSupport {
    private Object bean;
    private String property;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
           out.print(assemblerCode(bean.getClass(), property));

        } catch (java.io.IOException ex) {
            throw new JspException("Error in InputTag tag", ex);
        }
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public void setProperty(String property) {
        this.property = property;
    }
    
    public static String assemblerCode(Class objectClass, String propertyName){
        return "<input "
                + "name='"+propertyName+"'"
                //+ "type='"++"'";
                + "/>";
    }
    
}
