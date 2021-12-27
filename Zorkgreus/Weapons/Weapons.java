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

    public int weaponNormalAttack() {
        int rand1 = (int)(Math.random() * 2) + 1;

        if(rand1 == 2) {
            atk -= (int)(Math.random() * (atk / 10)) + 1;
        }else {
            atk += (int)(Math.random() * (atk / 10)) + 1;
        }

        return atk;
    }

    public int getWeaponAtk() {
        return atk;
    }

    public int getWeaponPriority() {
        return prio;
    }

    public int getWeaponSpeAtkDmg() {
        return speAtkDmg;
    }
}
