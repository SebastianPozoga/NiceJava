package eu.pozoga.nice.classes.demo1;


public class Human {
    
    public String name;
    
    public Integer money;

    public Human(String name) {
        this.name = name;
        money = new Integer(0);
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }
    
    public void vote(){
        money+=10;
    }

    public String getGroup(){
        return "Human";
    }
    
}
