package Zorkgreus.NPC;

import java.util.Scanner;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Player;

public class Charon extends NPC{
    public Charon(){
        displayIntroMessage();
        System.out.print("\n\n");
    }

    public void displayIntroMessage(){
        System.out.println("Waiting next to his boat on The River Styx, a shadowy figure dressed in a black cloak with golden coins around his neck turns towards you.");
        System.out.println("You see his large black hat covering most of his face, with the only visible features being his eerie, glowing purple eyes and mouth.");
        System.out.println("The figure is also seen to be gripping a large oar with both hands, carrying a pouch that has a skull embedded into its side.");
        System.out.println("As he mumbles unintelligbly, it is clear that this is Charon, The Stygian Boatman, responsible for escorting dead souls into the Underworld.");
    }

    public ArrayList<Boon> displayChoices(Player player, ArrayList<Boon> generatedBoons, ArrayList<Boon> playerBoons){
        Scanner in = new Scanner(System.in);
        boolean displayFirstMessage = true;
        boolean displaySellMessage = true;
        boolean validInput = false;

        int random1 = (int)(Math.random() * 3);
        int random2 = (int)(Math.random() * 3);
        if(random1 == random2){
            while(random1 == random2){
                random2 = (int)(Math.random() * 3);
            }
        }

        System.out.println("In front of you, there are 6 items: " + "\n");
        if(random1 == 0)
            System.out.println("Centaur Heart - Gain +25 max HP (without healing). | 75 Gold");
        else if(random1 == 1)
            System.out.println("Bowl of Noodles - Heal for 40% of your max HP. | 50 gold");
        else
            System.out.println("Pom of Power - One of your boons will gain +1 level. | 100 gold");

        System.out.println("Random Blessing - You can select one of 3 boons provided from ANY god (excluding Chaos). | 150 gold");

        if(random2 == 0)
            System.out.println("Centaur Heart - Gain +25 max HP (without healing). | 75 Gold");
        else if(random2 == 1)
            System.out.println("Bowl of Noodles - Heal for 40% of your max HP. | 50 gold");
        else
            System.out.println("Pom of Power - One of your boons will randomly gain +1 level. | 100 gold");
        
        System.out.println("\n" + "It seems Charon is also offering to purge certain items in exchange for gold. (Type \"sell\" to prompt sell commands!)");

        while (!validInput) {
            if(displayFirstMessage) {
                System.out.println("\n" + "To exit the shop interface, type \"exit\"." + "\n");
                System.out.println("To go to the 2nd page of items, type [2]." + "\n");
                System.out.print("Which one would you like to select: ");
                if(random1 == 0)
                    System.out.print("[C]entaur Heart, ");
                else if(random1 == 1)
                    System.out.print("[B]owl of Noodles, ");
                else 
                    System.out.print("[P]om of Power, ");

                System.out.print("[R]andom Blessing, or ");

                if(random2 == 0)
                    System.out.println("[C]entaur Heart");
                else if(random2 == 1)
                    System.out.println("[B]owl of Noodles");
                else 
                    System.out.println("[P]om of Power");
            }
            try {
                String msg = in.nextLine().toLowerCase();
                if(msg.equals("c") || msg.equals("centaur heart")){
                    if(player.getPlayerGold() < 75)
                        System.out.println("Ngggghhh. (You can't afford that.)");
                    else{
                        player.addPlayerMaxHP(25);
                        System.out.println("Hrrrrraughhhh. (Anything else?)");
                    }
                } else if(msg.equals("b") || msg.equals("bowl of noodles")) {
                    if(player.getPlayerGold() < 50)
                        System.out.println("Ngggghhh. (You can't afford that.)");
                    else{
                        player.addPlayerHP((int)(player.getPlayerHP() * 0.4));
                        System.out.println("Nhrrrrrrrgh. (Anything else?)");
                    }
                } else if(msg.equals("p") || msg.equals("pom of power")) {
                    if(player.getPlayerGold() < 100)
                        System.out.println("Ngggghhh. (You can't afford that.)");
                    else{
                        int random = (int)(Math.random() * playerBoons.size());
                        Boon b = playerBoons.get(random);
                        if(!b.canLevelAtIndex(playerBoons, random)){
                            while(!b.canLevelAtIndex(playerBoons, random)){
                                random = (int)(Math.random() * playerBoons.size());
                            }
                            b = playerBoons.get(random);
                        }
                        b.levelUpPom(playerBoons, random);
                        System.out.println("Grrrrhhhah. (Anything else?)");
                    }
                } else if(msg.equals("r") || msg.equals("random blessing")) {
                    if(player.getPlayerGold() < 150)
                        System.out.println("Ngggghhh. (You can't afford that.)");
                    else{
                        System.out.println("\n" + generatedBoons.get(0).getColour() + "Please select one of the boons by typing \"b[number]\":");
                        System.out.print("----------------------------------------------------------------------------------------------------------");
                        System.out.println(generatedBoons.get(0).displayBoon() + generatedBoons.get(1).displayBoon() + generatedBoons.get(2).displayBoon());
                        boolean validBoonInput = false;
                        while(!validBoonInput){
                            try {
                                String boonMsg = in.nextLine().toLowerCase();
                                if(boonMsg.equals("b1")){
                                    playerBoons.add(generatedBoons.get(0)); 
                                    System.out.println("You selected Boon: " + generatedBoons.get(0).getBoonName());
                                    for (Boon b : playerBoons) {
                                        b.levelUp(playerBoons, generatedBoons, 0);
                                    }
                                }
                                else if(boonMsg.equals("b2")){
                                    playerBoons.add(generatedBoons.get(1)); 
                                    System.out.println("You selected Boon: " + generatedBoons.get(1).getBoonName());
                                    for (Boon b : playerBoons) {
                                        b.levelUp(playerBoons, generatedBoons, 1);
                                    }
                                }
                                else if(boonMsg.equals("b3")){
                                    playerBoons.add(generatedBoons.get(1)); 
                                    System.out.println("You selected Boon: " + generatedBoons.get(1).getBoonName());
                                    for (Boon b : playerBoons) {
                                        b.levelUp(playerBoons, generatedBoons, 1);
                                    }
                                }
                                else
                                    System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                                
                            } catch (Exception ex){
                                System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                            }
                        }
                        System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                    }
                } else if(msg.equals("exit")) {
                    System.out.println("Ghhhhehh. (Pleasure doing business.)");
                    validInput = true;
                } else if(msg.equals("sell")) {
                    boolean validSellInput = false;
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
                                validSellInput = true;
                            }
                            else{
                                System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                            }
                        } catch (Exception ex){
                            displaySellMessage = false;
                        }
                    }
                } else {
                    System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                    displayFirstMessage = false;
                }
            } catch (Exception ex){
                displayFirstMessage = false;
            }
        }
        return playerBoons;
    }
}
