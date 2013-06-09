package eu.pozoga.nice.classes.example;

import eu.pozoga.nice.classes.JavaClasses;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Named;

/**
 *
 * @author spozoga
 */
@Stateless
@LocalBean
@Named("javaClasses")
public class JavaClassesExample {

    public Collection<Class> getEuPozogaClasses() throws Exception{
        JavaClasses jClasses = new JavaClasses();
        return jClasses.getClasses("eu.pozoga.nice.classes.example");
    }
    
    
    public Collection<Class> getProjectClasses() throws Exception{
        JavaClasses jClasses = new JavaClasses();
        return jClasses.getClasses("");
    }
}
