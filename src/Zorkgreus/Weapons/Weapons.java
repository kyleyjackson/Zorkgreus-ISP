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

    public String getName() {
        return weaponName;
    }

    public String getSpeAtkName() {
        return speAtkName;
    }

    public String getDescription() {
        return desc;
    }

    public int getAtk() {
        return atk;
    }

    public int getPriority() {
        return prio;
    }

    public int getDef() {
        return def;
    }

    public int getSpeAtkDmg() {
        return speAtkDmg;
    }

    public int getId() {
        return id;
    }

    public void changeAtk(int n) {
        atk = n;
        System.out.println("Your attack changed to " + n + "!");
    }

    public void changeDef(int n) {
        def = n;
        System.out.println("Your defense changed to " + n + "!");
    }

    public void changePrio(int n) {
        prio = n;
        System.out.println("Your priority changed to " + n + "!");
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
            System.out.println("Your abilities grow faster..");
            dmg = 8;

            changePrio(30);
        } else if (id == 1) {
            System.out.println("You've become tougher..");
            dmg = 25;

            changeDef(10);
            changeAtk(15);
            changePrio(6);
        } else if (id == 2) {
            System.out.println("Your attacks become faster and stronger..");
            dmg = 20;

            changePrio(8);
            changeAtk(19);
        } else if (id == 3) {
            System.out.println("Your strength reaches new levels..");
            dmg = 20;

            changeDef(5);
            changeAtk(20);
            changePrio(10);
        }

        return dmg;
    }
}
