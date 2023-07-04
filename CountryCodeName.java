/*
 * Student Name: Yao Huang
 * Lab Professor: Aman Kahlon
 * Due Date: June 8
 * Modified: June 8
 * Description: Assignment1 CountryCodeName class
 */

import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;


public class CountryCodeName {
	//declaration of the two-dimensional array
    private static String[][] countryCodesNames;

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		CountryCodeName codeAndName = new CountryCodeName();
		//call init method to populate the two-dimensional array of country codes and names
		codeAndName.init();
		
		//print the welcome message
		System.out.println("Welcome to Country Code Name. Enter “bye” anytime to end.");
		
		//prompt the user to input a code or a name, loop until the user input "bye"
		while (true) {
			System.out.print("Please enter a country code or a country name: ");
			String input = scanner.next();
			
			if(input.equalsIgnoreCase("Bye")) {
				System.out.println("\nBye.");
				return;
			}	
			
			//if the input is less than 2 letters, prompt the user to input again
			while(input.length() < 2) {
				System.out.print("Matching string cannot be less than two letters, please enter another matching string: ");
				input = scanner.next();

				if(input.equalsIgnoreCase("Bye")) {
					System.out.println("\nBye.");
					return;
				}	
			}
			
			// if the input is 2 letters, call getCountryName method to check if the input matches any country name
			if(input.length() == 2) {
				String countryName = CountryCodeName.getCountryName(input);
				if(countryName != null) {
					System.out.println("The country name for the country code entered " + input + " is " + countryName);
				}else {
					System.out.print("No match for country code entered. ");
					continue;
				}	
			} else {
				// if the input is more than 2 letters, call getCountryCode method to check if the input matches any country code
				String countryCode = CountryCodeName.getCountryCode(input);
				if(countryCode != null) {
					System.out.println("The country code for the country name entered " + input + " is " + countryCode);
				}else {
					System.out.print("No match for country code entered. ");
					continue;
				}	
			
			}
		
			//prompt the user to input a string 
			System.out.print("Please enter a matching string for the country names: ");
			input = scanner.next();
			
			if(input.equalsIgnoreCase("Bye")) {
				System.out.println("\nBye.");
				return;
			}
			
			//if the input is less than 2 letters, prompt the user to input again
			while(input.length() < 2) {
				System.out.print("Matching string cannot be less than two letters, please enter another matching string: ");
				input = scanner.next();

				if(input.equalsIgnoreCase("Bye")) {
					System.out.println("\nBye.");
					return;
				}	
			}
			
			//call getMatchedCountries method to check if the input matches
			String[] countyList = CountryCodeName.getMatchedCountries(input);
			if(countyList.length != 0) {
				System.out.println("		Results count: " + countyList.length);
				for(int i=0; i<countyList.length;i++) {
					System.out.println("		" + countyList[i]);
				}
				
			} else {
				System.out.println("No match for matching string entered\n");
			}
			
			
		}//end of the while loop
		
	}//end of the main method

    //populate the two-dimensional array of country codes and names
	public void init() {
		//call Locale class to get the country codes
		String[] countryCodes = Locale.getISOCountries();
		//assigns the new array to the countryCodesNames variable
		countryCodesNames = new String[countryCodes.length][2];
		
		for(int i=0; i < countryCodes.length; i++) {
			//copy the country code from countryCodes array to the first column
			countryCodesNames [i][0] = countryCodes[i];
			//creates a new Locale object using an empty language parameter
			Locale localer = new Locale("",countryCodes[i]);
			//assigns the display name of the country to the second column 
			countryCodesNames [i][1] = localer.getDisplayCountry();
		}
	}
	
	// returns the corresponding country name based on the input county code
	public static String getCountryName(String countryCode) {
		for(int i=0; i < countryCodesNames.length; i++) {
			if(countryCode.equalsIgnoreCase(countryCodesNames[i][0]))
				return countryCodesNames[i][1];
		}
		return null;
	}
	
	// returns the corresponding country code based on the input county name
	public static String getCountryCode(String countryName) {
		for(int i=0;i < countryCodesNames.length; i++) {
			if(countryName.equalsIgnoreCase(countryCodesNames[i][1]))
				return countryCodesNames[i][0];
		}
		return null;
	}
	
	public static String[] getMatchedCountries(String characters) {
		//initializes a dynamic array ArrayList to store the countries list
		ArrayList<String> matchedCountries = new ArrayList<>();
		
		//for-each loop that iterates over each array codeAndName in the countryCodesNames array.
		for (String[] codeAndName : countryCodesNames) {
			//if the input characters match the name or code in the countryCodesNames array
            if (codeAndName[1].toLowerCase().contains(characters.toLowerCase()) || codeAndName[0].toLowerCase().contains(characters.toLowerCase())) 
            {
                //add the code and name into the countries list in the format: "Country Name (Country Code)".
            	matchedCountries.add(codeAndName[1] + " (" + codeAndName[0] + ")");
            }
        }

        //creates a array resultArray with a size equal to the matchedCountries list.
		String[] resultArray = new String[matchedCountries.size()];
		//use the toArray() method to return the matchedCountries list
        return matchedCountries.toArray(resultArray);
	}
	
}//end of the class
