package Robotron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Steven on 05-Jul-17.
 */
public class EndBoss extends GameObject {
    private Handler handler;
    private BufferedImage img;


    public EndBoss(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = 5;
        try {
            // Grab the InputStream for the image.
            img = ImageIO.read(new FileInputStream("daveeindbaas.png"));

        } catch (IOException e) {
            System.out.println("deef image was not loaded.");

        }
    }
    @Override
    public void tick() {
        y += velY;
        if (y <= 0 || y >= Game.HEIGHT - 180)
            velY *= -1;
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)x,(int) y, 140, 180, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 140, 180);
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
