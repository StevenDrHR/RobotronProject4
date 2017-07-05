package Robotron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * MineEnemy class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */

public class MineEnemy extends GameObject {
    private Handler handler;
    private BufferedImage img;

    /**
     * Instantiate a new instance of MineEnemy.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     */
    public MineEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.Health = 0;
        velX = 0;
        velY = 0;
        try {
            img = ImageIO.read(new FileInputStream("mine.png"));

        } catch (IOException e) {
            System.out.println("The image was not loaded.");

        }
    }

    /**
     * Creates a hitbox that allows the program to check when it gets touched by other objects
     *
     * @return Returns a Rectangle at the position of the object that's also the size of the object
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 52, 52);
    }

    /**
     * Method which updates all the data of the class
     */
    public void tick() {
        collision();


    }

    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of Graphics originating from the Game class
     */
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawImage(img, (int) x, (int) y, 52, 52, null);
    }

    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Projectile) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);

                }


            }

        }


    }
}
