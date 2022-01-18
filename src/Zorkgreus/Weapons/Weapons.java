package Zorkgreus.Weapons;

public class Weapons {
    /**
     * *Each weapon has it's own set priority (1-20)
     * *Higher DMG = Lower prio* (general rule of thumb*)
     */
    private String weaponName;
    private int prio;
    private int atk;
    private int def;
    private int speAtkDmg;
    private int id;
    private String speAtkName;
    private String desc;

    private int basePrio; // priority at the start of the game
    private int baseAtk; // attack at the start of the game
    private int baseDef; // defense at the start of the game

    public Weapons(String weaponName, int prio, int atk, int def, int speAtkDmg, int id, String speAtkName, String desc) {
        this.weaponName = weaponName;
        this.prio = prio;
        this.atk = atk;
        this.def = def;
        this.speAtkDmg = speAtkDmg;
        this.id = id;
        this.speAtkName = speAtkName;
        this.desc = desc;

        basePrio = prio;
        baseAtk = atk;
        baseDef = def;
    }

    /*accessor method for weapon name */
    public String getName() {
        return weaponName;
    }

    /*accessor and mutator methods for special attack */
    public String getSpeAtkName() {
        return speAtkName;
    }

    public int getSpeAtkDmg() {
        return speAtkDmg;
    }

    /*accessor method for weapon description */
    public String getDescription() {
        return desc;
    }

    /*accessor and mutator methods for weapon attack */
    public int getAtk() {
        return atk;
    }

    public void changeAtk(int n) {
        atk = n;
        System.out.println("Your attack changed to " + n + "!");
    }

    /*accessor and mutator methods for weapon priority */
    public int getPriority() {
        return prio;
    }

    public void changePrio(int n) {
        prio = n;
        System.out.println("Your priority changed to " + n + "!");
    }

    /*accessor and mutator methods for weapon defence */
    public int getDef() {
        return def;
    }

    public void changeDef(int n) {
        def = n;
        System.out.println("Your defense changed to " + n + "!");
    }

    /*accessir method for weapon id */
    public int getId() {
        return id;
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
