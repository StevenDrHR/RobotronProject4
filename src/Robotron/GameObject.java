package Robotron;

import javafx.scene.shape.Circle;

import java.awt.*;


public abstract class GameObject {
    protected float x,y;  //only inheritable who inherits GameObject
    protected ID id;
    protected double velX, velY;
    protected float facing;
    protected float GoingUP;
    protected  float Health = 0;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setId(ID id){
        this.id = id;
    }
    public ID getId(){
        return id;
    }
    public void setVelX(double velX){
        this.velX = velX;
    }
    public void setVelY (double velY){
        this.velY = velY;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public double getVelX(){
        return velX;
    }
    public double getVelY(){
        return velY;
    }
    public float getFacing(){return facing;}
    public float getGoingUP(){return GoingUP;}
}