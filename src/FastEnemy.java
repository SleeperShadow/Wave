import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FastEnemy extends GameObject{
	
	private Handler handler;
	private Random r=new Random();
	public FastEnemy(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		velX=9;
		velY=3+r.nextInt(7);
		this.handler=handler;
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY; 
		if(y<=0 || y>=Base.HEIGHT-32)
			velY*=-1;
		if(x<=0 || x>=Base.WIDTH-32)
			velX*=-1;
		handler.addObject(new Trail(x,y,ID.Trail,Color.MAGENTA,16,16,0.05f,handler));
		
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.MAGENTA);
		g.fillRect((int)x, (int)y, 16, 16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

}

