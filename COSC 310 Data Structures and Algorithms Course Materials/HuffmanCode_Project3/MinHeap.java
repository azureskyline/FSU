/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancode_austria;

/**
 *  This class is responsible for simulating a min heap and keeping heap order. 
 *  Obviously. It was a blast. No really it was. took me 5 tries to get it right. Who knew
 *  Key extends Comparable is literally the key to all my problems.
 * @author Catherine Austria
 */
public class MinHeap<Key extends Comparable, E> {
    private HuffmanNode<Key, E>[] listArray;
    private int listSize;
    private int maxSize;
    /**
     * The usual constructor.
     * @param maxn max size
     * @param num max list size
     * @param somenode 
     */
    public MinHeap(int maxn, int num, HuffmanNode<Key, E>[] somenode) { 
        maxSize = maxn;
        listSize = num;
        listArray = somenode;
        heapify(); 
    }
    /**
     * Starts from middle at the start, children of nodes are visited before the parent node.
     */
    public void heapify() {
        for(int i = listSize/2 - 1; i >= 0; i --) {
            siftDown(i);
        }
    }
    /**
     * Getters be getting. Gets list size.
     * @return List's size.
     */
    public int length() { return listSize; }
    /**
     * Insert node. Basic of the Basic.
     * @param somenode Key and Value to be made into a node 
     */
    public void insert(HuffmanNode<Key, E> somenode) {
        listArray[listSize ++] = somenode;
        /** sift up the newly added node if it is less than its ancestors */
        siftUp(listSize - 1); 
    }
    /**
     * Remove minimum value.
     * @return the first node's value.
     */
    public HuffmanNode<Key, E> removeMin() {
        return remove(0);
    }
    /**
     * Remove node. 
     * @param pos Position of node.
     * @return value of removed node.
     */
    public HuffmanNode<Key, E> remove(int pos) {
        // if (pos >= 0) && (pos < listSize) then cannot remove
        if(pos == listSize -1) listSize --;
        else {
            swapNodes(pos, --listSize);// swap the max (at position 0) with the last one, then remove the last one
            if(pos > 0) siftUp(pos); //restore heap
            if(!isLeaf(pos)) siftDown(pos);
        }
        return listArray[listSize];
    }
    /**
     * Shift heap down
     * @param pos position value
     */
    public void siftDown(int pos) {
        while(!isLeaf(pos)) {//bad position
            int i = left(pos);
            /* if the right child is less than the left child, use the right child */
            if((i < listSize - 1) && (((Key) listArray[i].getKey()).compareTo((Key) listArray[i + 1].getKey()) > 0)) i ++;
            if(((Key) listArray[i].getKey()).compareTo((Key) listArray[pos].getKey()) < 0) {
                    swapNodes(i, pos);
            }
            pos = i;
        }
    }
    /**
     * Shift heap up
     * @param pos position value
     */
    public void siftUp(int pos) {
        while(pos > 0) {
            int p = parent(pos);
            if(((Key) listArray[pos].getKey()).compareTo((Key)listArray[p].getKey()) < 0) {
                    swapNodes(p, pos);
            }
            pos = p;
        }
    }
    /**
     * Swaps nodes! These names really are very literal.
     * @param i node to swap with j value
     * @param j node to swap with i value
     */
    public void swapNodes(int i, int j) {
        HuffmanNode<Key, E> tmp = listArray[i];
        listArray[i] = listArray[j];
        listArray[j] = tmp;
    }
    /**
     * Is it a leaf? Returns true or false.
     * @param pos Position of node.
     * @return Returns whether a leaf is true or false.
     */
    public boolean isLeaf(int pos) {
        if(pos < listSize/2) return false;
        else return true;
    }
    /**
     * Left child check
     * @param pos Position of node.
     * @return Position of left child.
     */
    public int left(int pos) { 
        return 2 * pos + 1; //if < the list size, then left child is empty
    }
    /**
     * Right child check.
     * @param pos Position of node.
     * @return Position of right child.
     */
    public int right(int pos) { 
        return 2 * pos + 2; //if < the list size, then  left child is empty
    }
    /**
     * Is it a parent? Find out more with another episode of "MinHeap and Pregnant"
     * @param pos Position of node. I've always wondered if you actually read these Steve.
     * @return position of node
     */
    public int parent(int pos) {
        return (int) Math.floor((pos - 1) / 2); //if pos > 0, node is a root node
    }
    /**
     * Displays what the heck is going on with the heap.
     */
    public void display() {
        String output = "[HeapSize: " + listSize + "] ";
        for(int i = 0; i < listSize; i ++) output += " " + listArray[i].getKey() + "(" + listArray[i].getValue() + ")";
        System.out.println(output);
    }
}
