package Zorkgreus.NPC;

import java.util.Scanner;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Item;
import Zorkgreus.Player;

public class NPC {

    /*all methods below exist just for polymorphism - allowing subclasses to override */
    public void displayIntroMessage(){
    }

    /**
     * displayChoices function for Sisyohus and Patroculus
     * @param player the player of the game
     */
    public void displayChoices(Player player){
    }

    /**
     * displayChoices function for Charon.
     * @param player the player of the game
     * @param generatedBoons randomly generated boons for the player to select
     * @param playerBoons the boons in the player's inventory
     * @param items items generated
     * @return potentially altered boons in the player's inventory
     */
    public ArrayList<Boon> displayChoices(Player player, ArrayList<Boon> generatedBoons, ArrayList<Boon> playerBoons, ArrayList<Item> items){ 
        return playerBoons;
    }

    /**
     * displayChoices function for Eurydice.
     * @param player the player of the game
     * @param playerBoons the boons in the player's inventory
     * @return altered boons in the player's inventory
     */
    public ArrayList<Boon> displayChoices(Player player, ArrayList<Boon> playerBoons){
        return playerBoons;
    }

    /**
     * Function for selling items at Charon.
     * @param player the player of the game
     * @param in Scanner for player to determine which items to sell.
     */
    public void sell(Player player, Scanner in){

    }

}
