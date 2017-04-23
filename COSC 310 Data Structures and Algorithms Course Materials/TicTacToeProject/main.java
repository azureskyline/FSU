package main;

//import Homeworks.Vowel;
import Homeworks.TicTacToe;
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
        //Vowel.testVowel(); //uses the Vowel Class in Homeworks package
        
        //Q3 HW00
        TicTacToe game = new TicTacToe();
        
        System.out.println("Type the letter of the option you would like to execute: ");
        System.out.print("A. Generate Tests \nB. Exit\n\t\t\tInput: ");
        Scanner choice = new Scanner( System.in );
        String someChoice = choice.nextLine().toLowerCase().trim();
        
        switch (someChoice){
            case "a":
                try{
                    //Tie
                    game.putMark(1,1);//x
                    game.putMark(0,2);//o
                    game.putMark(2,2);//x
                    game.putMark(0,0);//o
                    game.putMark(0,1);//x
                    game.putMark(2,1);//o
                    game.putMark(1,2);//x
                    game.putMark(1,0);//o
                    game.putMark(2,0);//x
                
                    System.out.println(game);
                    int winningPlayer = game.winner();
                    String[]outcome={"\nO wins","Tie\n","\nX wins"}; 
                    System.out.println(outcome[1 + winningPlayer]);
                    game.clearBoard();
                
                    // X wins
                    game.putMark(0,0);//x
                    game.putMark(1,1);//o
                    game.putMark(0,2);//x
                    game.putMark(2,0);//o
                    game.putMark(0,1);//x
                    game.putMark(2,2);//o
                    game.putMark(1,2);//x
                    game.putMark(2,1);//o
                    game.putMark(1,0);//x
                }catch(IllegalStateException e){
                    System.out.println(game);
                    int winningPlayer = game.winner();
                    String[]outcome={"O wins\n\n","Tie\n\n","X wins\n\n"}; 
                    System.out.println(outcome[1 + winningPlayer]);
                    game.clearBoard();
                }
                
                
                break;
            case "b":
                System.exit(0);
            default:
                System.out.println("\nInvalid Input. Try Again.");
                System.exit(0);
        }//end of switch
    }//end of main
}

/*

                
                
                
                
                
                
                
                
*/