/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.frostburg.cosc310;

import java.util.*;

/**
 * This is the main class responsible for generating misspellings.
 * @author Catherine Austria
 */
public class MisSpell extends SpellChecker{
    public List<String> possibilities = new ArrayList<String>();//stores possible suggestions
    private List<String> tempHolder = new ArrayList<String>(); //telps for the transposition method
    private int Ldistance=0; // the distance related to the two words
    private String wrongWord;// the original wrong word.
    
    
    /*public void editDistance(String firstWord, int fwSize, String secondWord, int swSize){
        //For my table version
    }*/
    //^Don't mind him, I intended him for my hash table implementation
    
    /**
     * Execute methods that make misspellings.
     * @param wordCheck the word being checked.
     */
    public void generateMispellings(String wordCheck){
        wrongWord=wordCheck;
        try{
            concatFL(wordCheck);
            concatLL(wordCheck);
            replaceFL(wordCheck);
            replaceLL(wordCheck);
            deleteFL(wordCheck);
            deleteLL(wordCheck);
            pluralize(wordCheck);
            transposition(wordCheck);
        }catch(StringIndexOutOfBoundsException e){ 
            System.out.println();
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println();
        }
        
        
    }
    
    /**
     * This method concats the word behind each of the alphabet letters and checks if it is in the dictionary. 
     * FL for first letter
     * @param word the word being manipulated.
     */
    public void concatFL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=97;i<123;i++){
            cur=(char)i;//assign ASCII from index i value
            tempWord+=cur;
            //if the word is in the dictionary then add it to the possibilities list
            tempWord=tempWord.concat(word); //add passed String to end of tempWord
            checkDict(tempWord); //check to see if in dictionary
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of concatFL
    
    /**
     * This concatenates the alphabet letters behind each of the word and checks if it is in the dictionary. LL for last letter.
     * @param word the word being manipulated.
     */
    public void concatLL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=123;i>97;i--){
            cur=(char)i;//assign ASCII from index i value
            tempWord=tempWord.concat(word); //add passed String to end of tempWord
            tempWord+=cur;
            //if the word is in the dictionary then add it to the possibilities list
            checkDict(tempWord);
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of concatLL
    
    /**
     * This method replaces the first letter (FL) of a word with alphabet letters.
     * @param word the word being manipulated.
     */
    public void replaceFL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=97;i<123;i++){
            cur=(char)i;//assign ASCII from index i value
            tempWord=cur+word.substring(1,word.length()); //add the ascii of i ad the substring of the word from index 1 till the word's last index
            checkDict(tempWord);
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of replaceFL
    
    /**
     * This method replaces the last letter (LL) of a word with alphabet letters
     * @param word the word being manipulated.
     */
    public void replaceLL(String word){
        char cur; // current character
        String tempWord=""; // stores temp made up word
        for(int i=97;i<123;i++){
            cur=(char)i;//assign ASCII from index i value
            tempWord=word.substring(0,word.length()-1)+cur; //add the ascii of i ad the substring of the word from index 1 till the word's last index
            checkDict(tempWord);
            tempWord="";//reset temp word to contain nothing
        }//end of for
    }//end of replaceLL
    
    /**
     * This deletes first letter and sees if it is in dictionary
     * @param word the word being manipulated.
     */
    public void deleteFL(String word){
        String tempWord=word.substring(1,word.length()-1); // stores temp made up word
        checkDict(tempWord);
        //print(possibilities);
    }//end of deleteFL
    
    /**
     * This deletes last letter and sees if it is in dictionary
     * @param word the word being manipulated.
     */
    public void deleteLL(String word){
        String tempWord=word.substring(0,word.length()-1); // stores temp made up word
        checkDict(tempWord);
        //print(possibilities);
    }//end of deleteLL
    
    /**
     * This method pluralizes a word input
     * @param word the word being manipulated.
     */
    public void pluralize(String word){
        String tempWord=word+"s";
        checkDict(tempWord);
    }//end of pluralize
    
    /**
     * It's purpose is to check a word if it is in the dictionary. 
     * If it is, then add it to the possibilities list.
     * @param word the word being checked.
     */
    public void checkDict(String word){
        if(dictionary.contains(word)){//check to see if tempWord is in dictionary
            //if the tempWord IS in the dictionary, then check if it is in the possibilities list 
            //then if tempWord IS NOT in the list, then add tempWord to list
            if(!possibilities.contains(word)) possibilities.add(word);
        }
    }//end of checkDict
    
    /**
     * This method transposes letters of a word into different places.
     * Not the best implementation. This guy was my last minute addition.
     * @param word the word being manipulated.
     */
    public void transposition(String word){
        wrongWord=word;
        int wordLen=word.length();
        String[] mixer = new String[wordLen]; //String[] length of the passed word
        //make word into String[]
        for(int i=0;i<wordLen;i++){
            mixer [i]=word.substring(i,i+1);
        }
        shift(mixer);
    }//end of transposition
    
    /**
     * This method takes a string[] list then shifts the value in between 
     * the elements in the list and checks if in dictionary, adds if so. 
     * I agree that this is probably the brute force implementation.
     * @param mixer the String array being shifted around.
     */
    public void shift(String[] mixer){
        System.out.println();
        String wordValue="";
        for(int i=0;i<=tempHolder.size();i++){
            resetHelper(tempHolder);//reset the helper
            transposeHelper(mixer);//fill tempHolder
            String wordFirstValue=tempHolder.remove(i);//remove value at index in tempHolder
            for(int j=0;j<tempHolder.size();j++){
                int inttemp=0;
                String temp;
                while(inttemp<j){
                    temp=tempHolder.remove(inttemp);
                    tempHolder.add(temp);
                    wordValue+=wordFirstValue+printWord(tempHolder);
                    inttemp++;
                    if(dictionary.contains(wordValue)) if(!possibilities.contains(wordValue)) possibilities.add(wordValue);
                    wordValue="";
                }//end of while
            }//end of for
        }//end for
    }//end of shift
    
    /**
     * This method fills a list tempHolder with contents from String[]
     * @param wordMix the String array being shifted around.
     */
    public void transposeHelper(String[] wordMix){
        for(int i=0;i<wordMix.length;i++){
            tempHolder.add(wordMix[i]);
        }
    }//end of transposeHelper
    
    /**
     * This resets a list
     * @param thisList removes the content of a list
     */
    public void resetHelper(List<String> thisList){
        while(!thisList.isEmpty()) thisList.remove(0); //while list is not empty, remove first value
    }//end of resetHelper
    
    /**
     * This method prints out a list
     * @param listPrint the list to print out.
     */
    public void print(List<String> listPrint){
        if (possibilities.isEmpty()) {
            System.out.print("Can't seem to find any related words for "+wrongWord);
            return;
        }
        System.out.println("Maybe you meant these for "+wrongWord+": ");
        System.out.printf("%s", listPrint);
        resetHelper(possibilities);
    }//end of print
    
    /**
     * This returns a String word version of a list
     * @param listPrint the list to make into a word.
     * @return the generated word version of a list.
     */
    public String printWord(List<String> listPrint){
        Object[] suggests = listPrint.toArray();
        String theWord="";
        for(Object word: suggests){//form listPrint elements into a word
            theWord+=word;
        }
        return theWord;
    }//end of printWord
}
