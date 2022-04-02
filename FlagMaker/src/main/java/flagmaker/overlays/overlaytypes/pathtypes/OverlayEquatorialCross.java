package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayEquatorialCross extends OverlayPath {
	private static final String PATH = "M 3,10 3,3 10,3 10,-3 3,-3 3,-10 -3,-10 -3,-3 -10,-3 -10,3 -3,3 -3,10 Z";
	private final Vector pathSize = new Vector(20, 20);

	public OverlayEquatorialCross(int maximumX, int maximumY) {
		super("equatorial cross", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
