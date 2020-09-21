package ch.bbw.zuul;

import java.util.ArrayList;

/**
 * Class Player - the player in an adventure game.
 *
 * A "Player" represents the user playing the game. It stores the inventory of
 * the player such as the location (Room) he's currently at.
 * 
 * @author Noelle Senti
 * @version 3.0 November 2019
 **/

public class Player {

	private ArrayList<Thing> inventory = new ArrayList<>();
	private ArrayList<Keys> keyChain = new ArrayList<>();

	public Player() {

	}

	// GETTER AND SETTER
	public ArrayList<Keys> getKeys() {
		return keyChain;
	}

	public ArrayList<Thing> getInventory() {
		return inventory;
	}

	public Thing getThing(int number) {
		return inventory.get(number);
	}
	
	public int getThingIndex(Thing comparison) {
		int index = 0;
		for (Thing thing : inventory) {
			index++;
			if (thing.equals(comparison)) {
				return index;
			}
		}
		return index;
	}

	// METHODS

	/*
	 * output all the things in the players inventory
	 */
	public void outputInventory() {
		System.out.println("work in progress");
	}

	/*
	 * add something to players inventory
	 */
	public void addThing(Thing thing) {
		inventory.add(thing);
	}

	/*
	 * add key to players key chain
	 */
	public void addKey(Keys key) {
		keyChain.add(key);
	}

	
/*
 * checks if key is the right key
 */
	public boolean rightKey(Room room) {
		for (Keys key : keyChain) {
			if (key.getRoom().equals(room)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * removes used thing
	 */

	public void removeThing(int index) {
		inventory.remove(index);
	}

	
	/*
	 * is the key chain empty
	 */
	public boolean checkInventoryKey() {
		if (keyChain.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/*
	 * index of key
	 */
	public int getIndexKey(Keys key) {
		for (int a = 0; a < keyChain.size(); a++) {
			if (keyChain.get(a).equals(key)) {
				return a;
			}
		}
		return 0;
	}

	// Remove used key
	public void removeKey(Room room) {
		int a = -1;
		for (Keys key : keyChain) {
			a++;
			if (key.getRoom().equals(room)) {
				keyChain.remove(a);
			}
		}
	}

}
