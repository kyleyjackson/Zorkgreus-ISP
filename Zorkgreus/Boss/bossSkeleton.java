package Zorkgreus.Boss;


public class bossSkeleton extends Boss{
   //subclass for bossSkeleton, found in the 10th room of floor 1 (Boss)
   public bossSkeleton(){
    super(15, 10, 10, 50);
   }
   public void displayBossMessage(){
      System.out.print("As you walk into the room, you hear a voice that sends shivers to your soul: ");
      System.out.println("YOU DARE CHALLENGE ME?");
      System.out.println("Jumping from a ledge that you can't even see, the skeleton, much larger than the others makes a crater in the ground with the sheer impact form tha landing. He drags a club across the ground leaving scratches in it as he starts to run towards you.");
   }

   //special attack called when he falls below 50% hp, increased atk and defence
   public void specialAttack(){
      super.changeBossAtk(30);
      super.changeBossDefence(15);
   }
   //displays the details of the special attack
   public void displaySpecialAttack(){
      System.out.println("The boss skeleton has fallen into a state of rage.");
      System.out.println("His eyes start to glow red, piercing into your soul with a vicious glare as he raises his club which seems to have gotten larger, grinding his boney teeth in anticipation. ");
      System.out.println("YOU. DIE. HERE.");
   }
   //final attack when skeleton dies
   public void finalAttack(){
      //if persons atk priority is below a certain amount ~ 5
      //person takes a decent amount of dmg
   }
}
