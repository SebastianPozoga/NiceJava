package eu.pozoga.nice.classes.demo1;


public class Worker extends Human {

    public Worker(String name) {
        super(name);
    }
    
    
    @Override
    public String getGroup(){
        return "Worker";
    }
    
}
