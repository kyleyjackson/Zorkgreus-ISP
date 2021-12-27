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
    private String speAtkName;
    private String desc;

    private int basePrio; //priority at the start of the game
    private int baseAtk; //attack at the start of the game
    private int baseDef; //defense at the start of the game
    

    public Weapons(String weaponName, int prio, int atk, int def, int speAtkDmg, String speAtkName, String desc) {
        this.weaponName = weaponName;
        this.prio = prio;
        this.atk = atk;
        this.def = def;
        this.speAtkDmg = speAtkDmg;
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
            dmg -= randDmg;
        } else if (rand == 2) {
            dmg += randDmg;
        } else {
            dmg += 0;
        }
        return dmg;
    }

    public int bowSpeAtk() {
        int dmg = getSpeAtkDmg();

        changePrio(30);

        return dmg;
    }
    
    public int shieldSpeAtk() {
        int dmg = getSpeAtkDmg();

        changeDef(5);
        changeAtk(20);
        changePrio(10);

        return dmg;
    }

    public int swordSpeAtk() {
        int dmg = getSpeAtkDmg();

        changePrio(8);

        return dmg;
    }

    public int spearSpeAtk() {
        int dmg = getSpeAtkDmg();

        changeDef(10);
        changeAtk(15);
        changePrio(6);

        return dmg;
    }
}
