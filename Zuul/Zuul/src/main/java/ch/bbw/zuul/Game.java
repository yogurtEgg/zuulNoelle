package ch.bbw.zuul;

import java.util.Scanner;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery and interact with some characters and things.
 * 
 * The user finds themselves in a scenario were they are a student at a
 * university whoms phone was taken by a professor. Their quest? Get to the
 * office and take the phone. But that's not as easy as it may sound...
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * exits, things, characters and interactions, creates the parser and starts the
 * game. It also evaluates and executes the commands that the parser returns.
 *
 * @author Noelle Senti (and Michael Kolling and David J. Barnes)
 * @version 3.0 November 2019
 */
public class Game {

	private final Parser parser;
	private Player player;
	private Room currentRoom;
	private String help = ("You are lost. You are alone...");
	private Boolean keyAvailable = false;
	Scanner sc = new Scanner(System.in);

	/**
	 * create the things 
	 * name, used
	 */
	Thing phone = new Thing("phone", false);
	Thing leaves = new Thing("leaves", false);
	Thing empty = new Thing("", true);
	Thing box = new Thing("box", false);
	Thing phoneNumber = new Thing("phone number", false);
	
	
	/**
	 * create the characters and assign the things they desire name, thing they
	 * want, output
	 */
	Character labProfessor = new Character("Lab Professor", leaves,
			"The lab Professor will tell you were to get the key in exchange for some leaves.");

	/**
	 * initialise the Interactions command, output, assigned object, correctly used
	 */
	Interactions take = new Interactions("take", "You take the ", leaves, false);
	Interactions nothing = new Interactions("", "This room is boring, nothing to do here yet!", empty, false);
	Interactions locked = new Interactions("locked",
			"This room needs a key and you don't have one! Type 'help' for key!", phone, false);
	Interactions search = new Interactions("search",
			"You search a cabinet, but get caught by a guard! You need to get away!", empty, false);
	Interactions give = new Interactions("give", "You give the ", empty, false);
	Interactions talk = new Interactions("talk", "You talk to ", empty, false);
	Interactions move = new Interactions("move", "You move the ", box, false);

	/**
	 * Rooms are being initialised description, assigned Interaction, name,
	 * (character)
	 */
	Room outside = new Room("in the campus park of the university, it's autumn. Leaves are on the floor", nothing,
			"entrance", leaves);
	Room frontRoom = new Room(
			"in the front room of the office, you can see the inside of the office through a window. Your phone is there",
			nothing, "front room");
	Room pub = new Room("in the campus pub. There are loads cabinets", nothing, "pub");
	Room lab = new Room("in a computing lab. There is a professor working by himself, he seems nice and helpful.",
			nothing, "lab", labProfessor);
	Room office = new Room("in the computing admin office, you can see your phone lying on the table.", locked,
			"office", phone);
	Room entryHall = new Room(
			"in the huge entry hall of the university, it looks fairly beautiful and is very detailed", nothing,
			"entry hall");
	Room storageRoom = new Room(
			"in the storage room of the university, it looks like noone cleaned in here for years. There are boxes laying everywhere. You can also see a ventilation shaft, but it's to far up to reach it.",
			nothing, "storageRoom");
	Room changingRoom = new Room("in the university changing room of the gym. It smells gross.", nothing, "changingRoom");
	Room gym = new Room("in the university gym. Ugh. I hate the gym.", nothing, "gym");

	/**
	 * All the exits are created and rooms are being assigned 
	 * name, description, going to, coming from, needed Thing
	 */
	Exits staircaseEntry = new Exits("staircase", "staircase to the front room of the office", frontRoom);
	Exits staircaseFrontroom = new Exits("staircase", "staircase to the entry hall", entryHall);
	Exits walkthroughEntry = new Exits("walkthrough", "walkthrough to the pub", pub);
	Exits walkthroughPub = new Exits("walkthrough", "walkthrough to the entry hall", entryHall);
	Exits entranceOutside = new Exits("entry", "entry to the university", entryHall);
	Exits exitEntryHall = new Exits("exit", "exit of the university", outside);
	Exits pathEntry = new Exits("path", "path leading to the lab", lab);
	Exits pathLab = new Exits("path", "path leading to the entry hall", entryHall);
	Exits portChangingRooms = new Exits("port", "port to the gym", gym);
	Exits portGym = new Exits("port", "port to the changing rooms.", outside);
	Exits gateGym = new Exits("gate", "gate to the campus park.", outside);
	Exits gateOutside = new Exits("gate", "gate to the gym.", gym);
	Exits corridorFrontroom = new Exits("corridor", "corridor leading to the storage room", storageRoom);
	Exits corridorStorageRoom = new Exits("corridor", "corridor leading to the front room", storageRoom);
	Exits doorFrontroom = new Exits("door", "door leading to office", office);
	Exits ventilationShaft = new Exits("ventilation", "ventilation, perfect to hide in.", changingRoom);
	

