package Zorkgreus.Boss;

import java.util.Scanner;

public class DemolisionistSkeletons extends Boss {
    // subclass for the Demolisionist Skeletons, found in the 5th room of floor 1
    // (miniBoss)

    public DemolisionistSkeletons() {
        super(5, 12, 15, 40, 40, 0);
        displayBossMessage();
    }

    public void displayBossMessage() {
        System.out.println(
                "Two skeletons carrying a bagful of bombs on their back look at you with glee in their eyes as they start to laugh maniacally. As they juggle those bombs around, they gesture for you to come");
    }

    //final attack for the miniboss, drops a bomb, interactive dodging
    public void finalBossAttack() {
        int count = 0;
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        while (!validInput) {
            if (count == 0)
                System.out.print(
                        "A skeleton demolisionist has died, its dropped a bomb, if you are fast enough you can dodge the bomb, please input a number to see [1-10]: ");
            else
                System.out.print("Invalid Input - Number Between [1-10]: ");
            try {
                int num = Integer.parseInt(in.nextLine().toUpperCase());
                int bombNum = (int) (Math.random() * 10) + 1;
                int responseNum = (int) (Math.random()*3) + 1;
                if (num <= 10 && num >= 1) {
                    if ((num >= (bombNum - 2) && num <= (bombNum + 2))) {
                        //exit the room at the end
                        if(responseNum==1)
                        System.out.println("Got out of there just in time. ");
                        else if(responseNum==2)
                        System.out.println("Faster than Usain Bolt.");
                        else
                        System.out.println("Impressive footwork. ");
                    } else {
                        //subtract hp at the end
                        if(responseNum==1){
                        System.out.println("A turtle's faster than you.");
                        System.out.println("You took _ damage.");
                        }else if(responseNum==2){
                        System.out.println("Looked slow out there.");
                        System.out.println("You took _ damage. ");
                        }else{
                        System.out.println("At least you tried.");
                        System.out.println("You took _ damage");
                        }
                    }
                    validInput = true;
                } else {
                    count++;
                }
            } catch (Exception ex) {
                count++;
            }
        }
    }
}
