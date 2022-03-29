package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayOctagram extends OverlayPath
{
	private final String path = "M -5,-5 -15,-5 -10,0 -15,5 l 10,0 0,10 5,-5 5,5 0,-10 L 15,5 10,0 15,-5 l -10,0 L 5,-15 0,-10 -5,-15 z";
	private final Vector pathSize = new Vector(30, 30);
	
	public OverlayOctagram(int maximumX, int maximumY)
	{
		super("octagram", maximumX, maximumY);
		constructor(path, pathSize);
	}	
}
