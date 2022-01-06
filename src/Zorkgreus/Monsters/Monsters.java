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
    
    public void addPlayerHP(int n){
        if(n < 0){
            hp -= n;
            System.out.println("The monster lost" + n + " HP!");
        }
        else{
            if((hp + n) > maxHP) {
                hp = maxHP;
                System.out.println("The monster's HP was maxed out!");
            } else {
                hp += n;
                System.out.println("The monster gained " + n + " HP!");
            }
        }
    }

    public void addPlayerMaxHP(int n){
        if(n < 0){
            maxHP -= n;
            System.out.println("The monster lost " + n + " max HP!");
        }
        else{
            maxHP += n;
            System.out.println("The monster gained " + n + " max HP!");
        }
    }

    public void addPlayerPriority(int n){
        if(n < 0){
            priority -= n;
            System.out.println("The monster lost " + n + " priority!");
        }
        else{
            priority += n;
            System.out.println("The monster gained " + n + " priority!");
        }
    }

    public void addPlayerAttack(int n){
        if(n < 0){
            atk -= n;
            System.out.println("The monster lost " + n + " attack!");
        }
        else{
            atk += n;
            System.out.println("The monster gained " + n + " attack!");
        }
    }

    public void addPlayerDefence(int n){
        if(n < 0){
            def -= n;
            System.out.println("The monster lost " + n + " defence!");
        }
        else{
            def += n;
            System.out.println("The monster gained " + n + " defence!");
        }
    }

    public boolean isAlive() {
        return hp >= 0;
    }

    public void displayMonsterMessage(){
        System.out.println(desc);
    }

    public int monsterNormalAttack() {
        int rand = (int) (Math.random() * 3) + 1;
        int randDmg = (int) (Math.random() * (atk / 10)) + 1;
        int dmg = atk;

        if (rand == 1) {
            dmg -= randDmg;
        } else if (rand == 2) {
            dmg += randDmg;
        } else {
            dmg += 0;
        }
        return dmg;
    }
}
