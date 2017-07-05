package Robotron;

import java.awt.*;

/**
 * Abstract class which is used to create all objects within the Game
 */
public abstract class GameObject {
    protected float x, y;
    protected ID id;
    protected double velX, velY;
    protected float facing;
    protected float GoingUP;
    protected float Health = 0;

    /**
     * Method to create an instance of GameObject
     *
     * @param x  X coordinate of the object
     * @param y  Y coordinate of the object
     * @param id Type of GameObject
     */
    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /**
     * Abstract method to update the GameObjects data
     */
    public abstract void tick();

    /**
     * Abstract method to render the object on the given instance of Graphics
     *
     * @param g Instance of Graphics
     */
    public abstract void render(Graphics g);

    /**
     * Abstract method that creates and allows the program to update the objects hitbox
     *
     * @return Returns and instance of Rectangle of the Objects size and at it's x,y coordinates
     */
    public abstract Rectangle getBounds();

    /**
     * Allows the program to change an Objects type
     *
     * @param id Type of GameObject you want the object to change to
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
     * Allows the program to see what type of GameObject it is
     *
     * @return Returns the type of GameObject
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the horizontal velocity of the Objects
     *
     * @param velX Horizontal velocity the Object is set to
     */
    public void setVelX(double velX) {
        this.velX = velX;
    }

    /**
     * Sets the vertical velocity of the Objects
     *
     * @param velY Vertical velocity the Object is set to
     */
    public void setVelY(double velY) {
        this.velY = velY;
    }

    /**
     * Method to get the objects x coordinate
     *
     * @return Return the instances x coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Method to get the objects y coordinate
     *
     * @return Return the instances y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Method to get the objects horizontal velocity
     *
     * @return Return the horizontal velocity
     */
    public double getVelX() {
        return velX;
    }

    /**
     * Method to get the objects vertical velocity
     *
     * @return Return the vertical velocity
     */
    public double getVelY() {
        return velY;
    }

    /**
     * Method to see which horizontal direction the object is facing
     *
     * @return Returns a float which tells the direction the object is facing
     */
    public float getFacing() {
        return facing;
    }

    /**
     * Method to see which vertical direction the object is facing
     *
     * @return Returns a float which tells the direction the object is facing
     */
    public float getGoingUP() {
        return GoingUP;
    }
}