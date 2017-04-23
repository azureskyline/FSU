/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AustriaCatherine;

/**
 *
 * @author Catherine Austria
 */
public class CircularlyLinkedList <E>{
    private static class Node<E>{//beginning of nested Node class
        private E element; // reference to the element stored at this node
        private Node<E> next; // reference to the subsequent node in the list

        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
        public E getElement() {return element;}
        public Node<E> getNext() {return next;}
        public void setNext(Node<E> n){next=n;}
    }//end of nested Node class
    
    //variables
    private Node<E> tail = null; // store tail but not head
    private int size = 0; // number of nodes in the list
    public CircularlyLinkedList() { } // constructs an initially empty list
    
    // access methods
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public E first() { // returns but does not remove the first element
        if (isEmpty()) return null;
        return tail.getNext().getElement(); // the head is placed after the tail
    }//end of first method
    public E last() { // returns but does not remove the last element
        if (isEmpty()) return null;
            return tail.getElement(); 
    }//end of last method
    
    // update methods
    public void rotate() { // rotate the first element to the back of the list
        if (tail != null) // if empty then do nothing
            tail = tail.getNext(); // the old head becomes the new tail
    }//end of rotate method
    public void addFirst(E e) { // adds element e to the front of the list
        if (size == 0) {
            tail = new Node<>(e, null); 
            tail.setNext(tail); // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext()); 
            tail.setNext(newest);
        }
        size++;
    }// end of addFirst method   
    public void addLast(E e) {// adds element e to the end of the list
        addFirst(e);// insert new element at front of list
        tail = tail.getNext();// now new element becomes the tail
    }//end of addLast
    public E removeFirst( ) {// removes and returns the first element
        if (isEmpty( )) return null; // nothing to remove
        Node<E> head = tail.getNext( ); 
        if (head == tail) tail = null;// must be the only node left
        else tail.setNext(head.getNext( )); // removes ”head” from the list
        size--;
        return head.getElement( );
    }
    public String toString(){
        String printer="";
        E objFirst = this.first();
        printer+=this.first();
        this.rotate();
        while(this.first()!=objFirst){
            printer +=", "+this.first();
            this.rotate();
        }
        printer+="\n";
        return printer;
    }
}
