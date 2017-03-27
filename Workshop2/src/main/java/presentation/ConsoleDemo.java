package main.java.presentation;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleDemo {

	boolean runDemoAgain = true;
	@Autowired
	private ColorConsole console;

	public void tester() {

		confirmdDemoAgain();
		while (runDemoAgain){
			runDemo();
			confirmdDemoAgain();
		}

		confirmExit();

	}

	private void runDemo() {
		System.out.println(console);
		console.println("testing 123 ", Color.magenta);
		console.print("testing 123 ", Color.green);
		console.print("testing 123 ", Color.white);
		console.print("testing 123", Color.orange);

		console.println("\nTesting a long text" +
						"\ntesting 123 test" + 
						"\ntesting 456 test" + 
						"\ntesting 789 test", Color.BLUE);

		String response = console.printResponse("What's your Name?", "Pipo", Color.green);

		console.println("\nYour name is: " + response + "\n", Color.ORANGE);

		response = console.printResponse("Vind je dit mooi? [J/N}", "", Color.green);
		response = response.toUpperCase();
		switch (response) {
		case "J":
			console.println("\nJippie, we vinden het allebij mooi! :)", Color.WHITE);
			break;
		case "N":
			console.println("\nWat!!! :(", Color.RED);
			break;
		default:
			console.println("Geen mening?", Color.MAGENTA);
			break;
		}
		
		char[] pass = console.printResponseMask("\nType een password", Color.cyan);
		console.println("je password is: ["+String.valueOf(pass)+"] oops.....verklapt!\n", Color.orange);
				
		for (int i = 5; i > 0; i--) {
			response = console.printResponse("Type wat zinnigs, je hebt nog " + i + (i > 1 ? " kansen" : " kans"),
					"Kaas", new Color(70, 206, 206));

			console.println("Antwoord: " + response + "\n", Color.ORANGE);
		}

		response = console.printResponse("Wil je het scherm leeg maken? [J/N}", "", Color.green);
		response = response.toUpperCase();
		switch (response) {
		case "J":
			console.clear();
			break;
		case "N":
			console.println("Ok!", Color.RED);
			break;
		default:
			console.println("?", Color.MAGENTA);
			break;
		}

	}

	public void confirmdDemoAgain() {
		String response;

		do {
			response = console.printResponse("Wil je de demo (nog een keer) doen? [J/N}", "J", Color.green);
			response = response.toUpperCase();

		} while (!response.equals("J") && !response.equals("N"));
		switch (response) {
		case "J":
			runDemoAgain = true;
			break;
		default:
			runDemoAgain = false;
			break;
		}
	}

	public void confirmExit() {

		String response = null;
		response = console.printResponse("\nWil je het venster afsluiten? [J/N}", "", Color.PINK);
		response = response.toUpperCase();

		while (!response.equals("J")) {
			response = console.printResponse("", "", Color.green);
			response = response.toUpperCase();
		}

		console.exit();
	}

}
