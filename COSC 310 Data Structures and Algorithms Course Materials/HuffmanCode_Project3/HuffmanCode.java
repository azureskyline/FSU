/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancode_austria;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class can take a string input or a .txt file and convert it into Huffman Code and vice versa.
 * @author Catherine Austria
 */
public class HuffmanCode {
    private static String encode;
    private static String decode;
    private static HuffmanTree huffmanthis;
    private static String text;
    private static int[] freq;
    private static char[] letters;//initial array of string input
    private static HashMap<Character,Integer> map = new HashMap<Character,Integer>(); 
    /**
     * Initiates program.
     * @param args Argh! no, this is where you pass the .txt you want to encode/decode
     */
    public static void main(String[] args) {
        /*text="Merry Christmas!";
        set(text);
        setup();*/
        try {
            // first check to see if the program was run with the command line argument
            if(args.length < 1) {
                System.out.println("Error: invalid text entry.");
                System.exit(1);
            }
            Scanner reader = new Scanner(new FileInputStream(args[0]));
            text=reader.next();
            System.out.println("Type a number from below: ");
            System.out.println("1. Auto Generate Test\t2.TXT Input\t3.Exit");
            Scanner theLine = new Scanner(System.in);
            int choice = theLine.nextInt(); // for manual input
            if(choice==1) {
                text="Merry Christmas!";
                set(text);
                setup();
            }
            else if(choice==2){
                while (reader.hasNext()) {  
                    text+=reader.next();
                }
                set(text);
                setup();
            }
            else if (choice==3) System.exit(0);
            else System.out.println("Invalid Input. Exiting.");       
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();//print error             
        }
        catch(IOException ex) {
            ex.printStackTrace();//print error
        }
    }
    /**
     * Performs encoding and decoding.
     */
    public static void setup(){
        try {
            PrintWriter writer = new PrintWriter("Huffman Output.txt", "UTF-8");
            String newletters = String.valueOf(letters);
            System.out.println(newletters);
            huffmanthis = new HuffmanTree(newletters, freq);
            
            encode = huffmanthis.encode(text);
            writer.println("Original Text: "+text);
            writer.println("Encoded Text: " + encode);
            System.out.println("Encoded Text: " + encode);
            
            decode = huffmanthis.decode(encode);
            writer.println("Text to Encode: "+encode);
            writer.println("Decoded Text: " + decode);
            System.out.println("Decoded Text: " + decode);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HuffmanCode.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HuffmanCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Sets freq (frequency array) and letters (character array) from HashMap
     * @param text Text to bias.
     */
    public static void set(String text){
        String s = text;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            Integer val = map.get(new Character(c));
            if(val != null){
                map.put(c, new Integer(val + 1));
            }else{map.put(c,1);}
        }
        //System.out.println(map.keySet());
        freq=new int[map.size()];
        letters=new char[map.size()];
        int x=0;
        for (Character key : map.keySet() ) {
            letters[x]=key; //put all char to char array
            freq[x++]=map.get(key); //put all freq to freq array
        }
        /*System.out.println(Arrays.toString(letters));
        for(int k =0;k < freq.length;k++){
            System.out.print(freq[k]+" ");
        }*/
    }
}
