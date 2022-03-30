package flagmaker.randomflag;

import javafx.scene.paint.Color;

public class ColorScheme {
	private static final Color Yellow = Color.rgb(253, 200, 47);
	private static final Color White = Color.WHITE;
	private static final Color Black = Color.BLACK;
	private static final Color Red = Color.rgb(198, 12, 48);
	private static final Color Orange = Color.rgb(255, 99, 25);
	private static final Color Green = Color.rgb(20, 77, 41);
	private static final Color Blue = Color.rgb(0, 57, 166);

	private final Color color1;
	private final Color color2;
	private final Color color3;
	private final Color metal;

	public ColorScheme() {
		Color[] colors = new Color[] { Black, Red, Orange, Green, Blue };
		int color1Index = Randomizer.randomWeighted(new int[] { 27, 102, 4, 45, 58 });

		int[][] firstOrderBase = new int[][] { // B R O G U
				new int[] { 0, 38, 0, 22, 11 }, // Black
				new int[] { 38, 0, 0, 76, 69 }, // Red
				new int[] { 0, 0, 0, 8, 1 }, // Orange
				new int[] { 22, 76, 8, 0, 34 }, // Green
				new int[] { 11, 69, 1, 34, 0 } // Blue
		};

		int color2Index = Randomizer.randomWeighted(firstOrderBase[color1Index]);

		int color3Index;
		do {
			color3Index = Randomizer.randomWeighted(firstOrderBase[color1Index]);
		} while (color3Index == color2Index);

		double[][] yellowProbabilities = new double[][] { // B R O G B
				new double[] { 0.00, 0.54, 0.00, 0.25, 0.60 }, // Black
				new double[] { 0.54, 0.00, 0.00, 0.59, 0.24 }, // Red
				new double[] { 0.00, 0.00, 0.00, 0.00, 0.00 }, // Orange
				new double[] { 0.25, 0.59, 0.00, 0.00, 0.55 }, // Green
				new double[] { 0.60, 0.60, 0.00, 0.55, 0.00 }, // Blue
		};
		double yellowProbability = yellowProbabilities[color1Index][color2Index];

		this.color1 = tweakColor(colors[color1Index]);
		this.color2 = tweakColor(colors[color2Index]);
		this.color3 = tweakColor(colors[color3Index]);
		this.metal = Randomizer.probabilityOfTrue(yellowProbability) ? tweakColor(Yellow) : White;
	}

	private ColorScheme(Color color1, Color color2, Color color3, Color metal) {
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.metal = metal;
	}

	public Color getColor1() {
		return color1;
	}

	public Color getColor2() {
		return color2;
	}

	public Color getColor3() {
		return color3;
	}

	public Color getMetal() {
		return metal;
	}

	public ColorScheme swapped() {
		return new ColorScheme(color3, color1, color2, metal);
	}

	private Color tweakColor(Color color) {
		if (color == Color.BLACK || color == Color.WHITE) { // Don't adjust black or white, it looks bad
			return color;
		}

		final int spread = 15;
		return Color.rgb(Randomizer.clamp(Randomizer.nextNormalized(color.getRed() * 255, spread), 0, 255, false),
				Randomizer.clamp(Randomizer.nextNormalized(color.getGreen() * 255, spread), 0, 255, false),
				Randomizer.clamp(Randomizer.nextNormalized(color.getBlue() * 255, spread), 0, 255, false));
	}
}
