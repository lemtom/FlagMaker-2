package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayAllahu extends OverlayPath {
	private static final String PATH = "M -55,-15 -55,-8.04721 20.59303,-8.04721 20.59303,-15 z M 25.19767,-15 25.19767,-3.41202 2.30232,-3.41202 2.30232,15 20.59303,15 20.59303,8.04721 9.2093,8.04721 9.2093,3.41202 25.19767,3.41202 25.19767,15 55,15 55,-15 48.09302,-15 48.09302,8.04721 43.48837,8.04721 43.48837,-15 36.70931,-15 36.70931,8.04721 32.10465,8.04721 32.10465,-15 z M -55,-3.41202 -55,15 -48.09302,15 -48.09302,3.41202 -32.10465,3.41202 -32.10465,15 -28.65116,15 -5.75581,15 -2.30232,15 -2.30232,-3.41202 -5.75581,-3.41202 -20.59303,-3.41202 -20.59303,3.41202 -9.2093,3.41202 -9.2093,8.04721 -25.19767,8.04721 -25.19767,-3.41202 z M -43.48837,8.04721 -43.48837,15 -36.7093,15 -36.7093,8.04721 z";
	private final Vector pathSize = new Vector(110, 30);

	public OverlayAllahu(int maximumX, int maximumY) {
		super("allahu", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
