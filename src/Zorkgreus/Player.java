package Zorkgreus;

public class Player {
    private int maxHP;
    private int HP;
    private int def;
    private int atk;
    private int dodge;
    private int prio;

    private int basePrio; //priority at the start of the game
    private int baseAtk; //attack at the start of the game
    private int baseDef; //defense at the start of the game
    private boolean extraLife = true;
    private boolean hyradliteGold = false;

    public Player(int prio, int atk, int def) {
        this.maxHP = 50;
        this.HP = 50;
        this.def = 10 + def;
        this.atk = atk;
        this.prio = prio;
        this.dodge = 0;
        
        basePrio = prio;
        baseAtk = atk;
        baseDef = def;

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

    public int getPlayerDodgeChange() {
        return dodge;
    }

    public int getPlayerPrio() {
        return prio;
    }

    public int getBasePrio(){
        return basePrio;
    }

    public int getBaseAtk(){
        return baseAtk;
    }

    public int getBaseDef(){
        return baseDef;
    }

    public boolean isAlive() {
        return HP >= 0;
    }

    public void setPlayerHP(int n) {
        if(n < getPlayerHP()){
            System.out.println("Your HP decreased to " + n + "!");
        } 
        else{
            System.out.println("Your HP increased to " + n + "!");
        }
        HP = n;
    }

    public void setPlayerMaxHP(int n) {
        if(n < getPlayerMaxHP()){
            System.out.println("Your maximum HP decreased to " + n + "!");
        }
        else{
            System.out.println("Your maximum HP increased to " + n + "!");
        }
        maxHP = n;
    }

    public void setPlayerDef(int n) {
        if(n < getPlayerDef()){
            System.out.println("Your defense decreased to " + n + "!");
        }
        else{
            System.out.println("Your defense increased to " + n + "!");
        }
        def = n;  
    }

    public void setPlayerAtk(int n) {
        if(n < getPlayerAtk()){
            System.out.println("Your attack decreased to " + n + "!");
        }
        else{
            System.out.println("Your attack increased to " + n + "!");
        }
        atk = n;
    }

    public void setPlayerDodge(int n) {
        if(n < getPlayerDodgeChange()){
            System.out.println("Your dodge chance decreased to " + n + "!");
        }
        else{
            System.out.println("Your dodge chance increased to " + n + "!");
        }
        dodge = n; 
    }

    public void setPlayerPrio(int n) {
        if(n < getPlayerPrio()){
            System.out.println("Your priority has decreased to " + n + "!");
        }
        else{
            System.out.println("Your priority has increased to " + n + "!");
        }
        prio = n;
        
    }

    public void incrimentPlayerHP(int n) {
        maxHP += n;
        HP += n;
    }

    public void addPlayerHP(int n){
        if((HP + n) > maxHP) {
            HP = maxHP;
        }else {
            HP += n;
        }
    }

    public void addPlayerMaxHP(int n){
        maxHP += n;
    }

    public void addPlayerPriority(int n){
        prio += n;
    }

    public void addPlayerAttack(int n){
        atk += n;
    }
}
