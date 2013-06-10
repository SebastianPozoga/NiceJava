package eu.pozoga.nice.classes.test;

import eu.pozoga.nice.classes.ex.TypeAnn;


@TypeAnn
public class C1 extends C3{
    
    private String string1;
    public String string2;
    private Integer integer1;
    public Integer integer2;

    public Integer getInteger1() {
        return integer1;
    }

    public void setInteger1(Integer fInteger1) {
        this.integer1 = fInteger1;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String fString1) {
        this.string1 = fString1;
    }
    
    
    
}
