package main;

import Homeworks.Vowel;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @version HW00 9/11/2015 
 * @author Catherine Austria
 */
public class main {
    public static void main(String [] args) {
        //Q1 HW00
        System.out.println("Type the letter of the option you would like to execute: ");
        System.out.print("A. Generate Tests \nB. Make Own Inputs \nC. Exit\n\t\t\tInput: ");
        Scanner choice = new Scanner( System.in );
        String someChoice = choice.nextLine().toLowerCase().trim();
        
        switch (someChoice){
            case "a":
                Vowel.testVowel(); //uses the Vowel Class in Homeworks package
                break;
            case "b":
                Vowel.inputVowel(); //user can input own values
                break;
            case "c":
                System.exit(0);
            default:
                System.out.println("\nInvalid Input. Try Again.");
                System.exit(0);
        }//end of switch
    }//end of main
}

/*

                
                
                
                
                
                
                
                
*/