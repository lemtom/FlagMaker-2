package flagmaker.data;

public class Size
{
	public int x;
	public int y;
	
	public Size(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return y + ":" + x;
	}
}
