package Robotron;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.*;
import Robotron.Game.State;
/**
 * Player class which extends GameObject and has all the additional implementation
 * for the methods that are needed to have it interact with other GameObjects.
 */
public class Player extends GameObject {
	Handler handler;
	Game game;
	private BufferedImage img;

    /**
     * Instantiate a new instance of player.
     *
     * @param x       X coordinate of the class
     * @param y       Y coordinate of the class
     * @param id      Type of GameObject
     * @param handler Instance of Handler class which loops through all GameObjects
     * @param game    Instance of Game class used to change the GameState if the player dies
     */
    public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		try {
			img = ImageIO.read(new FileInputStream("robot.png"));

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
		return new Rectangle((int)x, (int)y, 32, 32);
	}
    /**
     * Method which updates all the data of the class
     */
	@Override
	public void tick() {
		if (HUD.HEALTH > 0) {
            x += velX;
            y += velY;
            if (velX < 0 && velY < 0) {
                GoingUP = -1;
                facing = -1;
            }
            else if (velX > 0 && velY < 0) {
                GoingUP = -1;
                facing = 1;
            }
            else if (velX < 0 && velY > 0) {
                GoingUP = 1;
                facing = -1;
            }
            else if (velX > 0 && velY > 0) {
                GoingUP = 1;
                facing = 1;
            }
            else if (velX == 0 && velY > 0) {
                GoingUP = 1;
                facing = 0;
            }
            else if (velX == 0 && velY < 0) {
                GoingUP = -1;
                facing = 0;
            }
            else if (velX < 0 && velY == 0) {
                GoingUP = 0;
                facing = -1;
            }
            else if (velX > 0 && velY == 0) {
                GoingUP = 0;
                facing = 1;
            }

            x = Game.clamp(x, 0, Game.WIDTH - 38);
            y = Game.clamp(y, 0, Game.HEIGHT - 60);
        }
        else {
               GoingUP = 1;
               facing = 0;
               velY = 50;
               y += velY;
		       if (y >= 2050){
                    game.GameState = State.End;
                    handler.clearHandler();
                    HUD.HEALTH = 100;
		        } }
		collision();

	}
    /**
     * Uses the getBounds method to check if the object is being touched by other GameObjects
     */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.HealingEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;

                }
            }
            if (tempObject.getId() == ID.MineEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 100;
                }
            }
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.HealingEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			if (tempObject.getId() == ID.SmartEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 4;
				}
			}


		}
	}
    /**
     * Draws the image of the class on the given instance of Graphics
     *
     * @param g Instance of Graphics originating from the Game class
     */
	@Override
	public void render(Graphics g) {
		if (id == ID.Player)
			;
		g.drawImage(img, (int)x, (int)y, 32, 32, null);

	}

}
