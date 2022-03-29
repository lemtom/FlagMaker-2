package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayFlash extends OverlayPath
{
	private final String path = "m 62.353503,-160.6321 -191.463733,190.475 114.298053,0 L -138,156.57082 -127.44397,160.7 104.12739,-5.7821001 l -110.816867,0 L 137.77859,-129.2821 z";
	private final Vector pathSize = new Vector(276, 322);
	
	public OverlayFlash(int maximumX, int maximumY)
	{
		super("flash", maximumX, maximumY);
		constructor(path, pathSize);
	}	
}
