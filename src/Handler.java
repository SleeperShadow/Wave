import java.awt.Graphics;
import java.util.LinkedList;
import java.util.*;
public class Handler {
	ArrayList<GameObject> object=new ArrayList<GameObject>();
	public void tick() {
		for(int i=0;i<object.size();i++) {
			GameObject temp=object.get(i);
			temp.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0;i<object.size();i++) {
			GameObject temp=object.get(i); 
			temp.render(g);
		}
	}
	public void addObject(GameObject obj) {
		this.object.add(obj);
	}
	public void removeObject(GameObject obj) {
		this.object.remove(obj);
	}
	public void clearObjects() {
		object.clear();
		
		this.addObject(new Player(Base.WIDTH/2-32,Base.HEIGHT/2-32,ID.Player,this));
	}
}
