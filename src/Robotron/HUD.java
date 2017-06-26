package Robotron;

import java.awt.*;

import static java.awt.Color.WHITE;


public class HUD {

    public static int HEALTH = 100;

    private int score = 0;
    public int level = 1;
    Spawn p;

    public void tick(){
        HEALTH = (int) Game.clamp(HEALTH,0,100);


    }

    public void render(Graphics g) {
        Rectangle player = new Rectangle(15,15,200,32);
        g.setColor(Color.red);
        g.fillRect(15,15,200,32);
        g.setColor(Color.green);
        g.fillRect(15,15,HEALTH * 2,32);
        g.setColor(Color.white);
        g.drawRect(player.x,player.y,player.width,player.height);


        g.drawString("Level: " + level,15,86);
        g.drawString("Health: " + HEALTH, 15, 108);
        g.drawString("Score: " + p.scoreKeep,15,130);

    }

    public void score(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }

    public int getLevel()
    {

        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}

