package Robotron;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import Robotron.Game.State;

import javax.imageio.ImageIO;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;
	Spawn p;
	BufferedImage img1;
	BufferedImage img2;
	BufferedImage img3;
	BufferedImage img4;
	BufferedImage img5;
	BufferedImage img6;
	BufferedImage img7;

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		try {
			// Grab the InputStream for the image.
			img1 = ImageIO.read(new FileInputStream("wasd.png"));
			img2 = ImageIO.read(new FileInputStream("space.png"));
			img3 = ImageIO.read(new FileInputStream("robot.png"));
			img4 = ImageIO.read(new FileInputStream("enemyrobot.png"));
			img5 = ImageIO.read(new FileInputStream("healrobot.png"));
			img6 = ImageIO.read(new FileInputStream("Smart_enemy.png"));
			img7 = ImageIO.read(new FileInputStream("mine.png"));

		} catch (IOException e) {
			System.out.println("The image was not loaded.");

		}
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.GameState == State.Menu) {
			if (mouseOver(mx, my, 515, 350, 250, 90)) {

				game.GameState = State.Game;
				handler.addObject(new Player(game.WIDTH/2-32,game.HEIGHT/2-32, ID.Player, handler, game));
				handler.addObject(new BasicEnemy(500,550,ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(120,480,ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(420,80,ID.BasicEnemy, handler));
				handler.addObject(new SmartEnemy(100,100,ID.SmartEnemy, handler));
			}
			if (mouseOver(mx, my, 515, 600, 250, 90)) {
				System.exit(1);
			}
			if (mouseOver(mx, my, 515, 475, 250, 90)) {
				game.GameState = State.Help;
				// laat iets zien
			}
		}

		if (game.GameState == State.Help) {
			if (mouseOver(mx, my, 515, 735, 250, 90)) {
				game.GameState = State.Menu;
				return;
			}
		}
		if(game.GameState==State.End){
			if(mouseOver(mx,my,395,600,460,90)){
				game.GameState = State.Menu;
				p.scoreKeep = 0;
				hud.level = 1;
				return;
			}
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx >= x && mx <= x + width) {
			if (my >= y && my <= y + height) {
				return true;
			}
			return false;
		}
		return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 100);
		Font fnt2 = new Font("arial", 1, 60);
		Font fnt3 = new Font("arial", 1, 20);
		if (game.GameState == State.Menu) {
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 510, 110);

			g.setFont(fnt2);
			g.drawRect(515, 350, 250, 90);
			g.drawString("Play", 580, 415);

			g.drawRect(515, 475, 250, 90);
			g.drawString("Help", 580, 540);

			g.drawRect(515, 600, 250, 90);
			g.drawString("Quit", 580, 665);
		} else if (game.GameState == State.Help) {
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 510, 110);

			g.setFont(fnt2);
			g.drawImage(img1,250,200,138,91,null);
			g.drawImage(img2,650,200,138,91,null);
			g.drawImage(img3,250,350,50,50,null);
			g.drawImage(img4,250,450,50,50,null);
			g.drawImage(img5,250,550,50,50,null);
			g.drawImage(img6,650,350,50,50,null);
			g.drawImage(img7,650,450,50,50,null);
			g.drawString("Player",320,400);
			g.drawString("Move",410,270);
			g.drawString("Shoot",810,270);
			g.drawRect(515, 735, 250, 90);
			g.drawString("Back", 570, 800);
			g.setFont(fnt3);
			g.drawString("Touch: take damage over time.",320,470);
			g.drawString("Hit 3 times to kill.",320,500);
			g.drawString("Touch: take damage over time.",320,570);
			g.drawString("Hit 1 time to kill and gain 10 health.",320,600);
			g.drawString("Touch: take damage over time.",720,370);
			g.drawString("Hit 5 times to kill.",720,400);
			g.drawString("Touch: instant death.",720,470);
			g.drawString("Hit 1 time to kill.",720,500);
		}else if (game.GameState == State.End) {
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GAME OVER", 320, 110);

			g.setFont(fnt2);
			g.drawString("Your score is: " + Spawn.scoreKeep, 400, 400);
			g.drawRect(395, 600, 460, 90);
			g.drawString("Go to menu", 460, 665);
		}
	}
}