/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancode_austria;

/**
 * This is the node class used for the Huffman Tree.
 * @author Catherine Austria
 */
public class HuffmanNode <Key, E>{
    private Key key;
    private E value;
    private HuffmanNode right;
    private HuffmanNode left;
    public HuffmanNode(Key k, E e) {
            key = k;
            value = e;
            left = right = null;
    }
    public boolean isLeaf() {
            if((left == null) && (right == null)) return true;
            else return false;
    }
    public Key getKey() { return key; }
    public E getValue() { return value; }
    public void setKey(Key k) { key = k; }
    public void setValue(E e) { value = e; }
    public void setLeft(HuffmanNode node) { left = node; }
    public void setRight(HuffmanNode node) { right = node; }
    public void removeLeft() { left = null; }
    public void removeRight() { right = null; }
    public HuffmanNode getLeft() { return left; }
    public HuffmanNode getRight() { return right; }
    
}
