package document;

/** 
 * A class that represents a text document
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	//Creates a new document from the given text
	protected Document(String text){
		this.text = text;
	}
	
	//Returns the tokens that match the regex pattern from the document text string
	protected List<String> getTokens(String pattern){
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		return tokens;
	}
	
	// Returns the number of syllables in a word
	protected static int countSyllables (String word){
		//State what counts as a vowel
		String vowels = "aeiouy";
		char[] wordArray = word.toCharArray();
		int count = 0;
		boolean syll = true;
		//FOR TESTING: Add characters to a list to check them
		//List<Character> found = new ArrayList<Character>();
		
		for (int i=0; i<wordArray.length; i++) {
			char currChar = Character.toLowerCase(wordArray[i]);
			//Check for 'e' at the end of the word
			if (i==wordArray.length-1 && currChar =='e' && count>0 && syll == true) {
				count=count-1;
			}
			//Check to see if it's a vowel (next to a consonant i.e syll==true)
			if (vowels.indexOf(currChar)>=0 && syll == true) {
				count=count+1;
				syll = false;
				//found.add(currChar);
			}	
			//Check if it's a consonant
			else if (vowels.indexOf(currChar)<0) {
				syll = true;
			}
		}
		//System.out.println ("Syllables found: " + found);
		return count;
		}

	//Testing Method
	public static boolean testCase(Document doc, int syllables, int words, int sentences){
		//Examples are found in BasicDocument
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	//Returns the number of words in the document
	public abstract int getNumWords();
	
	//Returns the number of sentences in the document
	public abstract int getNumSentences();
	
	//Returns the number of syllables in the document
	public abstract int getNumSyllables();
	
	//Returns the entire text in the document
	public String getText(){
		return this.text;
	}
	
	// Returns the Flesch score (readability) of a document
	public double getFleschScore(){
	    double words = (double)getNumWords();
	    int sentences= getNumSentences();
	    double syllables= (double)getNumSyllables();
	    
	    return 206.835-(1.015*(words/sentences)) - (84.6*(syllables/words));
	}
}
