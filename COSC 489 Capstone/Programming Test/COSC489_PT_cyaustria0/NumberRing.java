import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

/**
 * This program acts as a Number Ring.
 * Created by Catherine Austria on 4/11/17.
 */
public class NumberRing<E> {
    private class Node {
        E element;
        Node next;
        Node prev;
        private Node(E element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public NumberRing(){
        size = 0;
    }
    /**
     * Inserts a node to the beginning of the ring.
     * @param someNode Node to add.
     */
    public void insert(E someNode){
        if (isNumeric(String.valueOf(someNode))){
            size++; //increment list size
            //System.out.println("Inserting: "+someNode); //test
            Node temp = new Node(someNode, head, null); //null for previous element because assumption of empty list
            if(head!=null) head.prev = temp; // if the list isn't null, then let us point temp to the previous postion of the pevious head the node
            head = temp; // add to the node before previous head
            if(tail==null) tail = temp; //list is only one element at this point, point tail to our temp node
        }else {
            System.out.println("Format Exception. Numbers only.");
            System.exit(1);
        }
    }
    /**
     * Checks to see if a strings is Numeric.
     * @param str String to check.
     * @return True if a String is a Number.
     */
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    /**
     * Inserts / Appends a node to the end of the ring.
     * @param someNode Node to add.
     */
    public void append(E someNode){
        Node oldhead = head;
        Node temp = new Node(someNode, head, oldhead);
        if(tail != null) {
            tail.next = oldhead;
            oldhead.prev = tail;
            jump(1); // point to next element
            Node tempr = head; // save next element head
            insert(temp.element); // insert node we want
            head.next = tempr; // set its next association of the node we want appended to the next element head
            tempr.prev = head; // set the next element prev association to our node we want
            oldhead.next = head; // set oldheld next to the appended node
            head.prev = oldhead;// set the prev association of the node we want appended to the oldhead
        }
        if(head == null) {
            temp = new Node(someNode, null, tail);
            head = temp;
            tail = temp;
            size++;
        } //list is empty, our temp also becomes head.
    }
    /**
     * Deletes the current head element.
     */
    public Node delete(){
        Node removed = head;
        //System.out.println("Deleting: "+head.element); //test
        head = head.next; // head pointer goes to the next element
        tail.next = head; // tail's next (head) association is remedied
        head.prev = tail; // head's prev (tail) association is remedied
        size--;
        return removed;
    }
    /**
     * Changes the head pointer to the next element by the number of times given to the method.
     * @param num Jump this many times.
     */
    public void jump(int num){
        //System.out.println("\nJumping "+num+" times...");
        Node temp = head;
        if(num<0){// if num is negative; jump counter-clockwise
            if (num>size) { num = num - size * (Math.abs(num)%size); }
            else {num = size - Math.abs(num);} // set the number of jumps to the size minus the number passed
            num = Math.abs(num); // take absolute value
        }
        while (num>0){
            head.prev = tail; // head's prev points to last element node
            tail = head; // tail now points to head element
            head = temp.next; // pointer goes to next element
            tail.next = head; // tail's next now points to new head
            temp = head; // reinitialize the current head element
            num--;
        }
    }
    /**
     * Sorts the number ring from least to greatest.
     */
    public void sort(){ //I feel I could've implemented this better. For a later less toxic time in my life I guess.
        ArrayList <Node> listt = new ArrayList<>();
        int[] list = new int[size];
        Node temp = head;
        Node origHead = head;
        int counter = size;
        int tmp = 0;
        for (int j = 0; j<size ; j++){
            list[j] = Integer.parseInt(String.valueOf(temp.element));
            listt.add(temp);
            temp = temp.next; // set to next element
        }
        for(int i=0; i < size; i++){
            for(int j=1; j < (size-i); j++){
                if(list[j-1] > list[j]){
                    //swap elements
                    //int[]
                    tmp = list[j-1];
                    list[j-1] = list[j];
                    list[j] = tmp;
                    //arraylist
                    temp = listt.get(j-1);
                    listt.set(j-1,listt.get(j));
                    listt.set(j,temp);
                }

            }
        }
        while (counter>0){ // delete the nodes in the list
            delete();
            counter--;
        }
        int headInt = listt.indexOf(origHead); // find the index of the original head node
        Collections.rotate(listt, size-1-headInt); //rotate till we get the original node head
        for (Node x: listt) { // re enter the sorted list to our num ring
            insert(x.element);
        }
    }
    /**
     * Prints the elements in the Number Ring.
     */
    public String print() {
        String print="Number Ring ->";
        Node temp = head; // set our print to point to head first
        int counter = size;
        try {
            while (counter>0){// print through list going forward
                print+="\t"+temp.element;
                temp = temp.next; // set to next element
                counter--;
            }
        }catch (NullPointerException e){}
        return print;
    }
    /*From here on out are methods that were used to help test, verify and confirm functionality*/
    /**
     * Returns the size of the ring.
     * @return
     */
    public int size(){return size;}
    /***
     * Returns a string representation of the head element.
     * @return
     */
    public String headElement(){return "\nHead Element: "+head.element;}
    /***
     * Returns a string representation of the tail element.
     * @return
     */
    public String tailElement(){return "\nTail Element: "+tail.element;}
    /**
     * Returns true of the ring is empty of elements. False otherwise.
     * @return
     */
    public boolean empty(){ return size==0; }
}
