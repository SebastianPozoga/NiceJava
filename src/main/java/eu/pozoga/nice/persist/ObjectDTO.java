package eu.pozoga.nice.persist;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.classes.ClassDesc;
import eu.pozoga.nice.code.helper.LoadHelper;
import java.util.Map;

/**
 *
 * @author Sebastian Pożoga
 */
public abstract class ObjectDTO implements DTO{

    private ClassDesc thisDesc;

    public ObjectDTO() throws Exception {
        thisDesc = C.get(getClass());
    }
    
    @Override
    public void load(Object o) throws Exception {
        LoadHelper.load(this, o);
    }

    @Override
    public void load(Map<String, Object> m) throws Exception {
        LoadHelper.load(this, m);
    }

    @Override
    public void set(String name, Object value) throws Exception {
        thisDesc.setProperty(this, name, value);
    }

    @Override
    public Object get(String name) throws Exception {
        return thisDesc.getProperty(this, name);
    }
    
}
