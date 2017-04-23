/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frostburg.cosc310;

/**
 * This class simulates the HashSet used for the dictionary.
 * @author Catherine Austria
 */
public class HashSet {
    private int myTablesSize; //size of dictionary being generated
    HashEntry[] table; //reference to the Generic table entry.
    
    /**
     * This constructor initializes a dictionary.
     * @param tablesSize size of the dictionary being made
     */
    HashSet(int tablesSize) {
        myTablesSize = tablesSize;
        table = new HashEntry[tablesSize];
        for (int i = 0; i < tablesSize; i++) table[i]=null;//fill everything in the 'table' with null
    }
    /**
     * The method looks up whether or not a word is in the dictionary.
     * @param key the String value/word being searched.
     * @return true or false depending on whether the word is in the dictionary or not.
     */
    public boolean contains(String key){
        int myHash = hashedValue(key);
        if  (table[myHash]==null) return false;
        else return true;
    }//end of contains
    /**
     * This method adds hashes a value before adding it to an array.
     * @param value  the String value/word being added.
     */
    public void add(String value){
        int myHash = findIndex(value);
        table[myHash] = new HashEntry(value);
    }//end of add
    /**
     * Finds what index in the array should the value be placed or fetched in/from.
     * @param value the String value/word being used to find the index of.
     * @return myHash which is the index
     */
    private int findIndex(String value) {
        int myHash = hashedValue(value);
        while (table[myHash] != null && !table[myHash].getValue().equals(value)) {
            myHash = (myHash + 1) % myTablesSize;
        }
        return myHash;
    }//end of findIndex
    /**
     * Contains the hash function for the HashSet and returns int with hash value.
     * @param value the String value of the word being hashed.
     * @return keyValue which pertains to the hash value.
     */
    public int hashedValue(String value){
        int keyValue = 0;
	for (int i = 0; i < value.length(); i++) {
	    int charCode = value.charAt(i) - 96; // 'a' has the code of 97 so subtract to make the letter start at index 1
            keyValue = (keyValue * 31 + charCode) % myTablesSize; //hash function that takes into account letters in alphabet
            //System.out.println("String Value " + value+ " * 27 + Character Code " + charCode + " % arraySize "+ myTablesSize + " = " + keyValue); //testing only
	}//end of for
	return keyValue;
    }//end of hashedValue
}
