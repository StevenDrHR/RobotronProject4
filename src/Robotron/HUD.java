package Robotron;

import java.awt.*;

/**
 * HUD class which creates the overlay for the game and stores the player's healthand level
 */
public class HUD {

    public static int HEALTH = 100;

    public int level = 1;
    Spawn p;

    /**
     * Method to update the displayed health
     */
    public void tick() {
        HEALTH = (int) Game.clamp(HEALTH, 0, 100);


    }

    /**
     * Method to render the HUD
     *
     * @param g Given instance of Graphics upon which the HUD elements are rendered
     */
    public void render(Graphics g) {
        Rectangle player = new Rectangle(15, 15, 200, 32);
        g.setColor(Color.red);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(player.x, player.y, player.width, player.height);


        g.drawString("Level: " + level, 15, 86);
        g.drawString("Health: " + HEALTH, 15, 108);
        g.drawString("Score: " + p.scoreKeep, 15, 130);

    }

    /**
     * Method that returns the level the player is currently in
     *
     * @return Returns an integer representing the player's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Method that sets the player's current level
     *
     * @param level Integer which represents the player's current level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}

