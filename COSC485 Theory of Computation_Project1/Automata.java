/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc485_project1;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Files;
import java.io.*;
/**
 *
 * @author azureskyline
 */
public class Automata {
    private static List<String> M = new ArrayList<String>(); //The machine
    private static List<String> states = new ArrayList<String>(); //states in M
    private static List<String> alphabet = new ArrayList<String>(); //alphabet used by M
    private static String startState = ""; //starting state
    private static List<String> finalStates = new ArrayList<String>(); //set of final states in M
    private static List<List<String>> transFunc = new ArrayList<List<String>>(); //helps in formulating transitions
    private static List<String> tagValues = new ArrayList<String>(); //helps in populating values for the Lists
    private static int statesNum=0; // number of states in total
    private static int finalStatesNum=0; //number of final states used to fill finalStates List
    private static List<String> testStrings = new ArrayList<String>(); //the list of string from .txt file we test
    protected static List<State> someStates = new ArrayList<State>(); //list of directed states

    public static void main(String[] args) {
        // The name of the file to open.
        /*String dfaFile = "COSC485_P1_DFA.txt"; //args[0]
        String nfaFile = "COSC485_P1_NFA.txt"; //args[1]
        String stringFile= "COSC485_P1_Strings.txt"; //args[2]
        String ansFile = "./COSC485_P1_Answers.txt" // args[3]
        */

        try {
            //setup output file writing
            File file = new File(args[3]);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writeThis = new BufferedWriter(writer);

            for (String testLine: Files.readAllLines(Paths.get(args[2]))) {
                for (String part:testLine.split("M\\s=\\s|\\{\\s*|\\s*\\},\\w*.*|\\}|\\s*,\\s*|\\(\\s*|\\s*\\)\\s*|^\\w+,\\s*|^\\w+\\s*\\w+\\s=\\s|^where")) {
                    if(!part.equals("")) {testStrings.add(part.trim());}
                }
            }
            for (String line : Files.readAllLines(Paths.get(args[0]))) {
                for (String part:line.split("M\\s=\\s|\\{\\s*|\\s*\\},\\w*.*|\\}|\\s*,\\s*|\\(\\s*|\\s*\\)\\s*|^\\w+,\\s*|^\\w+\\s*\\w+\\s=\\s|^where")) {
                    if(!part.equals("")) {tagValues.add(part.trim());}
                }
            }
            testDFA(writeThis);
            for (String line : Files.readAllLines(Paths.get(args[1]))) {
                for (String part:line.split("M\\s=\\s|\\{\\s*|\\s*\\},\\w*.*|\\}|\\s*,\\s*|\\(\\s*|\\s*\\)\\s*|^\\w+,\\s*|^\\w+\\s*\\w+\\s=\\s|^where")) {
                    if(!part.equals("")) {tagValues.add(part.trim());}
                }
            }
            testNFA(writeThis);

            writeThis.close();
            writer.close();

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
    /**
     * Prints out if a List of strings are valid for a machine M in NFA case.
     */
    private static void testNFA(BufferedWriter writeThis){
        fillArrays();
        alphabet.add("e");//add epsilon to alphabet
        createTransitions();
        printEverything();
        try {
            writeThis.write("\nNFA Results: ");
            for (String line: testStrings) {
                if (driverNFA(line)){
                    System.out.println("The input is: "+line+" is accepted by M\n");
                    writeThis.write("\nThe input is: "+line+" is accepted by M\n");
                }else {
                    System.out.println("The input is: "+line+" is not accepted by M\n");
                    writeThis.write("\nThe input is: "+line+" is not accepted by M\n");
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("_____________________________________________________________End of NFA_______________________________________________________________");
    }
    /**
     * Evaluates converted stack of String line using NFA
     * @param line the string to be tested.
     * @return true or false if the string is valid or not for machine M
     */
    private static boolean driverNFA(String line){
        Stack<String> inpuTake = new Stack<String>();
        convertLine(line, inpuTake);
        //evaluate stack
        State currState=someStates.get(0);//start at initial state
        int origSize=inpuTake.size();

        if(origSize==1){//if the current state is accepting
            if (currState.hasTransition(inpuTake.pop())) {return true;}
            else {return false;}
        }

        while(!inpuTake.isEmpty()){//while our stack isn't empty
            //System.out.println("Stack: "+inpuTake);//   test
            //___________________________________________________________________________________________________________________________
            if (currState.hasTransition("e")){//if current state has an epsilon transition
                //peek at the top of the stack
                String cmp = inpuTake.peek();
                //take the next transition state of e transition
                String nextState=currState.getTransitionStateName("e");
                //look through someStates for the nextState
                State tmpState=currState;
                for (State state:someStates) {
                    if (state.stateName.equals(nextState)){
                        tmpState=state;
                    }
                }
                //compare if that next transition state has a transition similar to the top pf stack
                if (tmpState.hasTransition(cmp)){
                    //if so then pop the top of stack
                    cmp=inpuTake.pop();
                    //go to the "next transition state"
                    currState=tmpState;
                }
                continue;
            }
            if(currState.hasTransition(inpuTake.peek())){//if the state has a transition like the that of the top of the stack
                //System.out.println("Curr State: "+currState.stateName+" Top of Stack: "+inpuTake.peek());//    test
                String outLine=inpuTake.pop();
                String nextState=currState.getTransitionStateName(outLine);
                //look through someStates for the nextState
                for (State state:someStates) {
                    if (state.stateName.equals(nextState)){
                        currState=state;
                    }
                }
                //System.out.println("Curr after Transition State: "+currState.stateName);    //test
                if (inpuTake.isEmpty()&&currState.isFinal()) return true;
            }else {
                return false;
            }//end if else
            //___________________________________________________________________________________________________________________________
        }//end while
        return false;
    }
    /**
     * Prints out if a List of strings are valid for a machine M in DFA case.
     */
    private static void testDFA(BufferedWriter writeThis){
        fillArrays();
        createTransitions();
        printEverything();
        try {
            writeThis.write("DFA Results: ");
            for (String line: testStrings) {
                if (driverDFA(line)){
                    System.out.println("The input is: "+line+" is accepted by M\n");
                    writeThis.write("\nThe input is: "+line+" is accepted by M\n");
                }else {
                    System.out.println("The input is: "+line+" is not accepted by M\n");
                    writeThis.write("\nThe input is: "+line+" is not accepted by M\n");
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("_____________________________________________________________End of DFA_______________________________________________________________");
    }
    /**
     * Evaluates converted stack of String line using DFA
     * @param line the string to be tested.
     * @return true or false if the string is valid or not for machine M
     */
    private static boolean driverDFA(String line){
        Stack<String> inpuTake = new Stack<String>();
        convertLine(line, inpuTake);
        //evaluate stack
        State currState=someStates.get(0);//start at initial state
        int origSize=inpuTake.size();
        if(origSize==1){//if the current state is accepting
            if (currState.hasTransition(inpuTake.pop())) {return true;}
            else {return false;}
        }
        while(!inpuTake.isEmpty()){//while our stack isn't empty
            //System.out.println("Stack: "+inpuTake);//   test
            if(currState.hasTransition(inpuTake.peek())){//if the state has a transition like the that of the top of the stack
                //System.out.println("Curr State: "+currState.stateName+" Top of Stack: "+inpuTake.peek());//    test
                String outLine=inpuTake.pop();
                String nextState=currState.getTransitionStateName(outLine);
                //look through someStates for the nextState
                for (State state:someStates) {
                    if (state.stateName.equals(nextState)){
                        currState=state;
                    }
                }
                //System.out.println("Curr after Transition State: "+currState.stateName);    //test
                if (inpuTake.isEmpty()&&currState.isFinal()) return true;
            }else {
                if (!inpuTake.isEmpty()) return false;
            }//end if else
        }//end while
        return false;//all else, not accepted
    }
    /**
     * Finds transitions associated to a state and puts them along with other transitions associated to that state
     */
    private static void conciser() {
        //System.out.println("\nConcising states...........someStates size:\t"+someStates.size()+"\n");
        for (int i = 0; i < someStates.size(); i++) {
            State curr=someStates.remove(0);//remove current state
            int initialSze=someStates.size();

            /*for (State state:someStates) {
                System.out.print(state.printState());
            }
            System.out.println("\nNumber of elements in someStates: "+someStates.size()+"\n");//    test*/

            for (int j = 0; j < initialSze; j++) {
                State state=someStates.get(j);
                //System.out.print("$ Current State for"+i+":\t"+curr.printState()+"$");   //  test
                //System.out.println("$ Reference State: \t"+state.printState()+"$");   //  test
                if (curr.stateName.equals(state.stateName)){//a state needs concising
                    //pop state's transitions
                    HashMap<String,String> temp=state.takeTransitions();
                    //pop in those transitions to current state
                    curr.addTransition(temp);
                    //remove that state
                    state=someStates.remove(j);
                    //increment
                    initialSze=initialSze-1;
                }
            }
            //put current state back in
            someStates.add(curr);
        }
        //reorganize order of states in someStates
        Collections.sort(someStates);
        /*for (State state:someStates) {
            System.out.print(state.printState());
        }
        System.out.println("\nNumber of elements in someStates: "+someStates.size()+"\n");//    test*/
    }
    /**
     * Converts a line to a stack of strings to be evaluated
     * @param line  String to be converted
     * @param inpuTake  Stack to place line in
     */
    private static void convertLine(String line, Stack<String> inpuTake){
        String[] tmp = line.split(""); //convert line to string array
        if (tmp.length==1){
            inpuTake.add(line);
        }else{
            for (int i = tmp.length-1; i >=0; i--) {//populate stack
                //System.out.println("tmp[i]: "+tmp[i]);
                inpuTake.add(tmp[i]);
            }
        }
        //System.out.println(inpuTake+"\nTop of Stack: "+inpuTake.peek());//  test
    }
    /**
     * Convert states into Nodes with transitions to other states etc. See Node class.
     */
    private static void createTransitions(){
        for (String state: states) {
            for (int i = 0; i < transFunc.size(); i++) {//go through the transitions in the .txt stored in transFunc
                if(transFunc.get(i).get(0).equals(state)){//if the value in row i col 0 is equal to any of the states in list of states above, meanse valid state with a transition
                    State placeThis; //make a new temp state

                    //add case for starting accepting state
                    if(finalStates.contains(transFunc.get(i).get(0))&&transFunc.get(i).get(0).equals(startState)){//if the next state is a final state
                        placeThis=new State(transFunc.get(i).get(0), 0);
                        placeThis.addTransition("",transFunc.get(i).get(0));
                        placeThis.setFinal();//set as a final state
                        //System.out.println("This starting state is final: "+placeThis.stateName);  //test
                        someStates.add(placeThis);// add to the main list we will reference later
                    }
                    //add the case for final accepting states
                    if(finalStates.contains(transFunc.get(i).get(2))){//if the next state is a final state
                        int num=Integer.valueOf(transFunc.get(i).get(2).substring(1));
                        placeThis=new State(transFunc.get(i).get(2), num);
                        placeThis.addTransition("",transFunc.get(i).get(2));
                        placeThis.setFinal();//set as a final state
                        //System.out.println("This state is final: "+placeThis.stateName);  //test
                        someStates.add(placeThis); // add to the main list we will reference later
                    }
                    int num; // this part is for reorganizing transitions
                    if (transFunc.get(i).get(0).equals(startState)){//if start state, 0 val
                        num=Integer.valueOf(transFunc.get(i).get(0).substring(1));
                    }else {
                        //if the number is greater than one digit
                        if (transFunc.get(i).get(2).length()>2){
                            num=Integer.valueOf(transFunc.get(i).get(2).substring(1,2));
                        }else{// else one digit only
                            num=Integer.valueOf(transFunc.get(i).get(2).substring(1));
                        }
                    }
                    //non-final transitions handled here
                    placeThis = new State(state, num);
                    placeThis.addTransition(transFunc.get(i).get(1), transFunc.get(i).get(2)); // add its the transition
                    //add the case for final accepting states
                    if(finalStates.contains(transFunc.get(i).get(2))){
                        placeThis.setFinal();//set as a final state
                    }
                    someStates.add(placeThis); // add to the main list we will reference later
                }//end if
            }//end for
        }//end foreach
        conciser();
    }
    /**
     * Initializes lists and variables
     */
    private  static void emptyArr(){
        M = new ArrayList<String>();
        states = new ArrayList<String>();
        alphabet = new ArrayList<String>();
        finalStates = new ArrayList<String>();
        transFunc = new ArrayList<List<String>>();
        someStates = new ArrayList<State>();
    }
    /**
     * Fill the arraylists set above with information from the .txt file
     */
    private static void fillArrays(){
        //re/initialize arrays
        emptyArr();
        //fill M
        int index=0;
        for (int i=0;i<=4;i++){
            M.add(tagValues.remove(index));
        }
        //fill states
        while(tagValues.get(index).matches("[a-z]\\d(\\d)*")){
            statesNum++;
            states.add(tagValues.remove(index));
        }
        //fill Alphabet
        while (tagValues.get(index).length()<2){
            alphabet.add(tagValues.remove(index));
        }
        //set startState
        startState = tagValues.remove(index);
        //fill finalStates
        while(tagValues.get(index).matches("[a-z]\\d")){
            finalStatesNum++;
            finalStates.add(tagValues.remove(index));
        }
        ArrayList<String> temp = new ArrayList<>();
        while (tagValues.size()!=0){
            if(tagValues.get(index).equals("")) tagValues.remove(index);//remove any "" if any
            temp.add(tagValues.remove(index)); //add tagValue to temp arraylist
            if (temp.size()%3==0){//if past 3 indexes, new array
                transFunc.add(temp);
                temp = new ArrayList<>();
            }
        }
    }
    /**
     * Prints out the information on the .txt file + # of total states and final states.
     */
    private static void printEverything(){
        System.out.print("\nM: "+M);
        System.out.println();
        System.out.print("States: "+states);
        System.out.println("\nNumber of States: "+statesNum);
        System.out.print("Alphabet: "+alphabet);
        System.out.println();
        System.out.println("Starting State "+startState);
        System.out.print("Final States: "+finalStates);
        System.out.println("\nNumber of Final States: "+finalStatesNum);
        System.out.println("Transition Functions: "); //OR System.out.println("Transition Functions: "+"\n"+transFunc);
        for (int i = 0; i < transFunc.size(); i++) {
            for (int j = 0; j < transFunc.get(i).size(); j++) {
                System.out.print(transFunc.get(i).get(j)+" ");
            }
            System.out.println();
        }
        System.out.println("Testing Strings: "+testStrings);
        System.out.println("\n");
    }
}

