package ch.bbw.zuul;

/**
 * * Class Character - a character in an adventure game.
 *
 * A "character" represents a person in the game. It is assigned to objects,
 * with which the player can get another thing or a clue.
 * 
 * @author Noelle Senti
 * @version 3.0 November 2019
 *
 */

public class Character {
	private String name;
	private Thing neededThing;
	private String output;

	public Character(String name, Thing thing, String output) {
		this.name = name;
		this.neededThing = thing;
		this.output = (output);
	}
	
	
	//GETTER AND SETTER
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Thing getThing() {
		return neededThing;
	}


	public void setThing(Thing neededThing) {
		this.neededThing = neededThing;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}
	
	//METHODS
	
	/*
	 * prints answer of character
	 */
	public void printOutput() {
		System.out.println(output);
	}
	
	/*
	 * checks if character needs thing
	 */
	public boolean needsThing(Thing thing) {
		if (thing.getName().equals(this.neededThing.getName())) {
			return true;
		} else {
			return false;
		}
	}

}
