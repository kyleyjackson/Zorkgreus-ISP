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

    public int getAtk(){
        return atk;
    }

    public int getPrio(){
        return priority;
    }

    public int getDef(){
        return def;
    }

    public int getHp(){
        return hp;
    }

    public int getDodgeChance(){
        return dodge;
    }

    public void displayMonsterMessage(){
        System.out.println(desc);
    }
}
