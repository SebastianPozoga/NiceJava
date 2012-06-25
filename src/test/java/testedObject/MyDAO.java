package testedObject;

import eu.pozoga.nice.persist.DAO;
import eu.pozoga.nice.persist.ManagedDAO;

/**
 *
 * @author Sebastian Pożoga
 */
@ManagedDAO(entity=MyEntity.class)
public class MyDAO extends DAO{

    public MyDAO() {
        super(MyEntity.class);
    }
    
}
