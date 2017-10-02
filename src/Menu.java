import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Component.BaselineResizeBehavior;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter {
	private Base game;
	private Handler handler;
	private Random r=new Random();
	private HUD hud;
	public Menu(Base game,Handler handler,HUD hud) {
		this.game=game;
		this.handler=handler;
		this.hud=hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		
		if(game.gameState==STATE.Menu) {
			//PLAY
			if(mouseOver(mx,my,Base.WIDTH/2-220,150, 420, 128)) {
			
			game.gameState=STATE.Difficulty;
			AudioPlayer.getSound("menu_sound").play(1,1f);
			}
		//HELP
			if(mouseOver(mx,my,Base.WIDTH/2-220,350, 420, 128)) {
			game.gameState=STATE.Help;
			AudioPlayer.getSound("menu_sound").play(0.5f,1f);
			}
		
		//QUIT
			if(mouseOver(mx,my,Base.WIDTH/2-220,550, 420, 128)) {
			System.exit(69);
			AudioPlayer.getSound("menu_sound").play(0.5f,1f);
			}
		
		
		}
		else if(game.gameState==STATE.Difficulty) {
			//Normal
			if(mouseOver(mx,my,Base.WIDTH/2-220,150, 420, 128)) {
			handler.object.clear();
			game.gameState=STATE.Game;
			handler.addObject(new Player(Base.WIDTH/2-32,Base.HEIGHT/2-32,ID.Player,handler));
			handler.addObject(new BasicEnemy(r.nextInt(Base.WIDTH-16),r.nextInt(Base.HEIGHT-16),ID.BasicEnemy,handler));
			Base.difficulty=false;
			
			AudioPlayer.getSound("menu_sound").play(1,1f);
			}
		//Expert
			if(mouseOver(mx,my,Base.WIDTH/2-220,350, 420, 128)) {
				handler.object.clear();
				game.gameState=STATE.Game;
				handler.addObject(new Player(Base.WIDTH/2-32,Base.HEIGHT/2-32,ID.Player,handler));
				handler.addObject(new BigEnemy(r.nextInt(Base.WIDTH-16),r.nextInt(Base.HEIGHT-16),ID.BigEnemy,handler));
				Base.difficulty=true;
				
			}
		
		//Back
			if(mouseOver(mx,my,Base.WIDTH/2-220,550, 420, 128)) {
				AudioPlayer.getSound("menu_sound").play(0.5f,1f);
				handler.object.clear();
				game.gameState=STATE.Menu;
				
			}
		
		
		}
		
		//Back button for help
		if(game.gameState==STATE.Help && mouseOver(mx,my,Base.HEIGHT-100,Base.WIDTH/2,200,100)){
			AudioPlayer.getSound("menu_sound").play(0.5f,1f);
			game.gameState=STATE.Menu;
			handler.object.clear();
			}
		if(game.gameState==STATE.Death && mouseOver(mx,my,Base.HEIGHT-100,Base.WIDTH/2,200,100)){
			AudioPlayer.getSound("menu_sound").play(0.5f,1f);
			handler.object.clear();
			game.gameState=STATE.Menu;

			hud.setLevel(1);
			hud.setScore(0);
			}
		}
	 
	public void mouseRelease(MouseEvent e) {
		
	}
	public void tick() {
		
	}
	
	private boolean mouseOver(int mx,int my,int x,int y,int width,int height) {
		if(mx>x && mx<x+width) {
			if(my>y && my<my+height){
				return true;
			}
			
		}
		return false;
	}
	
	public void render(Graphics g) {
		if(game.gameState==STATE.Menu) {
			
			g.setColor(new Color(20,160,20));
			g.setFont(new Font("Tahoma",Font.BOLD,52));
			g.drawString("Weiv", Base.WIDTH/2-72, 100);
			
			g.setColor(Color.GREEN);
			
			g.draw3DRect(Base.WIDTH/2-220,150, 420, 128,true);
			
			g.setFont(new Font("Verdana",Font.BOLD,32));
			g.drawString("PLAY", Base.WIDTH/2-60, 220);
			
			g.draw3DRect(Base.WIDTH/2-220,350, 420, 128,true);
			
			g.drawString("HELP", Base.WIDTH/2-60, 420);
			
			g.draw3DRect(Base.WIDTH/2-220,550, 420, 128,true);
			
			g.drawString("QUIT", Base.WIDTH/2-60, 620); 
		}else if(game.gameState==STATE.Help) {

			g.setColor(new Color(20,160,20));
			g.setFont(new Font("Tahoma",Font.BOLD,52));
			g.drawString("Help", Base.WIDTH/2-72, 100);
			
			g.draw3DRect(Base.HEIGHT-100,Base.WIDTH/2,200,100,true);
			g.drawString("BACK", Base.HEIGHT-70,Base.WIDTH/2+70);
			
			g.setFont(new Font("Verdana",Font.BOLD | Font.ITALIC,24));
			g.drawString("Use WASD or arrow keys to move your player and dodge the enemies!", 100, 300);
			g.drawString("Credits:", 100, 700);
			g.drawString("Music by Tanner Helland", 100, 725);
			
		}else if(game.gameState==STATE.Death) {

			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma",Font.BOLD,72));
			g.drawString("You DIED", Base.WIDTH/2-150, 300);
			g.setFont(new Font("Tahoma",Font.BOLD,32));
			g.drawString("Your score was : "+hud.getScore(), Base.WIDTH/2-150, 450);
			
			g.draw3DRect(Base.HEIGHT-100,Base.WIDTH/2,200,100,true);
			
			g.drawString("Try Again", Base.HEIGHT-70,Base.WIDTH/2+70);
			
			
		}else if(game.gameState==STATE.Difficulty) {
			
			g.setColor(new Color(20,160,20));
			g.setFont(new Font("Tahoma",Font.BOLD | Font.ITALIC,54));
			g.drawString("Select Difficulty", Base.WIDTH/2-200, 100);
			
			g.setColor(Color.GREEN);
			
			g.draw3DRect(Base.WIDTH/2-220,150, 420, 128,true);
			
			g.setFont(new Font("Verdana",Font.BOLD | Font.ITALIC,32));
			g.drawString("Normal", Base.WIDTH/2-60, 220);
			
			g.draw3DRect(Base.WIDTH/2-220,350, 420, 128,true);
			
			g.drawString("Expert", Base.WIDTH/2-60, 420);
			
			g.draw3DRect(Base.WIDTH/2-220,550, 420, 128,true);
			
			g.drawString("Back", Base.WIDTH/2-60, 620); 
		}
		
	}
	
	
}
