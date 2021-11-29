package src.Zorkgreus.Monsters;

public class Monsters {
    /* 
    Monsters Superclass
    Each monster has its own priority 
    */
    private int atk;
    private int priority;
    private int def;
    private int hp;
    public Monsters(int atk, int priority, int def, int hp){
        this.atk = atk;
        this.priority = priority;
        this.def = def;
        this.hp = hp;
    }
    public void displayMonsterMessage(){
    }
}
