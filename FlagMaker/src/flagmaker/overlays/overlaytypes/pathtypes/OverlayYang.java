package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayYang extends OverlayPath {
	private final String path = "M -83.20869,-55.472461 A 50.0022,50.0022 0 1 0 0,0 50.0022,50.0022 0 0 1 83.20869,55.472461 100.0044,100.0044 0 0 1 -83.20869,-55.472461 z";
	private final Vector pathSize = new Vector(200, 200);

	public OverlayYang(int maximumX, int maximumY) {
		super("yang", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
