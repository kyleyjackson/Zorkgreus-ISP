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
    private int maxHP;
    private int dodge;

    private String desc;
    private String name;
    private String location;
    
    public Monsters(String name, int atk, int priority, int def, int hp, int maxHP, int dodge, String desc, String location){
        this.name = name;
        this.atk = atk;
        this.priority = priority;
        this.def = def;
        this.hp = hp;
        this.maxHP = hp;
        this.dodge = dodge;
        this.desc = desc;
        this.location = location;
    }

    public String getName(){
        return name;
    }

    public int getAtk(){
        return atk;
    }

    public String getLocation(){
        return location;
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

    public int getMaxHP(){
        return maxHP;
    }

    public int getDodgeChance(){
        return dodge;
    }

    public void setAtk(int value){
        atk = value;
    }

    public void setPrio(int value){
        priority = value;
    }

    public void setDef(int value){
        def = value;
    }

    public void setHp(int value){
        hp = value;
    }

    public void setMaxHp(int value){
        maxHP = value;
    }

    public boolean isAlive() {
        return hp >= 0;
    }

    public void displayMonsterMessage(){
        System.out.println(desc);
    }
}
