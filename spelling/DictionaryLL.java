package spelling;

import java.util.LinkedList;

/**
 * Implements the Dictionary interface using a LinkedList
 */

public class DictionaryLL implements Dictionary {

	private LinkedList<String> dict;
	
	public DictionaryLL() {
		dict = new LinkedList<String>();
	}
    
    //Add words to the dictionary ignoring case
    public boolean addWord(String word) {
    	if (!dict.contains(word.toLowerCase())) {
    		dict.add(word.toLowerCase());
    		return true;
    	}
    	return false;
    }

    //Returns no.words in the dictionary
    public int size(){
        return dict.size();
    }

    //Identifies if it's a word according to the dictionary
    public boolean isWord(String s) {
        if (dict.contains(s.toLowerCase())) {
        	return true;
        }
        return false;
    }
}
