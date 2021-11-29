package src.Zorkgreus.Weapons;

public class Weapons {
    /**
     * *Each weapon has it's own set priority (1-20)
     * *Higher DMG = Lower prio
     */

    private int prio;
    private int atk;
    private int def;
    private int atkDmg;
    private int speAtkDmg;

    public Weapons(int prio, int atk, int def, int speAtkDmg) {
        this.prio = prio;
        this.atk = atk;
        this.def = def;
    }
}
