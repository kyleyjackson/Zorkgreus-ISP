package Zorkgreus.NPC;

import java.util.Scanner;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Inventory;
import Zorkgreus.Player;

public class Charon extends NPC{
    boolean displayFirstMessage = true;
    boolean displaySellMessage = true;

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
        boolean validInput = false;

        int random1 = (int)(Math.random() * 3);
        int random2 = (int)(Math.random() * 3);
        if(random1 == random2){
            while(random1 == random2){
                random2 = (int)(Math.random() * 3);
            }
        }
        
        System.out.print("Charon is offering you an assortment of items in exchange for gold. ");
        System.out.println("He is also offering to purge certain items in exchange for gold.");

        while (!validInput) {
            displaySellMessage = true;
            if(displayFirstMessage) {
                System.out.println("To exit the shop interface, type \"exit\".");
                System.out.println("To open the selling menu, type \"sell\".");
                System.out.println("To go to the 2nd page of items, type \"2\"." + "\n");
                System.out.println("Which one would you like to select: ");
                if(random1 == 0)
                    System.out.println("[C]entaur Heart - Gain +25 max HP (without healing). | 75 Gold");
                else if(random1 == 1)
                    System.out.println("[B]owl of Noodles - Heal for 40% of your max HP. | 50 gold");
                else
                    System.out.println("[P]om of Power - One of your boons will gain +1 level. | 100 gold");

                System.out.println("[R]andom Blessing - You can select one of 3 boons provided from ANY god (excluding Chaos). | 150 gold");

                if(random2 == 0)
                    System.out.println("[C]entaur Heart - Gain +25 max HP (without healing). | 75 Gold");
                else if(random2 == 1)
                    System.out.println("[B]owl of Noodles - Heal for 40% of your max HP. | 50 gold");
                else
                    System.out.println("[P]om of Power - One of your boons will randomly gain +1 level. | 100 gold");
            }
            try {
                String msg = in.nextLine().toLowerCase();
                if(msg.equals("c") || msg.equals("centaur heart")){
                    if(player.getPlayerGold() < 75)
                        System.out.println("Ngggghhh. (You can't afford that.)");
                    else{
                        player.addPlayerMaxHP(25);
                        player.addPlayerGold(-75);
                        System.out.println("Hrrrrraughhhh. (Anything else?)");
                    }
                } else if(msg.equals("b") || msg.equals("bowl of noodles")) {
                    if(player.getPlayerGold() < 50)
                        System.out.println("Ngggghhh. (You can't afford that.)");
                    else{
                        player.addPlayerHP((int)(player.getPlayerHP() * 0.4));
                        player.addPlayerGold(-50);
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
                        player.addPlayerGold(-100);
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
                        player.addPlayerGold(-150);
                        System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                    }
                } else if(msg.equals("exit")) {
                    System.out.println("Ghhhhehh. (Pleasure doing business.)");
                    validInput = true;
                } else if(msg.equals("2")){
                    System.out.println("\n" + "To exit the shop interface, type \"exit\"." + "\n");
                    System.out.println("To go to the 1st page of items, type [1]." + "\n");
                    System.out.println("Which one would you like to select:");
                    System.out.println("[T]orn Duffel Bag - Increase your inventory's max capacity by 50. | 80 gold");
                    System.out.println("[L]eather Backpack - Increase your inventory's max capacity by 100. | 140 gold");
                    System.out.println("[R]ugged Suitcase - Increase your inventory's max capacity by 200. | 220 gold.");
                    boolean validP2Input = false;
                    while(!validP2Input){
                        try {
                            String msgP2 = in.nextLine().toLowerCase();
                            if(msgP2.equals("t") || msgP2.equals("torn duffel bag")){
                                if(player.getPlayerGold() < 80)
                                    System.out.println("Ngggghhh. (You can't afford that.)");
                                else
                                    player.getInventory().addMaxWeight(50);
                                    player.addPlayerGold(-80);
                                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                            } else if(msgP2.equals("l") || msgP2.equals("leather backpack")){
                                if(player.getPlayerGold() < 140)
                                    System.out.println("Ngggghhh. (You can't afford that.)");
                                else
                                    player.getInventory().addMaxWeight(100);
                                    player.addPlayerGold(-140);
                                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                            } else if(msgP2.equals("r") || msgP2.equals("rugged suitcase")){
                                if(player.getPlayerGold() < 220)
                                    System.out.println("Ngggghhh. (You can't afford that.)");
                                else
                                    player.getInventory().addMaxWeight(200);
                                    player.addPlayerGold(-220);
                                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)"); 
                            } else if(msgP2.equals("1")){
                                displayFirstMessage = true;
                                validP2Input = true;
                            } else if(msgP2.equals("sell")){
                                sell(player, in);
                            }
                        } catch (Exception ex){
                            System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                        }
                    }
                } else if(msg.equals("sell")) {
                    sell(player, in);
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
    public void sell(Player player, Scanner in){
        boolean validSellInput = false;
        while(!validSellInput){
            if(displaySellMessage){
                System.out.println("Charon will trade the following items for gold: " + "\n");
                System.out.println("[Sk]eleton Bones | 5 Gold per Bone");
                System.out.println("[Sp]ider Legs | 10 Gold per Leg");
                System.out.println("[H]ero Urns | 15 Gold per Urn");
                System.out.println("\n" + "To go back to the buy menu, type \"buy\".");
                displayFirstMessage = true;
                displaySellMessage = false;
            }
            try {
                String sellMsg = in.nextLine().toLowerCase();
                if(sellMsg.equals("sk") || sellMsg.equals("skeleton bones")){
                    if(!player.getInventory().inInventory("Skeleton's Bone"))
                        System.out.println("Hnnnnnnnnnnrrghhhhh. (You don't have that.)");
                    else{
                        try{
                            System.out.println("You have " + player.getInventory().countItem("Skeleton's Bone") + " skeleton bones.");
                            System.out.println("Hsssurrrrrggh. (How many?) ");
                            String count = in.nextLine().toLowerCase();
                            int cParsed = Integer.parseInt(count);
                            if(cParsed > player.getInventory().countItem("Skeleton's Bone"))
                                System.out.println("(Charon can't take more than what you have...)");
                            else{
                                player.addPlayerGold(cParsed * 5);
                                System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                            }
                        } catch (Exception ex){
                            System.out.println("Grrrrraggggghhh. (Select a number.)");
                        }
                    }
                    displaySellMessage = true;
                }
                else if(sellMsg.equals("sp") || sellMsg.equals("spider legs")){
                    if(!player.getInventory().inInventory("Spider's Leg"))
                        System.out.println("Hnnnnnnnnnnrrghhhhh. (You don't have that.)");
                    else{
                        try{
                            System.out.println("You have " + player.getInventory().countItem("Spider's Leg") + " spider legs");
                            System.out.println("Hsssurrrrrggh. (How many?) ");
                            String count = in.nextLine().toLowerCase();
                            int cParsed = Integer.parseInt(count);
                            if(cParsed > player.getInventory().countItem("Spider's Leg"))
                                System.out.println("(Charon can't take more than what you have...)");
                            else{
                                player.addPlayerGold(cParsed * 10);
                                System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                            }
                        } catch (Exception ex){
                            System.out.println("Grrrrraggggghhh. (Select a number.)");
                        }
                    }
                    displaySellMessage = true;
                }
                else if(sellMsg.equals("h") || sellMsg.equals("hero urns")){
                    if(!player.getInventory().inInventory("Hero's Urn"))
                        System.out.println("Hnnnnnnnnnnrrghhhhh. (You don't have that.)");
                    else{
                        try{
                            System.out.println("You have " + player.getInventory().countItem("Hero's Urn") + " heros' urns.");
                            System.out.println("Hsssurrrrrggh. (How many?) ");
                            String count = in.nextLine().toLowerCase();
                            int cParsed = Integer.parseInt(count);
                            if(cParsed > player.getInventory().countItem("Spider's Leg"))
                                System.out.println("(Charon can't take more than what you have...)");
                            else{
                                player.addPlayerGold(cParsed * 15);
                                System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                            }
                        } catch (Exception ex){
                            System.out.println("Grrrrraggggghhh. (Select a number.)");
                        }
                    }
                    displaySellMessage = true;
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
    }
}
