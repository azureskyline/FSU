/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AustriaCatherine;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *  This is the main class that executes all other classes. It creates a GUI for the program to be shown in.
 * @author Catherine Austria
 */
public class main {
    private static DuckGooseGame myGame = new DuckGooseGame();
    public static JFrame f = new JFrame("Cat's Duckx2 Goose");
    public static void main(String[] args) {
        //for drawing
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });
        myGame.playGame(f);
    }
    //the method creates the GUI
    private static void createAndShowGUI(){
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 1000);
        f.add(new DuckGoosePanel());
        f.pack();
        f.setVisible(true);
    }
}
