package ch.bbw.zuul;


import java.util.Scanner;

/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Noelle Senti (and Michael Kolling and David J. Barnes)
 * @version 3.0 November 2019
 */
public class Parser {

    private CommandWordManager commands;  // holds all valid command words

    public Parser() {
        commands = new CommandWordManager();
    }

    public Command getCommand() {
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        Scanner reader = new Scanner(System.in);
        String inputLine = reader.nextLine();

        String[] words = inputLine.split(" ");
        if (words.length > 0) {
            word1 = words[0];
            if (words.length > 1) {
                word2 = words[1];
            }
        }

        // note: we just ignore the rest of the input line.
        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

	public void printCommands() {
		commands.printCommands();
		
	}
}
