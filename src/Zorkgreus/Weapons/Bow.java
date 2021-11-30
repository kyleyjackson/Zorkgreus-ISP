package Zorkgreus.Weapons;

public class Bow extends Weapons {
    public Bow() {
        super(15, 10, 0, 8); // *Highest prio since it's ranged
    }

    public int bowSpeAtk() {
        int dmg = getWeaponSpeAtkDmg();

        changeWeaponPrio(30);

        return dmg;
    }
}
