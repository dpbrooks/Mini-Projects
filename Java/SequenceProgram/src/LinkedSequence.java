

/**
 * A sequence implemented using a (singly-) linked list (without dummy nodes).
 * 
 * @author Michael Main, Beth Katz, Chad Hogg, Dylan Brooks
 * @param <E> The type of element stored in this sequence.
 */
public class LinkedSequence<E> implements Sequence<E> {
	
	/**
	 * A generic node in a single-linked list.
	 * 
	 * @author Michael Main, Beth Katz, Chad Hogg
	 * @param <E> The type of element stored in the node.
	 */
	public static class Node<E> {
		// Invariant of the Node class:
		//   1. The node's data is in the instance variable data.
		//   2. For the final node of a list, the link is null.
		//      Otherwise, link is a reference to the next node of the list.
		
		/** The element stored in this node. */
		public E value;
		/** A reference to the next node, or null if this is the last one. */
		public Node<E> next;
	   
		/**
		 * Initialize a node with given initial data and link to the next node.
		 * 
		 * The initialNext may be the null reference, which indicates that the
		 *   new node has nothing after it.
		 *   
		 * @param initialValue The initial data of this new node.
		 * @param initialNext A reference to the node after this new node.
		 */
		public Node(E initialValue, Node<E> initialNext) {
			value = initialValue;
			next = initialNext;
		}
		
		@Override
		public String toString() {
			return "a node containing " + value;
		}
	}

	
	// Invariants of the LinkedSequence class:
	// 1. the number of items in the sequence is maintained in size.  
	// 2. head points to the first node, if any, or it is null.
	// 3. tail points to the last node, if any, or it is null.
	// 4. if there is a current item, cursor points to it and
	//    precursor points to the node before it, if any.
	// 5. if there is no current item, cursor and precursor are both null.

	/** A reference to the first node, or null if there are none. */
	private Node<E> head;
	/** A reference to the last node, or null if there are none. */
	private Node<E> tail;
	/** A reference to the current node, or null if there is none. */
	private Node<E> cursor;
	/** A reference to the node before the current node, or null if there is none. */
	private Node<E> precursor;
	/** The number of elements in the sequence. */
	private int size;

	/**
	 * Initializes an empty sequence.
	 * 
	 * @postcondition The new sequence is empty.
	 */
	public LinkedSequence() {
		head = null;
		tail = null;
		cursor = null;
		precursor = null;
		size = 0;
	}


	/**
	 * Gets the number of elements in this sequence.
	 * 
	 * @return The number of elements in this sequence.
	 */ 
	@Override
	public int size() {
		return size;
	}

	
	/**
	 * Checks whether or not this has a current element.
	 * 
	 * @return True if this has a current element, or false otherwise.
	 */
	@Override
	public boolean isCurrent() {
		if (cursor == null) {
			return false;
		} else {
			return true;
		}
	}

	
	/**
	 * Gets the current element, if one exists.
	 * 
	 * @precondition This has a current element.
	 * @return The current element.
	 * @throws IllegalStateException If there is no current element.
	 */
	@Override
	public E getCurrent() {
		if (!isCurrent()) {
			throw new IllegalStateException("No current element.");
		} else {
			return cursor.value;
		}
	}

	
	/**
	 * Sets the current element at the beginning of this sequence.
	 * 
	 * @postcondition The first element of this sequence is now the current element (but 
	 *   if this sequence has no elements at all, then there is no current element).
	 */
	@Override
	public void start() {
		// TODO: Implement this.
		if (size == 0) {
			cursor = null;
		} else {
			cursor = head;
		}
		precursor = null;
	}

	
	/**
	 * Changes the current element to the next one in the sequence.
	 * 
	 * @precondition This has a current element.
	 * @postcondition If the current element was already the end element of this sequence 
	 *   (with nothing after it), then there is no longer any current element.  Otherwise, 
	 *   the new current element is the element immediately after the original current element.
	 * @throws IllegalStateException If there is no current element.
	 */
	@Override
	public void advance() {
		// TODO: Implement this.
		if (!isCurrent()) {
			throw new IllegalStateException("No current element.");
		} else if (cursor == tail) {
			cursor = null;
			precursor = null;
		} else if (cursor == head) {
			cursor = cursor.next;
			precursor = head;
		} else {
			cursor = cursor.next;
			precursor = precursor.next;
		}
	}

