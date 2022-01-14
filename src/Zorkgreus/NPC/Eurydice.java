package Zorkgreus.NPC;

import java.util.Scanner;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Player;

public class Eurydice extends NPC{
    public Eurydice(){
        displayIntroMessage();
        System.out.print("\n\n");
    }

    public void displayIntroMessage(){
        System.out.println("As you enter the room, you hear the beautiful singing of the carefree muse, Eurydice.");
        System.out.println("The atmosphere is warming and welcoming; you can smell the lingering scent of something delicious.");
        System.out.print("\"Hey hon, how's it goin? It gets a little lonely sometimes, always nice to see you drop by!");
        System.out.println(" Here, have something to eat. I've made a little too much for myself!\"");
    }

    public ArrayList<Boon> displayChoices(Player player, ArrayList<Boon> playerBoons){
        Scanner in = new Scanner(System.in);
        boolean displayFirstMessage = true;
        boolean validInput = false;

        System.out.println("Eurydice has offered you 3 items: " + "\n");
        System.out.println("Pom Porridge - Up to 3 of your boons gain +1 level.");
        System.out.println("Ambrosia Delight - One of your boons is upgraded to max level.");
        System.out.println("Refreshing Nectar - You can select one boon to gain +1 level.");

        while(!validInput){
            if(displayFirstMessage)
                System.out.print("Which item would you like to select: [P]om Porridge, [A]mbrosia Delight, or [R]efreshing Nectar");
            try{
                String msg = in.nextLine().toLowerCase();
                if(msg.equals("p") || msg.equals("pom porridge")){
                    int b1 = (int)(Math.random() * playerBoons.size());
                    int b2 = (int)(Math.random() * playerBoons.size());
                    int b3 = (int)(Math.random() * playerBoons.size());
                    int count = cantLevel(playerBoons);
                    Boon b;
                    if(playerBoons.size() - count >= 1){ //checks if the size of playerBoons minus the # of non-levellable boons is >= 1
                        b = playerBoons.get(b1); //assigns boon b to the first random value
                        if(!b.canLevelAtIndex(playerBoons, b1)){ //if it can't level the boon, cycle until it finds one it can level
                            while(!b.canLevelAtIndex(playerBoons, b1)){ //while it is an unlevellable boon or it is equal to the previous
                                b1 = (int)(Math.random() * playerBoons.size());
                            }
                            b = playerBoons.get(b1);
                        }
                        b.levelUpPom(playerBoons, b1); //level up the boon
                    }
                    if(playerBoons.size() - count >= 2){
                        b = playerBoons.get(b2);
                        if(!b.canLevelAtIndex(playerBoons, b2)){
                            while(!b.canLevelAtIndex(playerBoons, b2) || b2 == b1){ 
                                b2 = (int)(Math.random() * playerBoons.size());
                            }
                            b = playerBoons.get(b2);
                        }
                        b.levelUpPom(playerBoons, b2);
                    }
                    if(playerBoons.size() - count >= 3){ 
                        b = playerBoons.get(b3);
                        if(!b.canLevelAtIndex(playerBoons, b3)){
                            while(!b.canLevelAtIndex(playerBoons, b3) || b3 == b2 || b3 == b1){
                                b3 = (int)(Math.random() * playerBoons.size());
                            }
                            b = playerBoons.get(b3);
                        }
                        b.levelUpPom(playerBoons, b3);
                    }
                    validInput = true;
                    System.out.println("Bye now!");
                } else if(msg.equals("a") || msg.equals("ambrosia delight")){
                    int random = (int)(Math.random() * playerBoons.size());
                    Boon b = playerBoons.get(random);
                    if(!b.canLevelAtIndex(playerBoons, random)){
                        while(!b.canLevelAtIndex(playerBoons, random)){
                            random = (int)(Math.random() * playerBoons.size());
                            b = playerBoons.get(random);
                        }
                    }
                    if(b.getLevel() == 1)
                        b.levelUpPom(playerBoons, random);
                    if(b.getLevel() == 2)
                        b.levelUpPom(playerBoons, random);
                    validInput = true;
                    System.out.println("Good luck!");
                } else if(msg.equals("r") || msg.equals("refreshing nectar")){
                    int b1 = (int)(Math.random() * playerBoons.size());
                    int b2 = (int)(Math.random() * playerBoons.size());
                    int b3 = (int)(Math.random() * playerBoons.size());
                    int count = cantLevel(playerBoons);
                    Boon b = new Boon();
                    ArrayList<Boon> temp = new ArrayList<>();
                    if(playerBoons.size() - count >= 1){ //checks if the size of playerBoons minus the # of non-levellable boons is >= 1
                        b = playerBoons.get(b1); //assigns boon b to the first random value
                        if(!b.canLevelAtIndex(playerBoons, b1)){ //if it can't level the boon, cycle until it finds one it can level
                            while(!b.canLevelAtIndex(playerBoons, b1)){ //while it is an unlevellable boon or it is equal to the previous
                                b1 = (int)(Math.random() * playerBoons.size());
                            }
                            b = playerBoons.get(b1);
                        }
                        temp.add(b);
                    }
                    if(playerBoons.size() - count >= 2){
                        b = playerBoons.get(b2);
                        if(!b.canLevelAtIndex(playerBoons, b2)){
                            while(!b.canLevelAtIndex(playerBoons, b2) || b2 == b1){ 
                                b2 = (int)(Math.random() * playerBoons.size());
                            }
                            b = playerBoons.get(b2);
                        }
                        temp.add(b);
                    }
                    if(playerBoons.size() - count >= 3){ 
                        b = playerBoons.get(b3);
                        if(!b.canLevelAtIndex(playerBoons, b3)){
                            while(!b.canLevelAtIndex(playerBoons, b3) || b3 == b2 || b3 == b1){
                                b3 = (int)(Math.random() * playerBoons.size());
                            }
                            b = playerBoons.get(b3);
                        }
                        temp.add(b);
                    }
                    boolean validBoonUpgrade = false;
                    boolean boonPrompt = true;
                    while(!validBoonUpgrade){
                        if(boonPrompt){
                            System.out.println("\n" + "Please select one of the boons to level up by typing \"b[number]\":");
                            System.out.print("----------------------------------------------------------------------------------------------------------");
                            System.out.println(temp.get(0).displayBoon() + temp.get(1).displayBoon() + temp.get(2).displayBoon());
                        }
                        try{
                        String select = in.nextLine().toLowerCase();
                        if(select.equals("b1")){
                            b.levelUpPom(playerBoons, b1);
                            validBoonUpgrade = true;
                        }
                        else if(select.equals("b2")){
                            b.levelUpPom(playerBoons, b2);
                            validBoonUpgrade = true;
                        }
                        else if(select.equals("b3")){
                            b.levelUpPom(playerBoons, b3);
                            validBoonUpgrade = true;
                        }
                        else{
                            System.out.println("Invalid selection!");
                            boonPrompt = false;
                        }
                        } catch(Exception ex){
                            boonPrompt = false;
                        }
                    }
                    validInput = true;
                    System.out.println("Safe travels!");
                }else{
                    System.out.println("I don't have that, hon!");
                    displayFirstMessage = false;
                }
            } catch(Exception ex){
                displayFirstMessage = false;
            }
        }
        return playerBoons;
    }

    /**
     * Finds the number of boons in player's boons that can't be levelled.
     * @param playerBoons ArrayList of player's boons
     * @return number of boons that can't be levelled
     */
    public int cantLevel(ArrayList<Boon> playerBoons){
        int count = 0;
        for(Boon b : playerBoons){
            if(b.getLevel() < 3 && !(b.getBoonName() == "Smite" || b.getBoonName() == "First Strike" || b.getBoonName() == "Vitality"
            || b.getBoonName() == "High Tide" || b.getBoonName() == "Second Wind"))
                count++;
        }
        return count;
    }
}
