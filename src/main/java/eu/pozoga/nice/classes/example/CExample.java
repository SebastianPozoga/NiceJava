package eu.pozoga.nice.classes.example;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.classes.SimplePackFilter;
import eu.pozoga.nice.classes.ClassPack;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Named;


@Stateless
@LocalBean
@Named
public class CExample {

    public ClassPack getPack() throws Exception{
        return C.getInstance().getPack();
    }
    
    /**
     * Return all object with @Name annotation. Result is return all bean.
     * 
     * @return
     * @throws Exception 
     */
    public ClassPack getSelect() throws Exception{
        return C.getInstance().select(new SimplePackFilter(null, Named.class));
    }
    
}
