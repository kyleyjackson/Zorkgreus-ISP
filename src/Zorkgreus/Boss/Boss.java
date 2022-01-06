package Zorkgreus.Boss;

import java.util.ArrayList;

import Zorkgreus.Player;

public class Boss {
    /*
     * Boss Superclass
     * Boss has overall increased stats
     */
    
    //attributes for the boss
    private int atk;
    private int priority;
    private int def;
    private int hp;
    private int dodge;
    private int maxHP;
    private String name;
    //attributes for the special attack methods
    private boolean makeArray = true;
    private ArrayList<Integer> decrements = new ArrayList<>();

    public Boss(int atk, int priority, int def, int hp, int maxHP, int dodge, String name) {
        this.atk = atk;
        this.priority = priority;
        this.def = def;
        this.hp = hp;
        this.dodge = dodge;
        this.maxHP = maxHP;
        this.name = name;
    }

    /**
     * 
     * @param dmgDealt the amount of supposed damage
     * @return the new damage, either decreased, increased or the same
     */
    public int attack(int dmgDealt) {
        int dmg = dmgDealt;
        int rand = (int) (Math.random() * 3 + 1);
        int randDmg = (int) (Math.random() * (dmgDealt / 10)) + 1;

        if (rand == 1) {
            dmg -= randDmg;
        } else if (rand == 2) {
            dmg += randDmg;
        } else {
            dmg += 0;
        }
        return dmg;
    }

    /**
     * Checks to see if the hp is smaller than the last index of decrements, if so, removes it
     * 
     * @param decrements arraylist of integers based of the percentage of decrements
     * @param dmgDone the damage dealt
     * @return decrements
     */
    public ArrayList<Integer> compareHP(ArrayList<Integer> decrements, int dmgDone) {
        int last = decrements.get(decrements.size()-1);
        if((hp-=dmgDone)<=last){
            decrements.remove(decrements.size()-1);
        }
        return decrements;
    }

    /**
     * Checks if rage should be activated - for bosses only
     * 
     * @return if the current hp is lower than 33% of the original
     */
    public boolean activateRage() {
        return hp <= (maxHP / 3);
    }

    /**
     * displays info about the boss
     */
    public void bossInfo(){
        System.out.println(name);
        System.out.println("-----------------------");
        System.out.println("Attack: " + getAtk());
        System.out.println("Priority: " + getPrio());
        System.out.println("Defence: " + getDef());
        System.out.println("HP: " + getHP());
        System.out.println("Max HP: " + getMaxHP());
        System.out.println("Dodge Chance: " + getDodge());
    }

    public boolean isAlive() {
        return hp >= 0;
    }

    public String getName(){
        return name;
    }

    // accessor and mutator method for attack
    public int getAtk() {
        return atk;
    }

    public void addBossAtk(int addAtk) {
        atk += addAtk;
    }

    // accessor and mutator methods for priority
    public int getPrio() {
        return priority;
    }

    public void setBossPriority(int prioritySet) {
        priority = prioritySet;
    }

    public void addBossPriority(int addPriority) {
        priority += addPriority;
    }

    // accessor and mutator methods for defence
    public int getDef() {
        return def;
    }

    public void setBossDef(int setDef) {
        def = setDef;
    }

    public void addBossDefence(int addDef) {
        def += addDef;
    }

    // accessor and mutator method for dodge
    public int getDodge() {
        return dodge;
    }

    public void addBossDodge(int addDodge) {
        dodge += addDodge;
    }

    // accessor and mutator methods for HP
    public int getHP() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setHP(int health) {
        hp = health;
    }

    public void addBossHP(int addHP) {
        hp += addHP;
    }

    //accessor and mutator methods for makeArray
    public boolean getMakeArray(){
        return makeArray;
    }

    public void setMakeArray(boolean setArray){
        makeArray = setArray;
    }

    //accessor and mutator methods for decrements
    public ArrayList<Integer> getDecrements(){
        return decrements;
    }
    
    public void addToDecrements(int index, int num){
        decrements.add(index, num);
    }

    public int decrementsSize(){
        return decrements.size();
    }

    // all methods below exist just for polymorphism - allowing subclasses to override
    public void bossRage() {
    }

    public void bossRage(Player player){
    }

    public void specialBossAttack(int dmg) {
    }

    public void specialBossAttack(int dmg, Player player) {
    }

    public void finalBossAttack() {
    }

    public void finalBossAttack(Player player) {
    }

    public void displayBossRage() {
    }

    public void displayBossSpecialAttack() {
    }

    public void displayBossFinalAttack() {
    }

    public void displayBossMessage() {
    }
}
