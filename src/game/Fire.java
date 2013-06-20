package game;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.*;

/**
 * Cette classe représente le feu après l'explosion de la bombe
 * @author Julien Bignens
 * @author Bruno Carvalho
 * @author Gaëtan Djomnang Yaze
 * @author Marcel Sinniger
 *
 */
public class Fire extends TimerTask {

	public Image fire;
	private int timeLeft;
	private int radius;
	private boolean boom;
	private int x;
	private int y;
	private Timer timer;

	//private boolean directions[] = new boolean[4];
	
	public Fire(Image image, int timeLeft, int radius,  
			int x, int y)
	{
		this.timeLeft = timeLeft;
		this.radius = radius;
		this.boom = false;
		this.x = x;
		this.y = y;
		this.fire = image;
		this.timer = new Timer();
        this.timer.schedule(this, 3000);
	}
	
	/**
	 * Cette méthode gère le cycle de vie de l'explosion (la durée du feu)
	 */
	public void update()
	{
		if(timeLeft < 1)
			boom = true;
	}

	public int getTimeLeft(){ return timeLeft; }
	public void setTimeLeft(int t){ timeLeft = t; }
	public boolean getBoom() { return boom;}
	public int getRadius() { return radius; }
	public int X() { return x; }
	public int Y() { return y; }

	@Override
	public void run() {
		
		this.boom = true;
	}
}