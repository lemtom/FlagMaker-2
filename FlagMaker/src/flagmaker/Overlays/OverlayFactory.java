package flagmaker.Overlays;

import flagmaker.Files.FileHandler;
import flagmaker.Files.LocalizationHandler;
import flagmaker.MainWindowController;
import flagmaker.Overlays.OverlayTypes.*;
import flagmaker.Overlays.OverlayTypes.PathTypes.*;
import flagmaker.Overlays.OverlayTypes.RepeaterTypes.*;
import flagmaker.Overlays.OverlayTypes.ShapeTypes.*;
import flagmaker.Overlays.OverlayTypes.SpecialTypes.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.TreeMap;

public class OverlayFactory
{
	private static HashMap<String, String> typeMap;
	private static TreeMap<String, OverlayPath> customTypes;
	
	public static void setUpTypeMap()
	{
		typeMap = new HashMap<>();
		typeMap.put("border", "flagmaker.Overlays.OverlayTypes.OverlayBorder");
		typeMap.put("box", "flagmaker.Overlays.OverlayTypes.ShapeTypes.OverlayBox");
		typeMap.put("checkerboard", "flagmaker.Overlays.OverlayTypes.OverlayCheckerboard");
		typeMap.put("cross", "flagmaker.Overlays.OverlayTypes.OverlayCross");
		typeMap.put("diamond", "flagmaker.Overlays.OverlayTypes.ShapeTypes.OverlayDiamond");
		typeMap.put("ellipse", "flagmaker.Overlays.OverlayTypes.ShapeTypes.OverlayEllipse");
		typeMap.put("fimbriation backward", "flagmaker.Overlays.OverlayTypes.OverlayFimbriationBackward");
		typeMap.put("fimbriation forward", "flagmaker.Overlays.OverlayTypes.OverlayFimbriationForward");
		typeMap.put("half ellipse", "flagmaker.Overlays.OverlayTypes.OverlayHalfEllipse");
		typeMap.put("half saltire", "flagmaker.Overlays.OverlayTypes.OverlayHalfSaltire");
		typeMap.put("line", "flagmaker.Overlays.OverlayTypes.OverlayLine");
		typeMap.put("line horizontal", "flagmaker.Overlays.OverlayTypes.OverlayLineHorizontal");
		typeMap.put("line vertical", "flagmaker.Overlays.OverlayTypes.OverlayLineVertical");
		typeMap.put("pall", "flagmaker.Overlays.OverlayTypes.OverlayPall");
		typeMap.put("quadrilateral", "flagmaker.Overlays.OverlayTypes.OverlayQuadrilateral");
		typeMap.put("rays", "flagmaker.Overlays.OverlayTypes.OverlayRays");
		typeMap.put("ring", "flagmaker.Overlays.OverlayTypes.OverlayRing");
		typeMap.put("saltire", "flagmaker.Overlays.OverlayTypes.OverlaySaltire");
		typeMap.put("triangle", "flagmaker.Overlays.OverlayTypes.OverlayTriangle");
				
		typeMap.put("flag", "flagmaker.Overlays.OverlayTypes.SpecialTypes.OverlayFlag");
		typeMap.put("image", "flagmaker.Overlays.OverlayTypes.SpecialTypes.OverlayImage");
		
		typeMap.put("repeater lateral", "flagmaker.Overlays.OverlayTypes.RepeaterTypes.OverlayRepeaterLateral");
		typeMap.put("repeater radial", "flagmaker.Overlays.OverlayTypes.RepeaterTypes.OverlayRepeaterRadial");
		typeMap.put("transformer", "flagmaker.Overlays.OverlayTypes.RepeaterTypes.OverlayTransformer");
		
		typeMap.put("albania", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayAlbania");
		typeMap.put("albatross", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayAlbatross");
		typeMap.put("allahu", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayAllahu");
		typeMap.put("anchor", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayAnchor");
		typeMap.put("angola", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayAngola");
		typeMap.put("arevakhach", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayArevakhach");
		typeMap.put("arrowhead", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayArrowhead");
		typeMap.put("banner", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBanner");
		typeMap.put("banner 2", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBanner2");
		typeMap.put("bear", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBear");
		typeMap.put("beaver", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBeaver");
		typeMap.put("bee", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBee");
		typeMap.put("bhutan", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBhutan");
		typeMap.put("bison", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBison");
		typeMap.put("bolnisi cross", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBolnisiCross");
		typeMap.put("branches", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayBranches");
		typeMap.put("castle", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCastle");
		typeMap.put("cedar", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCedar");
		typeMap.put("chakra", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayChakra");
		typeMap.put("chicago", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayChicago");
		typeMap.put("coronet", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCoronet");
		typeMap.put("compass", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCompass");
		typeMap.put("condor", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCondor");
		typeMap.put("cpusa", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCpusa");
		typeMap.put("crescent", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCrescent");
		typeMap.put("crown", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCrown");
		typeMap.put("cross bottany", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCrossBottany");
		typeMap.put("cyprus", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayCyprus");
		typeMap.put("dove", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayDove");
		typeMap.put("eagle", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEagle");
		typeMap.put("eagle american", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEagleAmerican");
		typeMap.put("eagle outline", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEagleOutline");
		typeMap.put("eagle solid", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEagleSolid");
		typeMap.put("egypt", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEgypt");
		typeMap.put("equatorial cross", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEquatorialCross");
		typeMap.put("eritrea", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEritrea");
		typeMap.put("ermine", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayErmine");
		typeMap.put("ethiopia", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayEthiopia");
		typeMap.put("fasces", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayFasces");
		typeMap.put("fern", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayFern");
		typeMap.put("fire", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayFire");
		typeMap.put("flash", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayFlash");
		typeMap.put("fleur de lis", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayFleurDeLis");
		typeMap.put("forth international", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayForthInternational");
		typeMap.put("gear", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayGear");
		typeMap.put("gear 2", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayGear2");
		typeMap.put("globe", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayGlobe");
		typeMap.put("greenland", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayGreenland");
		typeMap.put("hammer and sickle", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayHammerSickle");
		typeMap.put("hand", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayHand");
		typeMap.put("harp", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayHarp");
		typeMap.put("iran", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayIran");
		typeMap.put("iron cross", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayIronCross");
		typeMap.put("kangaroo", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKangaroo");
		typeMap.put("kazakh banner", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKazakhBanner");
		typeMap.put("kazakh center", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKazakhCenter");
		typeMap.put("key", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKey");
		typeMap.put("key 2", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKey2");
		typeMap.put("keystone", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKeystone");
		typeMap.put("kiwi", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKiwi");
		typeMap.put("kosovo", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKosovo");
		typeMap.put("kuwait", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKuwait");
		typeMap.put("kyrgyzstan", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayKyrgyzstan");
		typeMap.put("laurel", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayLaurel");
		typeMap.put("laurel 2", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayLaurel2");
		typeMap.put("lesotho", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayLesotho");
		typeMap.put("lion", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayLion");
		typeMap.put("lotus", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayLotus");
		typeMap.put("malawi", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayMalawi");
		typeMap.put("maltese cross", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayMalteseCross");
		typeMap.put("manitoba", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayManitoba");
		typeMap.put("maple leaf", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayMapleLeaf");
		typeMap.put("maple triple", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayMapleTriple");
		typeMap.put("mexico", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayMexico");
		typeMap.put("mjolnir", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayMjolnir");
		typeMap.put("mozambique", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayMozambique");
		typeMap.put("nunavut", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayNunavut");
		typeMap.put("oak", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayOak");
		typeMap.put("occitania", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayOccitania");
		typeMap.put("octagram", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayOccitania");
		typeMap.put("oman", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayOman");
		typeMap.put("ontario", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayOntario");
		typeMap.put("palm", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayPalm");
		typeMap.put("papua new guinea", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayPapuaNewGuinea");
		typeMap.put("parteiadler", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayParteiadler");
		typeMap.put("pentagram", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayPentagram");
		typeMap.put("pine", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayPine");
		typeMap.put("reichsadler", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayReichsadler");
		typeMap.put("rooster", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayRooster");
		typeMap.put("rose", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayRose");
		typeMap.put("sagebrush", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySagebrush");
		typeMap.put("sagebrush 2", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySagebrush2");
		typeMap.put("saskatchewan", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySaskatchewan");
		typeMap.put("scotland", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayScotland");
		typeMap.put("shahadah", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShahadah");
		typeMap.put("shamrock 3", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShamrock3");
		typeMap.put("shamrock 4", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShamrock4");
		typeMap.put("shield", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield");
		typeMap.put("shield 2", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield2");
		typeMap.put("shield 3", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield3");
		typeMap.put("shield 4", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield4");
		typeMap.put("shield 5", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield5");
		typeMap.put("shield 6", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield6");
		typeMap.put("shield 7", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield7");
		typeMap.put("shield 8", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayShield8");
		typeMap.put("sikh", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySikh");
		typeMap.put("snake", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySnake");
		typeMap.put("springbok", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySpringbok");
		typeMap.put("star", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStar");
		typeMap.put("star eight", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStarEight");
		typeMap.put("star four", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStarFour");
		typeMap.put("star marshall", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStarMarshall");
		typeMap.put("star of david", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStarOfDavid");
		typeMap.put("star seven", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStarSeven");
		typeMap.put("star shadow", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStarShadow");
		typeMap.put("star six", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayStarSix");
		typeMap.put("sudan", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySudan");
		typeMap.put("sun", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySun");
		typeMap.put("swastika", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySwastika");
		typeMap.put("sword", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlaySword");
		typeMap.put("tajikistan", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayTajikistan");
		typeMap.put("takbir", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayTakbir");
		typeMap.put("torch", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayTorch");
		typeMap.put("tree", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayTree");
		typeMap.put("trident", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayTrident");
		typeMap.put("triskele", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayTriskele");
		typeMap.put("un", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayUN");
		typeMap.put("vanuatu", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayVanuatu");
		typeMap.put("venice", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayVenice");
		typeMap.put("vergina", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayVergina");
		typeMap.put("wales", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayWales");
		typeMap.put("wave", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayWave");
		typeMap.put("wheat", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayWheat");
		typeMap.put("yang", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayYang");
		typeMap.put("yin", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayYin");
		typeMap.put("zia", "flagmaker.Overlays.OverlayTypes.PathTypes.OverlayZia");
	}
	
	public static Overlay[] getShapes()
	{
		return new Overlay[]
		{
			new OverlayBorder(0, 0),
			new OverlayBox(0, 0),
			new OverlayCheckerboard(0, 0),
			new OverlayCross(0, 0),
			new OverlayDiamond(0, 0),
			new OverlayEllipse(0, 0),
			new OverlayFimbriationBackward(0, 0),
			new OverlayFimbriationForward(0, 0),
			new OverlayHalfEllipse(0, 0),
			new OverlayHalfSaltire(0, 0),
			new OverlayLine(0, 0),
			new OverlayLineHorizontal(0, 0),
			new OverlayLineVertical(0, 0),
			new OverlayPall(0, 0),
			new OverlayQuadrilateral(0, 0),
			new OverlayRays(0, 0),
			new OverlayRing(0, 0),
			new OverlaySaltire(0, 0),
			new OverlayTriangle(0, 0)
		};
	}
	
	public static Overlay[] getEmblems()
	{
		return new Overlay[]
		{
			new OverlayAlbania(0, 0),
			new OverlayAlbatross(0, 0),
			new OverlayAllahu(0, 0),
			new OverlayAnchor(0, 0),
			new OverlayAngola(0, 0),
			new OverlayArevakhach(0, 0),
			new OverlayArrowhead(0, 0),
			new OverlayBanner(0, 0),
			new OverlayBanner2(0, 0),
			new OverlayBear(0, 0),
			new OverlayBeaver(0, 0),
			new OverlayBee(0, 0),
			new OverlayBhutan(0, 0),
			new OverlayBison(0, 0),
			new OverlayBolnisiCross(0, 0),
			new OverlayBranches(0, 0),
			new OverlayCastle(0, 0),
			new OverlayCedar(0, 0),
			new OverlayChakra(0, 0),
			new OverlayChicago(0, 0),
			new OverlayCompass(0, 0),
			new OverlayCondor(0, 0),
			new OverlayCoronet(0, 0),
			new OverlayCpusa(0, 0),
			new OverlayCrescent(0, 0),
			new OverlayCrossBottany(0, 0),
			new OverlayCrown(0, 0),
			new OverlayCyprus(0, 0),
			new OverlayDove(0, 0),
			new OverlayEagle(0, 0),
			new OverlayEagleAmerican(0, 0),
			new OverlayEagleOutline(0, 0),
			new OverlayEagleSolid(0, 0),
			new OverlayEgypt(0, 0),
			new OverlayEquatorialCross(0, 0),
			new OverlayEritrea(0, 0),
			new OverlayErmine(0, 0),
			new OverlayEthiopia(0, 0),
			new OverlayFasces(0, 0),
			new OverlayFern(0, 0),
			new OverlayFire(0, 0),
			new OverlayFlash(0, 0),
			new OverlayFleurDeLis(0, 0),
			new OverlayForthInternational(0, 0),
			new OverlayGear(0, 0),
			new OverlayGear2(0, 0),
			new OverlayGlobe(0, 0),
			new OverlayGreenland(0, 0),
			new OverlayHammerSickle(0, 0),
			new OverlayHand(0, 0),
			new OverlayHarp(0, 0),
			new OverlayIran(0, 0),
			new OverlayIronCross(0, 0),
			new OverlayKangaroo(0, 0),
			new OverlayKazakhBanner(0, 0),
			new OverlayKazakhCenter(0, 0),
			new OverlayKey(0, 0),
			new OverlayKey2(0, 0),
			new OverlayKeystone(0, 0),
			new OverlayKiwi(0, 0),
			new OverlayKosovo(0, 0),
			new OverlayKuwait(0, 0),
			new OverlayKyrgyzstan(0, 0),
			new OverlayLaurel(0, 0),
			new OverlayLaurel2(0, 0),
			new OverlayLesotho(0, 0),
			new OverlayLion(0, 0),
			new OverlayLotus(0, 0),
			new OverlayMalawi(0, 0),
			new OverlayMalteseCross(0, 0),
			new OverlayManitoba(0, 0),
			new OverlayMapleLeaf(0, 0),
			new OverlayMapleTriple(0, 0),
			new OverlayMexico(0, 0),
			new OverlayMjolnir(0, 0),
			new OverlayMozambique(0, 0),
			new OverlayNunavut(0, 0),
			new OverlayOak(0, 0),
			new OverlayOccitania(0, 0),
			new OverlayOctagram(0, 0),
			new OverlayOman(0, 0),
			new OverlayOntario(0, 0),
			new OverlayPalm(0, 0),
			new OverlayPapuaNewGuinea(0, 0),
			new OverlayParteiadler(0, 0),
			new OverlayPentagram(0, 0),
			new OverlayPine(0, 0),
			new OverlayReichsadler(0, 0),
			new OverlayRooster(0, 0),
			new OverlayRose(0, 0),
			new OverlaySagebrush(0, 0),
			new OverlaySagebrush2(0, 0),
			new OverlaySaskatchewan(0, 0),
			new OverlayScotland(0, 0),
			new OverlayShahadah(0, 0),
			new OverlayShamrock3(0, 0),
			new OverlayShamrock4(0, 0),
			new OverlayShield(0, 0),
			new OverlayShield2(0, 0),
			new OverlayShield3(0, 0),
			new OverlayShield4(0, 0),
			new OverlayShield5(0, 0),
			new OverlayShield6(0, 0),
			new OverlayShield7(0, 0),
			new OverlayShield8(0, 0),
			new OverlaySikh(0, 0),
			new OverlaySnake(0, 0),
			new OverlaySpringbok(0, 0),
			new OverlayStar(0, 0),
			new OverlayStarEight(0, 0),
			new OverlayStarFour(0, 0),
			new OverlayStarMarshall(0, 0),
			new OverlayStarOfDavid(0, 0),
			new OverlayStarSeven(0, 0),
			new OverlayStarShadow(0, 0),
			new OverlayStarSix(0, 0),
			new OverlaySudan(0, 0),
			new OverlaySun(0, 0),
			new OverlaySwastika(0, 0),
			new OverlaySword(0, 0),
			new OverlayTajikistan(0, 0),
			new OverlayTakbir(0, 0),
			new OverlayTorch(0, 0),
			new OverlayTree(0, 0),
			new OverlayTrident(0, 0),
			new OverlayTriskele(0, 0),
			new OverlayUN(0, 0),
			new OverlayVanuatu(0, 0),
			new OverlayVenice(0, 0),
			new OverlayVergina(0, 0),
			new OverlayWales(0, 0),
			new OverlayWave(0, 0),
			new OverlayWheat(0, 0),
			new OverlayYang(0, 0),
			new OverlayYin(0, 0),
			new OverlayZia(0, 0)
		};
	}
	
	public static Overlay[] getCustom()
	{
		Overlay[] returnValue = new Overlay[]{};
		return customTypes.values().toArray(returnValue);
	}
	
	public static Overlay[] getSpecial()
	{
		return new Overlay[]
		{
			new OverlayFlag(0, 0),
			new OverlayImage(0, 0),
			new OverlayRepeaterLateral(0, 0),
			new OverlayRepeaterRadial(0, 0),
			new OverlayTransformer(0, 0)
		};
	}
	
	public static void fillCustomOverlays()
	{
		customTypes = new TreeMap<String, OverlayPath>() {};
		
		File directory;
		try
		{
			directory = new File(String.format("%s%sCustom", new File(MainWindowController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent(), FileHandler.getPathSeparator()));
			File[] files = directory.listFiles();
			if (files == null) return;
			
			for (File file : files)
			{
				OverlayPath overlay = FileHandler.loadOverlayFromFile(file);
				
				if (customTypes.containsKey(overlay.name) || typeMap.containsKey(overlay.name))
				{
					throw new Exception(String.format(LocalizationHandler.get("OverlayNameExists"), overlay.name));
				}
				
				customTypes.put(overlay.name, overlay);
			}
		}
		catch (Exception ex)
		{
		}
	}
	
	public static Overlay getFlagInstance(File path, int maximumX, int maximumY) throws Exception
	{
		return new OverlayFlag(FileHandler.loadFlagFromFile(path), path, maximumX, maximumY);
	}
	
	public static Overlay getImageInstance(File path, int maximumX, int maximumY)
	{
		return new OverlayImage(path, maximumX, maximumY);
	}
	
	public static Overlay getInstanceByLongName(String name, int defaultMaximumX, int defaultMaximumY)
	{
		try
		{
			Class c = Class.forName(name);
			return getInstance(c, defaultMaximumX, defaultMaximumY);
		}
		catch (Exception e)
		{
			return null;
		}		
	}

	public static Overlay getInstanceByShortName(String name, int defaultMaximumX, int defaultMaximumY)
	{
		if (customTypes.containsKey(name))
		{
			OverlayPath overlayCopy = customTypes.get(name).copy();
			overlayCopy.setMaximum(defaultMaximumX, defaultMaximumY);
			return overlayCopy;
		}
		
		try
		{
			Class c = Class.forName(typeMap.get(name));
			return getInstance(c, defaultMaximumX, defaultMaximumY);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	private static Overlay getInstance(Class c, int defaultMaximumX, int defaultMaximumY)
	{
		try
		{
			Constructor<Overlay> constructor = c.getDeclaredConstructor(new Class[] { int.class, int.class });
			Overlay o = constructor.newInstance(defaultMaximumX, defaultMaximumY);
			return o;
		}
		catch (Exception ex)
		{
			return null;
		}
	}
}
