package Robotron;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
* Game method that extends canvas and implements runnable.
* The main method from which all other methods are called.

*/
public class Game extends Canvas implements Runnable {
    private static Game _instance = null;

    public static final long serialVersionUID = -23456;
    public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Random r;
    private Handler handler;
    private HUD hud;
    private KeyInput keyInput;
    private Spawn spawn;
    private Menu menu;

    /**
     * Additional enum to allow the game to differentiate which state it is in
     */
    public enum State {
        Menu, Help, Game, End,
    }



    public State GameState = State.Menu;

    /**
     * Method which creates an instance of Game and all required additional classes
     */
    private Game() {
        hud = new HUD();
        handler = new Handler();
        menu = new Menu(this, handler, hud);
        this.addMouseListener(menu);

        keyInput = new KeyInput(handler);
        this.addKeyListener(keyInput);

        spawn = new Spawn(handler, hud, this);
        new window(WIDTH, HEIGHT, "Robotron", this);
        r = new Random();

    }

    /**
     * Method used to create a singleton instance of Game
     */
    private synchronized static void createInstance() {
        if (_instance == null) _instance = new Game();
    }

    /**
     * Method used to return a singleton instance of Game
     * @return Returns a singleton instance of Game
     */
    public static Game getInstance() {
        if (_instance == null) createInstance();
        return _instance;
    }

    /**
     * Method used to start the game
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    /**
     * Method used to stop the game from running
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which allows the program to keep running
     */
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }

        }
        stop();

    }

    /**
     * Method used to call the update methods of all classes accordingly
     */
    private void tick() {
        handler.tick();
        if (GameState == State.Game) {
            hud.tick();
            spawn.tick();


        }
        if (GameState == State.Menu || GameState == State.Help || GameState == State.End) {
            menu.tick();
        }

    }

    /**
     * Method which creates an instance of Graphics and calls the render methods of all classes on it
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);
        if (GameState == State.Game) {
            hud.render(g);
            if (spawn.levelup) {
                Font fnt = new Font("arial", 1, 100);
                g.setFont(fnt);
                g.drawString("Level up", 400, 300);
            }
        } else if (GameState == State.Menu || GameState == State.Help || GameState == State.End) {
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    /**
     * Method used to make sure objects don't extend their boundaries
     * @param var Value that needs to be checked
     * @param min Value used to check if var is not too low
     * @param max Value used to check if var is not too high
     * @return Returns the value of var
     */
    public static float clamp(float var, float min, float max) {
        if (var >= max)
            return max;
        else if (var <= min)
            return min;
        else
            return var;
    }

    /**
     * main method that instantiates our game class
     *
     * @param args
     */
    public static void main(String args[]) {
        Game.getInstance();
    }

}