package Robotron;

import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;

/**
 * window class which extends Canvas and provides the window in which the game is rendered
 */
public class window extends Canvas {
    public static final long serialVersionUID = -12345;

    /**
     * Method which instantiates the Window
     *
     * @param width  Width of the window
     * @param height Height of the window
     * @param title  Title of the window
     * @param game   Instance of game which renders within the window
     */
    public window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
