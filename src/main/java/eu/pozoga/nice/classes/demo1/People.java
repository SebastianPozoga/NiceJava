package eu.pozoga.nice.classes.demo1;

import eu.pozoga.nice.classes.Cloud;
import eu.pozoga.nice.classes.HardCloud;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.Init;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.inject.Named;


@Stateful
@LocalBean
@Named
public class People {

    protected Cloud cloud;
    
    @PostConstruct
    public void init() throws Exception{
        cloud = new HardCloud<Object>();
        addHuman(new Worker("Tom"));
        addHuman(new Worker("Arek"));
        addHuman(new Worker("Dawid"));
        addHuman(new Worker("Alladyn"));
        addHuman(new Fisherman("Daria"));
        addHuman(new Fisherman("Waldek"));
        addHuman(new Fisherman("Piotr"));
        addHuman(new Fisherman("Kris"));
    }

    public Cloud getCloud() {
        return cloud;
    }

    public Collection getAll() {
        return cloud.getObjects().values();
    }
    
    public void addHuman(Human human){
        cloud.put(human.name, human);
    }
}
