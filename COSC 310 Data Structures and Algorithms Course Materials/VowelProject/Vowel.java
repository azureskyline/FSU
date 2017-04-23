package Homeworks;
import java.util.Scanner;

/**
 * @author Catheirne Austria 9/11/2015
 * This class evaluates a string and prints how many vowels are in the string.
 */
public class Vowel {
    public static int vowelNum;//keeps track of how many vowels there in a word
    public static String someWord; //the word
    
    public Vowel(){
        vowelNum=0;
        someWord="";
    }//constructor end
    
    //Test method for vowelCount
    public static void testVowel(){
        someWord = "Working";
        System.out.println("The string is: "+someWord+"\nNumber of vowels in the string is: "+vowelCount(someWord));
        someWord = "128@#%#@";
        System.out.println("The string is: "+someWord+"\nNumber of vowels in the string is: "+vowelCount(someWord));
        someWord = "  hey";
        System.out.println("The string is: "+someWord+"\nNumber of vowels in the string is: "+vowelCount(someWord));
        someWord = "good sardines";
        System.out.println("The string is: "+someWord+"\nNumber of vowels in the string is: "+vowelCount(someWord));
    }//end of testVowel
    
    //This method counts the number of vowels in a word
    public static int vowelCount(String word){
        vowelNum=0;
        String myWord = word.trim().toLowerCase();
        for(int i=0;i<myWord.length();i++){
            switch(myWord.charAt(i)){
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    vowelNum++;
                default:
            }//end of switch
        }//end of for
        return vowelNum;
    }//end of vowelCount
    
    //A variation of testVowel where the user can input own string
    public static void inputVowel(){
        System.out.print("Please enter a string of words: ");
        Scanner myWord = new Scanner( System.in );
        someWord = myWord.nextLine();
        vowelCount(someWord);
        System.out.println("\nNumber of vowels in the entered string is: "+vowelNum);
    }//end of inputVowel
}
