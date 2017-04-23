/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frostburg.cosc310;

import java.util.Random;

/**
 * This is the entry class for a dictionary.
 * @author Catherine Austria
 */
public class HashEntry {
    private String value;  //the value associated with the key in the entry
    /**
     * Constructor that sets an entry.
     * @param value A word.
     */
    HashEntry(String value) {
        this.value = value;
    }
    /**
     * Gets the value/word and returns it.
     * @return the word being searched.
     */
    public String getValue() {
        return value;
    }//end of getValue
}
