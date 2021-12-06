package Zorkgreus.Boss;

import java.util.Scanner;

public class DemolisionistSkeletons extends Boss {
    // subclass for the Demolisionist Skeletons, found in the 5th room of floor 1 (miniBoss)

    public DemolisionistSkeletons() {
        super(5, 12, 15, 40, 40, 0);
        displayBossMessage();
    }
    public void displayBossMessage(){
        System.out.println("Two skeletons carrying a bagful of bombs on their back look at you with glee in their eyes as they start to laugh maniacally. As they juggle those bombs around, they gesture for you to come");
    }

    //final attack for the miniboss, drops a bomb, interactive dodging
    public void finalAttack() {
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
                if (num <= 10 && num >= 1) {
                    if ((num >= (bombNum - 2) && num <= (bombNum + 2))) {
                        System.out.println("You were fast enough and dodged the dumb");
                        // exit the room
                    } else {
                        System.out.println("You weren't fast enough and took __ damage");
                        // subtract hp
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
