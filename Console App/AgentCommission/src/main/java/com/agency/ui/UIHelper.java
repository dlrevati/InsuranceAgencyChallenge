package com.agency.ui;
import java.util.Scanner;
/**
 * UI Helper for getting Inputs/Outputs
 */
public class UIHelper {
	static Scanner inputReader=null;
	static{
		inputReader=new Scanner(System.in);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	closeScanner();
		    }
		}));
	}
	
	/**
	 * Write to display.
	 * @param message the message
	 */
	public static void writeToDisplay(String message)
	{
		System.out.println(message);
	}
	
	/**
	 * Gets the input from user.
	 * @return the input from user
	 */
	public static String getInputFromUser()
	{
		return getScannerInput();
	}
	
	/**
	 * Gets the input from user char.
	 * @return the input from user char
	 */
	public static char getInputFromUserChar(){
		String input=getScannerInput();
		char inputChar='0';
		if(input!=null){
			inputChar=input.charAt(0);
		}
		return inputChar;
	}

	/**
	 * Gets the scanner input.
	 * @return the scanner input
	 */
	private static String getScannerInput(){		
		String userInput="";
		userInput=inputReader.nextLine();
		return userInput;
	}
	
	/**
	 * Close scanner.
	 */
	private static void closeScanner(){
		if(inputReader!=null){
			inputReader.close();
		}
	}

}
