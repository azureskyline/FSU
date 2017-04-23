package cosc470_cyaustria0;

/**
 * Created by Catherine Austria on 5/11/16.
 */
public class Error extends Exception{ //expand on this
     public static void RuleNotFound(int rule){
         System.out.println("Semantic Error. Rule "+rule+" was not found.");
         System.exit(1);
     }
    public static void RHSNotFound(int tokenCode){
        System.out.println("Parsing Error. RHS token code "+tokenCode+" was not found.");
        System.exit(1);
    }
    public static void ifStatementError(int lineNum){
        System.out.println("If Statement Type Error at line: "+lineNum);
        System.exit(1);
    }
    public static void IDTypeMismatch(int lineNum, String IDName, String typeofRHS, String typeofID){
        System.out.println("ID to RHS Type Mismatch at line: "+lineNum+"\tID Name: "+IDName+"\nRHS type: "+typeofRHS+"\tID type: "+typeofID);
        System.exit(1);
    }
    public static void CharLitFormatError(int lineNum){
        System.out.println("Character Literal Format at line: "+lineNum+" is not valid.");
        System.exit(1);
    }
    public static void DivideByZero(String term){
        System.out.println("Term "+term+" value is 0. Cannot divide by Zero.");
        System.exit(1);
    }
}
