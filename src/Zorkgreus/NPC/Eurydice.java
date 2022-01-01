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
                    //level up 3 boons from playerBoons
                } else if(msg.equals("a") || msg.equals("ambrosia delight")){
                    //one of playerBoons is maxed out (goes to lvl 3)
                } else if(msg.equals("r") || msg.equals("refreshing nectar")){
                    //you select one of your boons to level up
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
}
