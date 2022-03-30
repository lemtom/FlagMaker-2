package flagmaker.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationHandler {
	private static Locale currentLocale;
	private static ResourceBundle bundle;
	private static ResourceBundle defaultBundle;

	private LocalizationHandler() {
		throw new IllegalStateException("Utility class");
	}

	public static void initialize() {
		currentLocale = getLocalePreference();
		if (currentLocale == null)
			currentLocale = Locale.US;
		defaultBundle = ResourceBundle.getBundle("bundles.strings", Locale.US);
		setBundle(currentLocale);
	}

	public static void setLanguage(Locale locale) {
		currentLocale = locale;
		setBundle(currentLocale);
		saveLocalePreference(currentLocale);
	}

	public static String get(String key) {
		try {
			return bundle.getString(key);
		} catch (Exception e) {
			return defaultBundle.getString(key);
		}
	}

	private static void setBundle(Locale locale) {
		try {
			bundle = ResourceBundle.getBundle("bundles.strings", locale);
		} catch (Exception e) {
			bundle = defaultBundle;
		}
	}

	private static Locale getLocalePreference() {
		File preferencesFile = new File("flagmaker.config");
		if (preferencesFile.exists()) {
			try (FileReader fr = new FileReader(preferencesFile); BufferedReader sr = new BufferedReader(fr)) {
				String line;
				while ((line = sr.readLine()) != null) {
					if (line.startsWith("locale=")) {
						return new Locale(line.split("=")[1]);
					}
				}
			} catch (Exception e) {
				return Locale.getDefault();
			}
		}

		return Locale.getDefault();
	}

	private static void saveLocalePreference(Locale locale) {
		File preferencesFile = new File("flagmaker.config");

		try {
			preferencesFile.createNewFile();
		} catch (Exception e) {
		}

		try (FileWriter writer = new FileWriter(preferencesFile, false);
				PrintWriter printLine = new PrintWriter(writer)) {
			printLine.println(String.format("locale=%s", locale.getLanguage()));
		} catch (Exception e) {
		}
	}
}
