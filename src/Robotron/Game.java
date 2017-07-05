package Robotron;

import Robotron.mediaplayer.*;
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
    private static Game _instance = null;

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
    public SmartEnemy sm;
    private Spawn spawn;
    // helloen l
    private Menu menu;
    //private AudioPlayer audioPlayer;
    // Allows the program to differentiate in where it is
    public enum State {
        Menu, Help, Game,End,
    };

    public State GameState = State.Menu;

    private Game(){
        hud = new HUD();
        handler = new Handler();
        menu = new Menu(this, handler, hud);
        this.addMouseListener(menu);

        keyInput = new KeyInput(handler);
        this.addKeyListener(keyInput);

        spawn = new Spawn(handler,hud,this);
        new window(WIDTH, HEIGHT,"Robotron", this);
        r = new Random();
        //AudioPlayer audioPlayer = new AudioPlayer();
        //audioPlayer.play("mp3", "darude.mp3");


//        handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32, ID.Player, handler));
//        handler.addObject(new BasicEnemy(500,550,ID.BasicEnemy, handler));
//        handler.addObject(new BasicEnemy(120,480,ID.BasicEnemy, handler));
//        handler.addObject(new BasicEnemy(420,80,ID.BasicEnemy, handler));
//        handler.addObject(new MineEnemy(500,550,ID.MineEnemy, handler));
//        handler.addObject(new MineEnemy(120,480,ID.MineEnemy, handler));
//        handler.addObject(new MineEnemy(420,80,ID.MineEnemy, handler));
//
//        handler.addObject(new HealingEnemy(300,300,ID.HealingEnemy,handler));
//        handler.addObject(new SmartEnemy(100,100,ID.SmartEnemy,handler));


    }
    private synchronized static void createInstance () {
        if (_instance == null) _instance = new Game();
    }
    public static Game getInstance () {
        if (_instance == null) createInstance ();
        return _instance;
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
        if (GameState == State.Game) {
            hud.tick();
            spawn.tick();


        }  if (GameState == State.Menu || GameState == State.Help || GameState == State.End) {
            menu.tick();
        }

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
        if (GameState == State.Game) {
            hud.render(g);
            if(spawn.levelup){
                Font fnt = new Font("arial", 1, 100);
                g.setFont(fnt);
                g.drawString("Level up",400,300);
            }
        } else if (GameState == State.Menu || GameState == State.Help || GameState == State.End) {
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }
    public static float clamp(float var, float min, float max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public static void  main(String args[]){
        Game.getInstance();
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

    public void setKeyInput(KeyInput keyInput) {
        this.keyInput = keyInput;
    }
}