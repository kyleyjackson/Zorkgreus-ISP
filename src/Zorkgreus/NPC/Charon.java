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
        System.out.println("Waiting next to his boat on The River Styx, a shadowy figure dressed in a black cloak and golden Obol's around his neck turns towards you.");
        System.out.println("You see his large black hat covering most of his face, with the only visible features being his eerie, glowing purple eyes and mouth.");
        System.out.println("The figure is also seen to be gripping a large oar with both hands, carrying a pouch that has a skull embedded into its side.");
        System.out.println("As he mumbles unintelligbly, it is clear that this is Charon, The Stygian Boatman, responsible for escorting dead souls into the Underworld.");
    }

    public void displayChoices(Player player, ArrayList<Boon> generatedBoons){
        Scanner in = new Scanner(System.in);
        boolean displayFirstMessage = true;
        boolean validInput = false;

        int random1 = (int)(Math.random() * 3);
        int random2 = (int)(Math.random() * 3);
        if(random1 == random2){
            while(random1 == random2){
                random2 = (int)(Math.random() * 3);
            }
        }

        System.out.println("In front of you, there are 3 items: ");
        if(random1 == 0)
            System.out.println("Centaur Heart - Gain +25 max HP (without healing).");
        else if(random1 == 1)
            System.out.println("Bowl of Noodles - Heal for 40% of your max HP.");
        else
            System.out.println("Pom of Power - One of your boons will gain +1 level.");

        System.out.println("Random Blessing - You can select one of 3 boons, offered from ANY god (excluding Chaos). ");

        if(random2 == 0)
            System.out.println("Centaur Heart - Gain +25 max HP (without healing).");
        else if(random2 == 1)
            System.out.println("Bowl of Noodles - Heal for 40% of your max HP.");
        else
            System.out.println("Pom of Power - One of your boons will gain +1 level.");
        
        System.out.println("----------------------------------------------------------------------------------------------------------");

        System.out.println("It seems Charon is also offering to purge certain items in exchange for Obol. (Type \"sell\" to prompt sell commands!");

        while (!validInput) {
            String msg = in.nextLine().toLowerCase();

        }
    }
}
