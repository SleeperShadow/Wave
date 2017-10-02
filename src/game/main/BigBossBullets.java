package game.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BigBossBullets extends GameObject{
	
	private Handler handler;
	Random r=new Random();
	public BigBossBullets(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velX=r.nextInt(21)-11;
		velY=7+handler.bonusEnemySpeed;
		this.handler=handler;
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY; 
		//if(y<=0 || y>=Base.HEIGHT-32)
			//velY*=-1;
		//if(x<=0 || x>=Base.WIDTH-32)
			//velX*=-1;
		
		if(y>=Base.HEIGHT)handler.removeObject(this);
		handler.addObject(new TrailOval(x,y,ID.TrailOval,Color.ORANGE,48,48,0.05f,handler));
		
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.ORANGE);
		g.fillOval((int)x,(int) y, 48, 48);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,48,48);
	}

}
