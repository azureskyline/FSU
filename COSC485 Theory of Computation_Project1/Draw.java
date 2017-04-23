/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cosc485_project1;

import javax.swing.*;
import java.awt.*;
import java.applet.*;

/**
 *
 * @author Catherine Austria
 */
public class Draw extends JPanel{
    public static void drawCircle(Graphics g, int x, int y, int radius) {
    int diameter = radius * 2;
    //shift x and y by the radius of the circle in order to correctly center it
    g.fillOval(x - radius, y - radius, diameter, diameter);
    }
}
