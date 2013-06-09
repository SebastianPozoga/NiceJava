package eu.pozoga.nice.classes;


public class WeakCloudTest extends AbstractCloudTest {

    @Override
    public AbstractCloud newInstance() throws Exception {
        return new WeakCloud();
    }
    
}
