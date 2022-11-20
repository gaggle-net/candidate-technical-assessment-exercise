package exercise;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

class returnDataTest {

@Test
	void test() throws IOException, ParseException, org.json.simple.parser.ParseException {
		
	
	// initialize parser
		JSONParser jsonparser = new JSONParser();
		
		// load JSON file
		FileReader reader = new FileReader("file.json");
		
		// parse JSON file
		Object obj = jsonparser.parse(reader);
		
		// convert java object into JSON object
		JSONObject info =(JSONObject)obj;
		
		// extract array
		JSONArray testArray = (JSONArray)info.get("People");
		
		
		// testing the search for each person by name
		String str = "Bruce Wayne";
		assertTrue(returnData.obtainIdOrName(str, testArray));
		JSONObject jsonObject = returnData.result();
		String actual = jsonObject.toString();
		String test = "{\"ID\":1,\"Name\":\"Bruce Wayne\"}";
		assertTrue(actual.equals(test));	
		
		String str2 = "Meryl Streep";
		assertTrue(returnData.obtainIdOrName(str2, testArray));
		JSONObject jsonObject2 = returnData.result();
		String actual2 = jsonObject2.toString();
		String test2 = "{\"ID\":2,\"Name\":\"Meryl Streep\"}";
		assertTrue(actual2.equals(test2));	
		
		String str3 = "Tom Hanks";
		assertTrue(returnData.obtainIdOrName(str3, testArray));
		JSONObject jsonObject3 = returnData.result();
		String actual3 = jsonObject3.toString();
		String test3 = "{\"ID\":3,\"Name\":\"Tom Hanks\"}";
		assertTrue(actual3.equals(test3));	
		
		String str4 = "Jennifer Aniston";
		assertTrue(returnData.obtainIdOrName(str4, testArray));
		JSONObject jsonObject4 = returnData.result();
		String actual4 = jsonObject4.toString();
		String test4 = "{\"ID\":4,\"Name\":\"Jennifer Aniston\"}";
		assertTrue(actual4.equals(test4));	
		
		
		
		// testing the search for each person by ID
		String str5 = "1";
		assertTrue(returnData.obtainIdOrName(str5, testArray));
		JSONObject jsonObject5 = returnData.result();
		String actual5 = jsonObject5.toString();
		String test5 = "{\"ID\":1,\"Name\":\"Bruce Wayne\"}";
		assertTrue(actual5.equals(test5));	
		
		String str6 = "2";
		assertTrue(returnData.obtainIdOrName(str6, testArray));
		JSONObject jsonObject6 = returnData.result();
		String actual6 = jsonObject6.toString();
		String test6 = "{\"ID\":2,\"Name\":\"Meryl Streep\"}";
		assertTrue(actual6.equals(test6));	
		
		String str7 = "3";
		assertTrue(returnData.obtainIdOrName(str7, testArray));
		JSONObject jsonObject7 = returnData.result();
		String actual7 = jsonObject7.toString();
		String test7 = "{\"ID\":3,\"Name\":\"Tom Hanks\"}";
		assertTrue(actual7.equals(test7));	
		
		String str8 = "4";
		assertTrue(returnData.obtainIdOrName(str8, testArray));
		JSONObject jsonObject8 = returnData.result();
		String actual8 = jsonObject8.toString();
		String test8 = "{\"ID\":4,\"Name\":\"Jennifer Aniston\"}";
		assertTrue(actual8.equals(test8));	
		
		
		
		// testing the search for each person by name with white space and weird casing
		String str9 = "         BrucE WayNe   ";
		assertTrue(returnData.obtainIdOrName(str9, testArray));
		JSONObject jsonObject9 = returnData.result();
		String actual9 = jsonObject9.toString();
		String test9 = "{\"ID\":1,\"Name\":\"Bruce Wayne\"}";
		assertTrue(actual9.equals(test9));	
		
		String str10 = "Meryl StrEEp       ";
		assertTrue(returnData.obtainIdOrName(str10, testArray));
		JSONObject jsonObject10 = returnData.result();
		String actual10 = jsonObject10.toString();
		String test10 = "{\"ID\":2,\"Name\":\"Meryl Streep\"}";
		assertTrue(actual10.equals(test10));	
		
		String str11 = "      TOM Hanks";
		assertTrue(returnData.obtainIdOrName(str11, testArray));
		JSONObject jsonObject11 = returnData.result();
		String actual11 = jsonObject11.toString();
		String test11 = "{\"ID\":3,\"Name\":\"Tom Hanks\"}";
		assertTrue(actual11.equals(test11));	
		
		String str12 = "JeNnIfer ANIston          ";
		assertTrue(returnData.obtainIdOrName(str12, testArray));
		JSONObject jsonObject12 = returnData.result();
		String actual12 = jsonObject12.toString();
		String test12 = "{\"ID\":4,\"Name\":\"Jennifer Aniston\"}";
		assertTrue(actual12.equals(test12));	
		
		
		
		// testing the search for each person by ID with white space
		String str13 = "     1";
		assertTrue(returnData.obtainIdOrName(str13, testArray));
		JSONObject jsonObject13 = returnData.result();
		String actual13 = jsonObject13.toString();
		String test13 = "{\"ID\":1,\"Name\":\"Bruce Wayne\"}";
		assertTrue(actual13.equals(test13));	
		
		String str14 = "2       ";
		assertTrue(returnData.obtainIdOrName(str14, testArray));
		JSONObject jsonObject14 = returnData.result();
		String actual14 = jsonObject14.toString();
		String test14 = "{\"ID\":2,\"Name\":\"Meryl Streep\"}";
		assertTrue(actual14.equals(test14));	
		
		String str15 = "        3       ";
		assertTrue(returnData.obtainIdOrName(str15, testArray));
		JSONObject jsonObject15 = returnData.result();
		String actual15 = jsonObject15.toString();
		String test15 = "{\"ID\":3,\"Name\":\"Tom Hanks\"}";
		assertTrue(actual15.equals(test15));	
		
		String str16 = " 4 ";
		assertTrue(returnData.obtainIdOrName(str16, testArray));
		JSONObject jsonObject16 = returnData.result();
		String actual16 = jsonObject16.toString();
		String test16 = "{\"ID\":4,\"Name\":\"Jennifer Aniston\"}";
		assertTrue(actual16.equals(test16));	
		
		
		
		// testing the search for each person without full name
		String str17 = "Bru";
		assertTrue(returnData.obtainIdOrName(str17, testArray));
		JSONObject jsonObject17 = returnData.result();
		String actual17 = jsonObject17.toString();
		String test17 = "{\"ID\":1,\"Name\":\"Bruce Wayne\"}";
		assertTrue(actual17.equals(test17));	
		
		String str18 = "Streep";
		assertTrue(returnData.obtainIdOrName(str18, testArray));
		JSONObject jsonObject18 = returnData.result();
		String actual18 = jsonObject18.toString();
		String test18 = "{\"ID\":2,\"Name\":\"Meryl Streep\"}";
		assertTrue(actual18.equals(test18));	
		
		String str19 = "    Tom     ";
		assertTrue(returnData.obtainIdOrName(str19, testArray));
		JSONObject jsonObject19 = returnData.result();
		String actual19 = jsonObject19.toString();
		String test19 = "{\"ID\":3,\"Name\":\"Tom Hanks\"}";
		assertTrue(actual19.equals(test19));	
		
		String str20 = "      JenNi";
		assertTrue(returnData.obtainIdOrName(str20, testArray));
		JSONObject jsonObject20 = returnData.result();
		String actual20 = jsonObject20.toString();
		String test20 = "{\"ID\":4,\"Name\":\"Jennifer Aniston\"}";
		assertTrue(actual20.equals(test20));	
		
		
		
		// testing the search for non-existing names
		String str21 = "Amanda Bynes";
		assertFalse(returnData.obtainIdOrName(str21, testArray));
		
		String str22 = "Bruce the Shark from NEMO";
		assertFalse(returnData.obtainIdOrName(str22, testArray));
		
		
		
		// testing the search for non-existing ID
		String str23 = "213";
		assertFalse(returnData.obtainIdOrName(str23, testArray));
		
		String str24 = "7";
		assertFalse(returnData.obtainIdOrName(str24, testArray));

	
	}
}
