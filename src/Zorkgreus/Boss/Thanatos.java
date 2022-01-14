package Zorkgreus.Boss;

import java.util.ArrayList;
import java.util.Scanner;

import Zorkgreus.CommandWords;
import Zorkgreus.Player;

public class Thanatos extends Boss {
    // subclass for Thanatos, found in the 18th room of floor 3 (Boss)
    public Thanatos() {
        super(17, 3, 20, 75, 75, 10, "Thanatos");
    }

    public void displayBossMessage() {
        System.out.println("Radiating power and sovereignty, this man pierces your soul with his green eyes, as you can feel your existence diminishing. ");
        System.out.println("Donning a tattered black robe and a scythe sharpened with the lives of countless others he throws a sack overflowing with skulls to the side. ");
        System.out.println("So you're the one? My deepest condolensces. ");
    }

    /**
     * special attack for Thanatos, interactive dodging, if not dodged: reduce
     * attack and priority or subtract HP
     * 
     * @param dmgDone damage done for compareHP method
     * @param player  player object to reduce attack and priority or subtract HP if
     *                not dodged
     */
    public void bossSpecialAttack(int dmgDone, Player player) {
        Scanner in = new Scanner(System.in);
        boolean validInput = false;
        boolean displayFirstMessage = true;
        boolean displayCommandMessage = false;
        int decrement = (int) (getMaxHP() * 0.25);
        if (getMakeArray()) {
            clearDecrements();
            for (int i = 1; i <= getMaxHP() / decrement; i++) {
                super.addToDecrements(0, getMaxHP() - decrement * i);
            }
        }
        super.setMakeArray(false);
        ArrayList<Integer> decrements = super.getDecrements();
        int temp = decrements.size();
        decrements = super.compareHP(decrements, dmgDone);
        if (temp != decrements.size()) {
            while (!validInput) {
                if(displayCommandMessage){
                    System.out.print("Can't do that here. [R]ight, [L]eft, or [D]eflect: ");
                    displayCommandMessage = false;
                }
                else if (displayFirstMessage == true)
                    System.out.print("Thanatos hurls his scythe at you, your blood dripping of it as it pierces through the air, would you like to dodge [R]eft, [L]ight, or [D]eflect it: ");
                else
                    System.out.print("Invalid Input - [R]ight, [L]eft, or [D]eflect: ");
                try {
                    String ans = in.nextLine().toUpperCase();
                    int responseNum = (int) (Math.random() * 3 + 1);
                    if (ans.equals("DEFLECT") || ans.equals("D")) {
                        if (player.getPlayerHP() <= (player.getPlayerMaxHP() / 2)
                                && player.getPlayerPrio() > (super.getPrio() + 2)) {
                            if (responseNum == 1) {
                                System.out.println("Nice coordination. ");
                            } else if (responseNum == 2) {
                                System.out.println("Gotta get you in the MLB. ");
                            } else {
                                System.out.println("Thanatos has to stop hitting himself. ");
                            }
                            super.addBossAtk(-5);
                            int dmgDealt = super.attack(15);
                            super.addBossHP(-dmgDealt);
                            System.out.println("Thanatos has taken " + dmgDealt + " damage. ");
                        } else {
                            if (responseNum == 1) {
                                System.out.println("Bit off more than you could chew. ");
                            } else if (responseNum == 2) {
                                System.out.println("The stick hit the wrench. ");
                            } else {
                                System.out.println("You missed the scythe lol. ");
                            }
                            player.addPlayerAttack(-5);
                            player.addPlayerPriority(-3);
                            int dmgDealt = super.attack(15);
                            player.addPlayerHP(-dmgDealt);
                            System.out.println("You have taken " + dmgDealt + " damage. ");
                            System.out.println("You have lost 5 attack and 3 priority. ");
                        }
                    } else if (ans.equals("RIGHT") || ans.equals("R") || ans.equals("LEFT") || ans.equals("L")) {
                        String scytheNum;
                        int num = (int) (Math.random() * 2 + 1);
                        if (num == 1)
                            scytheNum = "RIGHT-R";
                        else
                            scytheNum = "LEFT-L";
                        int dash = scytheNum.indexOf("-");
                        String firstWord = scytheNum.substring(0, dash);
                        String secondWord = scytheNum.substring(dash + 1);
                        if (ans.equals(firstWord) || ans.equals(secondWord)) {
                            if (responseNum == 1) {
                                System.out.println("Skimmed right past ya. ");
                            } else if (responseNum == 2) {
                                System.out.println("Can you see the future? ");
                            } else {
                                System.out.println("Hermes, is that you? ");
                            }
                        } else {
                            if (responseNum == 1) {
                                System.out.println("Oooooh. Hit you right in the... the sore spot. ");
                            } else if (responseNum == 2) {
                                System.out.println("Lost the 50/50 I guess.  ");
                            } else {
                                System.out.println("I predict... A scythe in your chest! ");
                            }
                            player.addPlayerAttack(-5);
                            player.addPlayerPriority(-3);
                            System.out.println("You have lost 5 attack and 3 priority. ");
                        }

                    } else {
                        CommandWords words = new CommandWords();
                        for(String word: words.getValidCommands()){
                            if(words.isCommand(ans.toLowerCase()))
                            displayCommandMessage = true;
                        }
                        displayFirstMessage = false;
                    }
                } catch (Exception ex) {
                    displayFirstMessage = false;
                }
            }
        }
    }

    /**
     * final attack for Thanatos, instant kill if no death defiance, if there is a death defiance, revive with 50% HP, and no death defiance
     * @param player player object to subtract HP from
     */
    public void bossFinalAttack(Player player) {
        Scanner in = new Scanner(System.in);
        if(!super.isAlive()){
            System.out.println("Thanatos slams his scythe into the ground, causing a tremor to ripple through the room. ");
            System.out.println("If I'm going to die, you're coming with me. ");
            System.out.println("A black mist trickles from his scythe and connects to your body as you feel your soul being ripped out. ");
            if(player.getExtraLife()){
                System.out.println("You fall to your knees as you take unbearable damage, yet your soul returns to you. ");
                System.out.println("Thanatos falls to the ground with a thump. ");
                System.out.println("I will see you again... ");
                player.setExtraLife(false);
                player.setPlayerHP(player.getPlayerMaxHP()/2);
            }else{
                System.out.println("You writhe in pain as your soul is ripped out and crushed between Thanatos's bare hands, your energy and life force diminishing. ");
                System.out.println("Thanatos takes a kneel on the ground. ");
                System.out.println("I guess I got the last laugh... ");
                player.setPlayerHP(0);
            }
            }
            
        }

    /**
     * rage called when Thanatos falls below 33% hp: increased attack and decrease priority
     */
    public void bossRage() {
        if (super.activateRage()) {
            super.addBossAtk(69);
            super.addBossPriority(-2);
            displayBossRage();
        }
    }

    public void displayBossRage() {
        System.out.println("Thanatos has fallen into a state of rage. ");
        System.out.println("Emanating a green glow, Thanatos begins to siphon energy from around him as he looks at you with a sneer. ");
        System.out.println("It's been a long time since I've had to go all out. What a nuisance. ");
    }
}
