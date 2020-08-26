/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * Finds words similar to another word
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) {
		this.dict = dict;
	}

	//Returns a list of strings that are on transformation away from s
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		   List<String> retList = new ArrayList<String>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
		   return retList;
	}

	
	//Mutates s by substitution
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	//Mutates s by insertion
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		//Iterate through letters in the string
		for (int index=0; index<s.length(); index++) {
			//Iterate through each letter of the alphabet
			for (int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				//Create StringBuffer of string
				StringBuffer sb = new StringBuffer(s);
				sb.insert(index, (char)charCode);
				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if (!currentList.contains(sb.toString()) &&
					(!wordsOnly|| dict.isWord(sb.toString()))&&
					!s.equals(sb.toString())){
					currentList.add(sb.toString());
				}
			}
		}
		/*for (String w:currentList) {
			System.out.println(w);
		}*/
	}

	//Mutates s by deletion
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		for (int index=0; index<s.length(); index++) {
			StringBuffer sb = new StringBuffer(s);
			sb.deleteCharAt(index);
			//System.out.println("sb: " + sb.toString());
			if (!currentList.contains(sb.toString()) &&
			   (!wordsOnly|| dict.isWord(sb.toString()))&&
				!s.equals(sb.toString())){
				currentList.add(sb.toString());
			}
		}
		//System.out.println(currentList);
	}

	//Creates a list of spelling suggestions using breadth first search
	@Override
	public List<String> suggestions(String word, int numSuggestions) {

		// initial variables
		List<String> queue = new LinkedList<String>(); 
		HashSet<String> visited = new HashSet<String>();  											 
		List<String> retList = new LinkedList<String>();
		 
		// insert first node
		queue.add(word);
		visited.add(word);
		
		//While we still need numSuggestions and the queue isn't empty
		while (numSuggestions>0 && !queue.isEmpty()) {
			//for the first word in the queue (assigned as currWord);
			String currWord = queue.remove(0);
			//do distance One (produces ArrayList)
			List<String> iteration = distanceOne(word,false);
			//For each word produced by distance 1;
			for (String s: iteration) {
				//Add it to visited if it hasn't been already;
				if (!visited.contains(s)) {
					visited.add(s);
					//Add it the back of the queue
					queue.add(s);
					//If it's a word in the dictionary:
					if (dict.isWord(s)) {
						//add it to the return list
						//reduce the number of suggestions by one
						retList.add(s);
						numSuggestions --;
					}
				}
			}
		}
		//return the list
		return retList;
	}	

	// testing code
   public static void main(String[] args) {
	   String word = "i";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   //List<String> currentList = new LinkedList<String>();

	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
   }

}
