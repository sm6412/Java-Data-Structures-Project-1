import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class contains a collection of words read from the input file. It also
 * contains methods that perform binary searches with words and prefixes in order
 * to ascertain whether they exist within the created dictionary.
 * 
 * @author Samira Mantri
 * @version 3/1/17
 *
 */
public class Dictionary{
	/** an ArrayList that represents a dictionary and is populated
	 * with words from the input file
	 */
	private ArrayList<String> dictionary= new ArrayList<String>();
	
	/**
	 * This constructor ensures the entered file is valid. If it is not,
	 * it throws the appropriate exceptions.
	 * 
	 * @param f
	 * 	the user entered file must not be null, must exist, have a name, can be read, 
	 * 	and is a file.
	 * 
	 * @throws IllegalArgumentException if the file does not have a name,
	 * does not exist, cannot be read, or is not a file.
	 */
	public Dictionary(File f) {
		// ensure the file is not equal to null
		if (f == null) {
			// if the file is null inform the user and throw an exception
			throw new IllegalArgumentException("Error: file cannot be null.");
		}
		// ensure the file has a name
		else if (f.getName().length()==0){
			// if the user did not enter a file inform them and throw an exception
			throw new IllegalArgumentException("Error: missing name of the input file.");
		}
		// if the file does not exist inform the user and throw an exception
		else if(f.exists()!=true){
			// inform them and throw and exception
			throw new IllegalArgumentException("Error: file "+f.getName()+" does not exist.");
		}
		// if the file cannot be read inform the user and throw an exception
		else if(f.canRead()!=true){
			// inform them and throw an exception
			throw new IllegalArgumentException("Error: file "+f.getName()+" cannot be read.");
		}
		// if the file is not a file inform the user and throw an exception
		else if(f.isFile()!=true){
			// inform them and throw an exception
			throw new IllegalArgumentException("Usage Error: the file "+f.getName()+" is not a valid file.");
		}
		// if the file is valid, put its information into the dictionary
		setDictionary(f);

	}
	
	/*
	 * This method reads the information from the file and populates
	 * the dictionary line by line.
	 * 
	 * @param f
	 * 	the user entered file that has a name, exists, can be read, and is a file.
	 * 	It must also be able to be found by the program.
	 * 
	 * @throws FileNotFoundException if the file cannot be found.
	 */
	private void setDictionary(File f){
		// populate the dictionary with the words stored in the file
		try{
			// send the file to the scanner to be read
			Scanner inputFile = new Scanner(f);

			// while there is another line in the file continue the loop
			while (inputFile.hasNextLine())	{
				// add the word on each line to the dictionary
				dictionary.add(inputFile.nextLine());
			}
			// close the scanner 
			inputFile.close();
		}
		// catch exceptions
		catch (FileNotFoundException e){
			// inform the user if the file does not exist
			System.out.println("Usage Error: the file "+f.getName()+" does not exist.");
			// exit the program
			System.exit(-1);
		}
	}
	
	/**
	 * This method takes a string and sends it to the method wordSearch
	 * that performs a binary search on it in order to determine if it is 
	 * contained in the dictionary.
	 * 
	 * @param str
	 * 	an entered string containing only letters.
	 * 
	 * @return
	 * 	returns true if the word is found in the dictionary by wordSearch, otherwise false.
	 */
	public boolean isWord(String str){
		// if the string is empty return false
		if (str.length()==0){
			return false;
		}
		else{
			// create an integer variable that represents the starting element
			// of the search
			int start=0;
			// create an integer variable that represents the last element
			// of the search
			int end= dictionary.size();
			// send the string to wordSearch to see whether it exists in the dictionary
			if (!(wordSearch(str,start,end))){
				// if the word does not exist return false
				return false;
			}
		}
		// if the word in its entirety exists within the dictionary return
		// true
		return true;
	}
	
