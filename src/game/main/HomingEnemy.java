package game.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HomingEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	public HomingEnemy(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.handler=handler;
		
		
		for(int i=0;i<handler.object.size();i++)
		{
			if(handler.object.get(i).getID()==ID.Player) player=handler.object.get(i);
		}
		
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+=velX+handler.bonusEnemySpeed;
		y+=velY+handler.bonusEnemySpeed; 
		float diffX=x-player.getX();
		float diffY=y-player.getY();
		float distance = (float) Math.hypot(diffX, diffY);
		
		velX=3*(-1/distance)*(diffX-14);
		velY=3*(-1/distance)*(diffY-14);
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.CYAN,28,28,0.05f,handler));
		
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.CYAN);
		g.fillRect((int)x, (int)y, 28, 28);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

}
