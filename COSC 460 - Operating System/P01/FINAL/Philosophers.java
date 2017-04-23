import java.util.Random;


/**
 * Created by Catherine Austria on 10/24/16 for COSC 460 - Operating Systems under Steve K.
 */
public class Philosophers implements Runnable{
    private Server myTable;     //The server the philosopher is sitting at.
    private int PhilosopherID;  //The place number or philosopher identification.
    public State state;        //What the philosopher is currently doing.
    /**
     * Constructor that takes the server and ID assigned to this Philosopher
     * @param table     The server the Philosopher is sitting at.
     * @param ID        The Philosopher's unique identification.
     */
    public Philosophers (Server table, int ID){
        myTable=table;
        PhilosopherID=ID;
    }
    /**
     * Forever runs alternating between thinking, hungry and eating
     */
    public void run(){
        while (true){
            //think > hungry > eat > repeat
            state=State.THINKING;
            waitPhil();
            state=State.HUNGRY;
            myTable.takeChopsticks(PhilosopherID);
            /*above: hungry and waits to eat if needed. There is already a waiting mechanism in the
            server to eat so no need to call wailPhil() before chopstick return.*/
            myTable.returnChopsticks(PhilosopherID);//after eating return chopstick
        }
    }
    /**
     * Generates random number between the min and max value seconds before a Philosopher action; random time
     */
    public void waitPhil() {
        try{
            Random wait = new Random();
            int min = 1000, max = 3000; //1 to 3 secs
            int waitNum = wait.nextInt((max - min) + 1) + min;
            Thread.sleep(waitNum);
        }catch (InterruptedException e){}
    }
}
