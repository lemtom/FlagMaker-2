package flagmaker.Overlays.OverlayTypes.PathTypes;

import flagmaker.Data.Vector;

public class OverlayKey extends OverlayPath
{
	private final String Path = "m 185.39537,-18.446433 c -1.15925,-1.1 -2.63051,-2.108588 -4.58392,-3.005704 -7.20377,-0.203534 -8.342,3.791678 -12.4505,5.757414 l 0.0198,-1.745732 c 0.003,-0.378107 -0.13952,-0.726403 -0.48343,-1.006288 -0.57319,-0.466469 -1.71589,-0.722503 -3.77937,-0.665472 l -234.095843,0.0139 c -2.134901,-1.69398 -4.851455,-2.956873 -7.94483,-3.639026 1.518246,-3.936911 2.356529,-8.224368 2.356034,-12.697329 -0.0021,-19.437441 -15.720913,-35.195115 -35.124851,-35.193014 -16.39311,0.0018 -30.11951,11.248292 -33.99674,26.459586 -3.00648,-0.838555 -6.13886,-1.331614 -9.41227,-1.331254 -19.40392,0.0021 -35.1355,15.792195 -35.13342,35.229607 0.002,19.4374308 15.74346,35.173535 35.14739,35.171445 3.27341,-4e-4 6.38121,-0.482599 9.38751,-1.321794 3.88049,15.210445 17.61684,26.459088 34.00995,26.457318 19.403924,-0.002 35.157013,-15.769565 35.154938,-35.206996 -4.76e-4,-4.472931 -0.891499,-8.7426074 -2.410601,-12.679189 3.093233,-0.6828023 5.852474,-1.95979537 7.987004,-3.654236 l 167.126179,-0.009 0.02176,70.998053 22.19808,0.003 -0.004,-14.588634 -11.49652,0.0112 0.0315,-12.377383 11.47395,0.0103 -0.005,-11.781463 -11.49652,0.0112 0.009,-12.355833 11.475,-0.0339 -0.004,-9.9689193 12.35713,-0.0127 0.0256,9.9915503 11.47395,0.0103 0.0126,12.378453 -11.49651,0.0112 0.006,11.737282 11.47394,0.0103 0.0127,12.378453 -11.47395,-0.0103 -0.0177,14.566003 22.2196,0.0253 -0.0207,-71.0422334 10.17228,10e-4 c 3.30159,0.090602 4.26209,-0.6799223 4.25496,-1.6882106 l -0.0244,-1.7467915 c 4.1322,1.9599854 5.24142,5.96455776 12.4617,5.75896404 6.25032,-2.87215194 7.71204,-6.93713534 8.95964,-11.10197054 -0.85835,-2.863152 -1.8227,-5.708723 -4.37305,-8.128727 z M -92.948515,-53.203674 c 4.541912,4.548775 7.363622,10.807488 7.364361,17.749434 5.09e-4,4.796766 -1.363112,9.279127 -3.711311,13.084746 -0.01847,0.006 -0.02556,-0.008 -0.04412,-0.001 -5.155294,1.823403 -7.729318,6.165721 -11.498015,12.057778 3.769666,5.8915263 6.338298,10.25720517 11.493982,12.0795081 0.006,0.0101 0.03763,-0.009 0.04412,0.001 2.349028,3.8051092 3.724808,8.2676689 3.725327,13.0644259 9.98e-4,13.883861 -11.257022,25.146215 -25.116979,25.147705 -11.50473,10e-4 -21.14011,-7.774031 -24.09788,-18.350272 0.957,-1.173121 1.6013,-2.612027 1.60113,-4.205486 -4.1e-4,-3.799959 -3.27097,-6.871474 -7.22755,-6.871054 -2.47286,2.2e-4 -4.68002,1.196071 -5.96379,3.017614 -2.43616,0.796255 -5.00867,1.273383 -7.70937,1.273683 -13.85993,0.001 -25.10392,-11.2322722 -25.10542,-25.116143 -9.9e-4,-13.883871 11.23551,-25.168835 25.09546,-25.170325 2.74854,-3.1e-4 5.37207,0.493909 7.84598,1.315734 1.29793,1.739821 3.42976,2.903092 5.82206,2.902822 3.95653,-5e-4 7.2266,-3.099696 7.2262,-6.899665 -1.7e-4,-1.567908 -0.58134,-3.018664 -1.511,-4.169705 2.97983,-10.53882 12.54807,-18.276293 24.02577,-18.277516 6.92997,-7.48e-4 13.199143,2.817943 17.741055,7.366716 z";
	private final Vector PathSize = new Vector(383, 145);
	
	public OverlayKey(int maximumX, int maximumY)
	{
		super("key", maximumX, maximumY);
		Constructor(Path, PathSize);
	}	
}
