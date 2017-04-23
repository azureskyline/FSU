/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cosc485_project1;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Catherine Austria
 */
public class State implements Comparable<State>{
    public String stateName; //name of this state
    private int value; //value of the order this state is called (e.g. 0 for q0) used for reordering later on.
    public boolean isFinalState; //sets if the state is accepting
    
    public HashMap<String,String> theTransitions = new HashMap<>();
    /**
     * Initializes the state with a name and value
     * @param name Name of the State
     * @param val val is used later on for reorganizing
     */
    public State(String name, int val){
        this.stateName=name;
        this.value=val;
    }
    /**
     * Initializes the state with a name, value and transitions
     * @param name Name of the State
     * @param val val is used later on for reorganizing
     * @param transitions transitions passed from another state
     */
    public State(String name, int val, HashMap<String,String> transitions){
        this.value=val;
        this.isFinalState=false;
        this.stateName=name;
        this.theTransitions=transitions;
    }
    /**
     * Add a transition to this State
     * @param transVar  The transition value (e.g. a, b etc)
     * @param nextState The state to which the transition is going to.
     */
    public void addTransition(String transVar, String nextState){
        theTransitions.put(transVar,nextState);
    }
    /**
     * Add a transition to this State
     * @param someTransitions The set of transitions passed from another State
     */
    public void addTransition(HashMap<String,String> someTransitions){
        theTransitions.putAll(someTransitions);
    }
    /**
     * Checks if the State has a transition via the transVar (key) and returns true or false
     * @param transVar  This is the key value being check if this state has that transition to another state.
     * @return True or False depending if transVar is in the set of transitions.
     */
    public boolean hasTransition(String transVar){
        if(theTransitions.containsKey(transVar)) return true;
        return false;
    }
    /**
     * Takes the name of the state the transition is going to.
     * @param transVar This is the key value being check if this state has that transition to another state.
     * @return The name of the state being transitioned to.
     */
    public String getTransitionStateName(String transVar){
        if(theTransitions.containsKey(transVar)){
            return theTransitions.get(transVar);
        }
        return null;
    }
    /**
     * Returns the set of transitions this state contains.
     * @return Returns the set of transitions this state has.
     */
    public HashMap<String,String> takeTransitions(){
        return theTransitions;
    }
    /**
     * Sets if this state is an Accepting State.
     */
    public void setFinal(){
        isFinalState=true;
    }
    /**
     * Finds out if this state is an accepting state.
     * @return True or False
     */
    public boolean isFinal(){
        return isFinalState;
    }
    /**
     * Prints information relative to this State class.
     * @return String of information regarding the state.
     */
    public String printState(){
        String thisState="";
        for (Map.Entry<String, String> line: theTransitions.entrySet()) {
            thisState+="\tState: q"+value+" outLine: "+line.getKey()+" Next State: "+line.getValue()+"\n";
        }
        return thisState+"\n";
    }
    @Override
    public int compareTo(State someState) { //Used to sort the state via value. Uses Comparator.
        return this.value - someState.value;
    }
}

