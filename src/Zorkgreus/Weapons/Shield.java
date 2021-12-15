/**
package Zorkgreus.Weapons;

public class Shield extends Weapons {
    public Shield() {
        super(1, 15, 30, 20); // *Lowest prio, good dmg, but gives you def
    }

    public int shieldSpeAtk() {
        int dmg = getWeaponSpeAtkDmg();

        changeWeaponDef(5);
        changeWeaponAtk(20);
        changeWeaponPrio(10);

        return dmg;
    }
}
*/