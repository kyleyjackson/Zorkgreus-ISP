package Zorkgreus.Boss;

import java.util.Scanner;

import Zorkgreus.Player;

public class Tarantula extends Boss {
    // subclass for the Tarantula, found in the 14th room of floor 2 (Boss)

    public Tarantula() {
        super(15, 5, 7, 75, 75, 10);
        displayBossMessage();
    }

    public void displayBossMessage() {
        System.out.println("With a snarl, the tarantula whips his head at you after feeding on a dead spider, leaving a trail of venom, eroding away the floor. ");
        System.out.println("Every step he takes is full of power, as his red eyes immediately recognize you as prey. ");
        System.out.println("G0t p@5t th3 0T#3r mUt@7I0n3? Im9f3sS1v3. ");
    }

    /**
     * rage called when once boss falls below 33%: increase attack and priority
     */
    public void bossRage() {
        super.addBossAtk(20);
        super.addBossPriority(2);
        displayBossRage();
    }

    public void displayBossRage() {
        System.out.println("The tarantula has fallen into a state of rage. ");
        System.out.println("The colours of his eyes begin to fade into darkness as he lets out a thunderous screech and pounces on you. ");
        System.out.println("1GnOr@n7 f0ol! ");
    }

    /**
     * special attack for the Tarantula, interactive dodging, if not dodged:
     * subtract HP and priority
     * 
     * @param dmgDone damage done for compareHP method
     * @param player  player object to subtract HP and priority if not dodged
     */
    public void specialBossAttack(int dmgDone, Player player) {
        Scanner in = new Scanner(System.in);
        boolean validInput = false;
        boolean displayFirstMessage = true;
        if (super.compareHP(0.75, dmgDone)) {
            while (!validInput) {
                if (displayFirstMessage == true)
                    System.out.print("The tarantula fires a flurry of webs at you. Would you like to dodge [R]ight, [L]eft, [U]p, or [D]own: ");
                else
                    System.out.print("Invalid Input - [R]ight, [L]eft, [U]p, or [D]own: ");
                try {
                    String ans = in.nextLine().toUpperCase();
                    if (ans.equals("RIGHT") || ans.equals("R") || ans.equals("LEFT") || ans.equals("L")|| ans.equals("UP") || ans.equals("U") || ans.equals("DOWN") || ans.equals("D")) {
                        String webAns;
                        int webNum = (int) ((Math.random() * 4) + 1);
                        if (webNum == 1)
                            webAns = "RIGHT-R";
                        else if (webNum == 2)
                            webAns = "LEFT-L";
                        else if (webNum == 3)
                            webAns = "UP-U";
                        else
                            webAns = "DOWN-D";
                        int dash = webAns.indexOf("-");
                        String firstWord = webAns.substring(0, dash);
                        String secondWord = webAns.substring(dash + 1);
                        int responseNum = (int) (Math.random() * 3) + 1;
                        if (firstWord.equals(ans) || secondWord.equals(ans)) {
                            if (responseNum == 1)
                                System.out.println("Speedy reflexes. ");
                            else if (responseNum == 2)
                                System.out.println("That was all luck. ");
                            else
                                System.out.println("Barely dodged that one. ");
                        } else {
                            if (responseNum == 1) {
                                System.out.println("Wow... You are slow. ");
                            } else if (responseNum == 2) {
                                System.out.println("You deserve to be hit after that performance. ");
                            } else {
                                System.out.println(
                                        "The time when planking feels faster when I see your reaction speed. ");
                            }
                            System.out.println("You took " + super.attack(5) + " damage.");
                            // subtract hp from the player
                            // subtract priority from the player
                        }
                        validInput = true;
                        displayFirstMessage = true;
                    } else {
                        displayFirstMessage = false;
                    }
                } catch (Exception ex) {
                    displayFirstMessage = false;
                }
            }
        }
    }

    /**
     * final attack for the Tarantula, interactive choices, deals damage if choices
     * are wrong too many times
     * 
     * @param player player object to subtract HP if wrong choice
     */
    public void finalBossAttack(Player player) {
        Scanner in = new Scanner(System.in);
        if (!super.isAlive()) {
            int height = 0;
            int poison = 0;
            boolean validInput = false;
            boolean displayFirstMessage = true;
            System.out.println("Wtih its final breath, the tarantula lets out a screech as it plummets into the ground, causing the room to fall apart. ");
            System.out.println("It's poisonous and radioactive blood starts to leak out as you desperately look for somewhere to run to. ");
            while (!validInput) {
                if (displayFirstMessage == true)
                    System.out.print("You spot three chunks of stone falling, which one would you like to jump to? [F]irst, [S]econd, or [T]hird: ");
                else
                    System.out.print("Invalid Input - [F]irst, [S]econd, or [T]hird: ");
                try {
                    String ans = in.nextLine().toUpperCase();
                    if (ans.equals("F") || ans.equals("FIRST") || ans.equals("S") || ans.equals("SECOND")|| ans.equals("T") || ans.equals("THIRD")) {
                        String correctAns;
                        int num = (int) ((Math.random() * 3) + 1);
                        if (num == 1)
                            correctAns = "FIRST-F";
                        else if (num == 2)
                            correctAns = "SECOND-S";
                        else
                            correctAns = "THIRD-T";
                        int dash = correctAns.indexOf("-");
                        String firstWord = correctAns.substring(0, dash);
                        String secondWord = correctAns.substring(dash + 1);
                        int responseNum = (int) (Math.random() * 3) + 1;
                        if (firstWord.equals(ans) || secondWord.equals(ans)) {
                            height += 3;
                            poison++;
                            if (responseNum == 1)
                                System.out.println("Fast jumps. ");
                            else if (responseNum == 2)
                                System.out.println("You sure you dont play in the NBA?. ");
                            else
                                System.out.println("Not a worry in the world. ");
                        } else {
                            if (height > 1)
                                height--;
                            poison++;
                            if (responseNum == 1) {
                                System.out.println("You jumped! Then fell through the stone. ");
                            } else if (responseNum == 2) {
                                System.out.println("Oh. Landed right on your butt ");
                            } else {
                                System.out.println("Looked really silly. ");
                            }
                        }
                        displayFirstMessage = true;
                        if (poison > height) {
                            System.out.println("You took " + super.attack(5) + " damage.");
                            // subtract max HP from the player
                        }
                        if (height >= 10)
                            validInput = true;
                        // exit the room
                    } else {
                        displayFirstMessage = false;
                    }
                } catch (Exception ex) {
                    displayFirstMessage = false;
                }
            }
        }
    }
}
