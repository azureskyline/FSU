import java.util.*;

/**
 * The First In First Out Page Replacement Algortihm's logic is as follows:
 * When a page must be replaced, the oldest page is chosen to be replaced.
 * Check for page fault, if occurance already in memory then fault.
 * If it is the first time loading a page into memory and
 * there is no pages yet to take that fram then fault as well.
 * Created by Catherine Austria on 12/8/16.
 */
public class FIFO extends ReplacementAlgorithm{
    LinkedList<Integer> list; //the queue we will be manipulating; contains the frames
    private List<Integer> memory = new ArrayList<>(); //used to keep track of faults
    public FIFO (int frameCount){
        super(0);
        pageFrameCount = frameCount;
        list = new LinkedList<>();
    }
    @Override
    public void insert(int pageNumber) {
        /*Logic:

         */
        if (memory.contains(pageNumber)){ // so number known to be in memory
            pageFaultCount++; //fault
        }else memory.add(pageNumber); // if not take note and add to memory

        if (list.size() < pageFrameCount&&!list.contains(pageNumber)){ //list not full and number not in memory
            list.addLast(pageNumber); //enqueue
            pageFaultCount++; // fault because not yet in memory
        }else {
            if (pageNumber==list.getFirst()){ //means the page is equivalent to what is the next page to be replaced in memory
                //do nothing so that we replace to spot on the next page iteration
            }else if (list.contains(pageNumber)){
                //do nothing if duplicate
            }
            else {
                list.removeFirst(); //dequeue
                list.addLast(pageNumber); //enqueue pagenumber
            }
        }
        System.out.println("PageNumber: "+pageNumber+"\tList: "+list); //print results
    }
}
