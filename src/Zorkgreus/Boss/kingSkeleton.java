package Zorkgreus.Boss;

public class kingSkeleton extends Boss {
   // subclass for bossSkeleton, found in the 10th room of floor 1 (Boss)
   public kingSkeleton() {
      super(15, 10, 10, 50, 0);
   }

   public void displayBossMessage() {
      System.out.print("As you walk into the room, you hear a voice that sends shivers to your soul: ");
      System.out.println("YOU DARE CHALLENGE ME?");
      System.out.println(
            "Jumping from a ledge that you can't even see, the skeleton, much larger than the others makes a crater in the ground with the sheer impact form tha landing. He drags a club across the ground leaving scratches in it as he starts to run towards you.");
   }

   // rage called when he falls below 33% hp, increased atk and defence
   public void bossRage() {
      super.addBossAtk(15);
      super.addBossDefence(5);
      displayBossRage();
   }

   // displays the details of the rage
   public void displayBossRage() {
      System.out.println("The boss skeleton has fallen into a state of rage.");
      System.out.println(
            "His eyes start to glow red, piercing into your soul with a vicious glare as he raises his club which seems to have gotten larger, grinding his boney teeth in anticipation. ");
      System.out.println("YOU. DIE. HERE.");
   }

   //final attack when kingSkeleton dies
   public void finalBossAttack() {
      // if persons atk priority is below a certain amount ~ 5
      // person takes a decent amount of dmg
   }
}
