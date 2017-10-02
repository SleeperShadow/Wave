package game.main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
public class KeyInput extends KeyAdapter{
	private Handler handler;
	private boolean[] keyDown=new boolean[4];
	private Base game;
	
	public KeyInput(Handler handler,Base game) {
		this.handler=handler;
		Arrays.fill(keyDown, false);
		this.game=game;
	}
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp=handler.object.get(i);
			
			if(temp.getID()==ID.Player) {
				if(key==KeyEvent.VK_W || key==KeyEvent.VK_UP) {
					keyDown[0]=true;
					temp.setVelY(-handler.speed);
					}
				if(key==KeyEvent.VK_S || key==KeyEvent.VK_DOWN) {
					keyDown[1]=true;
					temp.setVelY(handler.speed);
					}
				if(key==KeyEvent.VK_D || key==KeyEvent.VK_RIGHT) {
					keyDown[2]=true;
					temp.setVelX(handler.speed);
				}
				if(key==KeyEvent.VK_A || key==KeyEvent.VK_LEFT) {
					keyDown[3]=true ;
					temp.setVelX(-handler.speed);
				}
			}
		}
		if(key==KeyEvent.VK_P){
			if(game.gameState==STATE.Game) {
				if(Base.paused==true)Base.paused=false;
				else Base.paused=true;
			}//Base.paused=!Base.paused;
		}
		if(key==KeyEvent.VK_SPACE)
			if(game.gameState==STATE.Game)game.gameState=STATE.Shop;
			else if( game.gameState==STATE.Shop)game.gameState=STATE.Game;
		
		if(key==KeyEvent.VK_ESCAPE)
			System.exit(69);
	}
	
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp=handler.object.get(i);
			
			if(temp.getID()==ID.Player) {
				if(key==KeyEvent.VK_W || key==KeyEvent.VK_UP)
					keyDown[0]=false;
				if(key==KeyEvent.VK_S || key==KeyEvent.VK_DOWN)
					keyDown[1]=false;
				if(key==KeyEvent.VK_D || key==KeyEvent.VK_RIGHT)
					keyDown[2]=false;
				if(key==KeyEvent.VK_A || key==KeyEvent.VK_LEFT)
					keyDown[3]=false;
				
				if(!keyDown[0] && !keyDown[1])
					temp.setVelY(0);
				if(!keyDown[2] && !keyDown[3])
					temp.setVelX(0);
			}
		}
	}
	
}
