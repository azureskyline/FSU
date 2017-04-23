import java.util.Stack;
/**
 * The logic behind the Least Recently Used Page Replacement Algorithm is this:
 * Whenever a page is referenced, it is removed from the stack and put on the top.
 * In this way, the most recently used page is always at the top of the stack
 * and the least recently used page is always at the bottom.
 * Faults occur if replacing a page or if first time loading a page to a frame.
 * Created by Catherine Austria on 12/7/16.
 */
public class LRU extends ReplacementAlgorithm {
    public Stack<Integer> list = new Stack<>(); //stack being manipulated
    public LRU (int frameCount){
        super(0);
        pageFrameCount = frameCount;
    }
    @Override
    public void insert(int pageNumber) {
        if (list.size()!=pageFrameCount&&!list.contains(pageNumber)) { //frames is not full yet and page is not already in the frames
            list.push(pageNumber);
            pageFaultCount++; // fault because not yet in memory
        }
        else if (list.contains(pageNumber)&&list.size()<=pageFrameCount){ //page is already in frames
            list.remove(list.indexOf(pageNumber)); //remove that page from the stack at whatever index its on
            list.push(pageNumber); //then att it back to the top of the stack
        }
        else{ // frames are full; no duplicate page exists; lets handle that
            list.remove(list.firstElement()); //remove the last used element
            list.push(pageNumber); //push to stack top the recent page
            pageFaultCount++; //fault due to replacing

        }
        System.out.println("PageNumber: "+pageNumber+"\tList: "+list); //print results
    }
}
