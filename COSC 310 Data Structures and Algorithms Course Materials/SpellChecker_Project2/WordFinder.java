/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frostburg.cosc310;

import static edu.frostburg.cosc310.SpellChecker.dictionary;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class checks for mispellings [see that? Deal with it OCD complex.] 
 * if ever it occurs and notifies users of suggestions for correction.
 * @author Catherine Austria
 */
public class WordFinder extends SpellChecker{
    private int wordsLength;//length of String[] to check
    private List<String> wrongWords = new ArrayList<String>();//stores incorrect words
    
    /**
     * This methods checks the String[] for spelling errors. 
     * Hashes each index in the String[] to see if it is in the dictionary HashSet
     * @param words String list of misspelled words to check
     */
    public void initialCheck(String[] words){
        wordsLength=words.length;
        
        System.out.println();
        for(int i=0;i<wordsLength;i++){
            //System.out.println("What I'm checking: "+words[i]); //test only
            if(!dictionary.contains(words[i])) wrongWords.add(words[i]);
        } //end for
        //manualWordLookup(); //for testing dictionary only
        if (!wrongWords.isEmpty()) {
            System.out.println("Mistakes have been made!");
            printIncorrect();
        } //end if
        if (wrongWords.isEmpty()) {
            System.out.println("\n\nMove along. End of Program.");
        } //end if
    }//end of initialCheck
    
    /**
     * This method that prints the incorrect words in a String[] being checked and generates suggestions.
     */
    public void printIncorrect(){//delete this guy
        System.out.print("These words [ ");
        for (String wrongWord : wrongWords) {
            System.out.print(wrongWord + " ");
        }//end of for
        System.out.println("]seems incorrect.\n");
        suggest();
    }//end of printIncorrect
    
    /**
     * This method gives suggestions to the user based on the wrong words she/he misspelled.
     */
    public void suggest(){
        MisSpell test = new MisSpell();
        while(!wrongWords.isEmpty()&&test.possibilities.size()<=5){
            String wordCheck=wrongWords.remove(0);
            test.generateMispellings(wordCheck);
            //if the possibilities size is greater than 0 then print suggestions
            if(test.possibilities.size()>=0) test.print(test.possibilities);
        }//end of while
    }//end of suggest
    
    /*ENTERING TEST ZONE*/
    /**
     * This allows a tester to look thorough the dictionary for words if they are valid; and for testing only.
     */
    public void manualWordLookup(){
        System.out.print("Enter 'ext' to exit.\n\n");
        Scanner line = new Scanner(System.in);
        String look=line.nextLine();
        do{
        if(dictionary.contains(look)) System.out.print(look+" is valid\n");
        else System.out.print(look+" is invalid\n");
        look=line.nextLine();
        }while (!look.equals("ext"));
    }//end of manualWordLookup
}
