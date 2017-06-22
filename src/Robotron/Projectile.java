package Robotron;

import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * Created by Steven on 16-Jun-17.
 */
public class Projectile extends GameObject {
    private Handler handler;
    private float velX;
    private float velY;

    public Projectile(float x, float y, ID id, Handler handler, float velX, float velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= Game.HEIGHT)
            handler.removeObject(this);
        if (x <= 0 || x >= Game.WIDTH)
            handler.removeObject(this);
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tempObject.Health == 2) {
                        System.out.println("enemy has been damaged 3 times!");
                        handler.removeObject(tempObject);

                    } else {
                        tempObject.Health += 1;
                        System.out.println(" Enemy has been Damaged");
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
            }

                else if (tempObject.getId() == ID.SmartEnemy) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (tempObject.Health == 4) {
                            System.out.println("enemy has been damaged 3 times!");
                            handler.removeObject(tempObject);
                        }
                        else {
                            tempObject.Health += 1;
                            System.out.println(" Enemy has been Damaged");
                        }


                    }

                }
            }
        }
    }

