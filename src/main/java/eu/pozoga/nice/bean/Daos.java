/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.nice.bean;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.classes.PFilter;
import eu.pozoga.nice.persist.DAO;
import eu.pozoga.nice.persist.ManagedDAO;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Sebastian Pożoga
 */
@Stateless
@LocalBean
public class Daos {

    private Map<Class, DAO> map;
    
    @PostConstruct
    public void init() throws Exception{
        //init daos
        map = new HashMap<Class, DAO>();
        Collection<Class> classes = C.select(new PFilter(DAO.class, ManagedDAO.class)).getClasses();
        for(Class c : classes){
            DAO dao = (DAO) c.newInstance();
            map.put(dao.getEntityClass(), dao);
        }
    }
    
    public DAO get(Class c){
        DAO dao = map.get(c);
        if(dao==null){
            dao = new SimpleDAO(c);
            map.put(c, dao);
        }
        return dao;
    }
    
    public static class SimpleDAO extends DAO{

        public SimpleDAO(Class entityClass) {
            super(entityClass);
        }
        
    }
}
