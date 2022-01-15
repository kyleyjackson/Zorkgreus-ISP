package Zorkgreus;

public class Player {
    private int maxHP;
    private int HP;
    private int def;
    private int atk;
    private int dodge;
    private int prio;
    private int gold;

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
            System.out.println("You lost " + Math.abs(n) + " HP! Total: " + getPlayerHP());
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

    public int normalAttack() {
        int rand = (int) (Math.random() * 3) + 1;
        int randDmg = (int) (Math.random() * (atk / 10)) + 1;
        int dmg = atk;

        if (rand == 1) {
            if((dmg - randDmg) < 0)
                dmg = 0;
            else
                dmg -= randDmg;
        } else if (rand == 2) {
            dmg += randDmg;
        } else {
            dmg += 0;
        }
        return dmg;
    }

    public int specialAttack(int id) {
        int dmg = 0;

        if (id == 0) {
            System.out.println("You send out a volley of arrows... ");
            System.out.println();
            dmg = 10;
        } else if (id == 1) {
            System.out.println("You unleash a flurry of jabs... ");
            System.out.println();
            dmg = 17;
        } else if (id == 2) {
            System.out.println("You leap into the air, falling towards your opponent... ");
            System.out.println();
            dmg = 20;
        } else if (id == 3) {
            System.out.println("You toss your shield... ");
            System.out.println();
            dmg = 15;
        }
        return dmg;
    }
}
