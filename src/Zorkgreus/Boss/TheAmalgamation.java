package Zorkgreus.Boss;

import java.util.ArrayList;
import java.util.Scanner;

import Zorkgreus.CommandWords;
import Zorkgreus.Player;

public class TheAmalgamation extends Boss {
    // subclass for The Amalgamation, found in the 9th room of floor 3 (Mini Boss)
    public TheAmalgamation() {
        super(15, 3, 15, 60, 60, 10, "The Amalgamation");
    }

    public void displayBossMessage() {
        System.out.println("You hear the horrific sound of bones creaking, and chains clanking as what can only be called an amalgamation steps in front of you. ");
        System.out.println("With dismembered limbs and bones, barely held together by rusty chains making up its form, it pulls out a bloody bone from its body, and snaps it in half over its knee. ");
    }

    /**
     * special attack for The Amalgamation, every 10% hp decrease: decrease attack, increase priority and dodge
     * 
     * @param dmgDone damage done for the compareHP method
     */
    /*
    public void specialBossAttack(int dmgDone) {
        int decrement = (int) (getMaxHP() * 0.1);
        if(getMakeArray()){
            clearDecrements();
            for(int i = 1; i<=getMaxHP()/decrement; i++){
                super.addToDecrements(0, getMaxHP()-decrement*i);
            }
    }
    super.setMakeArray(false);
    ArrayList<Integer> decrements = super.getDecrements();
    int temp = decrements.size();
    decrements = super.compareHP(decrements, dmgDone);
        if (temp!=super.decrementsSize()) {
            super.addBossAtk(-2);
            super.addBossPriority(2);
            super.addBossDodge(2);
            displayBossSpecialAttack();
    }
    }*/

    /**
     * special attak for The Amalgamation, once it gets below 75% HP, decrease attack, increase priority and dodge
     * 
     * @param true if used
     */
    public boolean specialBossAttack() {
        if (getHP()<=(int)(getMaxHP()*0.75)) {
            super.addBossAtk(-6);
            super.addBossPriority(4);
            super.addBossDodge(20);
            displayBossSpecialAttack();
            return true;
    }
    return false;
    }

    public void displayBossSpecialAttack() {
        System.out.println("The Amalgamation feels lighter...");
    }

    /**
     * final attack for the Tarantula, interactive dodging, if not dodged: subtract maxHP, if maxHP is smaller than HP, subract HP instead
     * 
     * @param player player object to subtract maxHP or HP if not dodged
     */
    public void finalBossAttack(Player player) {
        Scanner in = new Scanner(System.in);
        if (!super.isAlive()) {
            boolean validInput = false;
            boolean displayFirstMessage = true;
            boolean displayCommandMessage = false;
            boolean msg = true;
            int count = 0;
            String bone;
            String ans = "";
            System.out.println("With a flash of light and a violent shattering sound The Amalgamation explodes, pulsing out a destructive shockwave and launching its bones at you. ");
            while (!validInput) {
                if (msg == true) {
                    int ansNum = (int) (Math.random() * 4);
                    if (ansNum == 1) {
                        ans = "RIGHT-R";
                        bone = "right";
                    } else if (ansNum == 2) {
                        ans = "LEFT-L";
                        bone = "left";
                    } else if (ansNum == 3) {
                        ans = "UP-U";
                        bone = "above";
                    } else {
                        ans = "DOWN-D";
                        bone = "below";
                    }
                    if (bone.equals("right") || bone.equals("left"))
                        System.out.println("A bone comes at you on your " + bone + " side. ");
                    else
                        System.out.println("A bone comes " + bone + " you. ");
                }
                if(displayCommandMessage){
                    System.out.print("There are bones breaking the sound barrier flying right at you. [R]ight, [L]eft, [U]p, or [D]own: ");
                    displayCommandMessage = false;
                }
                else if (displayFirstMessage == true)
                    System.out.print("Would you like to dodge [R]ight, [L]eft, [U]p, or [D]own: ");
                else
                    System.out.print("Invalid Input - [R]ight, [L]eft, [U]p, or [D]own: ");
                try {
                    String playerAns = in.nextLine().toUpperCase();
                    if (playerAns.equals("RIGHT") || playerAns.equals("R") || playerAns.equals("LEFT")|| playerAns.equals("L") || playerAns.equals("UP") || playerAns.equals("U")|| playerAns.equals("DOWN") || playerAns.equals("D")) {
                        int dash = ans.indexOf("-");
                        String firstWord = ans.substring(0, dash);
                        String secondWord = ans.substring(dash + 1);
                        int responseNum = (int) (Math.random() * 3 + 1);
                        if (!(playerAns.equals(firstWord) || playerAns.equals(secondWord))) {
                            if (responseNum == 1)
                                System.out.println("You're getting better. ");
                            else if (responseNum == 2)
                                System.out.println("You can read! ");
                            else if (responseNum == 3 && count < 6)
                                System.out.println("Can you dodge the next one? ");
                            else
                                System.out.println("That bone whizzed past you");
                            count++;
                        } else {
                            if (responseNum == 1) {
                                System.out.println("Did you fail english? Read the sentence. ");
                            } else if (responseNum == 2) {
                                System.out.println("How in the world did you get hit? ");
                            } else {
                                System.out.println("Dodged right into it. ");
                            }
                            int dmgDealt = super.attack(7);
                            System.out.println("You took " + dmgDealt + " damage.");
                            player.addPlayerHP(-dmgDealt);
                            count++;
                        }
                        msg = true;
                        displayFirstMessage = true;
                        if (count == 7) {
                            validInput = true;
                            // exit the room
                        }
                    } else {
                        CommandWords words = new CommandWords();
                        for(String word: words.getValidCommands()){
                            if(words.isCommand(playerAns.toLowerCase()))
                            displayCommandMessage = true;
                        }
                        displayFirstMessage = false;
                        msg = false;
                    }
                } catch (Exception ex) {
                    displayFirstMessage = false;
                    msg = false;
                }
            }
        }
    }

}
