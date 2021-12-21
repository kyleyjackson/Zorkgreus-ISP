package Zorkgreus.Boss;

public class QueenSpider extends Boss {
    // subclass for the Queen Spider, found in the 7th room of floor 2 (Mini Boss)

    public QueenSpider() {
        super(20, 7, 15, 40, 40, 5);
        displayBossMessage();
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
    public void specialBossAttack(int dmgDone) {
        if (super.compareHP(0.1, dmgDone)) {
            super.addBossDodge(4);
            displayBossSpecialAttack();
        }
    }

    public void displayBossSpecialAttack() {
        System.out.println("The spider queen grows more evasive...");
    }
}
