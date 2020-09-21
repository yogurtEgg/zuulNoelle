package ch.bbw.zuul;

/**
 * @author  Noelle Senti
 * @version 3.0 November 2019
 *
 */

public class Keys {
	private Room room;
	private String name;
	
	public Keys (String name, Room room) {
		this.name = name;
		this.room = room;
	}

//GETTER AND SETTER
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
