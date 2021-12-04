package Zorkgreus.Boss;

public class queenSpider extends Boss{
    //subclass for queenSpider, found in the 7th room of floor 2 (miniboss)

    public queenSpider(){
        super(20, 7, 15, 40, 5);
    }
    public void displayBossMessage(){
        System.out.println("An eerie noise echos throughout the misty Queen's nest... ");
        System.out.println("The Spider Queen emerges from the mist, screeching in anger. ");
    }

    //special attack for the queenSpider, increases dodge change every 10% HP decrease
    public void specialBossAttack(int oldHP){
        int newHP = super.getHP();
       if(newHP <= oldHP*0.9&&newHP>1){
           super.addBossDodge(4);
       }
       displayBossSpecialAttack();
    }

    //displays the details of the special attack
    public void displayBossSpecialAttack(){
        System.out.println("The spider queen grows more evasive...");
    }
}
