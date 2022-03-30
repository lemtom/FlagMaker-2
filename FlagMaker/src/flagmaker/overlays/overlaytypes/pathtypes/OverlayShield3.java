package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayShield3 extends OverlayPath {
	private final String path = "M -69 -92.1875 L -69 -20.9375 C -69 24.7055 -27.051 70.6275 0 92.1875 C 27.052 70.6275 69 24.7055 69 -20.9375 L 69 -92.1875 L -69 -92.1875 z";
	private final Vector pathSize = new Vector(200, 6);

	public OverlayShield3(int maximumX, int maximumY) {
		super("shield 3", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
