/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancode_austria;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The Huffman tree does the coding and decoding given a string of letters and 
 * int array of frequency for those corresponding letters.
 * @author Catherine Austria
 */
public class HuffmanTree <Key, E>{
    private HuffmanNode<Integer, String> root; //traversing nodes
    private MinHeap<Integer, String> heap; //tree
    private Hashtable<String, String> huffmantable; //huffman table 
    private Hashtable<String, Integer> freq;
    /**
     * Generates the Huffman tree.
     * @param letters The letters needed for the tree.
     * @param weights The corresponding values of the letters.
     */
    public HuffmanTree(String letters, int[] weights) {
        setup(letters, weights);
        makeTree();
        huffmantable = new Hashtable<String, String>();
        freq = new Hashtable<String, Integer>();
        for(int i = 0; i < letters.length(); i ++) 
            freq.put(letters.substring(i, i + 1), weights[i]);
            getCode();
    }
    /**
     * Sets things up for the tree.
     * @param letters The letters needed.
     * @param weights The corresponding values of the letters.
     */
    private void setup(String letters, int[] weights) {
        int maxNum = letters.length();
        HuffmanNode<Integer, String>[] nodes = new HuffmanNode[maxNum];
        for(int i = 0; i < maxNum; i ++) {
            nodes[i] = new HuffmanNode<Integer, String>(weights[i], letters.substring(i, i + 1)); 
        }
        heap = new MinHeap<Integer, String>(maxNum, maxNum, nodes);
        //heap.display();
    }
    /**
     * The names really are literal. Makes a tree based on values from freq and letters. First in min, second is next min value.
     */
    private void makeTree() {
        while(heap.length() > 1) {
            HuffmanNode<Integer, String> first = heap.removeMin();
            HuffmanNode<Integer, String> second = heap.removeMin();
            HuffmanNode<Integer, String> newnode = new HuffmanNode<Integer, String>(first.getKey() + second.getKey(), " ");
            newnode.setLeft(first);
            newnode.setRight(second);
            heap.insert(newnode);
            //heap.display(); //display what the heck is going on
        }
        root = heap.removeMin();
        //System.out.println("Weight " + root.getKey());
    }
    /**
     * Get the codes from the tree
     * Each leaf node is a letter
     * The path is the code.
     * Writes the codes to a separate .txt file
     */
    private void getCode() {
        //get the code, starting from the root
        getCodeHelper(root, "");
        Enumeration<String> keys = freq.keys();//display the code & compute the sum of weighted path lengths
        int sumOfWeightedPath = 0;
        try {
        PrintWriter writer = new PrintWriter("Codes and Frequencies.txt", "UTF-8");
            while(keys.hasMoreElements()) {
                String key = keys.nextElement();
                System.out.println("Letter: " + key + " " + huffmantable.get(key)); //* letters and their code equivalent
                sumOfWeightedPath += huffmantable.get(key).length() * freq.get(key);
                writer.println("Letter: " + key + " " + huffmantable.get(key));
            }
        System.out.println("Sum of Weighted Path Length: " + sumOfWeightedPath);
        System.out.println("Total Letters: " + root.getKey());	
        writer.println("Sum of Weighted Path Length: " + sumOfWeightedPath);
        writer.println("Total Letters: " + root.getKey());
        writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuffmanTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HuffmanTree.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    /**
     * Encodes a string
     * @param text text to be encoded
     * @return coded text
     */
    public String encode(String text) {
        String code = "";
        for(int i = 0; i < text.length(); i ++) {
            String letter = text.substring(i, i+1);
            code += huffmantable.get(letter);
        }
        return code;
    }
    /**
     * Helper to getCode that generates the "walking bit"
     * @param entry the node
     * @param incode code of node
     */
    private void getCodeHelper(HuffmanNode<Integer, String>entry, String incode) {
        if(entry == null) return;
        if(entry.getLeft() != null) {
            getCodeHelper(entry.getLeft(), incode + "0");
            getCodeHelper(entry.getRight(), incode + "1");
        }
        else {
            huffmantable.put(entry.getValue(), incode);
        } 
    }
    /**
     * Decodes a string.
     * @param codeText text to be decoded.
     * @return decoded text
     */
    public String decode(String codeText) {
        String text = "";
        HuffmanNode curr = root; 
        for(int i = 0; i < codeText.length(); i ++) {
            char tmp = codeText.charAt(i);
            if(tmp == '0') curr = curr.getLeft();
            else curr = curr.getRight();
            if(curr.isLeaf()) { 
            text += curr.getValue();
            curr = root;
            }
        }
        return text;
    }
}
