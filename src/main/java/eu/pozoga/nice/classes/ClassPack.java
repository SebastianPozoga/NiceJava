package eu.pozoga.nice.classes;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Sebastian Pożoga
 */
public class ClassPack {

    Collection<Class> clases;

    public ClassPack(Collection<Class> clases) {
        this.clases = clases;
    }

    /**
     * Get java class collection.
     *
     * @return Collection<Class> classes in the pack.
     */
    public Collection<Class> getClasses() {
        return clases;
    }
    
    protected void add(Class c){
        clases.add(c);
    }
    
    public void remove(Class c){
        clases.remove(c);
    }

    /**
     * Select classes by filter.
     * 
     * @param filter
     * @return selected subset. 
     */
    public ClassPack select(ClassFilterInterface filter) {
        Collection set = new HashSet();
        for (Class c : clases) {
            if (filter.filter(c)) {
                set.add(c);
            }
        }
        return new ClassPack(set);
    }
}
