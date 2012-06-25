package eu.pozoga.nice.persist;

import eu.pozoga.nice.code.helper.ValidatorHelper;
import eu.pozoga.nice.code.helper.ValidatorHelper.ValidException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Sebastian Pożoga
 */
public abstract class DAO {

    public static final int DEFAULT_PAGE_SIZE = 20;
    
    @PersistenceContext
    protected EntityManager em;

    private Class<Entity> entityClass;

    public DAO() throws Exception {
        ManagedDAO ann = getClass().getAnnotation(ManagedDAO.class);
        if(ann==null){
            throw new Exception("If you want use empty constructor, you must add ManagedBean annjotation and set entity class");
        }
        this.entityClass = ann.entity();
    }
    
    

    public DAO(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    public DAO(Class entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }
    
    public void persist(Entity entity) throws ValidException, Exception{
        ValidatorHelper.valid(entity);
        em.persist(entity);
    }
    
    public void remove(Entity entity) throws Exception{
        em.remove(entity);
    }
    
    public Entity find(long id) throws Exception{
        return em.find(entityClass, id);
    }
    
    public List<Entity> getPage(int page) throws Exception{
        return getPage(page, DEFAULT_PAGE_SIZE);
    }
    
    public List<Entity> getPage(int page, int pagesize) throws Exception{
        Query createQuery = getPageQuery();
        createQuery.setFirstResult(page);
        createQuery.setMaxResults(pagesize);
        return createQuery.getResultList();
    }
    
    protected Query getPageQuery(){
        return em.createQuery("SELECT o FROM "+entityClass.getSimpleName()+" o ORDER BY o.id DESC");
    }
    
    public List<Entity> getPageByOwner(Long owner, int page) throws Exception{
        return getPageByOwner(owner, page, DEFAULT_PAGE_SIZE);
    }
    
    public List<Entity> getPageByOwner(Long owner, int page, int pagesize) throws Exception{
        Query createQuery = getPageByOwnerQuery();
        createQuery.setParameter("owner", owner);
        createQuery.setFirstResult(page);
        createQuery.setMaxResults(pagesize);
        return createQuery.getResultList();
    }
    
    protected Query getPageByOwnerQuery(){
        return em.createQuery("SELECT o FROM "+entityClass.getSimpleName()+" o WHERE o.owner=:owner  ORDER BY o.id DESC");
    }
    

    public Class<Entity> getEntityClass() {
        return entityClass;
    }
    
    /*public void load(Object o) throws Exception{
        LoadObjectHelper.load(this, o);
    }
    
    public void load(Map<String, Object> m) throws Exception{
        LoadObjectHelper.load(this, m);
    }*/
    
}
