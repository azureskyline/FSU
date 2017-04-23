import java.util.ArrayList;

/**
 * This driver is to initialize the "Philosophers" as threads and run them indefinitely.
 * This program loops forever. Based on the monitor solution for the dining philosophers problem.
 * Created by Catherine Austria on 10/24/16 for COSC 460 - Operating Systems under Steve K.
 */
public class Driver {
    public static void main (String[] args){
        Server diningTable = new Server();                      //make the table the philosophers will eat at; aka your Monitor
        Philosophers zero = new Philosophers(diningTable, 0);   //philosopher 1
        Philosophers one = new Philosophers(diningTable, 1);    //philosopher 2
        Philosophers two = new Philosophers(diningTable, 2);    //philosopher 3
        Philosophers three = new Philosophers(diningTable, 3);  //philosopher 4
        Philosophers four = new Philosophers(diningTable, 4);   //philosopher 5
        ArrayList<Philosophers> list = new ArrayList<>();       //the philosophers came in as a group for a party
        list.add(zero);
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        diningTable.initialize(list);                           //seat the philosophers
        Thread a = new Thread(zero);                            //establish each of their food orders
        Thread b = new Thread(one);
        Thread c = new Thread(two);
        Thread y = new Thread(three);
        Thread z = new Thread(four);
        a.start();                                              //start cooking their orders as they small talk, or philosophically argue.
        b.start();
        c.start();
        y.start();
        z.start();
        try{
            while (true){                                       //service the food
                a.join();
                b.join();
                c.join();
                y.join();
                z.join();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
