package Zorkgreus.Weapons;

import java.util.ArrayList;

public class Weapons {
    /**
     * *Each weapon has it's own set priority (1-20)
     * *Higher DMG = Lower prio* (general rule of thumb*)
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

    //boon effects below

    public void brutalStrength(ArrayList<Integer> levels){
        if(levels.get(0) == 1){
            
        }
    }



}
