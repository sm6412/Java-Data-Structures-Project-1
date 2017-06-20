import java.io.File;
import java.util.ArrayList;
/**
 * This class contains the main() method, parses the command line arguments, creates
 * the Dictionary and Permutations objects, and then utilizes their methods and information
 * in order to display permutations created from the entered string that exist within a 
 * dictionary created from the file entered.
 *  
 * @author Samira Mantri
 * @version 3/1/17
 *
 */
public class ScrabbleHelper {
	public static void main(String[] args) {
		// create a try/catch block to ensure any errors that are encountered are addressed
		// and cause the program to terminate 
		try{
			// ensure the user enters two command line arguments
			if (args.length<2){
				// if the user does not inform them and throw an exception
				throw new IllegalArgumentException("Error: you must enter at least two command line arguments.");
			}
			
			// ensure the user entered a file path
			if (args[0].length()==0){
				// if the user did not enter a file inform them and throw an exception
				throw new IllegalArgumentException("Error: missing name of the input file.");
			}
			
			// ensure the user entered a string
			if (args[1].length()==0){
				// if the user did not enter a string inform them and throw an exception
				throw new IllegalArgumentException("Error: no letters provided, cannot compute any words.");
			}
			
			// create a file with the entered file name if the user supplies one
			File file= new File(args[0]);
			
			// send the file to the dictionary class to check for validity.
			// If it is valid, a dictionary will be created 
			Dictionary list = new Dictionary(file);
			
			// send the entered string to the Permutations class to be validated
			Permutations letters = new Permutations(args[1]);
			
			// this portion of the class will print the output based on the entered file
			// and string
			
			// send the arrayList containing permutations that exist within the dictionary
			// to the finalOutput method so that its information can be formatted
			System.out.println(finalOutput(letters.getAllWords(list)));
		}
		
		// catch exceptions and inform the user of the error
		catch (IllegalArgumentException e){
			// print the error message concerning the file
			System.out.println(e.getMessage());
			// exit the program
			System.exit(-1);
		}
		
	}
	
	/**
	 * This method creates a string that contains information regarding
	 * how many matching words are from the entered
	 * string's permutations. It also displays what they are.
	 * 
	 * @param list
	 * 	list is a reference to the arrayList that contains all the matching permutations
	 * 	that are found in the created dictionary.
	 * 
	 * @return
	 * 	the method returns 'No words found' if the arrayList is empty, otherwise
	 * 	it returns the string that contains how many words are in the arrayList
	 * 	and what they are.
	 */
	public static String finalOutput(ArrayList<String> list){
		// create a new string that will contain information regarding the
		// permutations that exist in the dictionary
		String permutationInfo="";
		
		// check to see if list contains words
		if (list.size()==0){
			// if not, inform the user
			return "No words found";
		}
		// if the list does contain words, add how many, and
		// what they are to permutationInfo
		else{
			if (list.size()==1){
				permutationInfo+="Found "+list.size()+" word:\n";
			}
			else{
				permutationInfo+="Found "+list.size()+" words:\n";
			}
			for (int x=0; x<list.size();x++){
				String permutation = list.get(x);
				permutationInfo+=String.format("   %s\n",permutation);
			}
		}
		// return the string that contains the matching words info
		return permutationInfo;
	}

}
