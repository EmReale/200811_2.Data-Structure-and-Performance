package spelling;

import java.util.TreeSet;

/**
 * Implements Dictionary interface with a Binary Search Tree
 */

public class DictionaryBST implements Dictionary {
   private TreeSet<String> dict;
	
	public DictionaryBST() {
		dict = new TreeSet<String>();
	}
    
    //Add word to the dictionary, or return false if it's already there
	public boolean addWord(String word) {
		if (!dict.contains(word.toLowerCase())) {
    		dict.add(word.toLowerCase());
    		return true;
    	}
    	return false;
    }

    //Returns no. of words in the dictionary
    public int size(){
        return dict.size();
    }

    //Returns whether or not s is a word in the dictionary
    public boolean isWord(String s) {
    	if (dict.contains(s.toLowerCase())) {
        	return true;
        }
        return false;
    }
}
