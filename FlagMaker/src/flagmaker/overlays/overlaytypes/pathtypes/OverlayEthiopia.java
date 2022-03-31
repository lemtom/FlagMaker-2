package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayEthiopia extends OverlayPath {
	private static final String PATH = "m -0.0447498,-96.09241 -4.21875,12.937505 17.3749998,53.40625 -23.15625,0 -2.59375,8 74.15625,0 11.03125,-8 -51.03125,0 -21.5624998,-66.343755 z m -54.8125002,17.156255 -3.21875,2.34375 30.5625,42.09375 3.21875,-2.375 -30.5625,-42.0625 z m 109.625,0 -30.5625,42.0625 3.21875,2.375 30.5625,-42.09375 -3.21875,-2.34375 z m -60.5937498,0.65625 -15.7812502,48.53125 -69.75,0 11.03125,8 56.125,0 -7.15625,22 6.8125,4.9374999 22.9375002,-70.5312499 -4.21875,-12.9375 z M 77.64275,-29.748655 32.2365,3.2513449 25.08025,-18.748655 l -8.40625,0 22.90625,70.53125 11.03125,8 -15.78125,-48.53125 56.4375,-41 -13.625,0 z m -153.84375,11 41.28125,30 -21.5625,66.3125 11.03125,-8 17.34375,-53.375 18.71875,13.59375 6.7812502,-4.9375 -60.0000002,-43.59375 -13.59375,0 z m 99.0625,28.78125 -60,43.59375 -4.1875,12.9375 41.2812502,-30 56.4374998,41 -4.21875,-12.9375 -45.4062498,-33 18.7187498,-13.59375 -2.625,-8 z m -65.375,1.5625 -49.4375,16.0625 1.21875,3.8125 49.46875,-16.0625 -1.25,-3.8125 z m 84.9375,0 -1.25,3.8125 49.46875,16.0625 1.21875,-3.8125 -49.4375,-16.0625 z m -44.4687498,32.3125 0,52 4,0 0,-52 -4,0 z";
	private final Vector pathSize = new Vector(184, 193);

	public OverlayEthiopia(int maximumX, int maximumY) {
		super("ethiopia", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
