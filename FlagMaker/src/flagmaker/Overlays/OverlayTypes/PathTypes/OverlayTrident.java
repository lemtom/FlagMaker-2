package flagmaker.Overlays.OverlayTypes.PathTypes;

import flagmaker.Data.Vector;

public class OverlayTrident extends OverlayPath
{
	private final String path = "m 0.05977064,-105.47812 c -6.72244004,19.28186 -13.56383964,39.331725 -28.22842964,54.715465 4.55045,-1.58194 12.52015,-2.99649 17.61692,-2.85472 l 0,81.06049 -21.74003,3.39848 c -0.78874,-0.0842 -1.05736,-1.36356 -1.05986,-3.11301 -2.08443,-25.1543 -7.75046,-46.32643 -14.25639,-68.18706 -0.46024,-2.99992 -8.69987,-14.41233 -2.35236,-12.41122 0.76456,0.10349 9.28839,3.75783 7.93601,2.0119 -11.5911,-12.60653 -28.50652,-21.6857 -44.95351,-24.38747 -1.45325,-0.39199 -2.305081,0.37558 -0.99523,2.14784 21.79229,35.32828 40.05071,77.00042 39.89979,126.45053 8.49113,0 29.03044,-5.26085 37.52158,-5.26085 0,0 0,57.189575 0,57.189575 l 21.22302,0 c 0,0 0,-57.189575 0,-57.189575 8.49112,0 29.03044,5.26085 37.52157,5.26085 -0.15092,-49.45011 18.10749,-91.12225 39.89979,-126.45053 1.30986,-1.77226 0.45803,-2.53983 -0.99524,-2.14784 -16.44698,2.70177 -33.36241,11.78094 -44.9535,24.38747 -1.35238,1.74593 7.17145,-1.90841 7.93602,-2.0119 6.34749,-2.00111 -1.89214,9.4113 -2.35238,12.41122 -6.50591,21.86063 -12.18488,43.03276 -14.26931,68.18706 -0.003,1.74945 -0.25818,3.02883 -1.04693,3.11301 l -21.74002,-3.39848 0,-81.06049 c 5.09675,-0.14177 13.06646,1.27278 17.61691,2.85472 C 13.623601,-66.146395 6.7822104,-86.19626 0.05977064,-105.47812 z";
	private final Vector pathSize = new Vector(180, 212);

	public OverlayTrident(int maximumX, int maximumY)
	{
		super("trident", maximumX, maximumY);
		constructor(path, pathSize);
	}
}
