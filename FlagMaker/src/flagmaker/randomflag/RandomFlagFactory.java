package flagmaker.randomflag;

import flagmaker.data.Flag;
import flagmaker.data.Ratio;
import flagmaker.divisions.*;
import flagmaker.files.LocalizationHandler;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.OverlayFactory;
import flagmaker.overlays.overlaytypes.*;
import flagmaker.overlays.overlaytypes.pathtypes.OverlayPath;
import flagmaker.overlays.overlaytypes.repeatertypes.OverlayRepeaterLateral;
import flagmaker.overlays.overlaytypes.repeatertypes.OverlayRepeaterRadial;
import flagmaker.overlays.overlaytypes.shapetypes.*;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class RandomFlagFactory {
	private Ratio ratio;
	private Ratio gridSize;
	private DivisionTypes divisionType;
	private Division division;
	private ArrayList<Overlay> overlays;
	private ColorScheme colorScheme;
	private boolean canHaveCanton;

	private final Overlay[] emblems = OverlayFactory.getEmblems();

	public Flag generateFlag() {
		return generateFlag(new ColorScheme());
	}

	public Flag generateFlag(ColorScheme colorScheme) {
		this.colorScheme = new ColorScheme();
		canHaveCanton = true;
		getRatio();
		overlays = new ArrayList<>();
		division = getDivision();

		Overlay[] overlays = new Overlay[] {};
		return new Flag(LocalizationHandler.get("Random"), ratio, gridSize, division, this.overlays.toArray(overlays));
	}

	private void getRatio() {
		ratio = new Ratio[] { new Ratio(3, 2), new Ratio(5, 3), new Ratio(7, 4), new Ratio(2, 1) }[Randomizer
				.randomWeighted(new int[] { 6, 2, 3, 4 })];
		gridSize = new Ratio(ratio.width * 8, ratio.height * 8);
	}

	private Division getDivision() {
		// Roughly based on real-life usage
		// 206 flags surveyed
		divisionType = DivisionTypes.values()[Randomizer.randomWeighted(new int[] { 9, // stripes
				22, // pales
				66, // fesses
				38, // blank
				21, // horizontal halves
				6, // vertical halves
				10, // diagonal
				12, // stripe
				9, // cross
				3, // x
				1, // ray
				0 // 11 // other
		})];

		switch (divisionType) {
		case STRIPES:
			return getStripes();
		case PALES:
			return getPales();
		case FESSES:
			return getFesses();
		case BLANK:
			return getBlank();
		case HORIZONTAL:
			return getHorizontal();
		case VERTICAL:
			return getVertical();
		case DIAGONAL:
			return getDiagonal();
		case STRIPE:
			return getStripe();
		case CROSS:
			return getCross();
		case X:
			return getX();
		case RAY:
			return getRay();
		default:
			return null;
		// throw new Exception("No valid type selection");
		}
	}

	private DivisionGrid getStripes() {
		int stripeCount = Randomizer.clamp(Randomizer.nextNormalized(10, 3), 5, 15, true);

		Color stripeOuterColor = colorScheme.getColor1();
		Color stripeInnerColor = colorScheme.getMetal();
		Color cantonColor = colorScheme.getColor2();

		if (Randomizer.probabilityOfTrue(0.125)) {
			int width = hoistElementWidth(true);
			addTriangle(1.0, 1.0, width, cantonColor, stripeInnerColor);
		} else {
			boolean isMainColorMetal = Randomizer.probabilityOfTrue(0.142857);
			if (isMainColorMetal) {
				stripeOuterColor = colorScheme.getMetal();
				stripeInnerColor = colorScheme.getColor1();
				cantonColor = colorScheme.getMetal();
			} else if (Randomizer.probabilityOfTrue(0.16667)) {
				cantonColor = stripeOuterColor;
			}

			double width = hoistElementWidth(false);
			double cantonHeight = gridSize.height * ((double) ((int) (stripeCount / 2.0) + 1) / stripeCount);
			if (width < cantonHeight)
				width = cantonHeight;

			overlays.add(new OverlayBox(cantonColor, 0, 0, width, cantonHeight, gridSize.width, gridSize.height));

			if (Randomizer.probabilityOfTrue(0.142857)) {
				addRepeater(width / 2, cantonHeight / 2, width * 3 / 4.0, cantonHeight * 3 / 4.0, stripeInnerColor,
						false);
			} else {
				addEmblem(1.0, width / 2, cantonHeight / 2, stripeInnerColor, false, Color.WHITE, false);
			}
		}

		return new DivisionGrid(stripeOuterColor, stripeInnerColor, 1, stripeCount);
	}

	private DivisionPales getPales() {
		Color c1 = colorScheme.getColor1();
		Color c2;
		Color c3;
		Color emblemColor;
		boolean isBalanced = true;
		boolean emblemInCenter = true;
		double probabilityOfEmblem;

		if (Randomizer.probabilityOfTrue(0.13636)) {
			c2 = colorScheme.getColor2();

			if (Randomizer.probabilityOfTrue(0.333)) {
				c3 = colorScheme.getColor3();
				emblemColor = colorScheme.getMetal();
				probabilityOfEmblem = 1.0;
			} else if (Randomizer.probabilityOfTrue(0.5)) {
				c3 = colorScheme.getMetal();
				emblemColor = colorScheme.getMetal();
				probabilityOfEmblem = 1.0;
			} else {
				c3 = colorScheme.getColor1();
				emblemInCenter = false;
				emblemColor = colorScheme.getMetal();
				probabilityOfEmblem = 1.0;
			}
		} else {
			c2 = colorScheme.getMetal();
			emblemColor = Randomizer.probabilityOfTrue(0.5) ? colorScheme.getColor1() : colorScheme.getColor2();

			if (Randomizer.probabilityOfTrue(0.2632)) {
				c3 = colorScheme.getColor2();
				probabilityOfEmblem = 0.357;
			} else {
				c3 = c1;
				probabilityOfEmblem = 0.6;
			}

			if (Randomizer.probabilityOfTrue(0.1052)) {
				isBalanced = false;
				probabilityOfEmblem = 1.0;
			}
		}

		addEmblem(probabilityOfEmblem, emblemInCenter ? gridSize.width / 2.0 : gridSize.width / 6.0,
				gridSize.height / 2.0, emblemColor, false, Color.WHITE, false);
		return new DivisionPales(c1, c2, c3, 1, isBalanced ? 1 : 2, 1);
	}

	private DivisionFesses getFesses() {
		Color c1;
		Color c2;
		Color c3;
		Color emblemColor;
		Color hoistColor;
		boolean isSpanish = false;
		boolean isLatvian = false;
		boolean isColombian = false;
		double probabilityOfEmblem;
		double probabilityOfHoist;

		if (Randomizer.probabilityOfTrue(0.166667)) {
			c1 = colorScheme.getMetal();
			c2 = colorScheme.getColor1();
			c3 = colorScheme.getColor2();
			hoistColor = c2;
			probabilityOfHoist = 0.0909;
			probabilityOfEmblem = 0.5454;
			isColombian = Randomizer.probabilityOfTrue(0.1818182);
			emblemColor = isColombian ? c3 : c1;
		} else {
			c1 = colorScheme.getColor1();

			if (Randomizer.probabilityOfTrue(0.29)) {
				c2 = colorScheme.getColor2();

				if (Randomizer.probabilityOfTrue(0.25)) {
					c3 = colorScheme.getColor1();
					isLatvian = Randomizer.probabilityOfTrue(0.5);
					isSpanish = !isLatvian;
					probabilityOfEmblem = isSpanish ? 1.0 : 0.0;
					probabilityOfHoist = 0;
					hoistColor = Color.TRANSPARENT;
					emblemColor = colorScheme.getMetal();
				} else if (Randomizer.probabilityOfTrue(0.5)) {
					c3 = colorScheme.getColor3();
					probabilityOfHoist = 0.0;
					probabilityOfEmblem = 0.833333;
					boolean hasFimbriations = Randomizer.probabilityOfTrue(0.5);
					isSpanish = !hasFimbriations && Randomizer.probabilityOfTrue(0.2);
					hoistColor = Color.TRANSPARENT;
					emblemColor = colorScheme.getMetal();

					if (hasFimbriations) {
						overlays.add(new OverlayLineHorizontal(colorScheme.getMetal(),
								isSpanish ? gridSize.height / 4.0 : gridSize.height / 3.0, gridSize.height / 20.0,
								gridSize.width, gridSize.height));
						overlays.add(new OverlayLineHorizontal(colorScheme.getMetal(),
								isSpanish ? 3 * gridSize.height / 4.0 : 2 * gridSize.height / 3.0,
								gridSize.height / 20.0, gridSize.width, gridSize.height));
					}
				} else {
					c3 = colorScheme.getMetal();
					hoistColor = colorScheme.getColor3();
					emblemColor = colorScheme.getMetal();
					probabilityOfHoist = 0.166667;
					probabilityOfEmblem = 0.166667;
				}
			} else {
				c2 = colorScheme.getMetal();

				if (Randomizer.probabilityOfTrue(0.2564)) {
					c3 = colorScheme.getColor1();
					isSpanish = Randomizer.probabilityOfTrue(0.3);
					isLatvian = !isSpanish && Randomizer.probabilityOfTrue(0.1429);
					hoistColor = colorScheme.getColor2();
					emblemColor = colorScheme.getColor2();
					probabilityOfHoist = 0.2;
					probabilityOfEmblem = 0.7;
				} else {
					c3 = colorScheme.getColor2();
					hoistColor = colorScheme.getColor3();
					emblemColor = Randomizer.probabilityOfTrue(0.5) ? c1 : c3;
					isColombian = Randomizer.probabilityOfTrue(0.0345);
					probabilityOfHoist = 0.2414;
					probabilityOfEmblem = 0.6552;
				}
			}
		}

		if (isSpanish) {
			probabilityOfEmblem = 1.0;
		} else if (isLatvian) {
			probabilityOfEmblem = 0.0;
		} else if (isColombian) {
			probabilityOfHoist = 0.0;
		}

		if (Randomizer.probabilityOfTrue(probabilityOfHoist)) {
			emblemColor = colorScheme.getMetal();
			addTriangle(1.0, probabilityOfEmblem, hoistElementWidth(true), hoistColor, emblemColor);
		} else {
			addEmblem(probabilityOfEmblem, gridSize.width / 2.0, gridSize.height / 2.0, emblemColor, false,
					Color.TRANSPARENT, false);
		}

		return new DivisionFesses(c1, c2, c3, isLatvian || isColombian ? 2 : 1, isSpanish ? 2 : 1, isLatvian ? 2 : 1);
	}

	private DivisionGrid getCross() {
		boolean backgroundIsMetal = Randomizer.probabilityOfTrue(0.25);
		boolean fimbriate = !backgroundIsMetal && Randomizer.probabilityOfTrue(0.4286);

		Color background = backgroundIsMetal ? colorScheme.getMetal() : colorScheme.getColor1();
		Color mainColor = backgroundIsMetal ? colorScheme.getColor1()
				: fimbriate ? colorScheme.getColor2() : colorScheme.getMetal();
		Color fimbriation = colorScheme.getMetal();
		double center = gridSize.height / 2.0;

		int crossWidth = Randomizer.clamp(Randomizer.nextNormalized(gridSize.width / 8.0, gridSize.width / 20.0), 2,
				gridSize.width / 4, false) - (fimbriate ? 1 : 0);
		int fimbriationWidth = crossWidth + 2;

		boolean canSaltire = false;

		double intersection = gridSize.width / 2.0;
		if (Randomizer.probabilityOfTrue(0.555556)) {
			intersection = gridSize.width / 3.0;
		} else {
			if (Randomizer.probabilityOfTrue(0.25)) {
				overlays.add(new OverlayCross(colorScheme.getMetal(), crossWidth, intersection, center, gridSize.width,
						gridSize.height));
				return new DivisionGrid(colorScheme.getColor1(), colorScheme.getColor2(), 2, 2);
			}

			canSaltire = !backgroundIsMetal;
		}

		if (canSaltire && Randomizer.probabilityOfTrue(0.5)) {
			if (fimbriate) {
				overlays.add(new OverlaySaltire(fimbriation, fimbriationWidth, gridSize.width, gridSize.height));
				overlays.add(new OverlayHalfSaltire(mainColor, crossWidth + 2, gridSize.width, gridSize.height));
			} else {
				overlays.add(new OverlaySaltire(colorScheme.getColor2(), crossWidth, gridSize.width, gridSize.height));
			}
		}

		if (fimbriate) {
			overlays.add(new OverlayCross(fimbriation, fimbriationWidth, intersection, center, gridSize.width,
					gridSize.height));
		}

		overlays.add(new OverlayCross(mainColor, crossWidth, intersection, center, gridSize.width, gridSize.height));

		return new DivisionGrid(background, background, 1, 1);
	}

	private DivisionGrid getBlank() {
		Color color = colorScheme.getColor1();

		switch (new int[] { 1, 2, 3, 4 }[Randomizer.randomWeighted(new int[] { canHaveCanton ? 10 : 0, 26, 2, 1 })]) {
		case 1:
			// Canton
			if (Randomizer.probabilityOfTrue(0.6)) {
				addFlag(new RandomFlagFactory().generateFlag(colorScheme.swapped()));
				addEmblem(1.0, 3 * gridSize.width / 4.0, gridSize.height / 2.0, colorScheme.getMetal(), true,
						colorScheme.getColor2(), false);
			} else {
				Color cantonColor = Randomizer.probabilityOfTrue(0.5) ? colorScheme.getColor2()
						: colorScheme.getMetal();
				overlays.add(new OverlayBox(cantonColor, 0, 0, gridSize.width / 2.0, gridSize.height / 2.0,
						gridSize.width, gridSize.height));

				if (Randomizer.probabilityOfTrue(0.5)) {
					addRepeater(gridSize.width / 4.0, gridSize.height / 4.0, gridSize.width / 3.0,
							gridSize.height / 3.0,
							cantonColor == colorScheme.getMetal() ? colorScheme.getColor1() : colorScheme.getMetal(),
							false);
				} else {
					addEmblem(1.0, gridSize.width / 4.0, gridSize.height / 4.0,
							cantonColor == colorScheme.getMetal() ? colorScheme.getColor1() : colorScheme.getMetal(),
							true,
							cantonColor == colorScheme.getMetal() ? colorScheme.getMetal() : colorScheme.getColor1(),
							false);
				}
			}
			break;
		case 2:
			// Center emblem
			color = getCenterEmblemForBlank();
			break;
		case 3:
			// Triangle
			int width = hoistElementWidth(true);
			if (Randomizer.probabilityOfTrue(0.5)) {
				addTriangle(1.0, 0.0, width <= gridSize.width / 2.0 ? width * 2 : gridSize.width,
						colorScheme.getMetal(), colorScheme.getMetal());
			} else {
				addTriangle(1.0, 0.0, width + 2, colorScheme.getMetal(), colorScheme.getMetal());
			}
			addTriangle(1.0, 1.0, width, colorScheme.getColor2(), colorScheme.getMetal());
			break;
		case 4:
			// Rays
			overlays.add(new OverlayRays(colorScheme.getMetal(), gridSize.width / 2.0, gridSize.height / 2.0, Randomizer
					.clamp(Randomizer.nextNormalized(gridSize.width * 3 / 4.0, gridSize.width / 10.0), 4, 20, false),
					gridSize.width / 4.0, gridSize.width, gridSize.height));
			addCircleEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(),
					colorScheme.getColor1(), colorScheme.getMetal());
			break;
		}

		return new DivisionGrid(color, colorScheme.getColor2(), 1, 1);
	}

	private Color getCenterEmblemForBlank() {
		switch (new int[] { 1, 2, 3, 4, 5 }[Randomizer
				.randomWeighted(new int[] { 20, 3, 1, canHaveCanton ? 3 : 0, 2 })]) {
		case 1:
			// Plain
			boolean isInverted = Randomizer.probabilityOfTrue(0.1);
			boolean useColor2 = Randomizer.probabilityOfTrue(.11);
			addEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0,
					useColor2 ? colorScheme.getColor2()
							: (isInverted ? colorScheme.getColor1() : colorScheme.getMetal()),
					!isInverted, useColor2 ? colorScheme.getMetal() : colorScheme.getColor2(), false);
			return isInverted ? colorScheme.getMetal() : colorScheme.getColor1();
		case 2:
			// Circled
			addCircleEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(),
					colorScheme.getColor1(), colorScheme.getMetal());
			return colorScheme.getColor1();
		case 3:
			// Repeater
			addRepeater(gridSize.width / 2.0, gridSize.height / 2.0, gridSize.height, 0, colorScheme.getMetal(), true);
			return colorScheme.getColor1();
		case 4:
			// Border
			overlays.add(
					new OverlayBorder(colorScheme.getColor2(), gridSize.width / 8.0, gridSize.width, gridSize.height));
			addEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(), true,
					colorScheme.getColor2(), false);
			return colorScheme.getColor1();
		default:
			// Stripes
			overlays.add(new OverlayLineHorizontal(colorScheme.getColor1(), gridSize.height * (1 / 6.0),
					gridSize.height / 8.0, gridSize.width, gridSize.height));
			overlays.add(new OverlayLineHorizontal(colorScheme.getColor1(), gridSize.height * (5 / 6.0),
					gridSize.height / 8.0, gridSize.width, gridSize.height));
			addEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getColor1(), false,
					colorScheme.getColor2(), false);
			return colorScheme.getMetal();
		}
	}

	private DivisionGrid getVertical() {
		switch (new int[] { 1, 2, 3 }[Randomizer.randomWeighted(new int[] { 2, 1, 3 })]) {
		case 1:
			// Color 1 / Metal
			addEmblem(1.0, Randomizer.probabilityOfTrue(0.5) ? gridSize.width / 2.0 : 3 * gridSize.width / 4.0,
					gridSize.height / 2.0, colorScheme.getColor2(), false, colorScheme.getMetal(), false);
			return new DivisionGrid(colorScheme.getColor1(), colorScheme.getMetal(), 2, 1);
		case 2:
			// Color 1 / Color 2
			addEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(), false,
					colorScheme.getMetal(), false);
			return new DivisionGrid(colorScheme.getColor1(), colorScheme.getColor2(), 2, 1);
		default:
			// Metal / Color 1
			addEmblem(0.5, gridSize.width / 4.0, gridSize.height / 4.0, colorScheme.getColor2(), false,
					colorScheme.getMetal(), false);
			return new DivisionGrid(colorScheme.getMetal(), colorScheme.getColor1(), 2, 1);
		}
	}

	private DivisionGrid getHorizontal() {
		switch (new int[] { 1, 2, 3 }[Randomizer.randomWeighted(new int[] { 4, 11, 6 })]) {
		case 1:
			// Color 1 / Metal
			addEmblem(0.25, gridSize.width / 4.0, gridSize.height / 4.0, colorScheme.getMetal(), false,
					colorScheme.getMetal(), false);
			return new DivisionGrid(colorScheme.getColor1(), colorScheme.getMetal(), 1, 2);
		case 2:
			// Color 1 / Color 2

			if (Randomizer.probabilityOfTrue(0.181818)) {
				double width = gridSize.width / 3.0;
				addTriangle(1.0, 0.5, (int) width, Color.BLACK, colorScheme.getMetal());
				overlays.add(new OverlayPall(colorScheme.getMetal(), width, gridSize.width / 8.0, gridSize.width,
						gridSize.height));
				overlays.add(new OverlayPall(colorScheme.getColor3(), width, gridSize.width / 5.0, gridSize.width,
						gridSize.height));
			} else {
				if (Randomizer.probabilityOfTrue(0.1111)) {
					addHoistForHorizontal(false, true);
				} else if (Randomizer.probabilityOfTrue(0.375)) {
					boolean isTriangleMetal = Randomizer.probabilityOfTrue(0.6667);
					addTriangle(1.0, 1.0, hoistElementWidth(true),
							isTriangleMetal ? colorScheme.getMetal() : colorScheme.getColor3(),
							isTriangleMetal ? colorScheme.getColor1() : colorScheme.getMetal());
				} else {
					// Plain with emblem
					double offset = Randomizer.probabilityOfTrue(0.25) ? 4.0 : 2.0;
					addEmblem(1.0, gridSize.width / offset, gridSize.height / offset, colorScheme.getMetal(), false,
							colorScheme.getMetal(), false);
				}
			}

			return new DivisionGrid(colorScheme.getColor1(), colorScheme.getColor2(), 1, 2);
		default:
			// Metal / Color 1
			if (Randomizer.probabilityOfTrue(0.333)) {
				addHoistForHorizontal(Randomizer.probabilityOfTrue(0.5), false);
			}
			return new DivisionGrid(colorScheme.getMetal(), colorScheme.getColor1(), 1, 2);
		}
	}

	private void addHoistForHorizontal(boolean isHalfSize, boolean isMetal) {
		int width = hoistElementWidth(false);
		overlays.add(new OverlayBox(isMetal ? colorScheme.getMetal() : colorScheme.getColor2(), 0, 0, width,
				isHalfSize ? gridSize.height / 2.0 : gridSize.height, gridSize.width, gridSize.height));
		addEmblem(1.0, width / 2.0, isHalfSize ? gridSize.height / 4.0 : gridSize.height / 2.0,
				isMetal ? colorScheme.getColor1() : colorScheme.getMetal(), false, colorScheme.getMetal(), false);
	}

	private Division getDiagonal() {
		boolean isForward = Randomizer.probabilityOfTrue(0.7);

		Color[][] schemes = new Color[][] {
				new Color[] { colorScheme.getMetal(), colorScheme.getColor1(), Color.TRANSPARENT, Color.TRANSPARENT,
						colorScheme.getColor2() },
				new Color[] { colorScheme.getColor1(), colorScheme.getColor2(), Color.TRANSPARENT, Color.TRANSPARENT,
						colorScheme.getMetal() },
				new Color[] { colorScheme.getColor1(), colorScheme.getColor1(), colorScheme.getColor2(),
						colorScheme.getMetal(), colorScheme.getMetal() },
				new Color[] { colorScheme.getColor1(), colorScheme.getColor2(), colorScheme.getColor3(),
						colorScheme.getMetal(), colorScheme.getMetal() },
				new Color[] { colorScheme.getMetal(), colorScheme.getMetal(), colorScheme.getColor1(),
						Color.TRANSPARENT, colorScheme.getColor2() },
				new Color[] { colorScheme.getColor1(), colorScheme.getColor2(), colorScheme.getMetal(),
						Color.TRANSPARENT, colorScheme.getMetal() } };
		Color[] scheme = schemes[Randomizer.randomWeighted(new int[] { 1, 1, 2, 3, 1, 2 })];

		Color topColor = scheme[0];
		Color bottomColor = scheme[1];
		Color stripeColor = scheme[2];
		Color fimbriationColor = scheme[3];
		Color emblemColor = scheme[4];

		boolean hasStripe = stripeColor != Color.TRANSPARENT;
		boolean hasFimbriation = fimbriationColor != Color.TRANSPARENT;

		if (hasStripe) {
			if (hasFimbriation) {
				if (isForward) {
					overlays.add(new OverlayFimbriationForward(fimbriationColor, gridSize.width / 3.0, gridSize.width,
							gridSize.height));
				} else {
					overlays.add(new OverlayFimbriationBackward(fimbriationColor, gridSize.width / 3.0, gridSize.width,
							gridSize.height));
				}
			}
			if (isForward) {
				overlays.add(new OverlayFimbriationForward(stripeColor, gridSize.width / 4.0, gridSize.width,
						gridSize.height));
			} else {
				overlays.add(new OverlayFimbriationBackward(stripeColor, gridSize.width / 4.0, gridSize.width,
						gridSize.height));
			}
		}

		double emblemLeft = hasStripe ? (isForward ? 1.0 : 5.0) * gridSize.width / 6.0 : gridSize.width / 2.0;
		double emblemTop = hasStripe ? gridSize.height / 4.0 : gridSize.height / 2.0;
		addEmblem(1.0, emblemLeft, emblemTop, emblemColor, false, emblemColor, false);

		if (isForward) {
			return new DivisionBendsForward(topColor, bottomColor);
		}

		return new DivisionBendsBackward(topColor, bottomColor);
	}

	private DivisionGrid getStripe() {
		return Randomizer.probabilityOfTrue(0.58333) ? getSingleStripe() : getMultiStripe();
	}

	private DivisionGrid getSingleStripe() {
		if (Randomizer.probabilityOfTrue(0.142)) {
			overlays.add(new OverlayLineHorizontal(colorScheme.getMetal(), gridSize.height / 2.0, gridSize.height / 6.0,
					gridSize.width, gridSize.height));
			addEmblem(0.75, gridSize.width / 3.0, gridSize.height / 1.333, colorScheme.getMetal(), false,
					colorScheme.getMetal(), false);
		} else {
			boolean isThick = Randomizer.probabilityOfTrue(0.66667);
			overlays.add(new OverlayLineHorizontal(colorScheme.getMetal(), gridSize.height / 2.0,
					gridSize.height / (isThick ? 1.5 : 3.0), gridSize.width, gridSize.height));
			overlays.add(new OverlayLineHorizontal(colorScheme.getColor2(), gridSize.height / 2.0,
					gridSize.height / (isThick ? 3.0 : 4.0), gridSize.width, gridSize.height));
			addEmblem(isThick ? 0.5 : 0.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(), false,
					colorScheme.getMetal(), false);
		}

		return new DivisionGrid(colorScheme.getColor1(), colorScheme.getColor2(), 1, 1);
	}

	private DivisionGrid getMultiStripe() {
		if (Randomizer.probabilityOfTrue(0.2)) {
			// Symmetric
			boolean swap = Randomizer.probabilityOfTrue(0.5);
			overlays.add(new OverlayLineHorizontal(swap ? colorScheme.getColor2() : colorScheme.getMetal(),
					gridSize.height / 2.0, gridSize.height / 1.4, gridSize.width, gridSize.height));
			overlays.add(new OverlayLineHorizontal(swap ? colorScheme.getMetal() : colorScheme.getColor2(),
					gridSize.height / 2.0, gridSize.height / 2.3333, gridSize.width, gridSize.height));
			overlays.add(new OverlayLineHorizontal(colorScheme.getColor3(), gridSize.height / 2.0,
					gridSize.height / 7.0, gridSize.width, gridSize.height));
		} else if (Randomizer.probabilityOfTrue(0.75)) {
			// Asymmetric
			boolean swap = Randomizer.probabilityOfTrue(0.3333);
			overlays.add(new OverlayBox(swap ? colorScheme.getColor2() : colorScheme.getMetal(), 0,
					gridSize.height / 4.0, gridSize.width, gridSize.height * 3 / 4.0, gridSize.width, gridSize.height));
			overlays.add(new OverlayBox(swap ? colorScheme.getMetal() : colorScheme.getColor2(), 0,
					gridSize.height / 2.0, gridSize.width, gridSize.height * 2.0, gridSize.width, gridSize.height));
			overlays.add(new OverlayBox(colorScheme.getColor3(), 0, gridSize.height * 3 / 4.0, gridSize.width,
					gridSize.height / 4.0, gridSize.width, gridSize.height));
		} else {
			// Uganda
			overlays.add(new OverlayLineHorizontal(colorScheme.getMetal(), gridSize.height * 3 / 12.0,
					gridSize.height / 6.0, gridSize.width, gridSize.height));
			overlays.add(new OverlayLineHorizontal(colorScheme.getColor2(), gridSize.height * 5 / 12.0,
					gridSize.height / 6.0, gridSize.width, gridSize.height));
			overlays.add(new OverlayLineHorizontal(colorScheme.getMetal(), gridSize.height * 9 / 12.0,
					gridSize.height / 6.0, gridSize.width, gridSize.height));
			overlays.add(new OverlayLineHorizontal(colorScheme.getColor2(), gridSize.height * 11 / 12.0,
					gridSize.height / 6.0, gridSize.width, gridSize.height));
		}

		if (Randomizer.probabilityOfTrue(0.4)) {
			addTriangle(1.0, 1.0, hoistElementWidth(true), colorScheme.getMetal(), colorScheme.getColor1());
		} else {
			addCircleEmblem(0.5, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(),
					colorScheme.getColor1(), colorScheme.getMetal());
		}

		return new DivisionGrid(colorScheme.getColor1(), colorScheme.getColor2(), 1, 1);
	}

	private Division getX() {
		switch (new int[] { 1, 2, 3 }[Randomizer.randomWeighted(new int[] { 2, 1, 1 })]) {
		case 1:
			// Two-color, fimbriation
			overlays.add(
					new OverlaySaltire(colorScheme.getMetal(), gridSize.width / 6.0, gridSize.width, gridSize.height));
			addCircleEmblem(0.5, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(),
					colorScheme.getColor1(), colorScheme.getColor1());
			return new DivisionX(colorScheme.getColor1(), colorScheme.getColor2());
		case 2:
			// Two-color, no fimbriation
			addCircleEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getColor2(),
					colorScheme.getMetal(), colorScheme.getMetal());
			if (Randomizer.probabilityOfTrue(0.5)) {
				overlays.add(new OverlayBorder(colorScheme.getColor2(), gridSize.width / 8.0, gridSize.width,
						gridSize.height));
			}
			return new DivisionX(colorScheme.getColor1(), colorScheme.getMetal());
		default:
			// One-color
			overlays.add(
					new OverlaySaltire(colorScheme.getMetal(), gridSize.width / 6.0, gridSize.width, gridSize.height));
			return new DivisionGrid(colorScheme.getColor1(), colorScheme.getColor1(), 1, 1);
		}
	}

	private DivisionGrid getRay() {
		overlays.add(new OverlayRays(colorScheme.getColor1(), gridSize.width / 2.0, gridSize.height / 2.0, 20,
				gridSize.width / 4.0, gridSize.width, gridSize.height));
		addCircleEmblem(1.0, gridSize.width / 2.0, gridSize.height / 2.0, colorScheme.getMetal(),
				colorScheme.getColor1(), colorScheme.getMetal());
		return new DivisionGrid(colorScheme.getMetal(), colorScheme.getMetal(), 1, 1);
	}

	private int hoistElementWidth(boolean isTriangle) {
		return (int) (gridSize.width * Randomizer.nextNormalized(isTriangle ? 0.45 : 0.35, 0.05));
	}

	private void addTriangle(double probability, double probabilityOfEmblem, int width, Color color,
			Color emblemColor) {
		if (!Randomizer.probabilityOfTrue(probability))
			return;
		overlays.add(new OverlayTriangle(color, 0, 0, width, gridSize.height / 2.0, 0, gridSize.height, gridSize.width,
				gridSize.height));

		if (Randomizer.probabilityOfTrue(probabilityOfEmblem)) {
			addEmblem(1.0, width / 3.0, gridSize.height / 2.0, emblemColor, false, Color.WHITE, false);
		}
	}

	private void addRepeater(double x, double y, double width, double height, Color color, boolean forceRadial) {
		boolean big = forceRadial;
		if (!forceRadial && Randomizer.probabilityOfTrue(0.5)) {
			overlays.add(new OverlayRepeaterLateral(x, y, width, height,
					Randomizer.clamp(Randomizer.nextNormalized(5, 2), 2, 8, false),
					Randomizer.clamp(Randomizer.nextNormalized(4, 2), 2, 8, false), gridSize.width, gridSize.height));
		} else {
			big = true;
			overlays.add(new OverlayRepeaterRadial(x, y, width / 3.0,
					Randomizer.clamp(Randomizer.nextNormalized(12, 4), 4, 25, false), Randomizer.probabilityOfTrue(0.5),
					gridSize.width, gridSize.height));
		}

		addEmblem(1, 0, 0, color, false, color, big);
	}

	private void addCircleEmblem(double probability, double x, double y, Color circleColor, Color emblemColor,
			Color colorIfStroke) {
		if (!Randomizer.probabilityOfTrue(probability))
			return;

		overlays.add(new OverlayEllipse(circleColor, x, y, gridSize.width / 4.0, 0.0, gridSize.width, gridSize.height));

		addEmblem(1.0, x, y, emblemColor, true, colorIfStroke, false);
	}

	private void addEmblem(double probability, double x, double y, Color color, boolean canStroke, Color colorIfStroked,
			boolean isBig) {
		if (probability < 1 && !Randomizer.probabilityOfTrue(probability))
			return;

		OverlayPath emblem = (OverlayPath) emblems[Randomizer.next(emblems.length)];
		emblem.setMaximum(gridSize.width, gridSize.height);
		emblem.setAttribute("X", x);
		emblem.setAttribute("Y", y);
		emblem.setAttribute("Size", gridSize.width / (isBig ? 3.0 : 6.0));
		emblem.setAttribute("Rotation", 0.0);

		if (canStroke && Randomizer.probabilityOfTrue(0.1)) {
			emblem.setAttribute("Color", colorIfStroked);
			emblem.setAttribute("Stroke", 2.0);
			emblem.setAttribute("StrokeColor", color);
			emblem.setAttribute("StrokeCurved", true);
		} else {
			emblem.setAttribute("Color", color);
			emblem.setAttribute("Stroke", 0.0);
		}

		overlays.add(emblem);
	}

	private void addFlag(Flag flag) {
		overlays.add(new OverlayFlag(flag, new File(""), 0, 0, gridSize.width / 2.0, gridSize.height / 2.0,
				gridSize.width, gridSize.height));
	}
}
