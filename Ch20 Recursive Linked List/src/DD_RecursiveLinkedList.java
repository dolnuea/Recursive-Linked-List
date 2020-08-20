/**
 * @author Dolunay Dagci
 * CISS 111-360
 * Assignment:  Ch20 Recursive Linked List
 * Due: 04.28.2019
 * This class is a Linked List consisting of linked list methods such as add,
 * remove, size, isEmpty etc., and 2 recursive methods, sort and reverse.
 */
public class DD_RecursiveLinkedList {

    private class DD_Node {
        String value;
        DD_Node next;

        /**
         Constructor
         @param val The element to store in the node.
         @param n The reference to the successor node.
         */
        DD_Node(String val, DD_Node n) {
            value = val;
            next = n;
        }

        /**
         Constructor
         @param val The element to store in the node.
         */
        DD_Node(String val) { // Call the other (sister) constructor.
            this(val, null);
        }
    }

      private DD_Node first;  // head of the linked list
      private DD_Node last;   // tail of the linked list

    /**
     Constructor.
     */
    DD_RecursiveLinkedList() {
        first = null;
        last = null;
    }

    /**
     The isEmpty method checks to see
     if the list is empty.
     @return true if list is empty,
     false otherwise.
     */
     boolean isEmpty() {
        return first == null;
    }

    /**
     The size method returns the length of the list.
     @return The number of elements in the list.
     */
     int size() {
        int count = 0;
        DD_Node p = first;
        while (p != null)
        {
            // There is an element at p
            count ++;
            p = p.next;
        }
        return count;
    }

    /**
     The add method adds an element to
     the end of the list.
     @param e The value to add to the
     end of the list.
     */
    void add(String e) {
        if (isEmpty())
        {
            first = new DD_Node(e);
            last = first;
        }
        else
        {
            // Add to end of existing list
            last.next = new DD_Node(e);
            last = last.next;
        }
    }

    /**
     The add method adds an element at a position.
     @param e The element to add to the list.
     @param index The position at which to add
     the element.
     @exception IndexOutOfBoundsException When
     index is out of bounds.
     */
    void add(int index, String e) {
        if (index < 0  || index > size())
        {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        // Index is at least 0
        if (index == 0)
        {
            // New element goes at beginning
            first = new DD_Node(e, first);
            if (last == null)
                last = first;
            return;
        }

        // Set a reference pred to point to the node that
        // will be the predecessor of the new node
        DD_Node pred = first;
        for (int k = 1; k <= index - 1; k++)
        {
            pred = pred.next;
        }

        // Splice in a node containing the new element
        pred.next = new DD_Node(e, pred.next);

        // Is there a new last element ?
        if (pred.next.next == null)
            last = pred.next;
    }

    /**
     The toString method computes the string
     representation of the list.
     @return The string form of the list.
     */
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();

        // Use p to walk down the linked list
        DD_Node p = first;
        while (p != null)
        {
            strBuilder.append(p.value).append(" ");
            p = p.next;
        }
        return strBuilder.toString();
    }

    /**
     The remove method removes the element at an index.
     @param index The index of the element to remove.
     @return The element removed.
     @exception IndexOutOfBoundsException When index is
     out of bounds.
     */
     String remove(int index) {
        if (index < 0 || index >= size())
        {
            String message = String.valueOf(index);
            throw new IndexOutOfBoundsException(message);
        }

        String element;  // The element to return
        if (index == 0)
        {
            // Removal of first item in the list
            element = first.value;
            first = first.next;
            if (first == null)
                last = null;
        }
        else
        {
            // To remove an element other than the first,
            // find the predecessor of the element to
            // be removed.
            DD_Node pred = first;

            // Move pred forward index - 1 times
            for (int k = 1; k <= index -1; k++)
                pred = pred.next;

            // Store the value to return
            element = pred.next.value;

            // Route link around the node to be removed
            pred.next = pred.next.next;

            // Check if pred is now last
            if (pred.next == null)
                last = pred;
        }
        return element;
    }

    /**
     The remove method removes an element.
     @param element The element to remove.
     @return true if the remove succeeded,
     false otherwise.
     */
     boolean remove(String element) {
        if (isEmpty())
            return false;

        if (element.equals(first.value))
        {
            // Removal of first item in the list
            first = first.next;
            if (first == null)
                last = null;
            return true;
        }

        // Find the predecessor of the element to remove
        DD_Node pred = first;
        while (pred.next != null &&
                !pred.next.value.equals(element))
        {
            pred = pred.next;
        }

        // pred.next == null OR pred.next.value is element
        if (pred.next == null)
            return false;

        // pred.next.value  is element
        pred.next = pred.next.next;

        // Check if pred is now last
        if (pred.next == null)
            last = pred;

        return true;
    }

// Recursive Methods

    /**
     * The reverse method reverses the order of the linked list
     * @param node the current node that is being computed
     * @param previousNode the old/previous node value which is already computed
     */
     private void reverse(DD_Node node, DD_Node previousNode) {
        //if the node is the only one in list (there's no next node)
        // set node as first, and the next node previousNode, then return null
        if (node.next == null) { first = node; node.next = previousNode; return; }
        DD_Node nextNode = node.next; //create a variable that holds the next node
        node.next = previousNode; //make next node the previous node

        reverse(nextNode, node); //now node is the previous node, and next nodes are computed recursively
    }

    /**
     * A corresponding void method for recursive reverse method
     */
    void reverse(){
         reverse(first,null);
    }

    /**
     * The Sort Method recursively goes through the elements of the list and puts them in alphabetically sorted order
     * @param head a reference of the first element, and it's used for next elements in the recursion.
     */
    private void sort(DD_Node head)
    {
        last = null;  // Tail is the sorted list and is null at first.
        if (head == null) return;  //base case is when the unsorted list becomes null, return.
        else {
            sort(head.next); // Traverse the given linked list and insert every node to sorted list recursively
            sortedInsert(head); // insert the current node (initially it's the head) in sorted linked list
        }
        first = last; //arrange the sorted list
    }

    /**
     * Corresponding method to be used in the Demo class for recursive sort method
     */
    void sort(){
        sort(first);
    }

    /**
     * The sortedInsert method inserts a newNode into the sorted list.
     * @param newNode node to be compared and added to the sorted list.
     */
    private void sortedInsert(DD_Node newNode)
    {

        if (last == null || last.value.compareTo(newNode.value) >= 0) //if last is null or last is bigger than the new node
        {
            newNode.next = last; //assign next node to last element
            last = newNode; //assign last to the node
        }
        else
        {
            DD_Node current = last; //current node is the last

            while (current.next != null && current.next.value.compareTo(newNode.value) < 0)  //compare every element and locate the node
            {
                current = current.next; //update current
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }
}
