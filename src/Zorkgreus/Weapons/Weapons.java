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

    public Weapons(String weaponName, int prio, int atk, int def, int speAtkDmg, String speAtkName, String desc) {
        this.weaponName = weaponName;
        this.prio = prio;
        this.atk = atk;
        this.def = def;
        this.speAtkDmg = speAtkDmg;
        this.speAtkName = speAtkName;
        this.desc = desc;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public String getWeaponSpeAtkName() {
        return speAtkName;
    }

    public String getDescription() {
        return desc;
    }

    public int getWeaponAtk() {
        return atk;
    }

    public int getWeaponPriority() {
        return prio;
    }

    public int getWeaponDef() {
        return def;
    }

    public int getWeaponSpeAtkDmg() {
        return speAtkDmg;
    }

    public void changeWeaponAtk(int n) {
        atk = n;
        System.out.println("Your attack changed to " + n + "!");
    }

    public void changeWeaponDef(int n) {
        def = n;
        System.out.println("Your defense changed to " + n + "!");
    }

    public void changeWeaponPrio(int n) {
        prio = n;
        System.out.println("Your priority changed to " + n + "!");
    }

    public int weaponNormalAtk() {
        int rand = (int) (Math.random() * 3) + 1;
        int randDmg = (int) (Math.random() * (atk / 10)) + 1;
        int dmg = atk;

        if (rand == 1) {
            dmg -= randDmg;
        } else if (rand == 2) {
            dmg += randDmg;
        }else {
            dmg += 0;
        }
        return dmg;
    }
    
}
