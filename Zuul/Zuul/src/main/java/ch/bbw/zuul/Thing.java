package ch.bbw.zuul;

/**
 * Class Thing - a thing in an adventure game.
 *
 * A "Thing" represents an object in the game. The user can interact with it and
 * have it in his inventory.
 * 
 * @author Noelle Senti
 * @version 3.0 November 2019
 *
 */
public class Thing {
	private String name;
	private boolean used;

	public Thing(String name, boolean used) {
		this.name = name;
		this.used = used;
	}

	//GETTER AND SETTER
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
}
