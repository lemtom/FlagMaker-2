package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayMalteseCross extends OverlayPath {
	private static final String PATH = "M -280,120 -10.000001,9.999998 -120,280 -1.3e-6,200 120,280 9.9999989,9.999998 280,120 200,-1.2999999e-6 280,-120 9.9999989,-10.000001 120,-280 -1.3e-6,-200 -120,-280 -10.000001,-10.000001 -280,-120 -200,-1.2999999e-6 z";
	private final Vector pathSize = new Vector(540, 540);

	public OverlayMalteseCross(int maximumX, int maximumY) {
		super("maltese cross", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
