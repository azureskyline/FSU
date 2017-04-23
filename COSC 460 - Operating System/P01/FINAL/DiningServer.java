/**
 * This was in the requirements to have so yeah...the "DiningServer interface" that the Server / Monitor inherits from.
 * Created by Catherine Austria on 10/24/16 for COSC 460 - Operating Systems under Steve K.
 */
public interface DiningServer {
    /* called by philosopher that wishes to eat*/
    void takeChopsticks(int philNumber);
    /* called by philosopher when it has finished eating */
    void returnChopsticks(int philNumber);
}
