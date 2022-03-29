package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayBolnisiCross extends OverlayPath
{
	private final String path = "M -20.4375 -58.25 A 312 312 0 0 1 -15.71875 -15.6875 A 312 312 0 0 1 -58.375 -20.4375 A 165 165 0 0 1 -58.375 20.4375 A 312 312 0 0 1 -15.71875 15.71875 A 312 312 0 0 1 -20.4375 58.25 A 165 165 0 0 1 20.4375 58.25 A 312 312 0 0 1 15.71875 15.71875 A 312 312 0 0 1 58.125 20.4375 A 165 165 0 0 1 58.125 -20.4375 A 312 312 0 0 1 15.71875 -15.71875 A 312 312 0 0 1 20.4375 -58.25 A 165 165 0 0 1 -20.4375 -58.25 z";
	private final Vector pathSize = new Vector(118, 118);
	
	public OverlayBolnisiCross(int maximumX, int maximumY)
	{
		super("bolnisi cross", maximumX, maximumY);
		constructor(path, pathSize);
	}	
}
