package Zorkgreus.Boss;

import java.util.ArrayList;

public class QueenSpider extends Boss {
    // subclass for the Queen Spider, found in the 7th room of floor 2 (Mini Boss)

    public QueenSpider() {
        super(20, 7, 15, 40, 40, 5, "Queen Spider");
    }

    public void displayBossMessage() {
        System.out.println("An eerie noise echos throughout the misty Queen's nest... ");
        System.out.println("The Spider Queen emerges from the mist, screeching in anger. ");
    }

    /**
     * special attack for the Queen Spider: every 10% hp decrease, increase dodge
     * 
     * @param dmgDone damage done for compareHP method
     */
    /*
    public void specialBossAttack(int dmgDone) {
    int decrement = (int) (getMaxHP() * 0.1);
        if(getMakeArray()){
            for(int i = 1; i<=getMaxHP()/decrement; i++){
                super.addToDecrements(0, getMaxHP()-decrement*i);
            }
    }
    super.setMakeArray(false);
    ArrayList<Integer> decrements = super.getDecrements();
    int temp = decrements.size();
    decrements = super.compareHP(decrements, dmgDone);
        if (temp!=super.decrementsSize()) {
            super.addBossDodge(4);
            displayBossSpecialAttack();
    }
}*/

/**
 * special attack for the Queen Spider, once it gets below 75% HP, increase dodge chance
 * @return true if used
 */
public boolean specialBossAttack(){
    if(getHP()<=(int)(getMaxHP()*0.75)){
        super.addBossDodge(30);
        displayBossSpecialAttack();
        return true;
    }
    return false;
}

    public void displayBossSpecialAttack() {
        System.out.println("The spider queen grows more evasive...");
    }
}
