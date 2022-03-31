package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayArrowhead extends OverlayPath {
	private static final String PATH = "m 26.045907,61.862183 c 4.72841,-5.4362 5.91989,-4.9776 8,-6 l 6,-6 c -17,-5 -12.25694,-15.0956 3,-13 1.93204,0.2654 3.53768,0.2043 5,0 0.25664,-8.9165 -0.62981,-8.3072 -1,-12 -3.68041,-5.0849 -2.00898,-7.6513 -2,-11 -3.30001,-3.8641 -2.52444,-6.7876999 -3,-9.9999999 -4.0379,-4.72119972 -3.18672,-8.1386997 -4,-11.9999997 -4.13615,-3.9928004 -3.68631,-6.9066004 -4,-10.0000004 -4.74266,-9.3958 -7.25859,-11.5306 -7,-16 -4.40601,-2.9977 -6.64539,-6.8287 -8,-11 -4.81594,-2.0575 -6.39792,-4.4743 -7,-7 -5.1859898,-5.6123 -8.9999997,-5 -11.9999997,-10 -2.9999997,5 -6.8140098,4.3877 -12.0000003,10 -0.60208,2.5257 -2.18406,4.9425 -7,7 -1.35461,4.1713 -3.59399,8.0023 -8,11 0.25859,4.4694 -2.25734,6.6042 -7,16 -0.31369,3.0934 0.13615,6.0072 -4,10.0000004 -0.81328,3.8613 0.0379,7.27879998 -4,11.9999997 -0.47556,3.2123 0.30001,6.1358999 -3,9.9999999 0.009,3.3487 1.68041,5.9151 -2,11 -0.37019,3.6928 -1.25664,3.0835 -1,12 1.46232,0.2043 3.06796,0.2654 5,0 15.25694,-2.0956 20,8 3,13 l 6,6 c 2.08011,1.0224 3.27159,0.5638 8,6 2.78062,-3.7265 7.92598,-1.7776 13,0 4.3089605,-0.7308 6.7378206,-2.4395 13.0000003,-1 6.2621799,-1.4395 8.6910399,0.2692 12.9999997,1 5.07402,-1.7776 10.21938,-3.7265 13,0 z";
	private final Vector pathSize = new Vector(98, 127);

	public OverlayArrowhead(int maximumX, int maximumY) {
		super("arrowhead", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
