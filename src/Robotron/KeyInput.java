package Robotron;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyInput class which extends KeyAdapter and allows the program to check which key's are being pressed
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private int moveUp = 8;
    private int moveDown = 8;
    private int moveLeft = 5;
    private int moveRight = 5;

    /**
     * Method which instantiates an instance of KeyInput
     *
     * @param handler Instance of the Handler class which is used in combination with key input to move the instance of Player
     */
    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    /**
     * Method which takes a KeyEvent, checks which KeyType it is and executes accordingly
     *
     * @param e Instance of KeyEvent
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) tempObject.setVelY(-moveUp);
                if (key == KeyEvent.VK_A) tempObject.setVelX(-moveLeft);
                if (key == KeyEvent.VK_D) tempObject.setVelX(+moveRight);
                if (key == KeyEvent.VK_S) tempObject.setVelY(+moveDown);
                if (key == KeyEvent.VK_SPACE) {
                    handler.addObject(new Projectile(tempObject.getX(), tempObject.getY(), ID.Projectile, handler, tempObject.getFacing() * 15, tempObject.getGoingUP() * 15));
                }
            }

        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);

    }

    /**
     * Method which checks whether a key is released and acts accordingly
     *
     * @param e Instance of KeyEvent
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) tempObject.setVelY(0);
                if (key == KeyEvent.VK_A) tempObject.setVelX(0);
                if (key == KeyEvent.VK_D) tempObject.setVelX(0);
                if (key == KeyEvent.VK_S) tempObject.setVelY(0);

            }
        }
    }
}