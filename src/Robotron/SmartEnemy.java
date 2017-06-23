package Robotron;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Karan on 22-6-2017.
 */

// hello
public class SmartEnemy extends GameObject
{

    private Handler handler;
    private BufferedImage img2;
    private Random r;
    public int time = 0;
    public int timeY = 0;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        this.Health = 0;

        for( int i=0;i< handler.object.size();i++ )
        {
            if(handler.object.get(i).getId()== ID.Player) player =handler.object.get(i);
        }
        //velX = 6;
        //velY = 6;

        try {
            // Grab the InputStream for the image.
            img2 = ImageIO.read(new FileInputStream("Smart_enemy.png"));

        } catch (IOException e) {
            System.out.println("The image was not loaded.");

        }
    }

    public void tick()
    { if (HUD.HEALTH > 0){ //checks if the player is alive
        x+= velX;
        y+= velY;

        float diffX = x - player.getX()- 8;
        float diffY = y -player.getY() -8;
        float distance = (float) Math.sqrt((x -player.getX()) *(x -player.getX())+(y - player.getY()) * (y - player.getY()));

        velX =  ((-1.0/ distance) * diffX);
        velY =  ((-1.0/ distance) * diffY);

        time++;
        timeY++;

       // if(time > 200 + (Math.random() * (4000 - 200))){
        //    velX *= -1;
        //    time = 0;}

        //if(timeY > 200 + (Math.random() * (4000 - 200))){
          //  velY *= -1;
            //timeY = 0;}


        if (y<=0 || y >= Game.HEIGHT - 100) velY *= -1;
        if (x<=0 || x >= Game.WIDTH - 60) velX *= -1;}
        else{} //if the player is dead do nothing

        collision();



    }


    public void render(Graphics g)
    {
        g.setColor(Color.red);
        g.drawImage(img2,(int)x,(int)y,30,30,null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 52, 52);
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
