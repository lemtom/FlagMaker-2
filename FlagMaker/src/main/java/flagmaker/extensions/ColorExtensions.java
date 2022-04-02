package flagmaker.extensions;

import javafx.scene.paint.Color;

public class ColorExtensions {

	private ColorExtensions() {
		throw new IllegalStateException("Utility class");
	}

	public static String toHexString(Color c, boolean includeOpacity) {
		return String.format("%s%s%s%s",
				includeOpacity && c.getOpacity() < 1.0 ? intToHex((int) (c.getOpacity() * 255)) : "",
				intToHex((int) (c.getRed() * 255)), intToHex((int) (c.getGreen() * 255)),
				intToHex((int) (c.getBlue() * 255)));
	}

	public static String toSvgFillWithOpacity(Color c) {
		return String.format("fill=\"#%s\"%s", toHexString(c, true),
				c.getOpacity() < 1.0 ? String.format(" fill=opacity=\"%s\"", c.getOpacity()) : "");
	}

	public static Color parseColor(String str) {
		double a = 1.0;
		int r;
		int b;
		int g;

		if (str.length() == 8) {
			a = ((double) Integer.parseInt(str.substring(0, 2), 16)) / 255.0;
			r = Integer.parseInt(str.substring(2, 4), 16);
			g = Integer.parseInt(str.substring(4, 6), 16);
			b = Integer.parseInt(str.substring(6, 8), 16);
		} else {
			r = Integer.parseInt(str.substring(0, 2), 16);
			g = Integer.parseInt(str.substring(2, 4), 16);
			b = Integer.parseInt(str.substring(4, 6), 16);
		}

		return Color.rgb(r, g, b, a);
	}

	private static String intToHex(int value) {
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toHexString(value));
		while (sb.length() < 2) {
			sb.insert(0, '0');
		}
		return sb.toString();
	}
}
