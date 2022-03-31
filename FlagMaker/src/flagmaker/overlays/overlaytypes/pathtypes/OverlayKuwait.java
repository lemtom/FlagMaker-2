package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;

public class OverlayKuwait extends OverlayPath {
	private static final String PATH = "m 113.61858,-57.679853 c -13.03232,0.235374 -26.106147,0.0044 -39.116065,0.725177 -0.07323,7.384738 0.467881,14.874803 -1.116582,22.15486 -1.971695,10.923346 -7.12363,21.749829 -16.311732,28.5124785 -2.822357,-8.6097375 -8.393287,-17.0797855 -17.351616,-20.3278355 -6.768726,-3.2972 -15.687871,0.65003 -18.061521,7.610838 -1.841217,6.037454 -0.392777,12.5440153 2.181439,18.14878505 C 27.527812,7.1670124 35.349226,12.51832 43.52717,15.466213 40.219869,21.92274 35.404375,27.711607 29.143592,31.562677 18.365815,38.428312 5.0048391,40.184868 -7.5627132,39.198157 -18.732597,35.205916 -27.862971,26.682276 -33.438522,16.416688 c -0.606157,-0.08859 -1.803599,-0.261678 -2.409748,-0.350267 -1.803341,14.534377 8.239856,27.582658 19.659695,35.572393 18.3838564,8.497534 40.568424,3.393602 56.216168,-8.385299 C 48.296407,35.37073 54.467665,24.903153 55.171017,13.387489 61.804191,9.8106781 68.029073,5.3526992 72.701,-0.58096795 82.006592,-11.975342 85.618049,-26.713065 86.729629,-41.034225 c 8.961484,-0.158153 17.926261,0.05765 26.888951,-0.09857 0.0158,-5.510313 0.0209,-11.037234 0,-16.54706 z m -183.766973,18.822919 c -0.664333,0.03169 -1.341682,0.164687 -2.024473,0.415393 -5.764217,2.634155 -7.281202,10.194715 -4.676802,15.522659 1.813171,2.862561 5.065961,5.687749 8.741803,4.504193 4.576865,-1.71491 7.682614,-6.495005 6.729816,-11.396894 -0.14744,-4.498233 -4.120002,-9.26715 -8.770344,-9.045351 z m -21.275707,0.0264 c -0.382672,0.01702 -0.772213,0.06339 -1.166523,0.140811 -4.670493,0.468788 -8.037997,5.37065 -7.912377,9.80925 -0.26218,5.075154 4.058843,10.753342 9.578331,10.145437 4.651386,0.0424 8.227482,-4.407982 8.770344,-8.682761 0.778776,-5.377972 -3.529686,-11.66818 -9.269775,-11.412737 z m 63.067276,9.089354 c -7.478546,8.517398 -15.819532,16.632968 -26.045261,21.8749965 -9.100682,4.8656071 -20.146363,5.1070299 -29.997892,2.5962037 -11.387116,-2.9798087 -19.022473,-12.5254422 -29.050753,-17.9674892 1.501,9.797408 8.12838,18.0373783 15.696359,24.17725835 12.566356,9.32663765 29.39239,13.83567365 44.825604,9.83741265 10.358002,-2.4460012 18.966381,-9.2939188 25.326438,-17.5151366 5.71723,1.5277281 11.626366,2.8849554 17.590626,2.4113902 9.25452426,-1.2410807 18.206826,-6.9268076 21.636014,-15.7532366 -4.4676055,0.02642 -8.7569781,1.647996 -13.2455933,1.464436 -10.0991787,0.422187 -19.2534897,-4.939335 -26.7355417,-11.125835 z m 64.223098,14.841486 c 1.530099,-0.03581 3.110627,0.998335 4.337905,1.802381 3.443418,2.917244 7.087406,6.5020339 7.305943,11.2648844 0.315097,2.542813 -2.683118,4.7375758 -5.022837,3.5554787 -5.043516,-2.29681615 -9.228512,-7.0565982 -10.045673,-12.5673851 -0.444347,-1.599473 0.597892,-2.877341 1.928156,-3.629406 0.484035,-0.294277 0.98647,-0.414009 1.496506,-0.425953 z M 16.138798,4.3826211 c -0.691355,0.00204 -1.398435,0.090709 -2.111874,0.2763417 C 6.4324895,5.8012612 3.3472172,16.6176 9.1931541,21.54045 13.589424,26.696329 22.558135,24.561225 24.955517,18.516533 28.108284,12.404795 22.821899,4.3629001 16.138798,4.3826211 Z M -6.1750119,7.0861934 c -0.7310152,-0.048751 -1.4855586,0.011126 -2.2527848,0.1971356 -3.3153103,0.5341754 -5.6961233,3.385119 -6.9010463,6.306576 -1.899127,4.876851 1.026518,10.725024 5.7416551,12.71348 C -4.1372616,28.360311 1.8154752,23.501343 2.0798487,18.076498 2.9103753,13.092233 -1.0579042,7.4274518 -6.1750119,7.0861934 Z";
	private final Vector pathSize = new Vector(251, 131);

	public OverlayKuwait(int maximumX, int maximumY) {
		super("kuwait", maximumX, maximumY);
		constructor(PATH, pathSize);
	}
}
