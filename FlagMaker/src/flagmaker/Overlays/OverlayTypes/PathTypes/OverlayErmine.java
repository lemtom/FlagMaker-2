package flagmaker.Overlays.OverlayTypes.PathTypes;

import flagmaker.Data.Vector;

public class OverlayErmine extends OverlayPath
{
	private final String path = "m 6.536855,-25.66495 c 0,3.449998 -2.7999997,6.249998 -6.24999766,6.249998 -3.44999904,0 -6.24999694,-2.8 -6.24999694,-6.249998 0,-3.449999 2.7999979,-6.249997 6.24999694,-6.249997 3.44999796,0 6.24999766,2.799998 6.24999766,6.249997 z m -9.0000007,11.000001 c 0,3.449998 -2.7999999,6.249997 -6.249998,6.249997 -3.4499993,0 -6.2499973,-2.799999 -6.2499973,-6.249997 0,-3.45 2.799998,-6.249998 6.2499973,-6.249998 3.4499981,0 6.249998,2.799998 6.249998,6.249998 z m 18.0000007,0 c 0,3.449998 -2.8,6.249997 -6.2499977,6.249997 -3.4499993,0 -6.249997,-2.799999 -6.249997,-6.249997 0,-3.45 2.7999977,-6.249998 6.249997,-6.249998 3.4499977,0 6.2499977,2.799998 6.2499977,6.249998 z m -17.0786987,-3.3135 2.951989,0.203586 C 1.5119383,1.463967 16.170094,31.9 16.170094,31.9 L 6.95785,23.094927 C 4.4130323,26.046917 -0.11674566,31.9 -0.11674566,31.9 c 0,0 -3.25736804,-5.90398 -5.59860094,-8.85597 L -16.2,31.798207 c 0,0 14.4545703,-30.130655 14.6581563,-49.776656 z";
	private final Vector pathSize = new Vector(34, 64);
	
	public OverlayErmine(int maximumX, int maximumY)
	{
		super("ermine", maximumX, maximumY);
		constructor(path, pathSize);
	}	
}
