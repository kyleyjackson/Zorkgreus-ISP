package Zorkgreus.Boss;

import java.util.Scanner;

import Zorkgreus.Player;

public class DemolisionistSkeleton extends Boss {
    // subclass for the Demolisionist Skeletons, found in the 5th room of floor 1 (Mini Boss)

    public DemolisionistSkeleton() {
        super(5, 12, 15, 40, 40, 0, "Demolisionist Skeleton");
    }

    public void displayBossMessage() {
        System.out.println("A skeleton carrying a bagful of bombs on its back look at you with glee in its eye as it start to laugh maniacally. As it juggles those bombs around, it gesture for you to come");
    }

    /**
     * final atack for the Demolisionist Skeleton, interactive dodging, if not dodged: subtract HP
     * 
     * @param player player object to subtract HP if not dodged
     */
    public void finalBossAttack(Player player) {
        int count = 0;
        boolean validInput = false;
        Scanner in = new Scanner(System.in);
        if (!super.isAlive()) {
            while (!validInput) {
                if (count == 0)
                    System.out.print("A skeleton demolisionist has died, its dropped a bomb, if you are fast enough you can dodge the bomb, please input a number to see [1-10]: ");
                else
                    System.out.print("Invalid Input - Number Between [1-10]: ");
                try {
                    int num = Integer.parseInt(in.nextLine().toUpperCase());
                    int bombNum = (int) (Math.random() * 10) + 1;
                    int responseNum = (int) (Math.random() * 3) + 1;
                    if (num <= 10 && num >= 1) {
                        if ((num >= (bombNum - 2) && num <= (bombNum + 2))) {
                            // exit the room at the end
                            if (responseNum == 1)
                                System.out.println("Got out of there just in time. ");
                            else if (responseNum == 2)
                                System.out.println("Faster than Usain Bolt .");
                            else
                                System.out.println("Impressive footwork. ");
                        } else {
                            if (responseNum == 1) {
                                System.out.println("A turtle's faster than you. ");
                            } else if (responseNum == 2) {
                                System.out.println("Looked slow out there. ");
                            } else {
                                System.out.println("At least you tried. ");
                            }
                            int dmgDealt = super.attack(12);
                            System.out.println("You have taken " + dmgDealt + " damage. ");
                            player.addPlayerHP(-dmgDealt);
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
}
