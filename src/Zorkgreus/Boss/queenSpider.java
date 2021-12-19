package Zorkgreus.Boss;

public class QueenSpider extends Boss{
    //subclass for the Queen Spider, found in the 7th room of floor 2 (miniboss)

    public QueenSpider(){
        super(20, 7, 15, 40, 40, 5);
        displayBossMessage();
    }
    
    //displays the intro message
    public void displayBossMessage(){
        System.out.println("An eerie noise echos throughout the misty Queen's nest... ");
        System.out.println("The Spider Queen emerges from the mist, screeching in anger. ");
    }

    //special attack for the queenSpider, increases dodge change every 10% HP decrease
    //param: dmgDone is the damage done
    public void specialBossAttack(int dmgDone){
        if(super.compareHP(0.1, dmgDone)){
            super.addBossDodge(4);
            displayBossSpecialAttack();
        }
    }

    //displays the details of the special attack
    public void displayBossSpecialAttack(){
        System.out.println("The spider queen grows more evasive...");
    }
}
