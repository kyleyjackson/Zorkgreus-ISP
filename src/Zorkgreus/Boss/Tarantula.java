package Zorkgreus.Boss;

import java.util.Scanner;

public class Tarantula extends Boss {
    // subclass for the Tarantula, found in the 14th room of floor 2 (boss)

    public Tarantula() {
        super(15, 5, 7, 75, 75, 10);
        displayBossMessage();
    }

    // displays the intro message
    public void displayBossMessage() {
        System.out.println(
                "With a snarl, the tarantula whips his head at you after feeding on a dead spider, leaving a trail of venom, eroding away the floor. ");
        System.out.println("Every step he takes is full of power, as his red eyes immediately recognize you as prey. ");
        System.out.println("G0t p@5t th3 0T#3r mUt@7I0n3? Im9f3sS1v3. ");
    }

    // rage called when he falls below 33% hp, increased atk and priority
    public void bossRage() {
        super.addBossAtk(20);
        super.addBossPriority(2);
        displayBossRage();
    }

    // displays the detail of the rage
    public void displayBossRage() {
        System.out.println("The tarantula has fallen into a state of rage. ");
        System.out.println(
                "The colours of his eyes begin to fade into darkness as he lets out a thunderous screech and pounces on you. ");
        System.out.println("1GnOr@n7 f0ol! ");
    }

    // special attack for the boss, shoots spider webs that stun you every 25% HP decrease, interactive dodging
    // param: dmgDone is the damage done
    public void specialBossAttack(int dmgDone) {
        Scanner in = new Scanner(System.in);
        boolean validInput = false;
        boolean correctAnswer = true;
        int count = 0;
        if (super.compareHP(0.75, dmgDone)) {
            while (!validInput) {
                if (count == 0||correctAnswer==true)
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
                        String secondWord = webAns.substring(dash+1);
                        int responseNum = (int) (Math.random() * 3) + 1;
                        if (firstWord.equals(ans) || secondWord.equals(ans)) {
                            if (responseNum == 1)
                                System.out.println("Speedy reflexes. ");
                            else if (responseNum == 2)
                                System.out.println("That was all luck. ");
                            else
                                System.out.println("Barely dodged that one. ");
                            validInput = true;
                            correctAnswer = true;
                        } else {
                            if (responseNum == 1) {
                                System.out.println("Wow... You are slow. ");
                                System.out.println("You took _ damage.");
                            } else if (responseNum == 2) {
                                System.out.println("You deserve to be hit after that performance. ");
                                System.out.println("You took _ damage.");
                            } else {
                                System.out.println("The time when planking feels faster when I see your reaction speed. ");
                                System.out.println("You took _ damage.");
                            }
                            correctAnswer = true;
                            // subtract hp from the player
                            // subtract priority from the player
                        }
                    } else {
                        count++;
                        correctAnswer = false;
                    }
                } catch (Exception ex) {
                    count++;
                    correctAnswer = false;
                }
            }
        }
    }

    // final attack when the tarantula dies
    public void finalBossAttack() {
        Scanner in = new Scanner(System.in);
        if (!super.isAlive()) {
            int height = 0;
            int poison = 0;
            int count = 0;
            boolean validInput = false;
            boolean correctAnswer = true;
            System.out.println("Wtih its final breath, the tarantula lets out a screech as it plummets into the ground, causing the room to fall apart. ");
            System.out.println("It's poisonous and radioactive blood starts to leak out as you desperately look for somewhere to run to. ");
            while (!validInput) {
                if (count == 0||correctAnswer == true)
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
                        String secondWord = correctAns.substring(dash+1);
                        int responseNum = (int) (Math.random() * 3) + 1;
                        if (firstWord.equals(ans) || secondWord.equals(ans)) {
                            height += 3;
                            poison++;
                            if (responseNum == 1)
                                System.out.println("Speedy reflexes. ");
                            else if (responseNum == 2)
                                System.out.println("That was all luck. ");
                            else
                                System.out.println("Barely dodged that one. ");
                            correctAnswer = true;
                        } else {
                            height--;
                            poison++;
                            if (responseNum == 1) {
                                System.out.println("Wow... You are slow. ");
                            } else if (responseNum == 2) {
                                System.out.println("You deserve to be hit after that performance. ");
                            } else {
                                System.out.println("The time when planking feels faster when I see your reaction speed. ");
                            }
                            correctAnswer = true;
                        }
                         if(poison > height){
                        System.out.println("You took _ damage. ");
                         }
                        if (height >= 10)
                            validInput = true;
                            //exit the room
                    } else {
                        count++;
                        correctAnswer = false;
                    }
                } catch (Exception Ex) {
                    count++;
                    correctAnswer = false;
                }
            }
        }
    }
}
