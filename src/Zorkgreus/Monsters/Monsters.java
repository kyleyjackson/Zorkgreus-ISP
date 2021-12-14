package Zorkgreus.Monsters;

public class Monsters {
    /* 
    Monsters Superclass
    Each monster has its own priority 
    */
    private int atk;
    private int priority;
    private int def;
    private int hp;
    private int dodge;

    private String desc;
    private String monsterType;
    
    public Monsters(int atk, int priority, int def, int hp, int dodge, String desc){
        this.atk = atk;
        this.priority = priority;
        this.def = def;
        this.hp = hp;
        this.dodge = dodge;
        this.desc = desc;
    }

    public void displayMonsterMessage(){
        System.out.println(desc);
    }
}
