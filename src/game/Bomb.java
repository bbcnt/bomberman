package game;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.*;

public class Bomb extends TimerTask {

   public Image bomb;
	private int timeLeft;
	private int radius;
	private Player creator;
	private boolean exploded;
	private int x;
	private int y;
	private boolean planted;
	private Timer timer;

	//private boolean directions[] = new boolean[4];
	
	public Bomb(Image image, int timeLeft, int radius, Player creator, 
			int x, int y)
	{
		this.timeLeft = timeLeft;
		this.radius = radius;
		this.creator = creator;
		this.exploded = false;
		this.planted = false;
		this.x = x;
		this.y = y;
		this.bomb = image;
		this.timer = new Timer();
        this.timer.schedule(this, 3000);

		/*this.directions[0] = true;
		this.directions[1] = true;
		this.directions[2] = true;
		this.directions[3] = true;*/
	}
	
	public void update()
	{
		if(timeLeft < 1)
			explode();
	}
	
	public void explode()
	{
		if(creator != null)
		{
			creator.setBombAmt(creator.getBombAmt() + 1);
		}
		exploded = true;
	}
	
	public int getTimeLeft(){ return timeLeft; }
	public void setTimeLeft(int t){ timeLeft = t; }
	public boolean getExploded() { return exploded;}
	public int getRadius() { return radius; }
	//public boolean[] getDirections() { return directions; }
	//public void setDirections(boolean[] d) { directions = d; }
	public int X() { return x; }
	public int Y() { return y; }
	public boolean isPlanted() { return planted; }
	public void setPlanted(boolean b) { planted = b; }

	@Override
	public void run() {
		
		this.exploded = true;
		this.explode();
	}

}
