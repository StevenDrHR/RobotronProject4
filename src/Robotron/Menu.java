package Robotron;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Robotron.Game.State;

public class Menu extends MouseAdapter {
	private Game game;
	private Handler handler;
	private HUD hud;

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
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
			if (mouseOver(mx, my, 515, 600, 250, 90)) {
				game.GameState = State.Menu;
				return;
			}
		}
		if(game.GameState==State.End){
			if(mouseOver(mx,my,395,600,460,90)){
				game.GameState = State.Menu;
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
			g.drawRect(515, 600, 250, 90);
			g.drawString("Back", 580, 665);
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