package Zorkgreus;

public class Player {
    private int maxHP;
    private int HP;
    private int def;
    private int atk;
    private int dodge;
    private int prio;
    private int gold;

    private int basePrio; // priority at the start of the game
    private int baseAtk; // attack at the start of the game
    private int baseDef; // defense at the start of the game

    private boolean extraLife = true;
    private boolean hyradliteGold = false;
    private Inventory inventory;
    private int maxWeight;

    public Player(int prio, int atk, int def) {
        this.maxHP = 50;
        this.HP = 50;
        this.def = 10 + def;
        this.atk = atk;
        this.prio = prio;
        this.dodge = 0;
        this.gold = 100;

        basePrio = prio;
        baseAtk = atk;
        baseDef = def;
        maxWeight = 100;
        inventory = new Inventory(maxWeight);

    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getPlayerHP() {
        return HP;
    }

    public int getPlayerMaxHP() {
        return maxHP;
    }

    public int getPlayerDef() {
        return def;
    }

    public int getPlayerAtk() {
        return atk;
    }

    public int getPlayerDodgeChange() {
        return dodge;
    }

    public int getPlayerPrio() {
        return prio;
    }

    public int getBasePrio() {
        return basePrio;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public boolean isAlive() {
        return HP >= 0;
    }

    public void setPlayerHP(int n) {
        HP = n;
    }

    public void setPlayerMaxHP(int n) {
        maxHP = n;
    }

    public void setPlayerDef(int n) {
        def = n;
    }

    public void setPlayerAtk(int n) {
        atk = n;
    }

    public void setPlayerDodge(int n) {
        dodge = n;
    }

    public void setPlayerPrio(int n) {
        prio = n;

    }

    public void setmaxWeight(int n) {
        maxWeight = n;
    }

    public void incrementPlayerHP(int n) {
        maxHP += n;
        HP += n;
    }

    public void addPlayerHP(int n) {
        if (n < 0) {
            HP += n;
            System.out.println("You lost" + n + " HP!");
        } else {
            if ((HP + n) > maxHP) {
                HP = maxHP;
                System.out.println("Your HP was maxed out!");
            } else {
                HP += n;
                System.out.println("You gained " + n + " HP!");
            }
        }
    }

    public void addPlayerMaxHP(int n) {
        maxHP += n;
        if (n < 0)
            System.out.println("You lost " + n + " max HP!");
        else
            System.out.println("You gained " + n + " max HP!");
    }

    public void addPlayerPriority(int n) {
        prio += n;
        if (n < 0)
            System.out.println("You lost " + n + " priority!");
        else
            System.out.println("Your gained " + n + " priority!");
    }

    public void addPlayerAttack(int n) {
        atk += n;
        if (n < 0)
            System.out.println("You lost " + n + " attack!");
        else
            System.out.println("Your gained " + n + " attack!");
    }

    public void addPlayerDefence(int n) {
        def += n;
        if (n < 0)
            System.out.println("You lost " + n + " defence!");
        else
            System.out.println("You gained " + n + " defence!");

    }

    public boolean getExtraLife() {
        return extraLife;
    }

    public void setExtraLife(boolean n) {
        extraLife = n;
    }

    public boolean getHydraliteGold() {
        return hyradliteGold;
    }

    public void setHydraliteGold(boolean n) {
        hyradliteGold = n;
    }

    public int getPlayerGold() {
        return gold;
    }

    public void addPlayerGold(int n) {
        gold += n;
    }
}
