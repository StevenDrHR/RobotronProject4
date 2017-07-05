package Robotron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * BasicEnemy class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */
public class BasicEnemy extends GameObject {
    private Handler handler;
    private BufferedImage img;
    public int time = 0;
    public int timeY = 0;

    /**
     * instantiate a new instance of BasicEnemy
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     */
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 4;
        velY = 4;
        try {
            img = ImageIO.read(new FileInputStream("enemyrobot.png"));

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
        if (HUD.HEALTH > 0) {
            x += velX;
            y += velY;
            time++;
            timeY++;
            if (time > 200 + (Math.random() * (4000 - 200))) {
                velX *= -1;
                time = 0;
            }

            if (timeY > 200 + (Math.random() * (4000 - 200))) {
                velY *= -1;
                timeY = 0;
            }

            if (y <= 0 || y >= Game.HEIGHT - 80)
                velY *= -1;
            if (x <= 0 || x >= Game.WIDTH - 52)
                velX *= -1;
        } else {
        }
        collision();

    }

    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of Graphics originating from the Game class
     */
    public void render(Graphics g) {
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
