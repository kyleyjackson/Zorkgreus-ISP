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
    public boolean activateSpecial(){
        return hp<=(hp/2);
    }
    public int getBossAtk(){
        return atk;
    }
    public int getBossPriority(){
        return priority;
    }
    public void changeBossAtk(int newAtk){
        atk = newAtk;
    }
    public void changeBossPriority(int newPriority){
        priority = newPriority;
    }
}
