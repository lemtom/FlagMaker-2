package flagmaker.extensions;

public class StringExtensions {
	private StringExtensions() {
		throw new IllegalStateException("Utility class");
	}

	public static String getFilenameWithoutExtension(String fname) {
		int period = fname.lastIndexOf(".");
		int slash = Math.max(fname.lastIndexOf("/"), fname.lastIndexOf("\\"));
		if (period > 0) {
			fname = fname.substring(0, period);
		}
		if (slash > 0) {
			fname = fname.substring(slash + 1);
		}
		return fname;
	}

	public static String getFilenameExtension(String fname) {
		int period = fname.lastIndexOf(".");
		if (period > 0) {
			return fname.substring(period + 1);
		}
		return "";
	}

	public static boolean isNullOrWhitespace(String s) {
		return s == null || s.isBlank();
	}

}
