package Zorkgreus;

public class Player {
    private int maxHP;
    private int HP;
    private int def;
    private int atk;

    public Player(int def, int atk) {
        this.maxHP = 50;
        this.HP = 50;
        this.def = 10 + def;
        this.atk = atk;
    }

    public int getPlayerHP() {
        return HP;
    }

    public int getPlayerMaxHP() {
        return maxHP;
    }

    public int getPlayerDef() {
        return def;
    }

    public int getPlayerAtk() {
        return atk;
    }

    public void changePlayerHP(int n) {
        HP = n;
        System.out.println("Your HP changed to " + n + "!");
    }

    public void changePlayerMaxHP(int n) {
        maxHP = n;
        System.out.println("Your maximum HP changed to " + n + "!");
    }

    public void changePlayerDef(int n) {
        def = n;
        System.out.println("Your defense changed to " + n + "!");
    }

    public void changePlayerAtk(int n) {
        atk = n;
        System.out.println("Your attack changed to " + n + "!");
    }
}
