package Zorkgreus.NPC;

import java.util.Scanner;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Item;
import Zorkgreus.Player;

public class Charon extends NPC{
    boolean displayFirstMessage = true;
    boolean displaySellMessage = true;

    public Charon(){
        displayIntroMessage();
        System.out.print("\n\n");
    }

    /**
     * Displays Charon's intro message.
     */
    public void displayIntroMessage(){
        System.out.println("Waiting next to his boat on The River Styx, a shadowy figure dressed in a black cloak with golden coins around his neck turns towards you.");
        System.out.println("You see his large black hat covering most of his face, with the only visible features being his eerie, glowing purple eyes and mouth.");
        System.out.println("The figure is also seen to be gripping a large oar with both hands, carrying a pouch that has a skull embedded into its side.");
        System.out.println("As he mumbles unintelligbly, it is clear that this is Charon, The Stygian Boatman, responsible for escorting dead souls into the Underworld.");
    }

    /**
     * Displays the choices at Charon's shop. 
     * @param player the player
     * @param generatedBoons the randomly generated boons from any god
     * @param playerBoons ArrayList of boons in player inventory
     * @param items items ArrayList initialized in Game.java
     * 
     * @return new playerBoons ArrayList
     */
    public ArrayList<Boon> displayChoices(Player player, ArrayList<Boon> generatedBoons, ArrayList<Boon> playerBoons, ArrayList<Item> items){
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
                System.out.println("Which one would you like to select: ");
                System.out.println("[B]owl of Noodles - Heal for 25% of your max HP. | 25 gold");
                System.out.println("[R]andom Blessing - You can select one of 3 boons provided from ANY god. | 100 gold");
                System.out.println("[C]entaur Heart - Gain +25 max HP (without healing). | 50 gold");
                System.out.println("\n" + "To exit the shop interface, type \"exit\".");
                System.out.println("To open the selling menu, type \"sell\".");
                System.out.println("To go to the 2nd page of items, type \"2\".");
            }
            try {
                String msg = in.nextLine().toLowerCase();
                if(msg.equals("c") || msg.equals("centaur heart")){
                    if(player.getPlayerGold() < 50)
                        System.out.println("Ngggghhh. (You can't afford that.)" + " You have " + player.getPlayerGold() + " gold.");
                    else{
                        player.addPlayerMaxHP(25);
                        player.addPlayerGold(-50);                      
                        System.out.println("Hrrrrraughhhh. (Anything else?)");
                        System.out.println();
                    }
                } else if(msg.equals("b") || msg.equals("bowl of noodles")) {
                    if(player.getPlayerGold() < 25)
                        System.out.println("Ngggghhh. (You can't afford that.)" + " You have " + player.getPlayerGold() + " gold.");
                    else{
                        player.addPlayerHP((int)(player.getPlayerHP() * 0.25));
                        player.addPlayerGold(-25);
                        System.out.println("Nhrrrrrrrgh. (Anything else?)");
                        System.out.println();
                    }
                } else if(msg.equals("r") || msg.equals("random blessing")) {
                    if(player.getPlayerGold() < 100)
                        System.out.println("Ngggghhh. (You can't afford that.)" + " You have " + player.getPlayerGold() + " gold.");
                    else{
                        System.out.println("\n" + generatedBoons.get(0).getColour() + "Please select one of the boons by typing \"b[number]\":");
                        System.out.print("----------------------------------------------------------------------------------------------------------");
                        System.out.println(generatedBoons.get(0).displayBoon() + generatedBoons.get(1).displayBoon() + generatedBoons.get(2).displayBoon());
                        boolean validBoonInput = false;
                        while(!validBoonInput){
                            try {
                                String boonMsg = in.nextLine().toLowerCase();
                                if(boonMsg.equals("b1")){
                                    Boon b = generatedBoons.get(0);
                                    b.levelUp(playerBoons, generatedBoons, 0);
                                    playerBoons.add(b); 
                                    System.out.println("You selected Boon: " + generatedBoons.get(0).getBoonName());
                                    validBoonInput = true;
                                }
                                else if(boonMsg.equals("b2")){
                                    Boon b = generatedBoons.get(1);
                                    b.levelUp(playerBoons, generatedBoons, 1);
                                    playerBoons.add(b);
                                    System.out.println("You selected Boon: " + generatedBoons.get(1).getBoonName()); 
                                    validBoonInput = true;
                                }
                                else if(boonMsg.equals("b3")){
                                    Boon b = generatedBoons.get(2);
                                    b.levelUp(playerBoons, generatedBoons, 2);
                                    playerBoons.add(b);
                                    System.out.println("You selected Boon: " + generatedBoons.get(2).getBoonName());
                                    validBoonInput = true;
                                }
                                else
                                    System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                                
                            } catch (Exception ex){
                                System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                            }
                        }
                        player.addPlayerGold(-100);
                        System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                        System.out.println();
                    }
                } else if(msg.equals("exit")) {
                    System.out.println("Ghhhhehh. (Pleasure doing business.)");
                    validInput = true;
                } else if(msg.equals("2")){
                    System.out.println("Which one would you like to select:");
                    System.out.println("[T]orn Duffel Bag - Increase your inventory's max capacity by 50. | 70 gold");
                    System.out.println("[L]eather Backpack - Increase your inventory's max capacity by 100. | 120 gold");
                    System.out.println("[R]ugged Suitcase - Increase your inventory's max capacity by 200. | 180 gold");
                    System.out.println("[V]ase of a Thousand Tormented Souls - Sets your inventory's max capacity to 69420. | 666 gold");
                    System.out.println("To open the selling menu, type \"sell\".");
                    System.out.println("To go to the 1st page of items, type \"1\".");
                    boolean validP2Input = false;
                    while(!validP2Input){
                        try {
                            String msgP2 = in.nextLine().toLowerCase();
                            if(msgP2.equals("t") || msgP2.equals("torn duffel bag")){
                                if(player.getPlayerGold() < 70)
                                    System.out.println("Ngggghhh. (You can't afford that.)" + " You have " + player.getPlayerGold() + " gold.");
                                else{
                                    player.getInventory().addMaxWeight(50);
                                    player.getInventory().addPlayerItem(items.get(22)); //torn duffel bag location in items ArrayList
                                    player.addPlayerGold(-70);
                                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                                    System.out.println();
                                }
                            } else if(msgP2.equals("l") || msgP2.equals("leather backpack")){
                                if(player.getPlayerGold() < 120)
                                    System.out.println("Ngggghhh. (You can't afford that.)" + " You have " + player.getPlayerGold() + " gold.");
                                else{
                                    player.getInventory().addMaxWeight(100);
                                    player.getInventory().addPlayerItem(items.get(23)); //leather backpack location in items ArrayList
                                    player.addPlayerGold(-120);
                                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)");
                                    System.out.println();
                                }
                            } else if(msgP2.equals("r") || msgP2.equals("rugged suitcase")){
                                if(player.getPlayerGold() < 180)
                                    System.out.println("Ngggghhh. (You can't afford that.)" + " You have " + player.getPlayerGold() + " gold.");
                                else{
                                    player.getInventory().addMaxWeight(200);
                                    player.getInventory().addPlayerItem(items.get(24)); //rugged suitcase location in items ArrayList
                                    player.addPlayerGold(-180);
                                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)"); 
                                    System.out.println();
                                }
                            } else if(msgP2.equals("v") || msgP2.equals("vase of a thousand tormented souls")){
                                if(player.getPlayerGold() < 666)
                                    System.out.println("Ngggghhh. (You can't afford that.)" + " You have " + player.getPlayerGold() + " gold.");
                                else{
                                    player.getInventory().setMaxWeight(69420);
                                    System.out.println("Your inventory's max weight was set to 69420. Excellent choice.");
                                    player.getInventory().addPlayerItem(items.get(27)); //vase of a thousand lost souls location in items ArrayList
                                    player.addPlayerGold(-666);
                                    System.out.println("Haaaaaaaaaahhhhhh. (Anything else?)"); 
                                    System.out.println();
                                }
                            } else if(msgP2.equals("1")){
                                displayFirstMessage = true;
                                validP2Input = true;
                            } else if(msgP2.equals("sell")){
                                sell(player, in);
                            } else if(msg.equals("exit") && !validP2Input) {
                                System.out.println("Why don't you take another look at page 1 before leaving?");
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
                System.out.println("Charon will trade the following items for gold: ");
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
                                for(int i=0; i < cParsed; i++){
                                    player.getInventory().dropItem("Skeleton's Bone");
                                }
                                validSellInput = true;
                            }
                        } catch (Exception ex){
                            System.out.println("Grrrrraggggghhh. (Select a number.)");
                        }
                    }
                    displaySellMessage = true;
                } else if(sellMsg.equals("sp") || sellMsg.equals("spider legs")){
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
                                for(int i=0; i < cParsed; i++){
                                    player.getInventory().dropItem("Spider's Leg");
                                }
                                validSellInput = true;
                            }
                        } catch (Exception ex){
                            System.out.println("Grrrrraggggghhh. (Select a number.)");
                        }
                    }
                    displaySellMessage = true;
                } else if(sellMsg.equals("h") || sellMsg.equals("hero urns")){
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
                                for(int i=0; i < cParsed; i++){
                                    player.getInventory().dropItem("Hero's Urn");
                                }
                                validSellInput = true;
                            }
                        } catch (Exception ex){
                            System.out.println("Grrrrraggggghhh. (Select a number.)");
                        }
                    }
                    displaySellMessage = true;
                } else if(sellMsg.equals("sell")){
                    System.out.println("Srrrrrrrrrrh. (Pick something.)");
                } else if(sellMsg.equals("back") || sellMsg.equals("buy")){
                    validSellInput = true;
                } else if(sellMsg.equals("exit") && !validSellInput){
                    System.out.println("Why don't you take another look at page 1 before leaving?");
                } else{
                    System.out.println("Gguhhhhhhh. (Invalid Selection.)");
                }
            } catch (Exception ex){
                displaySellMessage = false;
            }
        }
    }
}
