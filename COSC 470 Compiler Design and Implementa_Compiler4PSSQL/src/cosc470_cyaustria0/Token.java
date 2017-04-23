package cosc470_cyaustria0;

import java.util.List;

/**
 * Created by Catherine Austria on 5/1/16.
 */
public class Token{
    public String tokenName; //String name of the token
    public String tokenType; //int char or bool?
    public String tokenValue; //value of the token?
    public int tokenCode; // int value of the token
    public int tokenLine; //line of the text file the code is located
    public List<Token> tokenList; //This is mainly for the sake of identifier_list
    public int tokenTempCode=-1; //used in code gen; stores a temp int for reference for mini
    public String tokenVarName=""; //used to store the Var name of a token if it has one
    public Token (String name, String type, String value, int code, int lineNum, List<Token> alist){
        this.tokenName=name;
        this.tokenType=type;
        this.tokenValue=value;
        this.tokenCode=code;
        this.tokenLine=lineNum;
        this.tokenList=alist;
    }
    public void tempCodeAssign(int code){
        tokenTempCode=code;
    }
    public String toString(){
        String tokenInfo="\nToken Name: "+this.tokenName+"\t\t\t\t\t\tToken Type: "+this.tokenType+"\t\tToken Code: "+this.tokenCode+"\t\t\tToken Line: "+tokenLine+"\t\t\tToken Value: "+this.tokenValue+"\t\t\t\t\tIdentifierlist Content: ";
        if(tokenList!=null){
            for (Token s:tokenList) {
                tokenInfo+=" "+s.tokenName+",";

            }
        }
        return tokenInfo;
    }
}
