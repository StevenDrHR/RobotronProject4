package Robotron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class BasicEnemy extends GameObject {
	private Handler handler;
	private BufferedImage img;
	public int time = 0;
	public int timeY = 0;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 4;
		velY = 4;
		try {
			// Grab the InputStream for the image.
			img = ImageIO.read(new FileInputStream("enemyrobot.png"));

		} catch (IOException e) {
			System.out.println("The image was not loaded.");

		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 52, 52);
	}

	public void tick() {
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

		collision();

	}

	public void render(Graphics g) {
		g.drawImage(img, (int)x,(int) y, 52, 52, null);
	}

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
