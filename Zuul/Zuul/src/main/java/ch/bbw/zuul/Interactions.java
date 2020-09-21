package ch.bbw.zuul;

/**
 * Class Interactions - an interaction in an adventure game.
 *
 * An "Interaction" represents a possible interaction in the game one can make
 * with various objects. It is connected to things, which is interacts with.
 *  * 
 * @author Noelle Senti
 * @version 3.0 November 2019
 **/

public class Interactions {
	private  String interaction;
	private  String output;
	private  Thing object;
	private  boolean done;

	public Interactions(String interaction, String output, Thing object, boolean done) {
		this.interaction = interaction;
		this.output = output;
		this.object = object;
		this.done = done;
	}

	//GETTER AND SETTER
	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public Thing getObject() {
		return object;
	}

	public void setObject(Thing object) {
		this.object = object;
	}

	public void interactionOutput() {
		System.out.println(this.output);
		this.done = true;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean getDone() {
		return done;
	}
}
