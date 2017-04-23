package cosc470_cyaustria0;

import java.util.*;

/**
 * Created by Catherine Austria on 5/1/16.
 */
public class Rules {
    private static final Map<Integer, String> grammar = new HashMap<>();
    public static int[][] rules = new int[][]{//<editor-fold desc="reserve tree">
    /*0*/   {0, 0, 0}, //reduced to this
            { 100, 12, 8, 7, 105, 103, 6, 5, 102, 4, 3, 2, 1, 101},
            { 101, 1, 9},
            { 101, 1, 10},
            { 102, 1, 3 },
    /*5*/   { 102, 3, 3, 11, 102},
            { 102, 0, 0},
            { 103, 6, 14, 104, 13, 102, 12, 103},
            { 103, 0, 0},
            { 104, 1, 15},
    /*10*/  { 104, 1, 16},
            { 105, 3, 7, 106, 6},
            { 106, 1, 107},
            { 106, 3, 107, 14, 106},
            { 107, 1, 108},
    /*15*/  { 107, 1, 105},
            { 107, 4, 5, 3, 4, 17},
            { 107, 4, 5, 3, 4, 18},
            { 107, 5, 107, 5, 110, 4, 19},
            { 108, 3, 109, 20, 3},
    /*20*/  { 109, 1, 110},
            { 110, 1, 111},
            { 110, 3, 111, 114, 111},
            { 111, 1, 112},
            { 111, 3, 112, 115, 111},
    /*25*/  { 112, 1, 113},
            { 112, 3, 113, 116, 112},
            { 113, 1, 3},
            { 113, 1, 21},
            { 113, 1, 22},
    /*30*/  { 113, 1, 23},
            { 113, 3, 35,36,35},
            { 114, 1, 24},
            { 114, 1, 26},
            { 114, 1, 28},
    /*35*/  { 114, 1, 27},
            { 114, 1, 25},
            { 114, 1, 29},
            { 115, 1, 30},
            { 115, 1, 31},
    /*40*/  { 116, 1, 32},
            { 116, 1, 33},
            { 116, 1, 34}
    };//</editor-fold>
    /**
     * Constructor that sets the grammar.
     */
    public Rules(){
        setGrammar();
    }
    /**
     * Sets the Integer equivalents of the grammar and their String representations.
     */
    public void setGrammar(){//<editor-fold desc="reserve tree">
        grammar.put(100, "start");
        grammar.put(101, "access");
        grammar.put(102, "identifier_list");
        grammar.put(103, "declarations");
        grammar.put(104, "type");
        grammar.put(105, "compound_statement");
        grammar.put(106, "statement_list");
        grammar.put(107, "statement");
        grammar.put(108, "lefthandside");
        grammar.put(109, "righthandside");
        grammar.put(110, "expression");
        grammar.put(111, "simple_expression");
        grammar.put(112, "term");
        grammar.put(113, "factor");
        grammar.put(114, "relop");
        grammar.put(115, "addop");
        grammar.put(116, "mulop");
        grammar.put(9,"public");
        grammar.put(10,"private");
        grammar.put(1,"static");
        grammar.put(2,"void");
        grammar.put(3,"ID");
        grammar.put(12,"var");
        grammar.put(15,"char");
        grammar.put(16,"int");
        grammar.put(17,"get");
        grammar.put(18,"put");
        grammar.put(19,"if");
        grammar.put(21,"num");
        grammar.put(22,"true");
        grammar.put(23,"false");
        grammar.put(36,"literal");
        grammar.put(6,"{");
        grammar.put(7,"}");
        grammar.put(8,"$");
        grammar.put(11,",");
        grammar.put(14,";");
        grammar.put(13,":");
        grammar.put(4,"(");
        grammar.put(5,")");
        grammar.put(24,">");
        grammar.put(26,">=");
        grammar.put(20,"=");
        grammar.put(28,"==");
        grammar.put(27,"<=");
        grammar.put(25,"<");
        grammar.put(29,"<>");
        grammar.put(30,"+");
        grammar.put(31,"-");
        grammar.put(32,"*");
        grammar.put(33,"/");
        grammar.put(34,"%");
        grammar.put(35,"'");
    }//</editor-fold>
    /**
     * Gets Integer value of a token.
     * @param token String rep of a token we are looking for.
     * @return Integer
     */
    public Integer findIntKey(String token){
        for (Integer i : grammar.keySet()) {
            //if (isNumeric(grammar.get(i))) return
            if (grammar.get(i).equals(token)) {
                return i;
            }
        }
        return 3; // ID is 3
    }
    public String findStringKey(String token){
        for (Integer i : grammar.keySet()) {
            //if (isNumeric(grammar.get(i))) return
            if (grammar.get(i).equals(token)) {
                return String.valueOf(i);
            }
        }
        return "3"; // ID is 3
    }
    /**
     * Finds the String value from an Integer in grammar Map.
     * @param key
     * @return
     */
    public static String findStringValue(int key){
        return grammar.get(key);
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