	/**
	 * Adds a new element to this sequence, after the current element.
	 * 
	 * @param element The new element that should be added.
	 * @postcondition A new copy of the element has been added to this sequence.
	 *   If there was a current element, then the new element is placed after the
	 *   current element. If there was no current element, then the new element is
	 *   placed at the end of the sequence. In all cases, the new element becomes
	 *   the new current element of this sequence. 
	 */
	@Override
	public void addAfter(E element) {
		// TODO: Implement this.
		if (size == 0) {
			Node<E> newNode = new Node<E>(element, null);
			cursor = newNode;
			head = newNode;
			tail = newNode;
		} else if (cursor == null || cursor == tail) {
			Node<E> newNode = new Node<E>(element, null);
			cursor = newNode;
			tail.next = newNode;
			precursor = tail;
			tail = newNode;
		} else {
			addBetween(element, cursor, cursor.next);
		}
		size++;
	}


	/**
	 * Adds a new element to this sequence, before the current element.
	 * 
	 * @param element The new element that should be added.
	 * @postcondition A new copy of the element has been added to this sequence.
	 *   If there was a current element, then the new element is placed before the
	 *   current element. If there was no current element, then the new element is
	 *   placed at the start of the sequence. In all cases, the new element becomes
	 *   the new current element of this sequence. 
	 */
	@Override
	public void addBefore(E element) {
		// TODO: Implement this.
		if (size == 0) {
			Node<E> newNode = new Node<E>(element, null);
			cursor = newNode;
			head = newNode;
			tail = newNode;
		} else if (cursor == null || cursor == head) {
			Node<E> newNode = new Node<E>(element, head);
			head = newNode;
			cursor = newNode;
			precursor = null;
		} else {
			addBetween(element, precursor, cursor);
		}
		size++;
	}


	/**
	 * Remove the current element from this sequence.
	 * 
	 * @precondition This has a current element.
	 * @postcondition The current element has been removed from this sequence, and
	 *   the following element (if there is one) is now the new current element. 
	 *   If there was no following element, then there is now no current element.
	 * @throws IllegalStateException If there was no current element.
	 */
	@Override
	public void removeCurrent() {
		// TODO: Implement this.
		if (!isCurrent()) {
			throw new IllegalStateException("No current element.");
		} else if (size == 1) {
			head = null;
			tail = null;
			cursor = null;
			precursor = null;
		}else if (cursor == head) {
			head = head.next;
			cursor = head;
			precursor = null;
		} else if (cursor == tail) {
			tail = precursor;
			tail.next = null;
			cursor = null;
			precursor = null;
		} else {
			precursor.next = cursor.next;
			cursor = cursor.next;
		}
		size--;
	}
	
	/**
	 * Method that adds a node between two nodes given as parameters.
	 * 
	 * @param element The element that will be in the new node.
	 * @param before The node that will come before the node being added.
	 * @param after The node that will come after the node being added.
	 */
	private void addBetween(E element, Node<E> before, Node<E> after) {
		Node<E> newNode = new Node<E>(element, after);
		before.next = newNode;
		cursor = newNode;
		precursor = before;
	}
	
	/**
	 * Gets a string picture of the sequence, with the current element in parenthesis.
	 * 
	 * @return A string like "3 6 (9) 12".
	 */
	public String toString() {
		// DO NOT CHANGE THIS METHOD
		String answer = "";

		for(Node<E> current = head; current != null; current = current.next) {
			if(current == cursor) {
				answer += "(" + current.value + ") ";
			}
			else {
				answer += current.value + " ";                   
			}
		}
		
		if(size == 0) {
			answer = "empty sequence";
		}
		return answer;
	}
}

