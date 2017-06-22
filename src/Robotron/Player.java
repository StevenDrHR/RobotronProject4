package Robotron;

import com.sun.javafx.scene.traversal.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

/**
 * Created by Steven on 23-4-2016.
 */
public class Player extends GameObject {
	Handler handler;
	private BufferedImage img;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		try {
			// Grab the InputStream for the image.
			img = ImageIO.read(new FileInputStream("robot.png"));

		} catch (IOException e) {
			System.out.println("The image was not loaded.");

		}

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		// -1 links facing 1 rechts facing
		// -1 onder 1 boven
		if (velX < 0 && velY < 0) {
			GoingUP = -1;
			facing = -1;
		} // linksonder
		else if (velX > 0 && velY < 0) {
			GoingUP = -1;
			facing = 1;
		} // rechtsonder
		else if (velX < 0 && velY > 0) {
			GoingUP = 1;
			facing = -1;
		} // linksboven
		else if (velX > 0 && velY > 0) {
			GoingUP = 1;
			facing = 1;
		} // rechtsboven
		else if (velX == 0 && velY > 0) {
			GoingUP = 1;
			facing = 0;
		} // boven
		else if (velX == 0 && velY < 0) {
			GoingUP = -1;
			facing = 0;
		} // onder
		else if (velX < 0 && velY == 0) {
			GoingUP = 0;
			facing = -1;
		} // links
		else if (velX > 0 && velY == 0) {
			GoingUP = 0;
			facing = 1;
		} // rechts

		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);

		collision();

	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.HealingEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			// else if (tempObject.getId() == ID.BasicHealth) {
			// if (getBounds().intersects(tempObject.getBounds())) {
			// HUD.HEALTH += 2;
			// handler.removeObject(tempObject);
			// }
			// }
			// else if (tempObject.getId() == ID.FastEnemy) {
			// if (getBounds().intersects(tempObject.getBounds())) {
			// HUD.HEALTH -= 3;
			// }
			// }

		}
	}// collision codeollision code

	@Override
	public void render(Graphics g) {
		if (id == ID.Player)
			;
		g.drawImage(img, x, y, 32, 32, null);

	}

}
