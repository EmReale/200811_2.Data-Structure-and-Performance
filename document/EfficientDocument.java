package document;

import java.util.List;
import java.util.*;

/** 
 * EfficientDocument makes one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 */

public class EfficientDocument extends Document {

	private int numWords;  
	private int numSentences; 
	private int numSyllables;  
	
	public EfficientDocument(String text){
		super(text);
		processText();
	}
	
	//Returns whether or not it is a word
	private boolean isWord (String tok){
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
    //Counts number of words, syllables and sentences from one pass of the text
	private void processText(){
		// Call getTokens on the text to preserve separate words 
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		numSentences = 0;
		numWords = 0;
		numSyllables= 0;
		
		for (int i=0; i<tokens.size();i++) {
			String currToken = tokens.get(i);
			if (isWord(currToken)) {
				numWords += 1;
			}
			numSyllables += countSyllables(currToken);
			if (isWord(currToken)==false || i==tokens.size()-1) {
				numSentences += 1;
			}
		}
	}
	
	//Returns the number of sentences
	@Override
	public int getNumSentences() {
		processText();
		return numSentences;
	}

	//Returns the number of words
	@Override
	public int getNumWords() {
	    processText();
		return numWords;
	}

	//Returns the number of syllables
	@Override
	public int getNumSyllables() {
        processText();
		return numSyllables;
	}
	
	// Test cases
	public static void main(String[] args) {
	    testCase(new EfficientDocument("This is a test.  How many???  "
                + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new EfficientDocument(""), 0, 0, 0);
        testCase(new EfficientDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2); 
        testCase(new EfficientDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new EfficientDocument("Segue"), 2, 1, 1);
		testCase(new EfficientDocument("Sentence"), 2, 1, 1);
		testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
		testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		//MY TEST FOR PROCESS TEXT
		EfficientDocument ed = new EfficientDocument("This is a..... Test? Case");
		ed.processText();
		System.out.println(ed.getNumWords());
		System.out.println(ed.getNumSyllables());
		System.out.println(ed.getNumSentences());
	}
}
