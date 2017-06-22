package Robotron;

import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * Created by Steven on 16-Jun-17.
 */
public class Projectile extends GameObject {
    private Handler handler;
    private int velX;
    private int velY;

    public Projectile(int x, int y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
    }
    public Rectangle getBounds() {return new Rectangle(x, y, 16, 16);
    }
    public void tick() {
        x += velX;
        y += velY;
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x,y,16,16);
    }
    private void collision(){
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tempObject.Health == 2) {
                        System.out.println("enemy has been damaged 3 times!");
                        handler.removeObject(tempObject);

                    }else{
                        tempObject.Health +=1;
                        System.out.println(" Enemy has been Damaged");
                    }

                }
            }

        }

    }
}


