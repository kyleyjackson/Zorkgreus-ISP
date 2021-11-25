package Zorkgreus.Boss;

public class Boss {
    /*
    Boss Superclass
    Boss has overall increased class
    */
    private int atk;
    private int priority;
    private int def;
    private int hp;
    public Boss(int atk, int priority, int def, int hp){
        this.atk = atk;
        this.priority = priority;
        this.def = def;
        this.hp = hp;
    }
}
