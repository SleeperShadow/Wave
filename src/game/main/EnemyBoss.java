package game.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	private int timer=50;
	private int timer2=50;
	private static boolean alive=false;
	Random r=new Random();
	public EnemyBoss(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		alive=true;
		velX=0;
		velY=2;
		this.handler=handler;
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY; 
		if(timer<=0) {
			velY=0;
			timer2--;
		}
		else timer--;
		if(timer2<=0) {
			if(velX==0) velX=2;
			velX+= (velX>0)? 0.002f*handler.bonusEnemySpeed:-0.002f*handler.bonusEnemySpeed;
			velX=Base.clamp(velX, -20+handler.bonusEnemySpeed, 20+handler.bonusEnemySpeed);
			int spawn=r.nextInt(5);
			if(spawn==0) {
				if(Base.difficulty)
					handler.addObject(new BigBossBullets((int)x+48,(int)y+48,ID.BigEnemy,handler));
				else
					handler.addObject(new EnemyBossBullets((int)x+36,(int)y+36,ID.FastEnemy,handler));}
		}
		//if(y<=0 || y>=Base.HEIGHT-72)
		//	velY*=-1;
		if(x<=0 || x>=Base.WIDTH-72)
				velX*=-1;
		handler.addObject(new Trail(x,y,ID.Trail,Color.red,72,72,0.06f,handler));
		
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect((int)x,(int) y, 72, 72);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,72,72);
	}
	public static boolean isAlive() {
		return alive;
	}
	public static void killBoss() {
		alive=false;
	}

}
