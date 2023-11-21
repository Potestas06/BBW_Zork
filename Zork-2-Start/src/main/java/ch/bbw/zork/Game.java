package ch.bbw.zork;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Game {

	private Parser parser;
	private Room currentRoom;
	private Room entranceHall, throneRoom, library, storageRoom, secretLaboratory, treasureRoom;
	private ArrayList<Room> allRooms;

	public Game() {

		parser = new Parser(System.in);

		entranceHall = new Room("Entrance hall");
		throneRoom = new Room("Throne Room");
		library = new Room("Library");
		storageRoom = new Room("Storage Room");
		secretLaboratory = new Room("Secret Laboratory");
		treasureRoom = new Room("Treasure room");
		allRooms = new ArrayList<>();
		allRooms.add(entranceHall);
		allRooms.add(throneRoom);
		allRooms.add(library);
		allRooms.add(storageRoom);
		allRooms.add(secretLaboratory);
		allRooms.add(treasureRoom);

		entranceHall.put(null, throneRoom, null, null);
		throneRoom.put(null, secretLaboratory, library, null);
		library.put(throneRoom, null, storageRoom, null);
		storageRoom.put(library, null, null, null);
		secretLaboratory.put(null, null, treasureRoom, throneRoom);
		treasureRoom.put(secretLaboratory, null, null, null);

		currentRoom = entranceHall;
	}

	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean finished = false;

		while (!finished) {
			Command command = parser.get(); // reads a command
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing. Good bye.");
	}

	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to Zork!");
		System.out.println("Zork is a simple adventure game.");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		System.out.println(currentRoom.longDescription());
	}

	private void showMap() {
		for (Room room : allRooms) {
			System.out.println("*****************************************");
			if (room == currentRoom) {
				System.out.println("--You are currently in this room.--");
			}
			System.out.println(room.longDescription());
		}
		System.out.println("*****************************************");
	}

	private boolean processCommand(Command command) {
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		} else if (commandWord.equals("go")) {
			goRoom(command);

			// Gewonnen?
			// Hier ein Beispielcode für "gewonnen", der Code prüft Objektreferenzen

			if (currentRoom == treasureRoom) {
				System.out.println("Well you are rich now. You won the game.");
				return true;
			}

		} else if (commandWord.equals("map")) {
			showMap();
		} else if (commandWord.equals("quit")) {
			if (command.hasSecondWord())
				System.out.println("Quit what?");
			else
				return true; // signal that we want to quit
		}
		return false;
	}

	/*
	 * implementations of user commands:
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at Monash Uni, Peninsula Campus.");
		System.out.println();
		System.out.println("Your command words are:");
		System.out.println(parser.showCommands());
	}

	private void goRoom(Command command) {
		// if there is no second word, we don't know where to go...
		if (!command.hasSecondWord()) {
			System.out.println("Go where?");
		} else {
			String direction = command.getSecondWord();

			// Try to leave current room.
			Room nextRoom = currentRoom.nextRoom(direction);

			if (nextRoom == null)
				System.out.println("There is no door!");
			else {
				currentRoom = nextRoom;
				System.out.println(currentRoom.longDescription());
			}
		}
	}
}
