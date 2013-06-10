package eu.pozoga.nice.classes;

import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassPack {

    private Collection<Class> clases;

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

    protected void add(Class c) {
        clases.add(c);
    }

    public void remove(Class c) {
        clases.remove(c);
    }

    /**
     * Select classes by filter.
     *
     * @param filter
     * @return selected subset.
     */
    public ClassPack select(PackFilter filter) {
        Collection set = new HashSet();
        for (Class c : clases) {
            if (filter.filter(c)) {
                set.add(c);
            }
        }
        return new ClassPack(set);
    }

    public Collection invoke(Collection objects, String methodName, Object[] args, ClassFilter filter) {
        Collection results = new HashSet();
        for (Object object : objects) {
            if(!clases.contains(object.getClass())){
                continue;
            }
            try {
                Bean bean = BeanFactory.getInstance(object.getClass());
                results.addAll(bean.invoke(object, methodName, args, filter));
            } catch (Exception ex) {
                Logger.getLogger(ClassPack.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results;
    }
    
    public void setProperty(Collection objects, String propertyName, Object value, ClassFilter filter) {
        for (Object object : objects) {
            if(!clases.contains(object.getClass())){
                continue;
            }
            try {
                Bean bean = BeanFactory.getInstance(object.getClass());
                bean.setProperty(object, propertyName, value, filter);
            } catch (Exception ex) {
                Logger.getLogger(ClassPack.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Collection getProperty(Collection objects, String propertyName, ClassFilter filter) {
        Collection results = new HashSet();
        for (Object object : objects) {
            if(!clases.contains(object.getClass())){
                continue;
            }
            try {
                Bean bean = BeanFactory.getInstance(object.getClass());
                results.add(bean.getProperty(object, propertyName, filter));
            } catch (Exception ex) {
                Logger.getLogger(ClassPack.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return results;
    }
    
    public Collection filter(Collection objects) {
        Collection results = new HashSet();
        for (Object object : objects) {
            if(!clases.contains(object.getClass())){
                continue;
            }
            results.add(object);
        }
        return results;
    }
}
