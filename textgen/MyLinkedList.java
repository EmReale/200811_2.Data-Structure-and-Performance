package textgen;

import java.util.AbstractList;


/** 
 * A class that implements a doubly linked list
 */

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;
	
	//Constructor creates a new empty list
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	//Add element to the end of the list
	public boolean add(E element) {
		//Throws an excpeion if the element is null
		if (element == null) {
			throw new NullPointerException("Elements must have a value");
		}
		
		//Create a new node and add from the tail
		LLNode<E> n = new LLNode<E>(element);
		n.next = tail.next;
		n.prev = tail.prev;
		tail.prev = n;
		n.prev.next = n;
		
		//Increase size with each additional node
		size += 1;
		return false;
	}

	//Return the element at a givenindex
	public E get (int index) {
		//Throws exception if out of bounds
		if (index <0 || index>this.size-1 || size == 0) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}
		//Iterates through each node until it gets to the one that's index from the head
		LLNode<E> node = findNode(index);
		return node.data;
	}

	//Adds an element at a given index
	public void add(int index, E element ) {
		//Throws exception if out of bounds
		if (index <0 || index>this.size-1 || size == 0) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}
		
		if (element == null) {
			throw new NullPointerException("Elements must have a value");
		}
		
		LLNode<E> n = new LLNode<E>(element);
		
		//Find the node to replace (it'll become the next node)
		LLNode<E> nextNode = findNode(index);
		
		//Find the previous node
		LLNode<E> prevNode = nextNode.prev;
		n.next = nextNode; //could also use prevNode.next
		n.prev = prevNode.prev;
		n.next.prev = n;
		prevNode.next = n;
		
		//Increase size with each new node
		size += 1;
	}


	//Returns the size of the list
	public int size() {
		return this.size;
	}

	//Removes a node at index and returns it
	public E remove(int index) {
		//Throws exception if index doesn't exist
		if (index <0 || index>this.size || size == 0) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}
		
		//Find the node at that index
		LLNode<E> node = findNode(index);
		LLNode<E> prevNode = node.prev;
		LLNode<E> nextNode = node.next;
		
		//Reconnect the nodes on either side of it
		//Point its previous and next to null
		prevNode.next = nextNode;
		prevNode = nextNode.prev;
		node.next = null;
		node.prev = null;
		
		//Reduce size by 1;
		size = size - 1;
		return node.data;
	}

	//Sets an element at index to a new element
	public E set(int index, E element) {
		//Throws exception if index doesn't exist
		if (index <0 || index>this.size || size == 0) {
			throw new IndexOutOfBoundsException("Bad index: " + index);
		}
		
		//Throws null pointer if you try to add null value
		if (element == null) {
			throw new NullPointerException("Elements must have a value");
		}
		//Find the node
		LLNode<E> node = findNode(index);
		
		//Made the data = element
		node.data = element;

		return node.data;
	}   
	
	//Helper method to find the node at an index
	private LLNode<E> findNode(int index) {
		LLNode<E> node = null;
		node = head.next;
		for (int i=0; i<index; i++) {
			 node = node.next;
		}
		return node;
	}
}



class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode() {
		this.prev = null;
		this.next = null;
	}
}
