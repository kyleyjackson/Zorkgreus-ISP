package Zorkgreus.Boss;

import Zorkgreus.Player;

public class KingSkeleton extends Boss {
   // subclass for the King Skeleton, found in the 10th room of floor 1 (Boss)
   public KingSkeleton() {
      super(15, 10, 10, 50, 50, 0);
      displayBossMessage();
   }

   public void displayBossMessage() {
      System.out.print("As you walk into the room, you hear a voice that sends shivers to your soul: ");
      System.out.println("YOU DARE CHALLENGE ME?");
      System.out.println(
            "Jumping from a ledge that you can't even see, the skeleton, much larger than the others makes a crater in the ground with the sheer impact form tha landing. He drags a club across the ground leaving scratches in it as he starts to run towards you.");
   }

   /**
    * rage called when once boss falls below 33%: increase attack and defence
    */
   public void bossRage() {
      int count = 0;
      if (count == 0) {
         if (super.activateRage()) {
            super.addBossAtk(15);
            super.addBossDefence(5);
            displayBossRage();
         }
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
   }
}
