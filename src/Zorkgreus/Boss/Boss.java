package Zorkgreus.Boss;

public class Boss {
    /*
     * Boss Superclass
     * Boss has overall increased stats
     */
    private int atk;
    private int priority;
    private int def;
    private int hp;
    private int dodge;
    private int maxHP;

    public Boss(int atk, int priority, int def, int hp, int maxHP, int dodge) {
        this.atk = atk;
        this.priority = priority;
        this.def = def;
        this.hp = hp;
        this.dodge = dodge;
        this.maxHP = maxHP;
    }

    //accessor method for attack
    public int getAtk(){
        return atk;
    }

    //accessor method for priority
    public int getPrio(){
        return priority;
    }

    //accessor method for defence
    public int getDef(){
        return def;
    }

    //accessor method for dodge
    public int getDodgeChance(){
        return dodge;
    }

    //checks to see if hp is below 33%
    public boolean activateRage() {
        return hp <= (hp / 3);
    }

    //checks to see if the boss is alive
    //returns a boolean
    public boolean isAlive() {
        return hp >= 0;
    }

    //mutator method for hp
    public void setHP(int health){
        hp = health;
    }

    //accessor method for HP
    public int getHP(){
        return hp;
    }

    //accessor method for maxHP
    public int getMaxHP(){
        return maxHP;
    }

    //compares the maxHP, to the new HP
    //param: percentage (how much % you want to compare it by), dmgDone (how much damage is done)
    public boolean compareHP(double percentage, int dmgDone){
        int decrement = (int)(getMaxHP()*percentage);
        int temp = hp;
        if (getMaxHP() == hp)
            temp -= 0.1;
        hp -= dmgDone;
        return (hp/decrement != temp/decrement);
    }

    //mutator method for HP
    public void addBossHP(int addHP){
        hp += addHP;
    }

   //mutator method for attack
    public void addBossAtk(int addAtk) {
        atk += addAtk;
    }

    public void setBossPriority(int prioritySet){
        priority = prioritySet;
    }

    //mutator method for priority
    public void addBossPriority(int addPriority) {
        priority += addPriority;
    }

    //mutator method for defence
    public void setBossDef(int setDef){
        def = setDef;
    }
 
    //mutator method for defence
    public void addBossDefence(int addDef) {
        def += addDef;
    }

    //mutator method for dodge chance
    public void addBossDodge(int addDodge){
        dodge += addDodge;
    }

    //all methods below exist just for polymorphism - allowing subclasses to override
    public void bossRage(){
    }

    public void specialBossAttack() {
    }

    public void specialBossAttack(int h){
    }

    public void finalBossAttack() {
    }

    public void displayBossRage(){
    }

    public void displayBossSpecialAttack() {
    }

    public void displayBossFinalAttack() {
    }

    public void displayBossMessage() {
    }
}
