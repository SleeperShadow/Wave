import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
public class Base extends Canvas implements Runnable {
	private static final long serialVersionUID=-240840600533728354L;
	public static final int WIDTH=1080,HEIGHT=WIDTH/12*9;
	private Thread thread;
	private boolean running=false;
	private Handler handler;
	private Random r=new Random();
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	public static boolean paused=false;
	public static boolean difficulty=false;
	
	public STATE gameState=STATE.Menu;
	public Base() {
		handler=new Handler();
		hud=new HUD();
		menu=new Menu(this,handler,hud);
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop(1,0.2f);
		
		new Window(WIDTH,HEIGHT,"Let's build a game!",this);
	
		
		spawn=new Spawn(handler,hud);
		
		
		if(gameState==STATE.Game) 
		{
		handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH-16),r.nextInt(HEIGHT-16),ID.BasicEnemy,handler));
		}else {
			for(int i=0;i<10;i++)
				handler.addObject(new MenuParticle(r.nextInt(WIDTH-16),r.nextInt(HEIGHT-16),ID.MenuParticle,handler));
		}
	}
	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void run() {
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		long timer=System.currentTimeMillis();
		int frames=0;
		while(running){
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println("FPS:  "+frames);
				frames=0;
			}
			
		}
		stop();
	}
	private void tick() {
		
		if(gameState==STATE.Game) {
			if(!paused) {
			hud.tick();
			spawn.tick();
			handler.tick();
			
			if(HUD.HEALTH==0) {
				gameState=STATE.Death;
				HUD.HEALTH=100;
				spawn.resetScore();
				handler.clearObjects();
				for(int i=0;i<10;i++)
					handler.addObject(new MenuParticle(r.nextInt(WIDTH-16),r.nextInt(HEIGHT-16),ID.MenuParticle,handler));
			}
			}
		}else if(gameState==STATE.Menu || gameState==STATE.Death || gameState==STATE.Difficulty) {
			menu.tick();
			handler.tick();
		}
		
	}
	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		handler.render(g);
		if(paused) {
			g.setFont(new Font("Tahoma",Font.BOLD | Font.ITALIC,72));
			g.drawString("PAUSED", WIDTH/2-150, HEIGHT/2);
		}
		if(gameState==STATE.Game) {
			hud.render(g);
		}else if(gameState==STATE.Menu || gameState==STATE.Help || gameState==STATE.Death || gameState==STATE.Difficulty){
			menu.render(g);
		}
		
		
		
		g.dispose();
		bs.show();
		
	}
	public static float clamp(float val,float min, float max) {
		if(val>=max)
			return max;
		if(val<=min)
			return min;
		else
			return val;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Base();
	}
	

}
