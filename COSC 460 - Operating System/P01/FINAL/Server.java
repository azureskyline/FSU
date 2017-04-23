import java.util.ArrayList;
import java.util.concurrent.locks.*;
/**
 * This class acts as a Monitor to ensure every Philosopher gets to eat.
 * Algorithm based on the book Operating System Concepts Essentials Section 5.8.2
 * Created by Catherine Austria on 10/24/16 for COSC 460 - Operating Systems under Steve K.
 */
public class Server implements DiningServer{
    ArrayList<Philosophers> philosopher = new ArrayList<>();    //the philosophers
    public Lock lock = new ReentrantLock();                     //lock mechanism to monitor the philosophers
    private Condition eat = lock.newCondition();                //the condition needed to allow the philosopher to eat
    public void initialize(ArrayList<Philosophers> theEnlightened){
        philosopher=theEnlightened;
    }
    /**
     * Signals that the philosopher is HUNGRY and wants to eat. IF another philosopher has the chopsticks,
     * then the Philosopher[philNUM] will have to wait till signaled in test()
     * @param philNum   The ID number of the philosopher.
     */
    public void takeChopsticks(int philNum){
        lock.lock();
        try {
            philosopher.get(philNum).state=State.HUNGRY;
            System.out.println("Philosopher "+philNum+" is HUNGRY");
            test(philNum);
            if (philosopher.get(philNum).state != State.EATING) eat.await(); //if the philosopher was not allowed to eat, wait
        }catch (InterruptedException e){}
        finally {
            lock.unlock();
        }
    }
    /**
     * After eating the philosopher must return the chopstick.
     * The state of the philosopher returning the chopstick is then set back to THINKING
     * @param philNum The ID number of the philosopher.
     */
    public void returnChopsticks(int philNum){
        philosopher.get(philNum).state=State.THINKING;
        System.out.println("Philosopher "+philNum+" is THINKING");
        test((philNum+4)%5);
        test((philNum+1)%5);
        System.out.println();
    }
    /**
     * This tests if the Philosopher [philNum] can eat or not.
     * IF so then that Philosopher thread is signaled to eat.
     * @param philNum The ID number of the philosopher.
     */
    public void test(int philNum){
        //condition that there is no one else eating, then may the philosopher eat
        if((philosopher.get((philNum+4)%5).state !=State.EATING)&&(philosopher.get(philNum).state==State.HUNGRY)&&(philosopher.get((philNum+1)%5).state !=State.EATING)){
            philosopher.get(philNum).state=State.EATING; //let philosopher eat
            System.out.println("Philosopher "+philNum+" is EATING");
            eat.signal(); //signal
        }
    }
}
