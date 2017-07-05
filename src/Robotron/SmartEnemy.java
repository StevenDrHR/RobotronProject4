package Robotron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * SmartEnemy class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */


public class SmartEnemy extends GameObject {

    private Handler handler;
    private BufferedImage img2;
    public int time = 0;
    public int timeY = 0;
    private GameObject player;

    /**
     * Instantiate a new instance of projectile.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     */
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.Health = 0;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
        }

        try {
            img2 = ImageIO.read(new FileInputStream("Smart_enemy.png"));

        } catch (IOException e) {
            System.out.println("The image was not loaded.");

        }
    }

    /**
     * Method which updates all the data of the class
     */
    public void tick() {
        if (HUD.HEALTH > 0) {
            x += velX;
            y += velY;

            float diffX = x - player.getX() - 8;
            float diffY = y - player.getY() - 8;
            float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

            velX = ((-1.0 / distance) * diffX);
            velY = ((-1.0 / distance) * diffY);

            time++;
            timeY++;

            if (y <= 0 || y >= Game.HEIGHT - 100) velY *= -1;
            if (x <= 0 || x >= Game.WIDTH - 60) velX *= -1;
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
        g.setColor(Color.red);
        g.drawImage(img2, (int) x, (int) y, 30, 30, null);
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
