import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Catherine Austria on 9/15/16.
 * This program copies the contents of a file and converts them into a byte array.
 * The contents of the array is then copied one by one into an output file.
 * Both input and output files are specified by the user.
 */
public class ByteCopy {
    public static void main(String args[]){
        String inputFile, outputFile;
        inputFile=args[0]; //takes the first argument as the input file
        outputFile=args[1]; //takes the second argument as the input file
        try {
            File inTxt = new File(inputFile);
            File outTxt = new File(outputFile);
            if(outTxt.exists()){ //if file is present at the specific location, delete it
                outTxt.delete();
            }
            outTxt.createNewFile(); //output file gets created
            Path inpath = Paths.get(inTxt.getAbsolutePath()); //takes the path of the input file; a precaution
            PrintWriter out = new PrintWriter(outTxt);
            System.out.println("\nReading Input File...");
            byte[] bytedata = Files.readAllBytes(inpath);//converts the objects in the file to a byte array
            int i=0; //index used for copying to output file
            System.out.println("\nBytes Read:\n");
            while (i<bytedata.length){
                char c = (char) bytedata[i];    //convert byte to char
                //Files.write(outpath, bytedata); // one fell swoop method
                out.write(c);// write indvidually to output file
                System.out.print(c+"|"); //on-screen test for my personal fulfillment; please ignore this
                i++;
            }
            System.out.println("\n\nFile Copy Finished. Output File Rendered.");
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
