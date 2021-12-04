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

    public Boss(int atk, int priority, int def, int hp, int dodge) {
        this.atk = atk;
        this.priority = priority;
        this.def = def;
        this.hp = hp;
        this.dodge = dodge;
    }

    public boolean activateRage() {
        return hp <= (hp / 3);
    }

    public boolean isAlive() {
        return hp <= 0;
    }

    public int getHP(){
        return hp;
    }

    public void looseHP(int h){
        hp -= h;
    }

    public void addBossAtk(int addAtk) {
        atk += addAtk;
    }

    public void addBossPriority(int addPriority) {
        priority += addPriority;
    }

    public void addBossDefence(int addDef) {
        def += addDef;
    }

    public void addBossDodge(int addDodge){
        dodge += addDodge;
    }

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