	/**
	 * create the key
	 * 
	 */
	Keys officeKey = new Keys("key", office);

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game() {
		initializeObjects();
		parser = new Parser();
		player = new Player();
	}

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void initializeObjects() { // initialise inventory
		// initialise room exits
		entryHall.addExit(staircaseEntry);
		entryHall.addExit(pathEntry);
		entryHall.addExit(exitEntryHall);
		entryHall.addExit(walkthroughEntry);
		pub.addExit(walkthroughPub);
		lab.addExit(pathLab);
		frontRoom.addExit(staircaseFrontroom);
		frontRoom.addExit(doorFrontroom);
		frontRoom.addExit(corridorFrontroom);
		storageRoom.addExit(corridorStorageRoom);
		outside.addExit(entranceOutside);
		outside.addExit(gateOutside);
		gym.addExit(gateGym);

		currentRoom = outside; // start game outside
	}

	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean finished = false;
		while ((!phone.isUsed()) && !finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("\n \n" + " You did it! \n" + "Thank you for playing. Good bye.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to Adventure!");
		System.out.println("Adventure is a new, incredibly boring adventure game.");
		System.out.println("You are a student at a university and the computing professor took your phone.");
		System.out.println("Go to his office, the computing office, to get it.\n");
		System.out.println("Here is how the game works:\n"
				+ "If the output is 'Exits: staircase to heaven' type: 'go staircase'.\n");
		System.out.println("Type 'help' if you need any help or clues.");
		System.out.println();
		System.out.println("You are " + currentRoom.getDescription());
		System.out.println("Exits: ");
		currentRoom.printExits();

		System.out.println();
	}

	/**
	 * Given a command, process (that is: execute) the command. If this command ends
	 * the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		} else if (commandWord.equals("go")) {
			goRoom(command);
		} else if (commandWord.equals("quit")) {
			wantToQuit = quit(command);
		} else if (commandWord.equals("take")) {
			printTake(commandWord);
		} else if (commandWord.equals("give")) {
			printGive(command);
		} else if (commandWord.equals("talk")) {
			printTalk(commandWord);
		} else if (commandWord.equals("search")) {
			printSearch(commandWord);
		} else if (commandWord.equals("commands")) {
			printCommands();
		} else if (commandWord.equals("move")) {
			printMove(commandWord);
		} else {
			System.out.println("work in progress");
		}

		return wantToQuit;
	}

	/**
	 * You move a object.
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private void printMove(String command) {
		if (!checkInteraction(command)) {
			return;
		}
		move.setDone(true);
		System.out.println("You move the box and can now reach the ventilation shaft.");
		storageRoom.clearExits();
		storageRoom.addExit(ventilationShaft);

	}

	/**
	 * All the possible Commands are printed
	 */
	private void printCommands() {
		parser.printCommands();
	}

