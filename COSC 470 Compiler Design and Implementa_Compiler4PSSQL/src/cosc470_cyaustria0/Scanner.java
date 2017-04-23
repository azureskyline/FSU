/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cosc470_cyaustria0;

import java.io.*;
import java.util.*;

/**
 * Scanner for the compiler.
 * @author Catherine Austria on 02/4/16.
 */
public class Scanner {
    public static ArrayList<String> reserved = new ArrayList<String>();; //used for dissecting input files.
    private static Map<String, Integer> lineKeep = new HashMap<String, Integer>() {};   //keeps the line numbers for each type of token generated
    public static ArrayList<String> toParse = new ArrayList<String>();; //the elements of a .txt file is dissected and placed here for Tokenizer to have at.
    public static ArrayList<Token> parseReady = new ArrayList<>();
    private static Rules base = new Rules();
    /**
     * Loads txt file to scan and initiates the drive process.
     */
    public static void loadFile(String file){
        try{
            BufferedReader readthis = new BufferedReader(new FileReader(file));
            int linenum = 1;
            String line;
            while((line = readthis.readLine())!=null){
                if (!line.isEmpty()){
                    createParts(line, linenum);
                    linenum++;
                }
            }
            tokenPrep();
            readthis.close();
        }catch(FileNotFoundException e){}
        catch(IOException e){
            System.out.print("Input file for Scanning not found.");
        }
    }
    /**
     * Looks for a token in an index
     * May need to modify this later
     * @param index The index of the token you which to seek.
     * @return String representation of the token.
     */
    public static String getElementAtIndex(int index){
        return toParse.get(index);
    }
    /**
     * makes a clone of reserved called toParse.
     * For what reason did I create this? I forgot.
     */
    public static void tokenPrep(){
        Iterator copy = reserved.iterator();
        while(copy.hasNext()) {
            if (!copy.equals("")||!copy.equals(" ")){
                Object element = copy.next();
                toParse.add(String.valueOf(element));
            }
        }
        System.out.println("Tokens to parse: "+toParse+"\n"); //IMPORTANT TEST TO SEE TOKENS TO PARSE DO NOT DELETE
    }
    /**
     * This just divides what the scanner is reading.
     * The cases for the scanner is contained here.
     * @param sentence The String line the scanner is currently on.
     */
    public static void createParts(String sentence, int linenum){
        sentence=sentence.replaceAll("\\/\\/.*",""); //comments
        String replaceAll = sentence.replaceAll("([^\\w ])", " $1 ");
        List<String> asList = Arrays.asList(replaceAll.split(" +"));
        for (String string : asList) {
            reserved.add(string);
            lineKeep.put(string, linenum);
        }
    }
    //____________________________________________________________Token Making_________________________________________________________________________
    /**
     * Converts toParse array into Tokens for Parsing and places them into parseReady array
     */
    public static void tokenize(){
        String curToken="", typeofToken="", valofToken="";
        int codeofToken=0, lineofToken=0;
        int size=toParse.size();
        for (int i=0;i<size;i++) {
            if (toParse.get(i) == "" || toParse.get(i) == " " || toParse.get(i).isEmpty()) continue;
            curToken = toParse.get(i); //get the next element and set the name of token
            typeofToken = returnType(curToken); //set the type of token
            codeofToken = base.findIntKey(curToken); //set the # value from the attribute grammar off of base
            if (lineKeep.containsKey(curToken))
                lineofToken = lineKeep.get(curToken); //get the line the token is located in textfile
            if (codeofToken == 3) {//if token is num
                if (curToken.matches("\\d*")){      //if num assignment
                    curToken=toParse.get(i);
                    typeofToken="int";
                    codeofToken=21;
                    valofToken=toParse.get(i);
                    //System.out.println("I AM AN INT!!! "+toParse.get(i)+" WITH TYPE: "+typeofToken+" Code: "+codeofToken+" VALUE: "+valofToken);
                }
            }
            // for IDs
            boolean upcase = curToken.equals(curToken.toUpperCase());// IDs have to be uppercase
            if(upcase && curToken.matches("[A-Z][A-Z]*[0-9]*")){
                typeofToken="ID";
                valofToken=" "; //ID initially no value
            }
            parseReady.add(new Token(curToken, typeofToken, valofToken, codeofToken, lineofToken, null));//add this Token to parseReady
            //exception for 'literal'
            if (toParse.get(i).equals("'") && toParse.get(i + 2).equals("'")) { //Character literal is a single character within single quotation marks
                valofToken = "";
                curToken = toParse.get(i + 1); //get the literal character
                /*if (curToken.length()!=1){//error if character literal is not within format
                    Error.CharLitFormatError(lineofToken);
                }*/
                if (isNumeric(curToken)) valofToken = curToken; //get the string rep of the numValue
                if (curToken.matches("[A-Z]"))
                    valofToken = String.valueOf(Integer.valueOf(curToken.charAt(0))); //get the num val of the literal at [a-z][A-Z]
                typeofToken = "char"; //set the type of token
                codeofToken = 36; //set the # value from the attribute grammar off of base
                if (!lineKeep.isEmpty() && lineKeep.containsKey(curToken)) {
                    lineofToken = lineKeep.get(curToken); //get the line the token is located in textfile
                }
                //System.out.println("valofToken: " + curToken.charAt(0) + " belongs to literal: " + curToken + " codeofToken:" + codeofToken);  //test
                parseReady.add(new Token(curToken, typeofToken, valofToken, codeofToken, lineofToken, null));//add this Token to parseReady
                i++;
            }
            //System.out.println(i+"\tcurToken: "+curToken+"\t\t\ttypeofToken: "+typeofToken+"\t\tvalofToken: "+valofToken); //test
        }
    }
    /**
     * Returns what type is a token.
     * @param token
     * @return Either int, char or boolean.
     */
    public static String returnType(String token){
        token=token.toLowerCase();
        if (isNumeric(token)) return "int";
        if (token.equals("true")||token.equals("false")) return "boolean";
        else return "char";
    }
    /**
     * If string is a number
     * @param str
     * @return true or false
     */
    public static boolean isNumeric(String str){
        return str.matches("-?\\d+([.]\\d+)?");
    }
}
