package Zorkgreus.Weapons;

public class Weapons {
    /**
     * *Each weapon has it's own set priority (1-20)
     * *Higher DMG = Lower prio
     */

    private int prio;
    private int atk;
    private int def;
    private int speAtkDmg;

    public Weapons(int prio, int atk, int def, int speAtkDmg) {
        this.prio = prio;
        this.atk = atk;
        this.def = def;
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
    }

    public void changeWeaponDef(int n) {
        def = n;
    }

    public void changeWeaponPrio(int n) {
        prio = n;
    }

    public int weaponNormalAtk() {
        int rand = (int) (Math.random() * 2) + 1;
        int randDmg = (int) (Math.random() * (atk / 10)) + 1;
        int dmg = atk;

        if (rand > 1) {
            dmg -= randDmg;
        } else {
            dmg += randDmg;
        }
        return dmg;
    }
}
