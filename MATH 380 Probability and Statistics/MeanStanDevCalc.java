import java.util.*;

/**
 * Created by Catherine Austria on 4/25/17.
 * Generates random numbers from 0-9 based on a sample n digits.
 * Computes the mean of each of the samples taken.
 * Computes the mean of the total population samples.
 * Computes the standard deviation of the total population samples.
 */
public class MeanStanDevCalc {
    private static int sampleNum=0, drawNum=0;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter # of digits randomly selected for each sample: ");
        sampleNum=in.nextInt();
        System.out.println("Obtaining a sample of "+sampleNum+" digits between 0 and 9...");
        System.out.print("How many samples will be drawn: ");
        drawNum=in.nextInt();
        System.out.println("\nDrawing "+drawNum+" samples of "+sampleNum+"...");
        Random rand = new Random();
        double arrNums[] = new double[drawNum*sampleNum];
        for (int i = 0; i < drawNum*sampleNum; i++){
            arrNums[i] = rand.nextInt(10);                      //get random number bet 0 - 9
        }
        //show random results and sort random samples into 2D array
        List<Double> mean = new ArrayList<>();
        double meanNum=0;
        for (int j = 0; j < drawNum*sampleNum; j++) {           //print array of numbers
            System.out.print(arrNums[j]+"\t");
            meanNum+=arrNums[j];                                //mean
            if (j%sampleNum == sampleNum-1) {
                System.out.println();
                //mean
                meanNum = meanNum/sampleNum;
                System.out.println("\t\t\t\t\t\t\t\tSample Mean: "+meanNum);
                mean.add(meanNum);                              //store mean
                meanNum=0;                                      //reset mean

            }
        }
        System.out.println();
        // mean
        //the final mean is all the means added together divided by the sample n.
        double meanFinal=0;
        for (double x: mean) {
            meanFinal+=x;
        }
        meanFinal = meanFinal/drawNum;
        //standard deviation
        //the final standard deviation is all the means subtracted from the Mu raised to the power of 2 then added together divided by the sample n.
        List<Double> std = new ArrayList<>();
        double stdFinal=0, stdNum=0;
        for (double x: mean) {
            double stdTemp = Math.pow((x-meanFinal),2);       //just keeps track of each standard deviation for each sample
            stdNum += Math.pow((x-meanFinal),2);
            std.add(stdTemp);
        }
        stdFinal = stdNum/drawNum;
        //System.out.println("Numerator of the Standard Deviations for each sample (summation): ");
        //for (double x: std) System.out.print("\n"+x);
        //print final results
        System.out.println("Mu (Mean): "+meanFinal);
        System.out.println("Sigma (Standard Deviation): "+stdFinal);
    }
}