	/*
	 * This method takes a string, starting position, and ending position in order
	 * to perform a binary search on the string and determine whether it exists within
	 * the dictionary. The method is a helper method that performs the recursive functions
	 * of isWord().
	 * 
	 * @param str
	 * 	an entered string containing only letters.
	 * 
	 * @param start
	 * 	an integer that represents the starting index of the binary search.
	 * 
	 * @param end
	 * 	an integer that represents the ending index of the binary search.
	 * 
	 * @return
	 * 	returns true if the word is in the dictionary, otherwise false.
	 */
	private boolean wordSearch(String str, int start, int end){
		// obtain the middle of the particular portion of the 
		// list that is being searched
		int mid= (start+end)/2;
		// if the last index is less than the first, return false
		if (end<start){
			return false;
		}
		// if the last index is equal to the first and the 
		// middle element is not equal to the string, return false
		else if ((end==start)&&!((dictionary.get(mid)).equals(str))){
			return false;
		}
		// if the index of the middle element in the portion of the 
		// list being searched is equal to the entered string return true
		else if ((dictionary.get(mid)).equals(str)){
			return true;
		}
		// search through the words with a binary search
		else{
			if (str.compareTo(dictionary.get(mid))>0){
				// if the word being searched has characters that are larger
				// than the characters of the middle element
				// target the search to elements further down the list
				return wordSearch(str, mid+1, end);
			}
			else{
				// if the word being searched has characters that are smaller
				// than the characters of the middle element
				// target the search to earlier elements in the list 
				return wordSearch(str,start,mid-1);
			}
		}
	}
	
	/**
	 * This method takes a string that represents a word prefix and sends it to 
	 * the prefixSearch method that performs a binary search on it in order to determine 
	 * if it is the prefix of any of the words contained in the dictionary.
	 * 
	 * @param str
	 * 	an entered string that represents a prefix containing only letters.
	 * 
	 * @return
	 * 	returns true if the prefix is found in the dictionary by prefixSearch, otherwise false.
	 */
	public boolean isPrefix(String str){
		// create an integer variable that represents the starting element
		// of the search
		int start=0;
		// create an integer variable that represents the last element
		// of the search
		int end=dictionary.size();
		// send prefixes to prefixSearch to check whether they exist for words
		// in the dictionary
		if (!(prefixSearch(str,start,end))){
			// if the prefixSearch returns false, return false
			return false;
		}
		// if the prefix exists return true
		return true;
	}
	
	/*
	 * This method takes a string, starting position, and ending position in order
	 * to perform a binary search on the string and determine whether it is the prefix of
	 * any of the words in the dictionary. This method is a helper method that
	 * performs the recursive functions of the isPrefix method.
	 * 
	 * @param str
	 * 	an entered string representing a prefix that contains only letters.
	 * 
	 * @param start
	 * 	an integer that represents the starting index of the binary search.
	 * 
	 * @param end
	 * 	an integer that represents the ending index of the binary search.
	 * 
	 * @return
	 * 	returns true if the prefix is in the dictionary, otherwise false.
	 */
	private boolean prefixSearch(String str, int start, int end){
		// obtain the middle of the particular portion of the 
		// list that is being searched
		int mid= (start+end)/2;
		// if the last index is equal to the first return false
		if (end<start){
			return false;
		}
		// if the last index is equal to the first and the middle element does
		// not start with the prefix, return false
		else if (end==start&&!((dictionary.get(mid)).startsWith(str))){
			return false;
		}
		// if the index of the middle element in the portion of the 
		// list being searched starts with the prefix return true
		else if ((dictionary.get(mid)).startsWith(str)){
			return true;
		}
		else{
			// if the prefix being searched has characters that are larger
			// than the characters of the middle element
			// target the search to elements further down the list
			if (str.compareTo(dictionary.get(mid))>0){
				return prefixSearch(str, mid+1, end);
			}
			else{
				// if the prefix being searched has characters that are smaller
				// than the characters of the middle element
				// target the search to earlier elements in the list 
				return prefixSearch(str,start,mid-1);
			}
		}
	}
	


	

}
