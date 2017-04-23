/**
 * Created by cyaustria0 on 10/3/16. Catherine Austria
 * in firstLoop() i is not visible outside of its for loop
 * in secondLoop() i is visible because it has been declared outside of the for loop before the for executes
 */
public class program2java {
    public static void main(String[] args){
        firstLoop();
        System.out.println();
        secondLoop();
    }
    public static void firstLoop(){
        for (int i=0; i<5;i++) {
            System.out.println("firstLoop has executed this many times: "+i);
        }
    }
    public static void secondLoop(){
        int i;
        for (i=0; i<5;i++) System.out.println("secondLoop has executed this many times: "+i);
        System.out.println("This is i: "+i);
    }
}