	/**
	 * You get to talk with someone.
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private void printTalk(String command) {
		if (!checkInteraction(command)) {
			return;
		}
		System.out.println(talk.getOutput() + currentRoom.getCharacterName());
		System.out.println(currentRoom.getCharacter().getOutput());
		talk.setDone(true);
	}

	/**
	 * You give someone something and it gets checked to be valid and if you have
	 * something in store.
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private void printGive(Command command) {
		String commandWord = command.getCommandWord();
		if (!checkInteraction(commandWord)) {
			return;
		}
		int number = -1;
		if (player.getInventory().isEmpty()) {
			System.out.println("You have nothing to give.");
			return;
		} else
			try {
				if (!command.hasSecondWord()) {
					System.out.println("You have following things to give: \n");

					for (int a = 1; a <= player.getInventory().size(); a++) {
						System.out.print(a + " " + player.getInventory().get(a - 1).getName() + " ");
					}
					System.out.println("> ");

					number = Integer.parseInt(sc.nextLine());
					number--;

					Thing thing = player.getInventory().get(number);
					Character character = currentRoom.getCharacter();

					if (!character.needsThing(thing)) {
						System.out.println(character.getName() + " doesn't want " + thing.getName() + ". Try again!");
					} else {
						System.out.println(give.getOutput() + player.getThing(number).getName());
						character.printOutput();
						player.getThing(number).setUsed(true);
						player.removeThing(number);
						currentRoom.setInteractionState(true);
					}
				} else {
					System.out.println("You don't even know what's in your backpack! Try 'give'");
				}
			} catch (NumberFormatException e) {
				System.out.println("You have to enter a number!");
			}

	}

	/**
	 * Print out some help information. Here we print some stupid, cryptic message
	 * and a list of the command words.
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private void printHelp() {
		System.out.println(help);
		System.out.println("Type 'commands' for valid commands");
	}

	/**
	 * Here you can take stuff.
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private void printTake(String command) {
		if (!checkInteraction(command)) {
			System.out.println("There is nothing to take here!");
			return;
		}
		Thing object = currentRoom.getObject();
		player.addThing(object);
		System.out.println(take.getOutput() + object.getName());
		currentRoom.setInteractionState(true);
	}

	/**
	 * You search through something
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private void printSearch(String command) {
		if (!checkInteraction(command)) {
			System.out.println("You find nothing...");
			return;
		}
		if (keyAvailable) {
			player.addKey(officeKey);
			System.out.println("You take the key!");
		} else {
			System.out.println(search.getOutput());
			search.setDone(true);
			checkRoom();
		}
	}

	/**
	 * The Interaction gets checked to function in current room.
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private boolean checkInteraction(String command) {
		String currentInteraction = currentRoom.getInteraction();
		if (!(currentInteraction.equals(command))) {
			// if there is nothing right to interact with, we don't know what to do...
			System.out.println("Sorry, this is not possible here");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Try to go to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 * 
	 * @param command command is checked for being valid in this room.
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...

			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = null;
		for (Exits exit : currentRoom.getExits()) {
			if (direction.equals(exit.getName())) {
				nextRoom = exit.getRoom();
			}
		}

		if (nextRoom == null) {
			System.out.println("There is no such exit!");
		} else if ((nextRoom.getInteraction().equals("locked"))) {
			if (player.checkInventoryKey()) {
				System.out.println("Use key");
				if (player.rightKey(nextRoom)) {
					System.out.println("You have the right key! You use it and can now enter the room.");
					nextRoom.setInteraction(nothing);
					player.removeKey(currentRoom);
				} else {
					System.out.println();
					player.outputKeys();
				}
			} else {
				nextRoom.setInteractionState(true);
				System.out.println(locked.getOutput());
				help = "Talk to the professor for help!";
			}
		} /**
			 * else if (!currentRoom.getInteraction().isEmpty()) {
			 * currentRoom.setInteractionState(true); }
			 **/
		else {
			currentRoom = nextRoom;
			checkRoom();
			System.out.println("You are " + currentRoom.getDescription());
			System.out.print("Exits: ");
			currentRoom.printExits();

			System.out.println();
		}
	}

	/**
	 * All the Interactions get checked for being done, so the game can move
	 * forward.
	 */
	public void checkRoom() {
		if (locked.getDone()) {
			lab.setInteraction(talk);
		}

		if (talk.getDone()) {
			lab.setInteraction(nothing);
			outside.setInteraction(take);
			labProfessor.setOutput("I last saw a key in the campus pub.");
			help = "Take the leaves from outside!";
		}

		if (take.getDone()) {
			lab.setInteraction(give);
			outside.setInteraction(nothing);
			talk.setDone(false);
			help = "Give the leaves to the professor for help!";
		}

		if (give.getDone()) {
			pub.setInteraction(search);
			lab.setInteraction(nothing);
			take.setDone(false);
			help = "Search the key in the pub!";
		}

		if (search.getDone()) {
			give.setDone(false);
			locked.setOutput("He will find you here immediatly. You should go to the storage room!");
			outside.setInteraction(locked);
			lab.setInteraction(locked);
			lab.setInteraction(locked);

			help = "Go to the storage Room! You can hide there!";

			take.setObject(box);
			take.setOutput("You ");
			take.setDone(false);
			storageRoom.setInteraction(move);

			if (currentRoom.getName().equals("storageRoom")) {
				help = "Move the box to climb to the ventilation shaft";
			} else if (currentRoom.getName().equals("changingRoom")) {
				help = "You can get the key now! It's still in the pub.";
				outside.setInteraction(nothing);
				lab.setInteraction(nothing);
				lab.setInteraction(nothing);
				keyAvailable = true;
			}

		}

		if (currentRoom.getName().equals("office")) {
			take.setObject(phone);
			take.setOutput("You finally have your");
			currentRoom.setInteraction(take);
		}
	}

	/**
	 * "Quit" was entered. Check the rest of the command to see whether we really
	 * quit the game. Return true, if this command quits the game, false otherwise.
	 * 
	 * @param command Command is checked for being entered correctly.
	 */
	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
}