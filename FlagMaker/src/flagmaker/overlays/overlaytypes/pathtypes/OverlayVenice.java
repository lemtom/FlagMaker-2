package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayVenice extends OverlayPath {
	private final String path = "M 0.306528,-248.68023 C -136.74524,-248.68023 -248.5,-136.92546 -248.5,0.126303 c 0,137.051587 111.75476,248.235877 248.806528,248.235877 137.051732,0 248.235842,-111.18429 248.235842,-248.235877 0,-137.051763 -111.18411,-248.806533 -248.235842,-248.806533 z m 0,18.26103 c 1.128527,0.46749 2.243007,0.79984 3.422117,1.71106 35.01165,21.91835 50.9581,66.54758 61.63094,107.28355 13.35904,1.76767 27.12183,3.64454 41.087325,6.27723 -0.16435,-0.76331 -0.39992,-1.52114 -0.56975,-2.28263 -12.579665,-42.33765 -28.367415,-93.61261 -71.332155,-110.13683 99.828125,14.98883 178.253405,94.05946 192.882135,194.023443 -17.19805,-39.82165 -64.39716,-62.917473 -106.14223,-71.332153 -46.462465,-10.29904 -94.432545,-16.09454 -142.093611,-14.26643 -12.525971,0.73775 -25.282578,1.98114 -38.234031,3.42394 -3.464848,13.07435 -5.882608,26.53657 -7.9892,39.946013 21.15449,-2.52806 42.47899,-3.61423 63.913604,-3.42395 23.994413,0.21366 48.086003,1.99356 71.332113,4.56526 60.832785,3.44987 118.343895,27.19725 161.495985,69.04952 0.0548,1.89257 0.56974,3.80286 0.56974,5.70657 0.21001,3.43855 -0.33235,6.6338697 -0.71218,10.05781 l -3.28132,2.49665 -0.56975,0.56974 c -80.54757,64.03943 -192.174875,73.07772 -292.747097,61.06032 1.34949,9.60932 2.79412,19.09008 4.565258,28.532857 1.013487,3.81966 2.34289,8.04161 3.423943,11.9838 42.15577,4.3474 85.209031,3.21193 126.685861,0 56.568475,-7.99249 118.242355,-25.056317 154.648095,-71.332147 1.93658,-3.03443 2.45976,-3.62938 4.56526,-7.48994 C 212.7838,136.98286 134.29516,212.60489 33.404795,227.24603 c 20.39757,-6.94302 37.39256,-23.72491 46.22323,-42.22863 11.35343,-22.42765 19.902695,-46.07203 26.250235,-70.19083 -13.269935,3.08465 -26.915675,5.11875 -40.516665,6.84789 -6.1032,22.68038 -14.03762,44.98367 -24.53826,66.19623 -10.49498,15.53119 -20.93956,33.20257 -38.80465,41.65797 -0.95505,0.5807 0.59165,-0.19722 -1.711061,0.56975 -1.384187,-0.005 -3.019644,-0.47479 -3.994601,-1.13949 -50.044534,-36.70485 -62.893177,-104.15671 -71.902804,-162.637297 -9.524223,-87.98255 -3.884304,-183.899523 39.946002,-261.931643 8.735894,-13.74435 19.422066,-26.26721 33.098117,-34.23943 1.196097,-0.45835 0.872877,-0.18992 2.853286,-0.56975 z M -230.09032,8.4544327 c -0.0913,-2.76381 -0.14609,-5.5400297 -0.14609,-8.3281297 0,-8.53191 0.64279,-10.34323 0.64279,-10.34323 0,0 17.04082,-12.05428 23.32554,-16.47674 25.79187,-17.92174 54.4931,-29.97676 84.45726,-38.23403 1.78228,-13.84369 4.60854,-27.493083 7.41854,-41.087323 -44.21963,10.96538 -85.29818,30.505783 -112.41946,67.908213 16.41776,-97.351753 94.01545,-173.656923 191.74081,-188.887533 l -0.0033,-9.1e-4 c -44.32608,18.84994 -59.65877,72.35823 -71.90279,116.98472 -20.76444,97.324163 -16.81731,206.069143 27.391543,295.02976 9.323899,18.96463 27.528685,36.2381 47.364546,42.79929 C -131.84434,213.68138 -211.26375,135.98289 -227.28452,37.014863 l -0.12782,-0.336 c 24.31346,39.8505 73.17249,58.25305 112.44612,68.448357 -2.94167,-13.041657 -5.66056,-26.092997 -7.41855,-39.375337 -0.0548,-0.013 0.0548,-0.55696 0,-0.56974 -36.5436,-9.64676 -78.14003,-32.77034 -107.72929,-56.6732903 z M 75.062585,-67.781897 c 5.73779,44.59544 6.87034,90.1012 1.14131,134.67509 13.53672,-1.98406 26.848285,-4.56032 39.946005,-7.9892 4.64707,-39.31362 4.19584,-79.33833 -0.56974,-118.69669 -13.42551,-3.49827 -26.946895,-6.2387 -40.516665,-7.9892 z";
	private final Vector pathSize = new Vector(498, 498);

	public OverlayVenice(int maximumX, int maximumY) {
		super("venice", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
