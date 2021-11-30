package Zorkgreus.Weapons;

public class Sword extends Weapons {
    public Sword() {
        super(5, 17, 0, 20); // *Strong weapon >:D
    }

    public int swordSpeAtk() {
        int dmg = getWeaponSpeAtkDmg();

        changeWeaponPrio(8);

        return dmg;
    }
}
