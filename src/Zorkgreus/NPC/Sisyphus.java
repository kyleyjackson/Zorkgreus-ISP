package Zorkgreus.NPC;

import java.util.Scanner;

import Zorkgreus.Player;

public class Sisyphus extends NPC{
    
    public Sisyphus(Player player){
        displayIntroMessage();
        System.out.println("");
        System.out.println("");
        displayChoices(player);
    }

    public void displayIntroMessage(){
        System.out.println("Chained to a boulder 4 times the size of him, this man of great stature looks at you with eyes full of joy as he pats the boulder. ");
        System.out.println("Hello wanderer, how's your day going? Me and bouldy over here are having the time of our lives right now! ");
        System.out.println("See, I've been with good 'ol bouldy over here for as long as I can remember, and we've formed quite the friendship.  ");
        System.out.println("Please, on behalf of me and boudly take these offereings. ");
    }

    public void displayChoices(Player player) {
        Scanner in = new Scanner(System.in);
        boolean displayFirstMessage = true;
        boolean validInput = false;
        boolean displayFullHPMessage = false;
        System.out.println("Sisyphus has offered you 3 items: ");
        System.out.println("Touch of Midas - Gain 100 gold ");
        System.out.println("Centaur Soul - Gain 50 max HP (without restoring HP) ");
        System.out.println("Life Essence - Restore 50% of max HP ");
        while (!validInput) {
            if(displayFullHPMessage)
                System.out.print("Invalid Input - [T]ouch of Midas, or [C]entaur Soul: ");
            else if(player.getPlayerHP()==player.getPlayerMaxHP()){
            System.out.println("Because you are full HP already, you cannot select Life Essence. ");
            System.out.print("Which one would you like to select, [T]ouch of Midas, or [C]entaur Soul: ");
            }else if (displayFirstMessage)
                System.out.print("Which one would you like to select, [T]ouch of Midas, [C]entaur Soul, or [L]ife Essence: ");
            else
                System.out.print("Invalid Input - [T]ouch of Midas, [C]entaur Soul, or [L]ife Essence: ");
            try {
                String msg = in.nextLine().toUpperCase();
                if(player.getPlayerHP()==player.getPlayerMaxHP()&&((msg.equals("L")|| msg.equals("LIFE ESSENCE"))))
                    displayFullHPMessage = true;
                else if (msg.equals("T")
                        || msg.equals("TOUCH OF MIDAS")
                        || msg.equals("C")
                        || msg.equals("CENTAUR SOUL")
                        || msg.equals("L")
                        || msg.equals("LIFE ESSENCE")) {
                       if(msg.equals("T")||msg.equals("TOUCH OF MIDAS")){
                            player.addPlayerGold(100);
                       }else if(msg.equals("C")||msg.equals("CENTAUR SOUL")){
                            player.addPlayerMaxHP(50);
                       }else{
                            player.addPlayerHP(player.getPlayerMaxHP()/2);
                       }
                      validInput = true;
                }else{
                    if(player.getPlayerHP()==player.getPlayerMaxHP()){
                        displayFullHPMessage = true;
                    }
                    displayFirstMessage = false;
                }
            } catch (Exception ex) {
                if(player.getPlayerHP()==player.getPlayerMaxHP()){
                    displayFullHPMessage = true;
                }
                displayFirstMessage = false;
            }
        }
    }

}
