import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Catherine Austria on 10/5/16.
 */
public class AustriaCatherineShell {
    private static String[] toProcess; //array of the CurrentCMD parts
    private static List<String> CMDSoFar = new ArrayList<String>(); //to help print the custom made history
    private static String curDir = new File("").getAbsoluteFile().toString(); // current directory
    /**
     * Drives the execution of the whole process.
     * @param args  In case of terminal arguments.
     */
    public static void main(String[] args){
        while (true){
            Scanner terminalText = new Scanner(System.in);
            takeInput(terminalText);    //take user input
            System.out.println();
        }
    }
    /**
     * Prompts for user input. takes input and splices it into parts (via whitespace) and places it into the toProcess string array.
     * @param scan Scanner object used by the main program that takes the input
     */
    public static void takeInput(Scanner scan){
        System.out.println("\nPress enter command to continue or Type \"exit\" (or Ctrl+C) to end program: ");
        String cmd = scan.nextLine();
        if (cmd.equals("")) { // if no cmd, then do nothing
            takeInput(scan);
        }else { // if cmd then process cmd
            CMDSoFar.add(cmd);
            toProcess = cmd.split("\\s+");  //splits CurrentCMD by whitespace parts into toProcess
            processCMDs(toProcess[0]);
        }
    }
    /**
     * Creates custom actions when commands are entered.
     * @param cmd   The line of command to be executed.
     */
    public static void processCMDs(String cmd){
        try{
            switch (cmd.trim()){
                case "!!": //execute previous command
                    String newCMD = CMDSoFar.get(CMDSoFar.size()-2);
                    CMDSoFar.set(CMDSoFar.size()-1,newCMD);
                    toProcess = newCMD.split("\\s+");
                    processCMDs(toProcess[0]); //go through process for new cmd
                    break;
                case "exit":
                    System.exit(0); //end program no issues
                    break;
                case "history":
                    System.out.println();
                    for (String x:CMDSoFar) System.out.println(x);
                    break;
                case "cd":
                    if (toProcess.length==1){ //if cd only ; user home directory
                        curDir = new File(System.getProperty("user.home")).getAbsoluteFile().toString(); // change to user home directory
                    }else if(toProcess[1].equals("..")) { //if cd .. ; should move to parent directory
                        curDir = new File(curDir).getAbsoluteFile().getParent().toString(); // parent directory or current directory
                    }else { // normal change directory to some location
                        curDir = new File(toProcess[1]).getAbsoluteFile().toString(); // change the directory to what ever address
                    }
                    runProcess();
                    System.out.println("Directory Changed!");
                    break;
                case "ls":
                    System.out.println("The current directory is: "+curDir);
                    runProcess();
                    break;
                default:
                    if (cmd.matches("!\\d+")) { // if detects !#
                        int subNum = Integer.parseInt(cmd.substring(1)); //take num after !
                        String newerCMD = CMDSoFar.get(subNum);
                        toProcess = newerCMD.split("\\s+");
                        CMDSoFar.set(CMDSoFar.size()-1,newerCMD); // so that the newerCMD will override the !#
                        processCMDs(toProcess[0]); //go through process for new cmd
                        System.out.println(cmd+" command was executed!");
                    }else runProcess();
            }
        }catch (IOException e){
            System.out.println("SOMETHING WENT WRONG STEVE! IOExecption! Please type a valid command.");
        }
    }
    /**
     * Creates a process and runs the cmd.
     * @throws IOException  Input might be invalid thus here as a needed pre-caution.
     */
    public static void runProcess() throws IOException{
        ProcessBuilder pb = new ProcessBuilder(toProcess);
        pb.directory(new File(curDir)); //set directory
        Process runpro = pb.start();
        String outLine;
        InputStream is = runpro.getInputStream(); //read output
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        System.out.printf("Output of running %s is:\n", Arrays.toString(toProcess));
        while ((outLine = br.readLine()) != null) {
            System.out.println(outLine);//print output
        }
    }
}
