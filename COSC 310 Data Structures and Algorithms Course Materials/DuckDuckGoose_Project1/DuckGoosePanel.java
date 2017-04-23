/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AustriaCatherine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
/**
 * @author Catherine Austria
 */
class DuckGoosePanel extends JPanel{
    private int squareX = 60;
    private int squareY = 90;
    private int squareW = 40;
    private int squareH = 25;
    private DuckGooseGame myGame = new DuckGooseGame();
    //constructer
    public DuckGoosePanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                moveSquare(e.getX(),e.getY());
            }
        });
        addMouseMotionListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                moveSquare(e.getX(),e.getY());
            }
        });
    }
    private void moveSquare(int x, int y){
        int OFFSET=1;
        if((squareX!=x)||(squareY!=y)){
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        }
    }
    //preferred size for the window that will bu shown
    public Dimension getPreferredSize(){
        return new Dimension(600,250);
    }
    //paints the necessary images or text
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw some text
        g.setColor(Color.RED);
        g.drawString("Last It: ", 10, 150);
        g.setColor(Color.BLUE);
        g.drawString("This is player number: "+myGame.playerSize, 10, 20);
        g.drawString("These are the rounds: "+myGame.rounds, 10, 40);
        g.drawString("This kid was the first 'It' when game started: "+myGame.firstIT, 10, 60);
        //draw kids
        g.drawString("Players: Cam, Feli, Dom, Matt, Kim", 10, 80);
        g.drawString("\t"+myGame.it,squareX+20, 150);
        g.drawString("Current list contents: "+myGame.players(), 10, 120);
    }
}
