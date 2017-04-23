/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AustriaCatherine;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Catherine Austria
 */
public class DuckGooseGame{
    public int rounds=2;// number of rounds
    private final Random rand = new Random();
    public static final CircularlyLinkedList C = new CircularlyLinkedList();//list that holds players
    public static int playerSize; // number of players
    private int rotateCounter=0;//counter for tallying rotation
    public boolean willItWin=false; // holds whether or not the It will get the seat
    public static String[] names = {"Feli","Dom","Matt","Kim"};//stringlist of players
    public static Object it;// the it player
    public static Object goose;// the goose player
    public static Object firstIT; // the first IT ever
    
    public void setGame(){
        rand.setSeed(System.currentTimeMillis()); // current time is the seed
        Kids newKid = new Kids();
        
        //set the first It kid
        newKid.setKid("Cam", "It");
        C.addLast(newKid.getName()); //add player to CircularlyLinkList
        playerSize++;
        firstIT=C.first();
        
        //set the players
        for (String name : names) {
            newKid.setKid(name, "Player");
            C.addLast(newKid.getName()); //add players to CircularlyLinkList
            playerSize++;
        }
        System.out.println("Player Size: "+playerSize);//delete
        System.out.print("Players: "+C);//print players text game only
    }//end of setGame
    
    //controls gameplay
    public void playGame(JFrame f) {
        setGame();
        for (int i = 0; i < rounds; i++) {//simulates game rounds
            if(!willItWin){//helps control who will be it
                try{
                    chooseIt();//choose the it
                    chooseGoose(f);//choose the goose
                    chaseGoose(f);// chase that goose
                    winSet(f);//set round win conditions
                }catch(InterruptedException e){
                    System.out.println("The kids are going nuts. Check gameplay related methods.");
                }
            }// control 'it' end
        }//end of rounds
        System.out.println("Game Done. Snack Time!");
    }//end of playGame
    
    // choosing the it kid
    public void chooseIt() throws InterruptedException{
        System.out.println("Choosing It...");
        it = C.removeFirst(); // take out the it as the first of the players list
        Thread.sleep(800);//slow down the process for viewers to see
        System.out.println(it+" is the it");//text game only
        System.out.println("List no It: "+C);//delete
    }
    // choosing goose kid
    public void chooseGoose(JFrame f) throws InterruptedException{
        //this while loop similates the goose choosing
        System.out.println("Choosing Goose...");
        while(rand.nextBoolean()||rand.nextBoolean()){// randomly rotates list till true is returned
            C.rotate();
            System.out.print("Rotating Process: "+C);
            Thread.sleep(800);//slow down the process for viewers to see
            f.repaint();
        }
        goose = C.removeFirst();// pick goose who is currently first element of the list
        System.out.println("\n"+goose+" is the goose");//= text-based game only
        System.out.print("Goose State: "+C);
    }
    //it chasing the goose
    public void chaseGoose(JFrame f) throws InterruptedException{
    //this loop simulates the chasing part
        System.out.println("Chasing Goose...");
        for(int k=rand.nextInt(C.size())+1;k>0;k--){//set k as random 1-'Number of Players' rotates only
            C.rotate();
            rotateCounter++;
            f.repaint();//redraws the list
            Thread.sleep(800);//slow down the process for viewers to see
            System.out.print("Rotating Process: "+C);
            if(rand.nextBoolean()){ //if true then It gets the seat and wins
                willItWin=true;
            }
        }
    }
    //rotates the players to goose state
    public void rotateBack(){
        if(rotateCounter<=C.size()){//if rotateCounter is <= to number of non-It players
            for (int j=C.size()-rotateCounter;j>0;j--){C.rotate();} //set rotation before goose play
        }else{//if if rotateCounter is > to number of non-It players; 2 secarios
            if((rotateCounter%2)==0){//rotateCounter is even
                for (int j=rotateCounter;j>0;j--){C.rotate();}
            }else{//rotateCounter is odd
                for (int j=rotateCounter+2;j>0;j--){C.rotate();}
            }
        }
        rotateCounter=0;//reset the counter
    }
    //this sets round win or lose conditions for next round
    public void winSet(JFrame f) throws InterruptedException{
        if(willItWin){//if It wins
                System.out.println("It Won!: "+it);
                rotateBack();
                C.addLast(it); // winner (it) goes to the back of the group
                C.addFirst(goose); // loser(goose) gets to be picked the it next round
                System.out.println("List with last It: "+C+"\n");//delete
                Thread.sleep(1000);//slow down the process for viewers to see
                f.repaint();
                willItWin=false;//set win back to false
            }else{//It does not win
                System.out.println("It Did not Win: "+it);//= text-based game only
                rotateBack();
                C.addFirst(goose); // goose wins and goes back to first part of list
                C.addFirst(it); // it loses so it goes to first part of list to be chosen again for next round
                System.out.println("List with It: "+C+"\n");//= text-based game only
                Thread.sleep(1000);//slow down the process for viewers to see
                f.repaint();
                }//end of if else win
    }
    //returns string version of list of players on panel
    public static String players(){
        String kids=C.first()+"\t\t\t\t\t\t\t\t\t";//spaces between player names
        Object veryFirst=C.first();
        C.rotate();
        for(int i = playerSize;i>0;i++){
            if(veryFirst!=C.first()){
                kids+=C.first()+"\t\t\t\t\t\t\t\t\t";
                C.rotate();
            }
        }
        return kids;
    }
}
