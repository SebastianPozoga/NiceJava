package eu.pozoga.nice.classes;


public class HardCloudTest extends AbstractCloudTest{

    @Override
    public AbstractCloud newInstance() throws Exception {
        return new HardCloud();
    }
    
    
}
