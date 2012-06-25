package eu.pozoga.nice.bean;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.persist.DAO;
import org.junit.*;
import testedObject.MyManagedDAO;
import testedObject.MyEntity;
import testedObject.MyEntityWithoutDAO;

/**
 *
 * @author spozoga
 */
public class DaosTest {

    @Test
    public void testGetDAO() throws Exception {
        C.register(MyManagedDAO.class);
        Daos daos = new Daos();
        daos.init();
        //Test start
        DAO dao = daos.get(MyEntity.class);
        Assert.assertNotNull(dao);
        Assert.assertEquals(dao.getClass(), MyManagedDAO.class);
        //test end
        C.unregister(MyManagedDAO.class);
    }
    
    @Test
    public void testGetAutogenerate() throws Exception {
        Daos daos = new Daos();
        daos.init();
        //Test start
        DAO dao = daos.get(MyEntityWithoutDAO.class);
        Assert.assertNotNull(dao);
        Assert.assertEquals(MyEntityWithoutDAO.class, dao.getEntityClass());
        //test end
    }
}
