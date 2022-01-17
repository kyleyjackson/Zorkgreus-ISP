package Zorkgreus;

import java.util.Scanner;

public class Parser {
    private CommandWords commands; // holds all valid command words
    private Scanner in;

    public Parser() {
        commands = new CommandWords();
        in = new Scanner(System.in);
    }

    public Command getCommand() throws java.io.IOException {
        String inputLine = "";
        String[] words;

        System.out.print("> "); // print prompt

        inputLine = in.nextLine();

        words = inputLine.split(" ");

        String word1 = words[0];
        String word2 = null;
        String word3 = null;
        String word4 = null;
        String word5 = null;
        if (words.length > 1)
            word2 = words[1];
        if(words.length > 2)
            word3 = words[2];
        if(words.length > 3)
            word4 = words[3];
        if(words.length > 4)
            word5 = words[4];
        if (commands.isCommand(word1))
            return new Command(word1, word2, word3, word4, word5);
        else
            return new Command(null, word2, word3, word4, word5);
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands() {
        commands.showAll();
    }
}
