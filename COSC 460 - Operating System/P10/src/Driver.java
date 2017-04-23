/**
 * The driver class and ushers the process of determining how the randomly generated pages will be managed by two
 * page replacement algorithms called FIFO and LRU. The pages are represented by strings that will be manipulated in this program to simulate the paging.
 * The result showed on the console displace the stack or list of the pages
 * arranged into the specified frame size and how they are managed every step of the way. The page faults for the
 * algorithm is displayed at the end of iteration over the generated pages.
 * Created by Catherine Austria on 12/7/16.
 */
public class Driver {
    public static int[] pageStrings;    //the pages generated randomly for use for the FIFO and LRU algorithms.
    public static void main (String[] args){
        int lenPage, frameNum;
        lenPage = Integer.parseInt(args[0]); //args[0] is the length of the page references
        frameNum = Integer.parseInt(args[1]); //args[1] is the # of frames
        randomCase(lenPage, frameNum);  //execute both FIFO and LRU
        //testCase(3); //test case
    }
    /**
     * This randomly generates numbers as inpur for the algorithms.
     * @param lenPage   Number of pages to be generated.
     * @param frameNum  Number of frames.
     */
    public static void randomCase(int lenPage, int frameNum){
        PageGenerator refStrings = new PageGenerator(lenPage);
        pageStrings = refStrings.getReferenceString(); //create random generation of page references
        for (int x: pageStrings) System.out.print(x+"\t"); //print generated strings
        //FIFO
        System.out.println("\nFIFO:");
        FIFO first = new FIFO(frameNum);
        System.out.println();
        for (int x=0; x<pageStrings.length; x++) first.insert(pageStrings[x]);
        System.out.println("\nFIFO Final Fault Count: "+first.getPageFaultCount());
        //LRU
        System.out.println("\nLRU:");
        LRU second = new LRU(frameNum);
        for (int x=0; x<pageStrings.length; x++) second.insert(pageStrings[x]);
        System.out.println("\nLRU Final Fault Count: "+second.getPageFaultCount()+"\n");
    }
    /**
     * This is to check if the algorithms are working right. Test cases are taken from the book.
     * @param frameNum  The number of frames.
     */
    public static void testCase(int frameNum){
        pageStrings = new int[]{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 7, 1}; //test only
        for (int x: pageStrings) System.out.print(x+"\t"); //print generated strings
        //FIFO
        System.out.println("\nFIFO:");
        FIFO first = new FIFO(frameNum);
        System.out.println();
        for (int x=0; x<pageStrings.length; x++) first.insert(pageStrings[x]);
        System.out.println("\nFIFO Final Fault Count: "+first.getPageFaultCount()); //should = 15
        //LRU
        System.out.println("\nLRU:");
        LRU second = new LRU(frameNum);
        for (int x=0; x<pageStrings.length; x++) second.insert(pageStrings[x]);
        System.out.println("\nLRU Final Fault Count: "+second.getPageFaultCount());
    }
}
