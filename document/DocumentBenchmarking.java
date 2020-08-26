package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
		int increment = 20000;

		// The number of steps to run.  
		int numSteps = 20;
		
		// THe number of characters to start with. 
		int start = 50000;
		
		// numToCheck holds the number of characters that you should read from the 
		// file to create both a BasicDocument and an EfficientDocument.
		System.out.println("Number of chars Basic Time      Efficient Time");
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
			 numToCheck += increment){

			System.out.print (numToCheck + "\t \t");
			// Read numToCheck characters from the file into a String
			String textString = getStringFromFile(textfile,numToCheck);
			// Time how long it takes to create a BasicDocument and call FleshScore on it
			long startTime = System.nanoTime();
			for (int i=0;i<=trials;i++) {
				BasicDocument bd = new BasicDocument(textString);
				bd.getFleschScore();
			}
			long stopTime = System.nanoTime();
			double estTime = (stopTime-startTime)/100000000.0;
			// Print out the time it took to complete the loop 
			System.out.print(estTime + "\t");
			
			// Time the same criteria but for EfficientDocument
			startTime = System.nanoTime();
			for (int j=0;j<=trials;j++) {
				EfficientDocument ed = new EfficientDocument(textString);
				ed.getFleschScore();
			}
			stopTime = System.nanoTime();
			estTime = (stopTime-startTime)/100000000.0;
			// Print out the time it took to complete the last loop  
			System.out.print(estTime + "\n");
		}
	}
	
	//Get a specified number of characters from a text file
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e){
		  System.out.println(e);
		  System.exit(0);
		}

		return s.toString();
	}
	
}
