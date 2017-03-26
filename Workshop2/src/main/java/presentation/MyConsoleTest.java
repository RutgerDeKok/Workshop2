package main.java.presentation;

import java.awt.Color;

import org.springframework.context.ApplicationContext;

import main.java.config.ApplicationContextProvider;
import main.java.config.StartProgram;

public class MyConsoleTest {
	
	boolean runTestAgain = true;
	private ColorConsole console;
	
	

	public static void main(String[] args) {
		
		MyConsoleTest test = new MyConsoleTest();
		
		ApplicationContextProvider appContext = new ApplicationContextProvider();
		
		ApplicationContext context = StartProgram.context;
		System.out.println(context);
		System.out.println(test.console);
		
		test.console =(ColorConsole)context.getBean(main.java.presentation.ColorConsole.class);
		System.out.println(test.console);
		do{
			 test.runTest();
			 test.confirmTestAgain();
		}while(test.runTestAgain);
		
		test.confirmExit();
		
	}
	
	private void runTest(){
		
		console.print("testing 123 test", Color.magenta);
		
		console.print("\nTesting a long text"
					+"\ntesting 123 test"
					+"\ntesting 456 test"
					+"\ntesting 789 test", Color.BLUE);
		
		String response = console.printResponse("What's your Name?", "Pipo", Color.green);
		
		console.print("\nYour name is: "+response+"\n", Color.ORANGE);
		
		response = console.printResponse("Vind je dit mooi? [J/N}", "", Color.green);
		response = response.toUpperCase();
		switch (response){
		case "J":
			console.print("\nJippie, we vinden het allebij mooi! :)", Color.WHITE);
			break;
		case "N":
			console.print("\nWat!!! :(", Color.RED);
			break;
		default:
			console.print("Geen mening?", Color.MAGENTA);
			break;
		}
		
		
		
		for(int i = 5;i>0;i--){
			response = console.printResponse("Type wat zinnigs, je hebt nog "+i+(i>1? " kansen":" kans"), "Kaas", new Color(70,206,206));
			
			console.print("Antwoord: "+response+"\n", Color.ORANGE);
		}
		
		response = console.printResponse("Wil je het scherm leeg maken? [J/N}", "", Color.green);
		response = response.toUpperCase();
		switch (response) {
		case "J":
			console.clear();
			break;
		case "N":
			console.print("Ok!", Color.RED);
			break;
		default:
			console.print("?", Color.MAGENTA);
			break;
		}
		
	}
	
	
	
	
		public void confirmTestAgain(){
			String response;
	
			do{
			response = console.printResponse("Wil je de test nog een keer doen? [J/N}", "", Color.green);
			response = response.toUpperCase();
	
			}while (!response.equals("J") && !response.equals("N"));
			switch (response) {
			case "J":
				runTestAgain = true;
				break;
			default:
				runTestAgain = false;
				break;
			}
		}
		
		
		
		public void confirmExit(){
			
			String response = null;
			response = console.printResponse("\nWil je het venster afsluiten? [J/N}", "", Color.PINK);
			response = response.toUpperCase();
			
			while (!response.equals("J")){
			response = console.printResponse("", "", Color.green);
			response = response.toUpperCase();
			}
			
			console.exit();
		}
		

}
