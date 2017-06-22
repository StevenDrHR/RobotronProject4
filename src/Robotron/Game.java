package Robotron;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

//import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static javafx.application.Platform.exit;


public class Game extends Canvas implements Runnable{

    public static final long serialVersionUID = -23456;
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Random r;
    private Handler handler;
    private HUD hud;
    private KeyInput keyInput;
    private Player p;
    public BasicEnemy be;



    public Game(){
        handler = new Handler();

        keyInput = new KeyInput(handler);
        this.addKeyListener(keyInput);


        new window(WIDTH, HEIGHT,"Robotron", this);
        hud = new HUD();
        r = new Random();

        handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32, ID.Player, handler));
        handler.addObject(new BasicEnemy(500,550,ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(120,480,ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(420,80,ID.BasicEnemy, handler));
        handler.addObject(new HealingEnemy(300,300,ID.HealingEnemy,handler));
    }



    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;     //Thread on

    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false; //thread not on kill the thread
        }catch (Exception e) {
            e.printStackTrace();//tell us why it couldnt do it
        }
    }
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("fps = " + frames);
                frames = 0;
            }

        }
        stop();

    }
    private void tick(){
        handler.tick();
        hud.tick();

    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);
        handler.render(g);
        hud.render(g);
        g.dispose();
        bs.show();
    }
    public static int clamp(int var, int min, int max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public static void  main(String args[]){
        new Game();
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

    public void setKeyInput(KeyInput keyInput) {
        this.keyInput = keyInput;
    }
}