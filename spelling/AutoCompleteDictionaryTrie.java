package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.*;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    
    public AutoCompleteDictionaryTrie(){
		root = new TrieNode();
	}
	
    
	//Inserts a word into the trie using existing nodes and adding new ones only where necessary
    public boolean addWord(String word){
	    TrieNode currNode = root;
	    word = word.toLowerCase();
	    
	    for (char c : word.toCharArray()) {
	    	//Check the children for the character:
	    	//If the next node is null (i.e end of Trie)
	    	if (currNode.getChild(c) == null) {
	    		//Add a new node with the character
	    		//Set to currNode
	    		currNode = currNode.insert(c);
	    	}
	    	else {
	    		currNode = currNode.getChild(c);
	    	}
	    }
	    //If the word is already a word then it can't be new/ added
	    if (currNode.endsWord()) {
	    	return false;
	    }
	    
	    //Add 1 to the size each time a node is added
	    size ++;
	    //Create new Node and return true
	    currNode.setEndsWord(true);
	    return true;
	}

    
	//Returns no. of words in the dictionary
	public int size(){
	    return size;
	}
	
	//Returns whether or not s is a word in the trie
	@Override
	public boolean isWord(String s) {
	    TrieNode currNode = root;
	    s = s.toLowerCase();
	    
	    for (char c: s.toCharArray()) { 
	    	if (currNode.getChild(c) == null) {
	    		return false;
	    	}
	    	else {
	    		currNode = currNode.getChild(c);
	    	}	
	    }
	    return currNode.endsWord();
	}
	
	//Returns numComplations no. of valid words.
	//If the prefix is a valid word it should also be added
	//If there are no valid words, it should return an empty string
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) {
    	 TrieNode currNode = root;
    	 prefix = prefix.toLowerCase();
    	 // Find the prefix in the trie.
    	 for (char c : prefix.toCharArray()) {
    		 // If the stem does not appear in the trie, return an empty list
    		 if (currNode.getChild(c) == null) {
    			 return Collections.emptyList();
 	    	}
    		 //If it does, update currNode to be the next node
 	    	else {
 	    		currNode = currNode.getChild(c);	
 	    	}
 	    }
    	// Perform a breadth first search to generate completions: 
    	// Create a queue (LinkedList) 
    	Queue<TrieNode> q = new LinkedList<TrieNode>();
    	// Add the node to the back of the list
    	q.add(currNode);
    	//    Create a list of completions to return (initially empty)
    	List<String> completions = new LinkedList();
   
    	// While the queue is not empty and you don't have enough completions:
    	while (!q.isEmpty() && numCompletions>0) {
    		// remove the first Node from the queue	
    		TrieNode removed = q.remove();
    		// If it is a word, add it to the completions list
    		if (removed.endsWord()) {
    			completions.add(removed.getText());
    			numCompletions --;
    		}
    	    // Add all of its child nodes to the back of the queue
    		for (char c: removed.getValidNextCharacters()) {
    			q.add(removed.getChild(c));
    		}
    	}
    	// Return the list of completions
    	//System.out.println(complations);
        return completions;
     }

 	// For debugging
 	public void printTree(){
 		printNode(root);
 	}
 	
 	public void printNode(TrieNode curr){
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
 	static public void main (String[] args) {
 		AutoCompleteDictionaryTrie Trie = new AutoCompleteDictionaryTrie();
 		Trie.addWord("a");
 		Trie.addWord("beef");
 		System.out.println(Trie.size);
 	}
}