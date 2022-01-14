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

    /**
     * displays info about the monster
     */
    public void monsterInfo(){
        System.out.println(name + " Stats: ");
        System.out.println("-----------------------");
        System.out.println("Attack: " + atk);
        System.out.println("Priority: " + priority);
        System.out.println("Defence: " + getDef());
        System.out.println("HP: " + hp + "/" + maxHP);
        System.out.println("Dodge Chance: " + dodge);
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

    public int getHP(){
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

    public void setHP(int value){
        hp = value;
    }

    public void setMaxHP(int value){
        maxHP = value;
    }
    
    public void addMonsterHP(int n){
        if((hp + n) > maxHP)
            hp = maxHP;
        else
            hp += n;
    }

    public void addMonsterMaxHP(int n){
        maxHP += n;
    }

    public void addMonsterPriority(int n){
        priority += n;
    }

    public void addMonsterAttack(int n){
        atk += n;

    }

    public void addMonsterDefence(int n){
        def += n;
    }

    public boolean isAlive() {
        return hp >= 0;
    }

    public void displayMonsterMessage(){
        System.out.println(desc);
    }

    public int monsterNormalAttack() {
        int rand = (int) (Math.random() * 3) + 1;
        int randDmg = (int) (Math.random() * (atk / 10));
        int dmg = atk;

        if (rand == 1) {
            dmg += randDmg;
        } else if (rand == 2) {
            dmg += randDmg;
        } else {
            dmg += 0;
        }
        return dmg;
    }
}
