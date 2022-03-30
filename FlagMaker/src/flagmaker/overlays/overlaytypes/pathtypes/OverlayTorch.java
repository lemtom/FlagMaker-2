package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayTorch extends OverlayPath {
	private final String path = "m 28.308348,-149.1375 c -15.8207,0.778 -27.4854597,8.60932 -32.5624997,23.75 0.98391,-5.87144 -2.21481,-8.92821 -5.5937503,-16.90625 0.89896,7.08594 -5.56864,16.29425 -7.46875,25.65625 -1.91595,9.36673 2.55233,23.705406 8.9375,32.687496 l 20.8125,0.03125 c 24.56016,-26.957686 -12.5786597,-43.596876 15.875,-65.218746 z m 9.4375,11.78125 c -26.20629,12.21593 2.56488,26.17407 -20.03125,53.437496 l 8.84375,0 c 18.79549,-14.61964 10.42655,-24.886386 18.21875,-36.624996 -3.29282,2.3984 -8.48998,8.57544 -11.15625,11.84375 1.3843,-2.97557 1.98518,-6.4779 1.5,-10.75 -2.83076,-9.86474 3.58933,-9.84593 2.625,-17.90625 z m -65.59375,3.53125 c -0.37315,16.94652 -18.82382,25.76415 -0.6875,49.718746 0.028,0.05751 0.08384,0.08461 0.125,0.125 l 15.90625,0.03125 c -20.91016,-20.661726 -3.342,-27.593366 -15.34375,-49.874996 z m -7.28125,53.312496 -0.03125,6.71875 70.75,0.09375 0.03125,-6.71875 -70.75,-0.09375 z m 4.9375,11.3125 c 10.32576,10.7078 15.79657,18.5022 16.75,44.0625 9.1065303,0.004 18.2059903,0.04016 27.3125,0.0625 1.00752,-25.56266 6.4744,-33.34016 16.84375,-44.03125 -20.1215,-0.0474 -42.60926,-0.08072 -60.90625,-0.09375 z m 11.25,46.78125 0,8.34375 37.3125,0.0625 0.03125,-8.34375 -37.34375,-0.0625 z m 6.84375,10.5625 4.8750003,89.031254 2.75,58.25 c 0,0 0.95194,10.58139 4.46875,13.875 l 0.03125,-0.0312 c 3.51203,-3.2566 4.5,-13.84375 4.5,-13.84375 l 2.875,-58.21875 5.0937497,-89.000004 c -8.1980797,-0.0278 -16.3956497,-0.0506 -24.59375,-0.0625 z";
	private final Vector pathSize = new Vector(93, 300);

	public OverlayTorch(int maximumX, int maximumY) {
		super("torch", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
