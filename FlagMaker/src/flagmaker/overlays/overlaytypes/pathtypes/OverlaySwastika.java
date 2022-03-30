package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlaySwastika extends OverlayPath {
	private final String path = "m 0.0025,-7.06 -3.53125,3.53125 -0.71875,0.6875 0.71875,0.71875 2.125,2.12500003 L -2.81,1.44 -5.65375,-1.40375 -7.06,0.00250003 -3.52875,3.53375 -2.84125,4.2525 -2.1225,3.53375 0.0025,1.40875 1.44,2.815 -1.40375,5.65875 0.0025,7.065 3.53375,3.53375 4.2525,2.84625 3.53375,2.1275 1.40875,0.00250003 2.815,-1.435 5.65875,1.40875 7.065,0.00250003 3.53375,-3.52875 2.84625,-4.2475 2.1275,-3.52875 0.0025,-1.40375 -1.435,-2.81 1.40875,-5.65375 0.0025,-7.06 z";
	private final Vector pathSize = new Vector(16, 16);

	public OverlaySwastika(int maximumX, int maximumY) {
		super("swastika", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
