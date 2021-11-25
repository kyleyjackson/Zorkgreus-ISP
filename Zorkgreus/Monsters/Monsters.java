package Zorkgreus.Monsters;

public class Monsters {
    /* 
    Monsters Superclass
    Each monster has its own priority 
    */
    private int atk;
    private int priority;
    private int def;
    public Monsters(int atk, int priority, int def){
        this.atk = atk;
        this.priority = priority;
        this.def = def;
    }
}
