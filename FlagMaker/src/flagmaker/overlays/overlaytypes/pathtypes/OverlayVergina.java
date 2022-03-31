package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayVergina extends OverlayPath {
	private static final String PATH = "m 4.9e-6,-121 -7.5,89 a 7.5,7.5 0 0 0 15,0 L 4.9e-6,-121 z m -44.8437499,12.78125 21.875,67.124995 a 5.5,5.5 0 1 0 10.15625,-4.1875 l -32.03125,-62.937495 z m 89.6875,0 -32.03125,62.937495 a 5.5,5.5 0 1 0 10.15625,4.1875 l 21.875,-67.124995 z m -130.40625,22.656245 57.625,68.25 a 7.5130096,7.5130096 0 1 0 10.625,-10.625 l -68.25,-57.625 z m 171.125,0 -68.25,57.625 a 7.5130096,7.5130096 0 1 0 10.625,10.625 l 57.625,-68.25 z m -193.781255,40.71875 62.937505,32.03125 a 5.5,5.5 0 1 0 4.1875,-10.15625 l -67.125005,-21.875 z m 216.4375,0 -67.124995,21.875 a 5.5,5.5 0 1 0 4.1875,10.15625 l 62.937495,-32.03125 z M 4.9e-6,-19.5 c -10.7695499,0 -19.5,8.73045 -19.5,19.5 0,10.76954966 8.73045,19.5 19.5,19.5 10.7695501,0 19.5,-8.73045 19.5,-19.5 C 19.5,-10.769555 10.769555,-19.5 4.9e-6,-19.5 z M -32,-7.5 -121,-4.66e-6 -32,7.4999953 a 7.5,7.5 0 0 0 0,-15 z m 64,0 a 7.5,7.5 0 1 0 0,15 L 121,-4.66e-6 32,-7.5 z m 11.15625,19.8749997 a 5.5,5.5 0 0 0 -2.0625,10.59375 l 67.124995,21.875 -62.937495,-32.03125 a 5.5,5.5 0 0 0 -2.125,-0.4375 z m -86.625,0.03125 a 5.5,5.5 0 0 0 -1.8125,0.40625 l -62.937505,32.03125 67.125005,-21.875 a 5.5,5.5 0 0 0 -2.375,-10.5625 z m 20.53125,2.71875 a 7.5,7.5 0 0 0 -5,2.1875 l -57.625,68.25 68.25,-57.625 a 7.5,7.5 0 0 0 -5.625,-12.8125 z m 45.46875,0 a 7.5,7.5 0 0 0 -5.21875,12.8125 l 68.25,57.625 -57.625,-68.25 a 7.5,7.5 0 0 0 -5.40625,-2.1875 z m -22.75,9.375 a 7.5,7.5 0 0 0 -7.28124998,7.5 L 4.9e-6,121 l 7.5,-89 a 7.5,7.5 0 0 0 -7.71875002,-7.5 z m -17.875,13.1875 a 5.5,5.5 0 0 0 -4.875,3.40625 l -21.875,67.125005 32.03125,-62.937505 a 5.5,5.5 0 0 0 -5.28125,-7.59375 z m 35.875,0 a 5.5,5.5 0 0 0 -4.96875,7.59375 l 32.03125,62.937505 -21.875,-67.125005 a 5.5,5.5 0 0 0 -5.1875,-3.40625 z";
	private final Vector pathSize = new Vector(242, 242);

	public OverlayVergina(int maximumX, int maximumY) {
		super("vergina", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
