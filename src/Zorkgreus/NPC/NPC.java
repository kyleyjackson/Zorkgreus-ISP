package Zorkgreus.NPC;

import java.util.ArrayList;
import Zorkgreus.Boon;
import Zorkgreus.Player;

public class NPC {

    // all methods below exist just for polymorphism - allowing subclasses to override
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
     * @return potentially altered boons in the player's inventory
     */
    public ArrayList<Boon> displayChoices(Player player, ArrayList<Boon> generatedBoons, ArrayList<Boon> playerBoons){ 
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

}
