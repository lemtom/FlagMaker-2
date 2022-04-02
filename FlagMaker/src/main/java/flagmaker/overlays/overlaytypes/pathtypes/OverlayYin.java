package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayYin extends OverlayPath {
	private static final String PATH = "M 83.20869,-55.472461 A 50.0022,50.0022 0 0 1 0,0 50.0022,50.0022 0 1 0 -83.20869,55.472461 100.0044,100.0044 0 1 0 83.20869,-55.472461 z";
	private final Vector pathSize = new Vector(200, 200);

	public OverlayYin(int maximumX, int maximumY) {
		super("yin", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
