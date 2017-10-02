package game.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{
	private Handler handler;
	private int B1=500;
	private int B2=500;
	private int B3=500;
	private HUD hud;
	private int sp=0;
	public Shop(Handler handler,HUD hud) {
		this.handler=handler;
		this.hud=hud;
		
	}
	public void reset() {
		sp=0;
		B1=500;B2=500;B3=500;
		hud.HEALTH=100;
		hud.bounds=0;
		handler.speed=5;
		handler.bonusEnemySpeed=0;
	}
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,48));
		
		g.drawString("SHOP", Base.WIDTH/2-50, 50);
		//box1
		g.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC,32));
		g.draw3DRect(Base.WIDTH/2-130, 100, 260, 120,true);
		g.drawString("Upgrade Health",Base.WIDTH/2-130,140);
		g.drawString("Cost : " +B1,Base.WIDTH/2-130,180);
		//box2
		g.draw3DRect(Base.WIDTH/2-130, 260, 260, 120,true);
		g.drawString("Upgrade SPEED",Base.WIDTH/2-130,300);
		g.drawString("Cost : " +B2, Base.WIDTH/2-130,340);
		//box3
		g.draw3DRect(Base.WIDTH/2-130, 420, 260, 120,true);
		g.drawString("Refill HEALTH",Base.WIDTH/2-130,460);
		g.drawString("Cost : " +B3, Base.WIDTH/2-130,500);
		
		g.setColor(Color.red);
		g.drawString("Score: "+hud.getScore(), 50, 50);
		g.drawString("Press Space to go back!", 50, 100);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		if(mx>=Base.WIDTH/2-130 && mx<=Base.WIDTH/2+130) {
			//box 1
			if(my>=100 && my<=220) {
				if(hud.getScore()>=B1) {
					hud.setScore(hud.getScore()-B1);
					B1+=500;
					hud.bounds+=40;
					hud.HEALTH+=hud.bounds/2;
				}}
			//box 2
			else if(my>=260 && my<=380) {
				if(hud.getScore()>=B2)
				{
					hud.setScore(hud.getScore()-B2);
					B2+=500;
					handler.speed+=++sp;
					
				}
			}
			//box 3
			else if(my>=420 && my<=540) {
				if(hud.getScore()>=B3) {
					hud.setScore(hud.getScore()-B3);
					hud.HEALTH=100+hud.bounds/2;
				}
			}
			
		}
		
		
			
	}
	
	public void tick() {
		
	}
	
	
}
