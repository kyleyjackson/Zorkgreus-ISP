package Zorkgreus.Boss;

import Zorkgreus.Player;

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

    /**
     * 
     * @param dmgDealt the amount of supposed damage
     * @return the new damage, either decreased, increased or the same
     */
    public int attack(int dmgDealt) {
        int dmg = dmgDealt;
        int rand = (int) (Math.random() * 3) + 1;
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
     * compares the maxHP, to the new HP
     * 
     * @param percentage percentage to compare the old and new hp
     * @param dmgDone    damage dealt
     * @return if newHP is smaller than the percentage of maxHP
     */
    public boolean compareHP(double percentage, int dmgDone) {
        int decrement = (int) (getMaxHP() * percentage);
        int temp = hp;
        if (getMaxHP() == hp)
            temp -= 0.1;
        hp -= dmgDone;
        return (hp / decrement != temp / decrement);
    }

    /**
     * Checks if rage should be activated - for bosses only
     * 
     * @return if the current hp is lower than 33% of the original
     */
    public boolean activateRage() {
        return hp <= (maxHP / 3);
    }

    public boolean isAlive() {
        return hp >= 0;
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

    // all methods below exist just for polymorphism - allowing subclasses to override
    public void bossRage() {
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
