import java.io.*;
import java.util.*;
/**
 * Created by Catherine Austria on 3/7/17.
 */
public class Main {
    public static void main (String [] args){
        NumberRing numList = new NumberRing(); // declare our number ring
        String path = "/COSC489_PT_2168_Input.txt"; // relative path
        InputStream paths = path.getClass().getResourceAsStream(path);
        int temp = 0;
        Scanner scanner = new Scanner(paths);
        while (scanner.hasNextInt()){ //scan for ints
            temp = scanner.nextInt();
            numList.append(temp); // add int to number ring
            System.out.println(numList.print()); //print ring
        }
        numList.jump(numList.size()+1); // adjust to place the first number added as the first header
        System.out.println(numList.print()); // final result number ring

    }
}
