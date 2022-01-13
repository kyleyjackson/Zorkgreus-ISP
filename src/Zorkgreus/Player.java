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
        inventory = new Inventory(150);

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

    public int getPlayerGold() {
        return gold;
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

    public void incrementPlayerHP(int n) {
        maxHP += n;
        HP += n;
    }

    public void addPlayerHP(int n) {
        if (n <= 0) {
            HP += n;
            System.out.println("You lo st" + Math.abs(n) + " HP! Total: " + getPlayerHP());
        } else {
            if ((HP + n) > maxHP) {
                HP = maxHP;
                System.out.println("Your HP was maxed out!");
            } else {
                HP += n;
                System.out.println("You gained " + n + " HP! Total: " + getPlayerHP());
            }
        }
    }

    public void addPlayerMaxHP(int n) {
        maxHP += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " max HP! Total: " + getPlayerMaxHP());
        else
            System.out.println("You gained " + n + " max HP! Total: " + getPlayerMaxHP());
    }

    public void addPlayerPriority(int n) {
        prio += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " priority! Total: " + getPlayerPrio());
        else
            System.out.println("Your gained " + n + " priority! Total: " + getPlayerPrio());
    }

    public void addPlayerAttack(int n) {
        atk += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " attack! Total: " + getPlayerAtk());
        else
            System.out.println("Your gained " + n + " attack! Total: " + getPlayerAtk());
    }

    public void addPlayerDefence(int n) {
        def += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " defence! Total: " + getPlayerDef());
        else
            System.out.println("You gained " + n + " defence! Total: " + getPlayerDef());

    }

    public void addPlayerGold(int n) {
        gold += n;
        if (n <= 0)
            System.out.println("You spent " + Math.abs(n) + " gold! Total: " + getPlayerGold());
        else
            System.out.println("You got " + n + " gold! Total: " + getPlayerGold());
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

}
