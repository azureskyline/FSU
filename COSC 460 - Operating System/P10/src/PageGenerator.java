import java.util.Random;
/**
 * This class generates the random page strings for use of the FIFO and LRU algorithms.
 * Created by Catherine Austria on 12/7/16.
 */
public class PageGenerator {
    private int pageNumSize = -1;   // number of pages to generate
    public PageGenerator(int length){
        pageNumSize = length;
    }
    /**
     * Generates the pages / strings for the page replacement algorithms.
     * @return
     */
    public int[] getReferenceString(){
        int[] strings = new int[pageNumSize];
        Random gen = new Random();
        for (int i = 0; i<pageNumSize; i++) strings[i] = gen.nextInt(10); //generate number from 0 - 9
        return strings;
    }
}
