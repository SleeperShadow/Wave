import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int score=0;
	private int level;
	private int min=200;
	Random r=new Random();
	
	public Spawn(Handler handler,HUD hud) {
		this.handler=handler;
		this.hud=hud;
		level=hud.getLevel();
	}
	public void tick() {
		score++;
		if(score>=min) {
			if(Base.difficulty==true) {
				if(EnemyBoss.isAlive()==false) {
					score=0;
					hud.setLevel(++level);
					if(level%15==0) {
						handler.clearObjects();
						handler.addObject(new EnemyBoss(Base.WIDTH/2-48,-100,ID.EnemyBoss,handler));
						}
					else if(level %5 ==0)
						handler.addObject(new HomingEnemy(r.nextInt((int)Base.WIDTH-32),r.nextInt((int)Base.HEIGHT-32),ID.HomingEnemy,handler));
					else if(level %3 ==0)
						handler.addObject(new FastEnemy(r.nextInt((int)Base.WIDTH-16),r.nextInt((int)Base.HEIGHT-16),ID.FastEnemy,handler));
					else
						handler.addObject(new BigEnemy(r.nextInt((int)Base.WIDTH-32),r.nextInt((int)Base.HEIGHT-32),ID.BigEnemy,handler));
					}
					else {
						if(score>=3000) {
							handler.clearObjects();
							EnemyBoss.killBoss();}
						}
			}
			else {
				if(EnemyBoss.isAlive()==false) {
				score=0;
				hud.setLevel(++level);
				if(level%15==0) {
					handler.clearObjects();
					handler.addObject(new EnemyBoss(Base.WIDTH/2-48,-100,ID.EnemyBoss,handler));
					}
				else if(level % 6 ==0)
					handler.addObject(new HomingEnemy(r.nextInt((int)Base.WIDTH-32),r.nextInt((int)Base.HEIGHT-32),ID.HomingEnemy,handler));
				else if(level %4 ==0)
					handler.addObject(new BigEnemy(r.nextInt((int)Base.WIDTH-32),r.nextInt((int)Base.HEIGHT-32),ID.BigEnemy,handler));
				else if(level %3 ==0)
					handler.addObject(new FastEnemy(r.nextInt((int)Base.WIDTH-16),r.nextInt((int)Base.HEIGHT-16),ID.FastEnemy,handler));
				else
					handler.addObject(new BasicEnemy(r.nextInt((int)Base.WIDTH-16),r.nextInt((int)Base.HEIGHT-16),ID.BasicEnemy,handler));
				}
				else {
					if(score>=3000) {
						handler.clearObjects();
						EnemyBoss.killBoss();}
					}
			}
				}
	}
	public void resetScore() {
		score=0;
	}
}
