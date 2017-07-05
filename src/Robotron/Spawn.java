package Robotron;

import java.util.Random;

/**
 * Spawn class which is used to spawn new enemies when all of them are dead and differentiate between levels
 */
public class Spawn {
    private Handler handler;
    private HUD hud;
    public static int scoreKeep = 0;
    public int delay = 0;
    private int levelspawner = 0;
    private Game game;
    public static int bosslevel = 1;
    public boolean levelup;
    private Random r;




    /**
     * Method which creates an instance of Spawn
     *
     * @param handler Instance of Handler class which is used to add new GameObjects to the handler
     * @param hud     Instance of HUD class which is used to check and update the current level
     * @param game    Instance of Game class which is used to change the GameState to end if all levels have been beaten
     */
    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
        this.levelup = false;
    }

    /**
     * Method which checks the total score the player has and uses it to change between levels
     */
    public void tick() {
        delay++;
        r = new Random();
        if (scoreKeep == 210) {
            levelup = true;
            scoreKeep += 10;
            delay = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 2;
            System.out.println("levelspawner" + levelspawner);
        }
        if (levelspawner == 2 && delay >= 300) {
            levelup = false;
            handler.addObject(new BasicEnemy(100, 100, ID.BasicEnemy, handler));
            handler.addObject(new SmartEnemy(230, 300, ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(300, 400, ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(400, 700, ID.SmartEnemy, handler));
            delay = 0;
            levelspawner = 0;
        } else if (scoreKeep == 450) {
            levelup = true;
            scoreKeep += 10;
            delay = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 3;
            System.out.println("levelspawner" + levelspawner);
        }
        if (levelspawner == 3 && delay >= 300) {
            levelup = false;
            handler.addObject(new BasicEnemy(100, 100, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(400, 120, ID.BasicEnemy, handler));
            handler.addObject(new SmartEnemy(300, 400, ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(600, 700, ID.SmartEnemy, handler));
            handler.addObject(new HealingEnemy(700, 100, ID.HealingEnemy, handler));
            delay = 0;
            levelspawner = 0;
        } else if (scoreKeep == 680) {
            levelup = true;
            scoreKeep += 10;
            delay = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 4;
            System.out.println("levelspawner" + levelspawner);
        }
        if (levelspawner == 4 && delay >= 300) {
            levelup = false;
            handler.addObject(new BasicEnemy(100, 100, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(400, 120, ID.BasicEnemy, handler));
            handler.addObject(new SmartEnemy(300, 400, ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(600, 700, ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(700, 100, ID.SmartEnemy, handler));
            handler.addObject(new HealingEnemy(700, 100, ID.HealingEnemy, handler));
            handler.addObject(new MineEnemy(500, 270, ID.MineEnemy, handler));
            handler.addObject(new MineEnemy(350, 670, ID.MineEnemy, handler));
            handler.addObject(new MineEnemy(720, 640, ID.MineEnemy, handler));
            delay = 0;
            levelspawner = 0;
        } else if (scoreKeep == 1000) {
            levelup = true;
            scoreKeep += 10;

            delay = 0;
            hud.setLevel(hud.getLevel() + 1);
            levelspawner += 5;
            System.out.println("levelspawner boss" + levelspawner);
        }
        if (levelspawner == 5 && delay >= 300) {
            levelup = false;
            handler.addObject(new EndBoss(800, 750, ID.EndBoss, handler));
            bosslevel += 1;
            levelspawner = 0;
            delay = 0;
            System.out.println("bosslevel" + bosslevel);
        }
        if (bosslevel == 2) {
            if (delay >= 300 + (Math.random() * (4000 - 200))){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
                delay = 0;}
        }
        if (bosslevel == 3){
            bosslevel = 1;
            handler.clearHandler(); //deletes all active enemies
            HUD.HEALTH = 100; //resets health after the game
            game.GameState = Game.State.End; //sets gamestate to end screen

        }
    }
}








