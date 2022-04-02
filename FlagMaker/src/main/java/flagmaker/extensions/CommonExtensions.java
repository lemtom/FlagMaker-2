package flagmaker.extensions;

public class CommonExtensions {

	private CommonExtensions() {
		throw new IllegalStateException("Utility class");
	}

	private static final String OS = System.getProperty("os.name");

	public static final Runtime RunTime = Runtime.getRuntime();

	public static boolean isWindows() {
		return OS.contains("win");
	}

	public static boolean isMac() {
		return OS.contains("mac");
	}

	public static boolean isLinux() {
		return OS.contains("nix") || OS.contains("nux");
	}

	public static String titleAndVersionString(Class<?> c) {
		Package p = c.getPackage();
		String version = p.getImplementationVersion() != null ? p.getImplementationVersion() : "";
		String title = p.getImplementationTitle() != null ? p.getImplementationTitle() : "FlagMaker";
		return String.format("%s %s", title, version);
	}
}
