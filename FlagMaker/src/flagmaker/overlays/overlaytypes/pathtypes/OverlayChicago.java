package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayChicago extends OverlayPath {
	private static final String PATH = "M 0.0,1.0 L 0.183,0.317 0.866,0.500 0.366,0 0.866,-0.500 0.183,-0.317 0,-1.000 -0.183,-0.317 -0.866,-0.500 -0.366,0 -0.866,0.500 -0.183,0.317 Z";
	private final Vector pathSize = new Vector(2, 2);

	public OverlayChicago(int maximumX, int maximumY) {
		super("chicago", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
