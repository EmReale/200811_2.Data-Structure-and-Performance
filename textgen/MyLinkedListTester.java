/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * Tester class for my Linked List
 */

public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> sizeList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++){
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		sizeList = new MyLinkedList<Integer>();
		sizeList.add(65);
		sizeList.add(21);
		sizeList.add(42);
	}

	
	// Test if the get method is working correctly
	@Test
	public void testGet(){
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {	
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAdd () {
		shortList.add("C");
		assertEquals("Add check get(0) ","A", shortList.get(0));
		assertEquals("Add check get (1)","B", shortList.get(1));
		assertEquals("Add check get(2)","C", shortList.get(2));
		assertEquals("Shortlist size",3, shortList.size());
		
		emptyList.add(2);
		assertEquals("Shortlist size",1, emptyList.size());
		assertEquals("Add check get(0) ",(Integer)2, emptyList.get(0));
	}


	@Test
	public void testSize(){
		assertEquals("Check size is correct ", 3, sizeList.size());
		assertEquals("Check at index 0",(Integer)65, sizeList.get(0));
		assertEquals("Check at index 0",(Integer)21, sizeList.get(1));
		assertEquals("Check at index 0",(Integer)42, sizeList.get(2));
	}
	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex(){
		sizeList.add(1,100);
		assertEquals("Check at index 0",(Integer)65, sizeList.get(0));
		assertEquals("Check at index 1",(Integer)100, sizeList.get(1));
		assertEquals("Check at index 2",(Integer)21, sizeList.get(2));
		assertEquals("Check at index 3",(Integer)42, sizeList.get(3));
		assertEquals("Check size",4, sizeList.size());
		
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {	
		}
		
		try {
			sizeList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			sizeList.get(5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove(){
		MyLinkedList<String> remList = new MyLinkedList<String>();
		remList.add("I");
		remList.add("am");
		remList.add("a");
		remList.add("new");
		String a = remList.remove(1);
		
		assertEquals("Remove: check a is correct ", "am", a);
		assertEquals("Check index (0)", "I", remList.get(0));
		assertEquals("Check index (1)", "a", remList.get(1));
		assertEquals("Check index (2)", "new", remList.get(2));
		assertEquals("Check size", 3, remList.size());
		
		try {
			remList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			remList.get(5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// TODO: Add more tests here
	}
	
	
	/** Test setting an element in the list */
	@Test
	public void testSet(){
	    sizeList.set(1, 6);
	    
	    try {
			sizeList.set(-1,6);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	    
	    try {
			sizeList.set(1,null);
			fail("Check null pointer");
		}
		catch (NullPointerException n) {
		}
	    
	    assertEquals("Check index (1)",(Integer)6, sizeList.get(1));
	    assertEquals("Check index (0)",(Integer)65, sizeList.get(0));
	    assertEquals("Check index (2)",(Integer)42, sizeList.get(2));
	    assertEquals("Check size",3, sizeList.size());
	}
}
