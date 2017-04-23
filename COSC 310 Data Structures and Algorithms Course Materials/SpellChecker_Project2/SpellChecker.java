/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frostburg.cosc310;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * The main program that loads the correct dictionary spellings 
 * and takes input to be analyzed from user.
 * @author Catherine Austria
 */
public class SpellChecker {
    private static String stringInput; // input to check;
    private static String[] checkThis; // the stringInput turned array of words to check.
    public static HashSet dictionary; // the dictionary used
    
    /**
     * Main method.
     * @param args Argh! arguments...
     */
    public static void main(String[] args) {
        setup();
    }//end of main
    /**
     * This method loads the dictionary and initiates the checks for errors in a scanned input.
     */
    public static void setup(){
        int tableSIZE=59000;
        dictionary = new HashSet(tableSIZE);
        try {
            //System.out.print(System.getProperty("user.dir"));//just to find user's working directory;
            // I combined FileReader into the BufferReader statement
            //the file is located in edu.frostburg.cosc310
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./dictionary.txt"));
            String line = null; // notes one line at a time
            while((line = bufferedReader.readLine()) != null) {
                dictionary.add(line);//add dictinary word in
            }
            prompt();
            bufferedReader.close(); //close file        
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();//print error             
        }
        catch(IOException ex) {
            ex.printStackTrace();//print error
        }
    }//end of setUp
    /**
     * Just a prompt for auto generated tests or manual input test.
     */
    public static void prompt(){
        System.out.println("Type a number from below: ");
        System.out.println("1. Auto Generate Test\t2.Manual Input\t3.Exit");
        Scanner theLine = new Scanner(System.in);
        int choice = theLine.nextInt(); // for manual input
        if(choice==1) autoTest();
        else if(choice==2) startwInput();
        else if (choice==3) System.exit(0);
        else System.out.println("Invalid Input. Exiting.");
    }
    /**
     * Manual input of sentence or words.
     */
    public static void startwInput(){
        //printDictionary(bufferedReader); // print dictionary
        System.out.println("Spell Checker by C. Austria\nPlease enter text to check: ");
        Scanner theLine = new Scanner(System.in);
        stringInput = theLine.nextLine(); // for manual input
        System.out.print("\nYou have entered this text: "+stringInput+"\nInitiating Check..."); 
        /*------------------------------------------------------------------------------------------------------------*/
        //final long startTime = System.currentTimeMillis(); //speed test
        WordFinder grammarNazi = new WordFinder(); //instance of MisSpell
        splitString(removePunctuation(stringInput));//turn String line to String[]
        grammarNazi.initialCheck(checkThis);
        //final long endTime = System.currentTimeMillis();
        //System.out.println("Total execution time: " + (endTime - startTime) );
    }//end of startwInput
    /**
     * Generates a testing case.
     */
    public static void autoTest(){
        System.out.println("Spell Checker by C. Austria\nThis sentence is being tested:\nThe dog foud my hom. And m ct hisse xdgfchv!@# ");
        WordFinder grammarNazi = new WordFinder(); //instance of MisSpell
        splitString(removePunctuation("The dog foud my hom. And m ct hisse xdgfchv!@# "));//turn String line to String[]
        grammarNazi.initialCheck(checkThis);
    }//end of autoTest
    
    /**
     * This method prints the entire dictionary. 
     * Was used in testing.
     * @param bufferedReader the dictionary file
     */
    public static void printDictionary(BufferedReader bufferedReader){
        String line = null; // notes one line at a time
        try{
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }catch(FileNotFoundException ex) {
            ex.printStackTrace();//print error             
        }
        catch(IOException ex) {
            ex.printStackTrace();//print error
        }
    }//end of printDictionary
    
    /**
     * This methods splits the passed String and puts them into a String[]
     * @param sentence The sentence that needs editing.
     */
    public static void splitString(String sentence){
        // split the sentence in between " " aka spaces
        checkThis = sentence.split(" ");
    }//end of splitString
    
    /**
     * This method removes the punctuation and capitalization from a string.
     * @param sentence The sentence that needs editing.
     * @return the edited sentence.
     */
    public static String removePunctuation(String sentence){
        String newSentence; // the new sentence
        //remove evil punctuation and convert the whole line to lowercase
        newSentence = sentence.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
        return newSentence;
    }//end of removePunctuation
}
