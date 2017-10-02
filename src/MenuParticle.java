import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	
	private Handler handler;
	private Random r=new Random();
	private int red,green,blue;
	private Color color;
	public MenuParticle(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		velX=r.nextInt(6)+5;
		velY=r.nextInt(2)+2;
		
		this.handler=handler;
		red=r.nextInt(255);
		green=r.nextInt(255);
		blue=r.nextInt(255);
		color=new Color(red,green,blue);
	}


	public void tick() {
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY; 
		if(y<=0 || y>=Base.HEIGHT-32)
			velY*=-1;
		if(x<=0 || x>=Base.WIDTH-32)
			velX*=-1;
		handler.addObject(new Trail(x,y,ID.Trail,color,24,24,0.02f,handler));
		
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.fillRect((int)x, (int)y, 24, 24);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

}

