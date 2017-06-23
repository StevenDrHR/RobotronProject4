package Robotron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
/**
 * Created by Robin on 6/22/2017.
 */
public class MineEnemy extends GameObject {
    private Handler handler;
    private BufferedImage img;
    private Random r;
    public int time = 0;
    public int timeY = 0;
    public MineEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.Health = 0;
        velX = 0;
        velY = 0;
        try {
            // Grab the InputStream for the image.
            img = ImageIO.read(new FileInputStream("mine.png"));

        } catch (IOException e) {
            System.out.println("The image was not loaded.");

        }
    }
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 52, 52);
    }

    public void tick() {
        collision();


    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawImage(img,(int)x,(int)y,52,52,null);
    }
    private void collision(){
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
