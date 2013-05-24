package game;

public class Fire {

	private int direction;
	private int timeLeft;
	private boolean dead;
	
	public Fire(int direction)
	{
		this.direction = direction;
		this.timeLeft = 50;
		this.dead = false;
	}
	
	public void update()
	{
		timeLeft --;
		if (timeLeft < 1) { dead = true; }
	}
	
	public void setDirections(int d)
	{
		direction = d;
	}
	
	public int getDirection() { return direction; }
	
}
