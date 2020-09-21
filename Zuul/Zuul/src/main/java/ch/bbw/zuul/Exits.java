package ch.bbw.zuul;

/**
 * Class Exits - an exit in an adventure game.
 *
 * An "Exit" represents the connection between the scenery of the game. It is
 * connected to two other rooms.
 * 
 * @author Noelle Senti
 * @version 3.0 November 2019
 *
 */

public class Exits {
	private String name;
	private String description;
	private Room room;
	private Thing neededThing;

	public Exits(String name, String description, Room room, Thing neededThing) {
		this.name = name;
		this.description = description;
		this.room = room;
		this.neededThing = neededThing;
	}

	public Exits(String name, String description, Room room) {
		this.name = name;
		this.description = description;
		this.room = room;
	}
	
	//GETTER AND SETTER
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Thing getNeededThing() {
		return neededThing;
	}

	public void setNeededThing(Thing neededThing) {
		this.neededThing = neededThing;
	}

}
