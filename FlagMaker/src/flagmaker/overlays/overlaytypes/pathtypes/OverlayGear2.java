package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayGear2 extends OverlayPath {
	private final String path = "M -1.9375 -24.65625 L -1.9375 -19.65625 C -3.243925 -19.53216 -4.499614 -19.27475 -5.71875 -18.90625 L -7.65625 -23.53125 L -11.21875 -22.03125 L -9.3125 -17.40625 C -10.45439 -16.79305 -11.539012 -16.10026 -12.53125 -15.28125 L -16.0625 -18.8125 L -18.8125 -16.0625 L -15.28125 -12.5 C -16.084275 -11.51286 -16.742862 -10.44514 -17.34375 -9.3125 L -22.03125 -11.25 L -23.53125 -7.65625 L -18.84375 -5.71875 C -19.206008 -4.49971 -19.444254 -3.24289 -19.5625 -1.9375 L -24.65625 -1.9375 L -24.65625 1.9375 L -19.53125 1.9375 C -19.398867 3.18432 -19.136882 4.3965 -18.78125 5.5625 L -23.5625 7.53125 L -22 11.3125 L -17.1875 9.34375 C -16.610342 10.38084 -15.936724 11.37067 -15.1875 12.28125 L -18.875 15.96875 L -15.96875 18.875 L -12.25 15.15625 C -11.334835 15.88625 -10.350535 16.53497 -9.3125 17.09375 L -11.34375 22 L -7.5625 23.5625 L -5.5 18.65625 C -4.38931 18.97765 -3.244646 19.19229 -2.0625 19.3125 L -2.0625 24.65625 L 2.0625 24.65625 L 2.0625 19.28125 C 3.244075 19.15433 4.390662 18.95297 5.5 18.625 L 7.5 23.5625 L 11.3125 22 L 9.28125 17.03125 C 10.310728 16.4704 11.280141 15.8235 12.1875 15.09375 L 15.96875 18.875 L 18.875 15.96875 L 15.125 12.21875 C 15.864157 11.31302 16.492827 10.31092 17.0625 9.28125 L 21.96875 11.34375 L 23.5625 7.5625 L 18.6875 5.53125 C 19.028448 4.40774 19.240854 3.22994 19.375 2.03125 L 24.65625 2.03125 L 24.65625 -2.0625 L 19.40625 -2.0625 C 19.287358 -3.29861 19.057085 -4.49786 18.71875 -5.65625 L 23.53125 -7.625 L 22.03125 -11.21875 L 17.28125 -9.25 C 16.684887 -10.38518 15.986511 -11.4472 15.1875 -12.4375 L 18.78125 -16.0625 L 16.0625 -18.8125 L 12.46875 -15.21875 C 11.489419 -16.03485 10.408317 -16.72894 9.28125 -17.34375 L 11.25 -22.03125 L 7.65625 -23.53125 L 5.71875 -18.875 C 4.499203 -19.25321 3.245828 -19.49195 1.9375 -19.625 L 1.9375 -24.65625 L -1.9375 -24.65625 z M -0.09375 -15.3125 C 8.277471 -15.3125 15.09375 -8.5587214 15.09375 -0.1875 C 15.09375 8.1836863 8.277471 15 -0.09375 15 C -8.464975 15.000035 -15.21875 8.1837215 -15.21875 -0.1875 C -15.21875 -8.5587564 -8.464975 -15.3125 -0.09375 -15.3125 z ";
	private final Vector pathSize = new Vector(50, 50);

	public OverlayGear2(int maximumX, int maximumY) {
		super("gear 2", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
