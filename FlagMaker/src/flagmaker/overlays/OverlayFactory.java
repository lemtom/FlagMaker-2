package flagmaker.overlays;

import flagmaker.MainWindowController;
import flagmaker.files.FileHandler;
import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.overlaytypes.*;
import flagmaker.overlays.overlaytypes.pathtypes.*;
import flagmaker.overlays.overlaytypes.repeatertypes.*;
import flagmaker.overlays.overlaytypes.shapetypes.*;
import flagmaker.overlays.overlaytypes.specialtypes.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.TreeMap;

public class OverlayFactory {
	private static HashMap<String, String> typeMap;
	private static TreeMap<String, OverlayPath> customTypes;

	private OverlayFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static void setUpTypeMap() {
		typeMap = new HashMap<>();
		typeMap.put("border", "flagmaker.overlays.overlaytypes.OverlayBorder");
		typeMap.put("box", "flagmaker.overlays.overlaytypes.ShapeTypes.OverlayBox");
		typeMap.put("checkerboard", "flagmaker.overlays.overlaytypes.OverlayCheckerboard");
		typeMap.put("cross", "flagmaker.overlays.overlaytypes.OverlayCross");
		typeMap.put("diamond", "flagmaker.overlays.overlaytypes.ShapeTypes.OverlayDiamond");
		typeMap.put("ellipse", "flagmaker.overlays.overlaytypes.ShapeTypes.OverlayEllipse");
		typeMap.put("fimbriation backward", "flagmaker.overlays.overlaytypes.OverlayFimbriationBackward");
		typeMap.put("fimbriation forward", "flagmaker.overlays.overlaytypes.OverlayFimbriationForward");
		typeMap.put("half ellipse", "flagmaker.overlays.overlaytypes.OverlayHalfEllipse");
		typeMap.put("half saltire", "flagmaker.overlays.overlaytypes.OverlayHalfSaltire");
		typeMap.put("line", "flagmaker.overlays.overlaytypes.OverlayLine");
		typeMap.put("line horizontal", "flagmaker.overlays.overlaytypes.OverlayLineHorizontal");
		typeMap.put("line vertical", "flagmaker.overlays.overlaytypes.OverlayLineVertical");
		typeMap.put("pall", "flagmaker.overlays.overlaytypes.OverlayPall");
		typeMap.put("quadrilateral", "flagmaker.overlays.overlaytypes.OverlayQuadrilateral");
		typeMap.put("rays", "flagmaker.overlays.overlaytypes.OverlayRays");
		typeMap.put("ring", "flagmaker.overlays.overlaytypes.OverlayRing");
		typeMap.put("saltire", "flagmaker.overlays.overlaytypes.OverlaySaltire");
		typeMap.put("triangle", "flagmaker.overlays.overlaytypes.OverlayTriangle");

		typeMap.put("flag", "flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag");
		typeMap.put("image", "flagmaker.overlays.overlaytypes.specialtypes.OverlayImage");

		typeMap.put("repeater lateral", "flagmaker.overlays.overlaytypes.repeatertypes.OverlayRepeaterLateral");
		typeMap.put("repeater radial", "flagmaker.overlays.overlaytypes.repeatertypes.OverlayRepeaterRadial");
		typeMap.put("transformer", "flagmaker.overlays.overlaytypes.repeatertypes.OverlayTransformer");

		typeMap.put("albania", "flagmaker.overlays.overlaytypes.pathtypes.OverlayAlbania");
		typeMap.put("albatross", "flagmaker.overlays.overlaytypes.pathtypes.OverlayAlbatross");
		typeMap.put("allahu", "flagmaker.overlays.overlaytypes.pathtypes.OverlayAllahu");
		typeMap.put("anchor", "flagmaker.overlays.overlaytypes.pathtypes.OverlayAnchor");
		typeMap.put("angola", "flagmaker.overlays.overlaytypes.pathtypes.OverlayAngola");
		typeMap.put("arevakhach", "flagmaker.overlays.overlaytypes.pathtypes.OverlayArevakhach");
		typeMap.put("arrowhead", "flagmaker.overlays.overlaytypes.pathtypes.OverlayArrowhead");
		typeMap.put("banner", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBanner");
		typeMap.put("banner 2", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBanner2");
		typeMap.put("bear", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBear");
		typeMap.put("beaver", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBeaver");
		typeMap.put("bee", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBee");
		typeMap.put("bhutan", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBhutan");
		typeMap.put("bison", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBison");
		typeMap.put("bolnisi cross", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBolnisiCross");
		typeMap.put("branches", "flagmaker.overlays.overlaytypes.pathtypes.OverlayBranches");
		typeMap.put("castle", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCastle");
		typeMap.put("cedar", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCedar");
		typeMap.put("chakra", "flagmaker.overlays.overlaytypes.pathtypes.OverlayChakra");
		typeMap.put("chicago", "flagmaker.overlays.overlaytypes.pathtypes.OverlayChicago");
		typeMap.put("coronet", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCoronet");
		typeMap.put("compass", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCompass");
		typeMap.put("condor", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCondor");
		typeMap.put("cpusa", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCpusa");
		typeMap.put("crescent", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCrescent");
		typeMap.put("crown", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCrown");
		typeMap.put("cross bottany", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCrossBottany");
		typeMap.put("cyprus", "flagmaker.overlays.overlaytypes.pathtypes.OverlayCyprus");
		typeMap.put("dove", "flagmaker.overlays.overlaytypes.pathtypes.OverlayDove");
		typeMap.put("eagle", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEagle");
		typeMap.put("eagle american", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEagleAmerican");
		typeMap.put("eagle outline", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEagleOutline");
		typeMap.put("eagle solid", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEagleSolid");
		typeMap.put("egypt", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEgypt");
		typeMap.put("equatorial cross", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEquatorialCross");
		typeMap.put("eritrea", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEritrea");
		typeMap.put("ermine", "flagmaker.overlays.overlaytypes.pathtypes.OverlayErmine");
		typeMap.put("ethiopia", "flagmaker.overlays.overlaytypes.pathtypes.OverlayEthiopia");
		typeMap.put("fasces", "flagmaker.overlays.overlaytypes.pathtypes.OverlayFasces");
		typeMap.put("fern", "flagmaker.overlays.overlaytypes.pathtypes.OverlayFern");
		typeMap.put("fire", "flagmaker.overlays.overlaytypes.pathtypes.OverlayFire");
		typeMap.put("flash", "flagmaker.overlays.overlaytypes.pathtypes.OverlayFlash");
		typeMap.put("fleur de lis", "flagmaker.overlays.overlaytypes.pathtypes.OverlayFleurDeLis");
		typeMap.put("forth international", "flagmaker.overlays.overlaytypes.pathtypes.OverlayForthInternational");
		typeMap.put("gear", "flagmaker.overlays.overlaytypes.pathtypes.OverlayGear");
		typeMap.put("gear 2", "flagmaker.overlays.overlaytypes.pathtypes.OverlayGear2");
		typeMap.put("globe", "flagmaker.overlays.overlaytypes.pathtypes.OverlayGlobe");
		typeMap.put("greenland", "flagmaker.overlays.overlaytypes.pathtypes.OverlayGreenland");
		typeMap.put("hammer and sickle", "flagmaker.overlays.overlaytypes.pathtypes.OverlayHammerSickle");
		typeMap.put("hand", "flagmaker.overlays.overlaytypes.pathtypes.OverlayHand");
		typeMap.put("harp", "flagmaker.overlays.overlaytypes.pathtypes.OverlayHarp");
		typeMap.put("iran", "flagmaker.overlays.overlaytypes.pathtypes.OverlayIran");
		typeMap.put("iron cross", "flagmaker.overlays.overlaytypes.pathtypes.OverlayIronCross");
		typeMap.put("kangaroo", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKangaroo");
		typeMap.put("kazakh banner", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKazakhBanner");
		typeMap.put("kazakh center", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKazakhCenter");
		typeMap.put("key", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKey");
		typeMap.put("key 2", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKey2");
		typeMap.put("keystone", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKeystone");
		typeMap.put("kiwi", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKiwi");
		typeMap.put("kosovo", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKosovo");
		typeMap.put("kuwait", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKuwait");
		typeMap.put("kyrgyzstan", "flagmaker.overlays.overlaytypes.pathtypes.OverlayKyrgyzstan");
		typeMap.put("laurel", "flagmaker.overlays.overlaytypes.pathtypes.OverlayLaurel");
		typeMap.put("laurel 2", "flagmaker.overlays.overlaytypes.pathtypes.OverlayLaurel2");
		typeMap.put("lesotho", "flagmaker.overlays.overlaytypes.pathtypes.OverlayLesotho");
		typeMap.put("lion", "flagmaker.overlays.overlaytypes.pathtypes.OverlayLion");
		typeMap.put("lotus", "flagmaker.overlays.overlaytypes.pathtypes.OverlayLotus");
		typeMap.put("malawi", "flagmaker.overlays.overlaytypes.pathtypes.OverlayMalawi");
		typeMap.put("maltese cross", "flagmaker.overlays.overlaytypes.pathtypes.OverlayMalteseCross");
		typeMap.put("manitoba", "flagmaker.overlays.overlaytypes.pathtypes.OverlayManitoba");
		typeMap.put("maple leaf", "flagmaker.overlays.overlaytypes.pathtypes.OverlayMapleLeaf");
		typeMap.put("maple triple", "flagmaker.overlays.overlaytypes.pathtypes.OverlayMapleTriple");
		typeMap.put("mexico", "flagmaker.overlays.overlaytypes.pathtypes.OverlayMexico");
		typeMap.put("mjolnir", "flagmaker.overlays.overlaytypes.pathtypes.OverlayMjolnir");
		typeMap.put("mozambique", "flagmaker.overlays.overlaytypes.pathtypes.OverlayMozambique");
		typeMap.put("nunavut", "flagmaker.overlays.overlaytypes.pathtypes.OverlayNunavut");
		typeMap.put("oak", "flagmaker.overlays.overlaytypes.pathtypes.OverlayOak");
		typeMap.put("occitania", "flagmaker.overlays.overlaytypes.pathtypes.OverlayOccitania");
		typeMap.put("octagram", "flagmaker.overlays.overlaytypes.pathtypes.OverlayOccitania");
		typeMap.put("oman", "flagmaker.overlays.overlaytypes.pathtypes.OverlayOman");
		typeMap.put("ontario", "flagmaker.overlays.overlaytypes.pathtypes.OverlayOntario");
		typeMap.put("palm", "flagmaker.overlays.overlaytypes.pathtypes.OverlayPalm");
		typeMap.put("papua new guinea", "flagmaker.overlays.overlaytypes.pathtypes.OverlayPapuaNewGuinea");
		typeMap.put("parteiadler", "flagmaker.overlays.overlaytypes.pathtypes.OverlayParteiadler");
		typeMap.put("pentagram", "flagmaker.overlays.overlaytypes.pathtypes.OverlayPentagram");
		typeMap.put("pine", "flagmaker.overlays.overlaytypes.pathtypes.OverlayPine");
		typeMap.put("reichsadler", "flagmaker.overlays.overlaytypes.pathtypes.OverlayReichsadler");
		typeMap.put("rooster", "flagmaker.overlays.overlaytypes.pathtypes.OverlayRooster");
		typeMap.put("rose", "flagmaker.overlays.overlaytypes.pathtypes.OverlayRose");
		typeMap.put("sagebrush", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySagebrush");
		typeMap.put("sagebrush 2", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySagebrush2");
		typeMap.put("saskatchewan", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySaskatchewan");
		typeMap.put("scotland", "flagmaker.overlays.overlaytypes.pathtypes.OverlayScotland");
		typeMap.put("shahadah", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShahadah");
		typeMap.put("shamrock 3", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShamrock3");
		typeMap.put("shamrock 4", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShamrock4");
		typeMap.put("shield", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield");
		typeMap.put("shield 2", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield2");
		typeMap.put("shield 3", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield3");
		typeMap.put("shield 4", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield4");
		typeMap.put("shield 5", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield5");
		typeMap.put("shield 6", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield6");
		typeMap.put("shield 7", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield7");
		typeMap.put("shield 8", "flagmaker.overlays.overlaytypes.pathtypes.OverlayShield8");
		typeMap.put("sikh", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySikh");
		typeMap.put("snake", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySnake");
		typeMap.put("springbok", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySpringbok");
		typeMap.put("star", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStar");
		typeMap.put("star eight", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStarEight");
		typeMap.put("star four", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStarFour");
		typeMap.put("star marshall", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStarMarshall");
		typeMap.put("star of david", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStarOfDavid");
		typeMap.put("star seven", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStarSeven");
		typeMap.put("star shadow", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStarShadow");
		typeMap.put("star six", "flagmaker.overlays.overlaytypes.pathtypes.OverlayStarSix");
		typeMap.put("sudan", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySudan");
		typeMap.put("sun", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySun");
		typeMap.put("swastika", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySwastika");
		typeMap.put("sword", "flagmaker.overlays.overlaytypes.pathtypes.OverlaySword");
		typeMap.put("tajikistan", "flagmaker.overlays.overlaytypes.pathtypes.OverlayTajikistan");
		typeMap.put("takbir", "flagmaker.overlays.overlaytypes.pathtypes.OverlayTakbir");
		typeMap.put("torch", "flagmaker.overlays.overlaytypes.pathtypes.OverlayTorch");
		typeMap.put("tree", "flagmaker.overlays.overlaytypes.pathtypes.OverlayTree");
		typeMap.put("trident", "flagmaker.overlays.overlaytypes.pathtypes.OverlayTrident");
		typeMap.put("triskele", "flagmaker.overlays.overlaytypes.pathtypes.OverlayTriskele");
		typeMap.put("un", "flagmaker.overlays.overlaytypes.pathtypes.OverlayUN");
		typeMap.put("vanuatu", "flagmaker.overlays.overlaytypes.pathtypes.OverlayVanuatu");
		typeMap.put("venice", "flagmaker.overlays.overlaytypes.pathtypes.OverlayVenice");
		typeMap.put("vergina", "flagmaker.overlays.overlaytypes.pathtypes.OverlayVergina");
		typeMap.put("wales", "flagmaker.overlays.overlaytypes.pathtypes.OverlayWales");
		typeMap.put("wave", "flagmaker.overlays.overlaytypes.pathtypes.OverlayWave");
		typeMap.put("wheat", "flagmaker.overlays.overlaytypes.pathtypes.OverlayWheat");
		typeMap.put("yang", "flagmaker.overlays.overlaytypes.pathtypes.OverlayYang");
		typeMap.put("yin", "flagmaker.overlays.overlaytypes.pathtypes.OverlayYin");
		typeMap.put("zia", "flagmaker.overlays.overlaytypes.pathtypes.OverlayZia");
	}

	public static Overlay[] getShapes() {
		return new Overlay[] { new OverlayBorder(0, 0), new OverlayBox(0, 0), new OverlayCheckerboard(0, 0),
				new OverlayCross(0, 0), new OverlayDiamond(0, 0), new OverlayEllipse(0, 0),
				new OverlayFimbriationBackward(0, 0), new OverlayFimbriationForward(0, 0), new OverlayHalfEllipse(0, 0),
				new OverlayHalfSaltire(0, 0), new OverlayLine(0, 0), new OverlayLineHorizontal(0, 0),
				new OverlayLineVertical(0, 0), new OverlayPall(0, 0), new OverlayQuadrilateral(0, 0),
				new OverlayRays(0, 0), new OverlayRing(0, 0), new OverlaySaltire(0, 0), new OverlayTriangle(0, 0) };
	}

	public static Overlay[] getEmblems() {
		return new Overlay[] { new OverlayAlbania(0, 0), new OverlayAlbatross(0, 0), new OverlayAllahu(0, 0),
				new OverlayAnchor(0, 0), new OverlayAngola(0, 0), new OverlayArevakhach(0, 0),
				new OverlayArrowhead(0, 0), new OverlayBanner(0, 0), new OverlayBanner2(0, 0), new OverlayBear(0, 0),
				new OverlayBeaver(0, 0), new OverlayBee(0, 0), new OverlayBhutan(0, 0), new OverlayBison(0, 0),
				new OverlayBolnisiCross(0, 0), new OverlayBranches(0, 0), new OverlayCastle(0, 0),
				new OverlayCedar(0, 0), new OverlayChakra(0, 0), new OverlayChicago(0, 0), new OverlayCompass(0, 0),
				new OverlayCondor(0, 0), new OverlayCoronet(0, 0), new OverlayCpusa(0, 0), new OverlayCrescent(0, 0),
				new OverlayCrossBottany(0, 0), new OverlayCrown(0, 0), new OverlayCyprus(0, 0), new OverlayDove(0, 0),
				new OverlayEagle(0, 0), new OverlayEagleAmerican(0, 0), new OverlayEagleOutline(0, 0),
				new OverlayEagleSolid(0, 0), new OverlayEgypt(0, 0), new OverlayEquatorialCross(0, 0),
				new OverlayEritrea(0, 0), new OverlayErmine(0, 0), new OverlayEthiopia(0, 0), new OverlayFasces(0, 0),
				new OverlayFern(0, 0), new OverlayFire(0, 0), new OverlayFlash(0, 0), new OverlayFleurDeLis(0, 0),
				new OverlayForthInternational(0, 0), new OverlayGear(0, 0), new OverlayGear2(0, 0),
				new OverlayGlobe(0, 0), new OverlayGreenland(0, 0), new OverlayHammerSickle(0, 0),
				new OverlayHand(0, 0), new OverlayHarp(0, 0), new OverlayIran(0, 0), new OverlayIronCross(0, 0),
				new OverlayKangaroo(0, 0), new OverlayKazakhBanner(0, 0), new OverlayKazakhCenter(0, 0),
				new OverlayKey(0, 0), new OverlayKey2(0, 0), new OverlayKeystone(0, 0), new OverlayKiwi(0, 0),
				new OverlayKosovo(0, 0), new OverlayKuwait(0, 0), new OverlayKyrgyzstan(0, 0), new OverlayLaurel(0, 0),
				new OverlayLaurel2(0, 0), new OverlayLesotho(0, 0), new OverlayLion(0, 0), new OverlayLotus(0, 0),
				new OverlayMalawi(0, 0), new OverlayMalteseCross(0, 0), new OverlayManitoba(0, 0),
				new OverlayMapleLeaf(0, 0), new OverlayMapleTriple(0, 0), new OverlayMexico(0, 0),
				new OverlayMjolnir(0, 0), new OverlayMozambique(0, 0), new OverlayNunavut(0, 0), new OverlayOak(0, 0),
				new OverlayOccitania(0, 0), new OverlayOctagram(0, 0), new OverlayOman(0, 0), new OverlayOntario(0, 0),
				new OverlayPalm(0, 0), new OverlayPapuaNewGuinea(0, 0), new OverlayParteiadler(0, 0),
				new OverlayPentagram(0, 0), new OverlayPine(0, 0), new OverlayReichsadler(0, 0),
				new OverlayRooster(0, 0), new OverlayRose(0, 0), new OverlaySagebrush(0, 0),
				new OverlaySagebrush2(0, 0), new OverlaySaskatchewan(0, 0), new OverlayScotland(0, 0),
				new OverlayShahadah(0, 0), new OverlayShamrock3(0, 0), new OverlayShamrock4(0, 0),
				new OverlayShield(0, 0), new OverlayShield2(0, 0), new OverlayShield3(0, 0), new OverlayShield4(0, 0),
				new OverlayShield5(0, 0), new OverlayShield6(0, 0), new OverlayShield7(0, 0), new OverlayShield8(0, 0),
				new OverlaySikh(0, 0), new OverlaySnake(0, 0), new OverlaySpringbok(0, 0), new OverlayStar(0, 0),
				new OverlayStarEight(0, 0), new OverlayStarFour(0, 0), new OverlayStarMarshall(0, 0),
				new OverlayStarOfDavid(0, 0), new OverlayStarSeven(0, 0), new OverlayStarShadow(0, 0),
				new OverlayStarSix(0, 0), new OverlaySudan(0, 0), new OverlaySun(0, 0), new OverlaySwastika(0, 0),
				new OverlaySword(0, 0), new OverlayTajikistan(0, 0), new OverlayTakbir(0, 0), new OverlayTorch(0, 0),
				new OverlayTree(0, 0), new OverlayTrident(0, 0), new OverlayTriskele(0, 0), new OverlayUN(0, 0),
				new OverlayVanuatu(0, 0), new OverlayVenice(0, 0), new OverlayVergina(0, 0), new OverlayWales(0, 0),
				new OverlayWave(0, 0), new OverlayWheat(0, 0), new OverlayYang(0, 0), new OverlayYin(0, 0),
				new OverlayZia(0, 0) };
	}

	public static Overlay[] getCustom() {
		Overlay[] returnValue = new Overlay[] {};
		return customTypes.values().toArray(returnValue);
	}

	public static Overlay[] getSpecial() {
		return new Overlay[] { new OverlayFlag(0, 0), new OverlayImage(0, 0), new OverlayRepeaterLateral(0, 0),
				new OverlayRepeaterRadial(0, 0), new OverlayTransformer(0, 0) };
	}

	public static void fillCustomOverlays() {
		customTypes = new TreeMap<String, OverlayPath>() {
		};

		File directory;
		try {
			directory = new File(String.format("%s%sCustom", new File(
					MainWindowController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())
							.getParent(),
					FileHandler.getPathSeparator()));
			File[] files = directory.listFiles();
			if (files == null)
				return;

			for (File file : files) {
				OverlayPath overlay = FileHandler.loadOverlayFromFile(file);

				if (customTypes.containsKey(overlay.name) || typeMap.containsKey(overlay.name)) {
					throw new Exception(String.format(LocalizationHandler.get("OverlayNameExists"), overlay.name));
				}

				customTypes.put(overlay.name, overlay);
			}
		} catch (Exception ex) {
		}
	}

	public static Overlay getFlagInstance(File path, int maximumX, int maximumY) throws Exception {
		return new OverlayFlag(FileHandler.loadFlagFromFile(path), path, maximumX, maximumY);
	}

	public static Overlay getImageInstance(File path, int maximumX, int maximumY) {
		return new OverlayImage(path, maximumX, maximumY);
	}

	public static Overlay getInstanceByLongName(String name, int defaultMaximumX, int defaultMaximumY) {
		try {
			Class c = Class.forName(name);
			return getInstance(c, defaultMaximumX, defaultMaximumY);
		} catch (Exception e) {
			return null;
		}
	}

	public static Overlay getInstanceByShortName(String name, int defaultMaximumX, int defaultMaximumY) {
		if (customTypes.containsKey(name)) {
			OverlayPath overlayCopy = customTypes.get(name).copy();
			overlayCopy.setMaximum(defaultMaximumX, defaultMaximumY);
			return overlayCopy;
		}

		try {
			Class c = Class.forName(typeMap.get(name));
			return getInstance(c, defaultMaximumX, defaultMaximumY);
		} catch (Exception e) {
			return null;
		}
	}

	private static Overlay getInstance(Class c, int defaultMaximumX, int defaultMaximumY) {
		try {
			Constructor<Overlay> constructor = c.getDeclaredConstructor(new Class[] { int.class, int.class });
			return constructor.newInstance(defaultMaximumX, defaultMaximumY);
		} catch (Exception ex) {
			return null;
		}
	}
}
