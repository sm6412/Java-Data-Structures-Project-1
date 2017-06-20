import java.util.ArrayList;
import java.util.Collections;
/**
 * This class utilizes a user entered sequence of letters to generate
 * permutations. Its permutations are then checked for within the 
 * dictionary for matching words.
 *  
 * @author Samira Mantri
 * @version 3/1/17
 *
 */
public class Permutations {
	/** letters represents the string the user enters **/
	private String letters;
	
	/**
	 * This constructor should check to see whether the entered string is
	 * not null, empty, and is composed only of letters before sending
	 * it to setLetters.
	 * 
	 * @param letters
	 * 	letters is the user entered string and must not be null, empty,
	 * 	and all its characters must be letters. If not an exception is thrown.
	 * 
	 * @throws IllegalArgumentException if the string is equal to null,
	 * 	is empty, or does not only contain letters.
	 * 	
	 */
	public Permutations(String letters) throws IllegalArgumentException{
		// ensure the user entered string is not equal to null
		if (letters==null){
			// if the user enters a string equal to null throw an exception
			throw new IllegalArgumentException("Error: string cannot be equal to null.");
		}
		// check to see whether the user entered a string
		else if (letters.length()==0){
			// if the user did not enter a string inform them and throw an exception
			throw new IllegalArgumentException("Error: no letters provided, cannot compute any words.");
		}
		// check to see whether the entered string is composed of letters
		for (int x=0; x<(letters).length();x++){
			// get the individual characters in a string
			char singleChar = letters.charAt(x);
			if (!(Character.isLetter(singleChar))){
				// throw an exception if any of the characters are not letters 
				throw new IllegalArgumentException("Error: you entered an invalid character; only letters can be accepted.");
			}
		}
		// send the string to setLetters if it is valid
		setLetters(letters);

	}
	
	/*
	 * This method sets the user entered string equal to letters after it has
	 * already been validated by the constructor.
	 * 
	 * @param letter
	 * 	letter is the user entered string that has already been validated 
	 * 	by the constructor.
	 */
	private void setLetters(String letter){
		// if the string is valid make it lower case
		String lowerCaseString = (letter).toLowerCase();
		// set the string equal to the letters instance variable
		this.letters=lowerCaseString;
	}
	
	/**
	 * This method creates an arrayList that will be populated with word 
	 * permutations after it has been sent to its helper method,
	 * the permutationMaker method.
	 * 
	 * @return the arrayList that contains all the word permutations.
	 */
	public ArrayList<String> getAllPermutations(){
		// create an empty arrayList
		ArrayList<String> permutationList= new ArrayList<String>();
		// return the arrayList populated with permutations
		return permutationMaker(permutationList,this.letters, "");
	}
	
	/*
	 * This method is a helper method that assists getAllPermutations in actually performing
	 * the recursive functions that create the word permutations.
	 * 
	 * @param list
	 * 	An empty arrayList used to contain all the word permutations.
	 * 
	 * @param str
	 * 	The original string that the user entered.
	 * 
	 * @param prefix
	 * 	A string that is used to build the individual permutations. 
	 * 
	 * @return an ArrayList containing all the generated permutations.
	 */
	private ArrayList<String> permutationMaker(ArrayList<String> list, String str, String prefix){
		// when the string length is 0 add the permutation to the list
		if (str.length()==0){
			if (!(list.contains(prefix))){
				list.add(prefix);
			}
		}
		else{
			// use a loop to generate permutations of the string 
			for (int x=0; x<str.length();x++){
				permutationMaker(list,str.substring(0, x)+str.substring(x+1),prefix+str.charAt(x));
			}
		}
		// return list of permutations
		return list;
	}
	
	/**
	 * This method creates an arrayList that will be populated with permutations that
	 * exist within the created dictionary after it calls its helper method, constructWords.
	 * After the arrayList is filled it will return it.
	 * 
	 * @param dictionary
	 * 	dictionary is of type Dictionary and is an object that provides access to
	 * 	the dictionary class and its dictionary and methods.
	 * 
	 * @return an arrayList filled with permutations that exist within the dictionary.
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary){
		// create an empty arrayList
		ArrayList<String> matchingPermutations= new ArrayList<String>();
		// populate the arrayList with permutations found in the dictionary
		matchingPermutations = constructWords(dictionary,matchingPermutations,this.letters, "");
		// sort the arrayList containing the matching words
		Collections.sort(matchingPermutations);
		// return the arrayList
		return matchingPermutations;
	}
	
	/*
	 * This method is a helper method to getAllWords and performs the recursive functions of
	 * the method. It generates an arrayList that contains all the permutations that exist
	 * within the dictionary before returning it.
	 * 
	 * @param dictionary
	 * 	dictionary is of type Dictionary and is an object that provides access to
	 * 	the dictionary class and its dictionary and methods.
	 * 
	 * @param list
	 * 	list is an arrayList that will be used to hold all the permutations that exist
	 * 	within the dictionary.
	 * 
	 * @param str
	 * 	this is the user entered string that will have permutations generated from.
	 * 
	 * @param prefix
	 * 	a string used to build the individual permutations.
	 * 
	 * @return an arrayList containing all the permutations that match words within the 
	 * 	dictionary. 
	 */
	private ArrayList<String> constructWords(Dictionary dictionary,ArrayList<String> list, String str, String prefix){
		// when the string length is 0 add the permutation to the list
		if (str.length()==0){
			// add the prefix to the list if it does not exist within it already
			// and it is a word in the dictionary
			if (!(list.contains(prefix))&&(dictionary.isWord(prefix))){
				list.add(prefix);
			}
		}
		
		// if the string length is not 0 keep building the permutation
		else{
			// if the prefix does not exist in the dictionary stop
			// building the permutation
			if (!(dictionary.isPrefix(prefix))){
				return list;
			}
			// use a loop to generate permutations of the string 
			for (int x=0; x<str.length();x++){
				constructWords(dictionary,list,str.substring(0, x)+str.substring(x+1),prefix+str.charAt(x));
			}
			
		}
		// return list of all permutations that exist in the dictionary
		return list;
	}

}
