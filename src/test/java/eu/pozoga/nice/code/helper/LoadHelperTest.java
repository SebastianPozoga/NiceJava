package eu.pozoga.nice.code.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;
import testedObject.MyEntity;
import testedObject.MyEntityOptions;

/**
 *
 * @author Sebastian Pożoga
 */
public class LoadHelperTest {

    @Test
    public void testLoad_Map_Object() throws Exception {
        //data
        Long owner = new Long(22);
        Date date = new Date();
        //object
        MyEntityOptions opt = new MyEntityOptions();
        opt.owner = owner;
        opt.setAddDate(date);
        //load
        Map map = new HashMap();
        LoadHelper.load(map, opt);
        //asserts
        assertEquals(owner, map.get("owner"));
        assertEquals(date, map.get("addDate"));
    }

    @Test
    public void testLoad_Object_Map() throws Exception {
        Long owner = new Long(22);
        Date date = new Date();
        Map map = new HashMap();
        map.put("owner", owner);
        map.put("addDate", date);
        MyEntityOptions opt = new MyEntityOptions();
        LoadHelper.load(opt, map);
        assertEquals(owner, opt.owner);
        assertEquals(date, opt.getAddDate());
    }

    @Test
    public void testLoad_Object_Object() throws Exception {
        //data
        Long owner = new Long(22);
        Date date = new Date();
        //object
        MyEntityOptions opt = new MyEntityOptions();
        opt.owner = owner;
        opt.setAddDate(date);
        //load to entity
        MyEntity ent = new MyEntity();
        LoadHelper.load(ent, opt);
        //asserts
        assertEquals(opt.owner, ent.getOwner());
        assertEquals(opt.getAddDate(), ent.getAddDate());
    }
}
