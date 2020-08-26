package document;

import java.util.List;
import java.util.*;

/** 
 * Implementation of the Document abstract class
 * Loops through the document several times to return no. words, syllables and sentences
 */
public class BasicDocument extends Document {
	//Create a new BasicDocument object
	public BasicDocument(String text){
		super(text);
	}

	//Returns the number of words in the document 
	@Override
	public int getNumWords(){
		//Returns at least one of each upper or lowercase letter only
		List<String> words = getTokens("[a-zA-Z]+");
	    return words.size();
	}
	
	//Returns the number of sentences in the document
	@Override
	public int getNumSentences(){
		List<String> sentences = getTokens("[^.!?]+");
		return sentences.size();
	}
	
	//Return the number of syllables in the document
	@Override
	public int getNumSyllables(){
		List<String> words = getTokens("[a-zA-Z]+");
		int syls = 0;
		for (String word: words) {
			syls += countSyllables(word);	
		}  
		return syls;
	}
	
	//Method to test the methods above
	public static void main(String[] args){
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
	}
	
}
