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
    private Inventory inventory;

    public Player(int prio, int atk, int def) {
        this.maxHP = 69420;
        this.HP = 69420;
        this.def = 10 + def;
        this.atk = atk;
        this.prio = prio;
        this.dodge = 1;
        this.gold = 100;

        inventory = new Inventory(150);

    }

    /*accessor method for inventory*/
    public Inventory getInventory() {
        return inventory;
    }

    /*accessor and mutator methods for HP */
    public int getPlayerHP() {
        return HP;
    }

    public void setPlayerHP(int n) {
        HP = n;
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

    /*accessor and mutator methods for maxHP */
    public int getPlayerMaxHP() {
        return maxHP;
    }

    public void setPlayerMaxHP(int n) {
        maxHP = n;
    }

    public void addPlayerMaxHP(int n) {
        maxHP += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " max HP! Total: " + getPlayerMaxHP());
        else
            System.out.println("You gained " + n + " max HP! Total: " + getPlayerMaxHP());
    }

    /*accessor and mutator methods for defence */
    public int getPlayerDef() {
        return def;
    }

    public void setPlayerDef(int n) {
        def = n;
    }

    public void addPlayerDefence(int n) {
        def += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " defence! Total: " + getPlayerDef());
        else
            System.out.println("You gained " + n + " defence! Total: " + getPlayerDef());

    }

    /*accessor and mutator methods for attack */
    public int getPlayerAtk() {
        return atk;
    }

    public void setPlayerAtk(int n) {
        atk = n;
    }

    public void addPlayerAttack(int n) {
        atk += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " attack! Total: " + getPlayerAtk());
        else
            System.out.println("You gained " + n + " attack! Total: " + getPlayerAtk());
    }

    /*accessor and mutator methods for dodge */
    public int getPlayerDodgeChange() {
        return dodge;
    }

    public void setPlayerDodge(int n) {
        dodge = n;
    }

    /*accessor and mutator methods for priority */
    public int getPlayerPrio() {
        return prio;
    }

    public void setPlayerPrio(int n) {
        prio = n;
    }

    public void addPlayerPriority(int n) {
        prio += n;
        if (n <= 0)
            System.out.println("You lost " + Math.abs(n) + " priority! Total: " + getPlayerPrio());
        else
            System.out.println("You gained " + n + " priority! Total: " + getPlayerPrio());
    }

    /*accessor and mutator methods for gold */
    public int getPlayerGold() {
        return gold;
    }

    public void addPlayerGold(int n) {
        gold += n;
        if (n <= 0)
            System.out.println("You spent " + Math.abs(n) + " gold! Total: " + getPlayerGold());
        else
            System.out.println("You got " + n + " gold! Total: " + getPlayerGold());
    }

    /*accessor and mutator methods for the extra life */
    public boolean getExtraLife() {
        return extraLife;
    }

    public void setExtraLife(boolean n) {
        extraLife = n;
    }  

    /**
     * checks if HP is bigger than 0
     * @return true if HP is bigger than 0
     */
    public boolean isAlive() {
        return HP > 0;
    }
    
    /**
     * increases/decreases HP and max HP by n
     * @param n number added to HP and max HP
     */
    public void incrementPlayerHP(int n) {
        maxHP += n;
        HP += n;
    }

    /**
     * normal attack function, damage dealt is randomized within a threshold based of attack
     * @return damage done
     */
    public int normalAttack() {
        int rand = (int) (Math.random() * 3) + 1;
        int randDmg = (int) (Math.random() * (atk / 10)) + 1;
        int dmg = atk;

        if (rand == 1) {
            if ((dmg - randDmg) < 0)
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

    /**
     * special attack function, damage dealt is weapon based
     * @param id number used to identify weapon to determine which special attack to use
     * @return damage done
     */
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
