
package cosc470_cyaustria0;
import java.io.*;
/**
 *
 * @author azureskyline
 */
public class COSC470_cyaustria0 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            //THIS part prints console output to see process
            PrintStream out = new PrintStream(new FileOutputStream("process.txt"));
            System.setOut(out);
            //THIS Ends

            Scanner myScan = new Scanner();
            myScan.loadFile(args[0]);//load the text file and start scanning
            myScan.tokenize();//make tokens from the file
            //System.out.println(myScan);

            Parser parseMe = new Parser();
            System.out.println("\nParser Returns: "+parseMe.driver());

            //parseMe.symTabPrint();//prints out symboltable
        }catch (FileNotFoundException e){}
    }
    
}
