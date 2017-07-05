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
     * Creates our main method used to run the game and instantiates everything that is needed to run it.
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
     * create a private singleton instance of game
     */
    private synchronized static void createInstance() {
        if (_instance == null)
            _instance = new Game();
    }

    /**
     * method that returns a singleton instance of game
     *
     * @return
     */
    public static Game getInstance() {
        if (_instance == null)
            createInstance();
        return _instance;
    }

    /**
     * starts a new thread for the game and sets running to true which allows run to loop
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    /**
     * stop the thread and set running to false to end the program
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
     * Runs through all the methods needed to execute the program
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
     * Method that calls the needed tick methods depending on what the state of the program is
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
     * Creates an instance of Graphics upon which all the render methods are called
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
     * Method that helps to stop the characters from leaving the screen
     *
     * @param var the value of the character that needs to be checked
     * @param min the minimum value
     * @param max the maximum value
     * @return returns a value thats within the given min and max
     */
    public static float clamp(float var, float min, float max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    /**
     * main method that instantiates our game class
     *
     * @param args
     */
    public static void main(String args[]) {
        new Game();
    }

    public KeyInput getKeyInput() {
        return keyInput;
    }

    public void setKeyInput(KeyInput keyInput) {
        this.keyInput = keyInput;
    }
}