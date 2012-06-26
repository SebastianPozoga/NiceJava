package eu.pozoga.nice.bean;

import eu.pozoga.nice.classes.C;
import eu.pozoga.nice.persist.DAO;
import org.junit.*;
import testedObject.MyDAO;
import testedObject.MyEntity;
import testedObject.MyEntityWithoutDAO;

/**
 *
 * @author spozoga
 */
public class DaosTest {

    @Test
    public void testGetDAO() throws Exception {
        C.register(MyDAO.class);
        Daos daos = new Daos();
        daos.init();
        //Test start
        DAO dao = daos.get(MyEntity.class);
        Assert.assertNotNull(dao);
        Assert.assertEquals(dao.getClass(), MyDAO.class);
        //test end
        C.unregister(MyDAO.class);
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
