package Robotron;

import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;

public class window extends Canvas {
    public static final long serialVersionUID = -12345;

    public window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);       //frame of our window
        frame.setPreferredSize(new Dimension(width,height));//setting our size
        frame.setMaximumSize(new Dimension(width,height));  //max size
        frame.setMinimumSize(new Dimension(width,height));  //min size

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make the exit button work
        frame.setResizable(false);              // dont resize our window
        frame.setLocationRelativeTo(null);      //start in the middle not top left
        frame.add(game);                        //add game class into frame
        frame.setVisible(true);                 //set the frame to be visible
        game.start();                           //call game start method
    }
}
