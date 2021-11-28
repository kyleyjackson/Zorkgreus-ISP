package Zorkgreus.Boss;

import java.util.Scanner;

public class demolisionistSkeletons extends Boss {
    // subclass for demolisionistSkeletons (Richie and Harrison), found in the 5th
    // room of floor 1 (miniBoss)
    private int hp;
    Scanner in = new Scanner(System.in);

    public demolisionistSkeletons() {
        super(5, 12, 15, 40);
        hp = 15;
    }

    public boolean isAlive() {
        return hp <= 0;
    }

    public void specialAttack() {
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
