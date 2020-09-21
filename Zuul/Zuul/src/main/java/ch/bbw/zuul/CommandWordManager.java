package ch.bbw.zuul;


import java.util.ArrayList;

/** 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Noelle Senti (and Michael Kolling and David J. Barnes)
 * @version 3.0 November 2019
 **/
public class CommandWordManager {

    // an arraylist that holds all valid command words
    private ArrayList<String> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWordManager() {
        this.validCommands = new ArrayList<>();
        this.validCommands.add("go");
        this.validCommands.add("quit");
        this.validCommands.add("help");
        this.validCommands.add("talk");
        this.validCommands.add("take");
        this.validCommands.add("search");
        this.validCommands.add("commands");
        this.validCommands.add("give");
        this.validCommands.add("move");
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @param aString Command Word
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString) {
        for (String c : validCommands) {
            if (c.equals(aString)) {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

	public void printCommands() {
		for (String command : validCommands) {
			System.out.print(command + "  ");
		}
		System.out.println("");
		
	}

}
