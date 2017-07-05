package Robotron;


import java.awt.*;

/**
 * Projectile class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */
public class Projectile extends GameObject {
    private Handler handler;
    private float velX;
    private float velY;
    Spawn p;

    /**
     * Instantiate a new instance of projectile.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     * @param velX    Horizontal velocity of the class
     * @param velY    Vertical velocity of the class
     */
    public Projectile(float x, float y, ID id, Handler handler, float velX, float velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
    }

    /**
     * Creates a hitbox that allows the program to check when it gets touched by other objects
     *
     * @return Returns a Rectangle at the position of the object that's also the size of the object
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    /**
     * Method which updates all the data of the class
     */
    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= Game.HEIGHT)
            handler.removeObject(this);
        if (x <= 0 || x >= Game.WIDTH)
            handler.removeObject(this);
        collision();
    }

    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of Graphics originating from the Game class
     */
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tempObject.Health == 2) {
                        System.out.println("enemy has been damaged 3 times!");
                        handler.removeObject(tempObject);
                        p.scoreKeep += 50;
                        System.out.println("ScoreKeep" + p.scoreKeep);


                    } else {
                        tempObject.Health += 1;
                        System.out.println(" Enemy has been Damaged");
                    }

                }
            }
            if (tempObject.getId() == ID.MineEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tempObject.Health == 0) {
                        System.out.println("Mine has been destroyed!");
                        handler.removeObject(tempObject);
                        p.scoreKeep += 10;

                    } else {
                        tempObject.Health = 0;
                        System.out.println("Not possible...");
                    }

                }
            } else if (tempObject.getId() == ID.HealingEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tempObject.Health == 0) {
                        System.out.println("Healingenemy has been killed!");
                        handler.removeObject(tempObject);
                        HUD.HEALTH += 10;
                    }

                }
            } else if (tempObject.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tempObject.Health == 4) {
                        System.out.println("enemy has been damaged 4 times!");
                        handler.removeObject(tempObject);
                        p.scoreKeep += 60;
                        System.out.println("Scorekeep" + p.scoreKeep);
                    } else {
                        tempObject.Health += 1;
                        System.out.println(" Enemy has been Damaged");
                    }


                }

            }
        }
    }
}

