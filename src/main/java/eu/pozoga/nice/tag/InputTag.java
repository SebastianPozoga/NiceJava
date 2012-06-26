package eu.pozoga.nice.tag;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.classes.ClassDesc;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Sebastian Pożoga
 */
public class InputTag extends SimpleTagSupport {

    private Collection<Class> TEXT_CLASSES;

    private Object bean;
    private String property;
    
    
    public InputTag() {
        Collection c = Arrays.asList(new Class[]{String.class});
        TEXT_CLASSES = new HashSet(c);
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        try {
            ClassDesc desc = C.get(bean.getClass());
            Class type = desc.getPropertyType(property);
            if (TEXT_CLASSES.contains(type)){
                out.print( InputTextTag.toCode(property) );
                return;
            }
            throw new JspException("Class "+bean.getClass()+" is not supported by InputTag");
        } catch (Exception ex) {
            throw new JspException(ex);
        }
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
