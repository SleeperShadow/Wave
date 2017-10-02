import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	private Random r=new Random();
	private Handler handler;
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler=handler;
	}

	public void tick() {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		if(x<=0)
			x=Base.WIDTH-32;
		else if(x>=Base.WIDTH-32)
			x=0;
		if(y<=0)
			y=Base.HEIGHT-32;
		else if(y>=Base.HEIGHT-32)
			y=0;
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.WHITE,32,32,0.08f,handler));
		
		collision();
		
	}
	
	private void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp=handler.object.get(i);
			ID tempID=temp.getID();
			if(tempID==ID.BasicEnemy || tempID==ID.HomingEnemy) {
				if(getBounds().intersects(temp.getBounds()))
					HUD.HEALTH--;
				}
			else if(tempID==ID.FastEnemy || tempID==ID.BigEnemy) {
				if(getBounds().intersects(temp.getBounds()))
					HUD.HEALTH-=2;
				}
			else if(tempID==ID.EnemyBoss)
				if(getBounds().intersects(temp.getBounds()))
					HUD.HEALTH-=3;
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void render(Graphics g) {
		
	
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 32, 32);
	}
	
}
