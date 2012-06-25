package testedObject;

import eu.pozoga.nice.persist.DAO;
import eu.pozoga.nice.persist.ManagedDAO;

/**
 *
 * @author Sebastian Pożoga
 */
@ManagedDAO(entity=MyEntity.class)
public class MyManagedDAO extends DAO{

    public MyManagedDAO() {
        super(MyEntity.class);
    }
    
}
