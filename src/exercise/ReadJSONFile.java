package exercise;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJSONFile {

	// no constructor 
	
	// methods
	/**
	 * Reads the JSONFile.
	 * Stores JSON data as an array.
	 * Controls the program.
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
		
		// initialize parser
		JSONParser jsonparser = new JSONParser();
		
		// load JSON file
		FileReader reader = new FileReader("file.json");
		
		// parse JSON file
		Object obj = jsonparser.parse(reader);
		
		// convert java object into JSON object
		JSONObject info =(JSONObject)obj;
		
		// extract array
		JSONArray array = (JSONArray)info.get("People");
		
		// while askAgain returns true, continuing searching data
		while (askAgain() == true) {
			
			personIdOrName(array);
		}
		
		// when askAgain returns false, exit the program
		System.out.println("Thank you, goodbye.");
	}

	/**
	 * Asks for input from user.
	 * If a 0, exits the program.
	 * If a 1, starts the program. 
	 * Doesn't allow for any other input.
	 * @return true if user wants to search, false if exit.
	 */
	public static boolean askAgain() {
		
		// prints the user a message
		// scans in
		Scanner input = new Scanner(System.in);
		
		// sets the number
		int number = 0;
		
		// run once and loop until input is valid
		do 
		{
			// prints the user a message
			System.out.println("Please enter 0 to exit or 1 to search: ");
			
			// prevent an invalid input
			while (!input.hasNextInt()) {
				System.out.println("");
				System.out.println("Please enter 0 to exit or 1 to search: ");
				input.next();
			}
			
			// set the number
			number = input.nextInt();
			
			// if the number is outside the range, loop again
			if (number < 0 || number > 1) {
				System.out.println("");
				//System.out.println("Please enter 0 to exit or 1 to search: ");
			}

			
		} while (number < 0 || number > 1);
		
		// if number is 0, exit the program
		if (number == 0) {
			return false;
		}
		
		// else continue
		return true;
		
	}

	
	/**
	 * Asks for input from the user of the person to search for.
	 * Will except an ID or Name. 
	 * Converts both to a string to be used in the DataReturn class.
	 * @param num
	 * @param array
	 */
	public static void personIdOrName (JSONArray array) {
		

		// prints the user a message
		System.out.println("Please enter the ID or name to search: ");
		
		// takes a string input
		Scanner input = new Scanner(System.in);
		
		// stores the input in a string variable
		String search = input.next();
		
		
		// call the obtainIdOrName method in DataReturn class
		returnData.obtainIdOrName(search, array);
		
	}

}
