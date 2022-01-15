package Zorkgreus.Boss;

import Zorkgreus.Player;

public class KingSkeleton extends Boss {
   // subclass for the King Skeleton, found in the 10th room of floor 1 (Boss)
   public KingSkeleton() {
      super(15, 10, 10, 50, 50, 0, "King Skeleton");
   }

   public void displayBossMessage() {
      System.out.print("As you walk into the room, you hear a voice that sends shivers to your soul: ");
      System.out.println("YOU DARE CHALLENGE ME?");
      System.out.println("Jumping from a ledge that you can't even see, the skeleton, much larger than the others makes a crater in the ground with the sheer impact form tha landing. He drags a club across the ground leaving scratches in it as he starts to run towards you.");
   }

   /**
    * rage called when once boss falls below 33%: increase attack and defence
    */
   public void bossRage() {
         if (super.activateRage()) {
            super.addBossAtk(15);
            super.addBossDef(5);
            displayBossRage();
   }
}

   public void displayBossRage() {
      System.out.println("The king skeleton has fallen into a state of rage.");
      System.out.println("His eyes start to glow red, piercing into your soul with a vicious glare as he raises his club which seems to have gotten larger, grinding his boney teeth in anticipation. ");
      System.out.println("YOU. DIE. HERE.");
   }

   /**
    * final attack for the King Skeleton, if the player priority is within a certain amount: subtract HP
    * 
    * @param player player object to check for priority and subtract HP if too low
    */
   public void finalBossAttack(Player player) {
      if(!super.isAlive()){
      System.out.println("The king skeleton falls onto one knee as he slams his club into the ground. ");
      int responseNum = (int) (Math.random() * 3 + 1);
      if(Math.abs(player.getPlayerPrio() - getPrio()) <=5){
         if (responseNum == 1) {
            System.out.println("Got too close. ");
        } else if (responseNum == 2) {
            System.out.println("Why did you walk towards him? ");
        } else {
            System.out.println("Are you into dead skeletons? ");
        }
         int dmgDealt = super.attack(15);
         player.addPlayerHP(-dmgDealt);
         System.out.println("You have taken " + dmgDealt + " damage. ");
      }else{
         if (responseNum == 1) {
            System.out.println("Got out of there quick enough. ");
        } else if (responseNum == 2) {
            System.out.println("Good thing you started running. I would've too. ");
        } else {
            System.out.println("That was a bit anticlimatic. ");
        }
      }
   }
}
}
