package ch.bbw.zuul;

import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Noelle Senti (and Michael Kolling and David J. Barnes)
 * @version 3.0 November 2019
 *
 **/
public class Room {

	private final String description;
	private String name;
	private ArrayList<Exits> exits;
	private Interactions interaction;
	private Thing thing;
	private Character character;

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 * Interactions describes all the possible actions and character that are in the
	 * room.
	 * 
	 * @param description Description of the Room
	 * @param interaction Interaction doable in the Room
	 * @param name The name of the room
	 * @param character The character present in the room
	 */
	public Room(String description, Interactions interaction, String name, Character character) {
		this.description = description;
		this.interaction = interaction;
		this.name = name;
		this.character = character;
		exits = new ArrayList<>();
	}

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard". It
	 * contains no Character
	 * 
	 * @param description Description of the Room
	 * @param interaction Interaction doable in the Room
	 * @param name The name of the room
	 */
	public Room(String description, Interactions interaction, String name) {
		this.description = description;
		this.interaction = interaction;
		this.name = name;
		exits = new ArrayList<>();		
	}

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 * Interactions describes all the possible actions and character that are in the
	 * room.
	 * 
	 * @param description Description of the Room
	 * @param interaction Interaction doable in the Room
	 * @param name The name of the room
	 * @param thing Thing in the room
	 */
	public Room(String description, Interactions interaction, String name, Thing thing) {
		this.description = description;
		this.interaction = interaction;
		this.name = name;
		this.thing = thing;
		exits = new ArrayList<>();
	}
	
	//GETTER AND SETTER
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInteraction(Interactions interaction) {
		this.interaction = interaction;
	}

	public String getInteraction() {
		return interaction.getInteraction();
	}
	
	//Interaction has been made
	public boolean interactionState() {
		return interaction.getDone();
	}

	public void setInteractionState(boolean state) {
		interaction.setDone(state);
	}
	
	//print output of interaction
	public void printOutput() {
		interaction.interactionOutput();
	}

	public String getCharacterName() {
		return character.getName();
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Thing getObject() {
		return thing;
	}

	public ArrayList<Exits> getExits() {
		return exits;
	}

	public void setExits(ArrayList<Exits> exits) {
		this.exits = exits;
	}
	
	//add Exits to the room
	public void addExit(Exits e) {
		exits.add(e);
	}
	
	//Remove all exits
	public void clearExits() {
		exits.clear();
	}
	
	//Print all exits of the room
	public void printExits() {
		for(Exits exit : exits) {
			System.out.println(exit.getDescription());
		}
	}
}
