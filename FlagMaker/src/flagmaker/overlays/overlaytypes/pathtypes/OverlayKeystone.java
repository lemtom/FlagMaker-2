package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayKeystone extends OverlayPath {
	private final String path = "m -90.189954,-114.36858 21.81,48.630011 H -120 l 60.370046,180.309989 h 119 L 119.74,-65.738569 H 68.120046 l 21.809981,-48.630011 z";
	private final Vector pathSize = new Vector(240, 230);

	public OverlayKeystone(int maximumX, int maximumY) {
		super("keystone", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
