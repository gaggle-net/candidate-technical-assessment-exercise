package exercise;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class returnData {

	// instance variable
	static JSONObject jsonreturn; 
	
	// no constructor
	
	// methods
	
	/**
	 * Takes a string from scanner method and cleans it.
	 * Iterates over the JSON array.
	 * Converts each iteration to a string that is lower case.
	 * Compares it to the string parameter.
	 * If the same, stores it as a JSONObject and calls return() to print it.
	 * Otherwise, continues the iteration.
	 * If completes the iteration and none is found, prints a a message.
	 * @param str from scanner
	 * @param array from JSON file
	 * @return
	 */
	public static boolean obtainIdOrName (String str, JSONArray array) {
		
		// trim and lower case the provided string
		String newStr = str.trim().toLowerCase();
		
		// iterate over the array
		for (int i=0; i<array.size(); i++) {
			
			// create an object of the array
			JSONObject people = (JSONObject) array.get(i);
			
			// turns the object into a string that is all lower case
			String person = people.toString().toLowerCase();
			
			// if string contains name searched for
			if (person.contains(newStr)) {
				
				 jsonreturn = people; 
				 result();
				 
				 return true;
			}
		}
		
		// otherwise, print this error message
		System.out.println("Person could not be found");
		System.out.println("");
		return false;
	}
	
	/**
	 * This methods main goal is to print the JSONObject.
	 * Separated from the above method to help with testing. 
	 * @return the JSONOBject
	 */
	public static JSONObject result() {
		
		System.out.println("");
		System.out.println(jsonreturn);
		System.out.println("");
		
		return jsonreturn; 
	
	}
	
}
