package Zorkgreus.NPC;

import java.util.Scanner;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Player;

public class Charon extends NPC{
    public Charon(Player player, ArrayList<Boon> generatedBoons){
        displayIntroMessage();
        System.out.print("\n\n");
        displayChoices(player, generatedBoons);
    }

    public void displayIntroMessage(){
        System.out.println("Waiting next to his boat on The River Styx, a shadowy figure dressed in a black cloak with golden coins around his neck turns towards you.");
        System.out.println("You see his large black hat covering most of his face, with the only visible features being his eerie, glowing purple eyes and mouth.");
        System.out.println("The figure is also seen to be gripping a large oar with both hands, carrying a pouch that has a skull embedded into its side.");
        System.out.println("As he mumbles unintelligbly, it is clear that this is Charon, The Stygian Boatman, responsible for escorting dead souls into the Underworld.");
    }

    public void displayChoices(Player player, ArrayList<Boon> generatedBoons){
        Scanner in = new Scanner(System.in);
        boolean displayFirstMessage = true;
        boolean displaySellMessage = true;
        boolean validInput = false;
        boolean validSellInput = false;

        int random1 = (int)(Math.random() * 3);
        int random2 = (int)(Math.random() * 3);
        if(random1 == random2){
            while(random1 == random2){
                random2 = (int)(Math.random() * 3);
            }
        }

        System.out.println("In front of you, there are 3 items: " + "\n");
        if(random1 == 0)
            System.out.println("Centaur Heart - Gain +25 max HP (without healing). | 75 Gold");
        else if(random1 == 1)
            System.out.println("Bowl of Noodles - Heal for 40% of your max HP. | 50 gold");
        else
            System.out.println("Pom of Power - One of your boons will gain +1 level. | 100 gold");

        System.out.println("Random Blessing - You can select one of 3 boons provided from ANY god (excluding Chaos). | 150 gold");

        if(random2 == 0)
            System.out.println("Centaur Heart - Gain +25 max HP (without healing).");
        else if(random2 == 1)
            System.out.println("Bowl of Noodles - Heal for 40% of your max HP.");
        else
            System.out.println("Pom of Power - One of your boons will gain +1 level.");
        
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("It seems Charon is also offering to purge certain items in exchange for gold. (Type \"sell\" to prompt sell commands!)");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        while (!validInput) {

            if(displayFirstMessage) {
                System.out.print("Which one would you like to select: ");
                if(random1 == 0)
                    System.out.print("[C]entaur Heart, ");
                else if(random1 == 1)
                    System.out.print("[B]owl of Noodles, ");
                else 
                    System.out.print("[P]om of Power, ");

                System.out.print("[R]andom Blessing, or ");

                if(random2 == 0)
                    System.out.print("[C]entaur Heart, ");
                else if(random2 == 1)
                    System.out.print("[B]owl of Noodles, ");
                else 
                    System.out.print("[P]om of Power, ");

                System.out.println("\n" + "To exit the shop interface, type \"exit\".");
            }
            try {
                String msg = in.nextLine().toLowerCase();
                if(msg.equals("c") || msg.equals("centaur heart")){
                    player.addPlayerMaxHP(25);
                    System.out.println("Hrrrrraughhhh. (Anything else?)");
                } else if(msg.equals("b") || msg.equals("bowl of noodles")) {
                    player.addPlayerHP((int)(player.getPlayerHP() * 0.4));
                    System.out.println("Nhrrrrrrrgh. (Anything else?)");
                } else if(msg.equals("p") || msg.equals("pom of power")) {
                    player.addPlayerAttack(69420); //code later
                    System.out.println("Grrrrhhhah. (Anything else?)");
                } else if(msg.equals("r") || msg.equals("random blessing")) {
                    player.addPlayerAttack(69420); //display generated boons here
                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                } else if(msg.equals("exit")) {
                    System.out.println("Ghhhhehh. (Pleasure doing business.)");
                    validInput = true;
                } else if(msg.equals("sell")) {
                    while(!validSellInput){
                        if(displaySellMessage){
                            System.out.println("Charon will trade the following items for gold: " + "\n");
                            System.out.println("[Sk]eleton's Bone | 5 Gold per Bone");
                            System.out.println("[Sp]ider's Leg | 10 Gold per Leg");
                            System.out.println("[H]ero's Urn | 15 Gold per Urn");
                            System.out.println("\n" + "To go back to the buying menu, type \"buy\".");
                            displayFirstMessage = true;
                            displaySellMessage = false;
                        }
                        try {
                            String sellMsg = in.nextLine().toLowerCase();
                            if(sellMsg.equals("sk") || sellMsg.equals("skeleton's bone")){
                                //prompt for count and give appropriate gold, also check maxWeight.
                            }
                            else if(sellMsg.equals("sp") || sellMsg.equals("spider's leg")){
                                //prompt for count and give appropriate gold, also check maxWeight.
                            }
                            else if(sellMsg.equals("h") || sellMsg.equals("hero's urn")){
                                //prompt for count and give appropriate gold, also check maxWeight.
                            }
                            else if(sellMsg.equals("back") || sellMsg.equals("buy")){
                                break;
                            }
                            else{
                                System.out.println("Gguhhhhhhh. (Invalid Selection!)");
                            }
                        } catch (Exception ex){
                            displaySellMessage = false;
                        }
                    }
                } else {
                    System.out.println("Gguhhhhhhh. (Invalid Selection!)");
                    displayFirstMessage = false;
                }
            } catch (Exception ex){
                displayFirstMessage = false;
            }
        }
    }
}
