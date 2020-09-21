package ch.bbw.zuul;

/**
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is null.
 *
 * If the command had only one word, then the second word is null.
 * 
 * @author  Noelle Senti (and Michael Kolling and David J. Barnes)
 * @version 3.0 November 2019
 */

public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     * Create a command object. First and second word must be supplied, but
     * either one (or both) can be null. The command word should be null to
     * indicate that this was a command that is not recognised by this game.
     * @param firstWord First part of the Command
     * @param secondWord Second part of the Command
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return commandWord
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * Return the second word of this command. Returns null if there was no
     * second word.
     * @return secondWord
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Return true if this command was not understood.
     * @return true, if commandWord is null
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * Return true if the command has a second word.
     * @return true if secondWord is not null
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

