package flagmaker.about;

import flagmaker.extensions.CommonExtensions;
import flagmaker.files.LocalizationHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutController extends VBox {
	@FXML
	private Tab tabCredits;
	@FXML
	private Tab tabHistory;
	@FXML
	private Label lblVersion;
	@FXML
	private VBox creditBox;
	@FXML
	private VBox historyBox;

	public AboutController(Stage stage) {
		load(stage);

		tabCredits.setText(LocalizationHandler.get("Credits"));
		tabHistory.setText(LocalizationHandler.get("History"));
		lblVersion.setText(CommonExtensions.titleAndVersionString(getClass()));

		addCredits();
		addHistory();
	}

	private void addCredits() {
		try {
			addCredit(new URI("https://github.com/andrewsarnold"), "Andrew Arnold", "Development");
			addUnlinkedCredit("Hellerick Ferlibay — Russian translation");
			addUnlinkedCredit("Tikchbila — French translation");
			addUnlinkedCredit("Cubenity — Polish translation");
			addCredit(new URI("http://www.crwflags.com/fotw/flags/index.html"), "Flags of the World",
					"Flag construction specifications and colors");
			addCredit(new URI("http://en.wikipedia.org/"), "Wikipedia", "Flag construction specifications and colors");
			addCredit(new URI("https://flag-designer.appspot.com/"), "Lars Ruoff",
					"Inspiration; eagle, sun, and tree patterns");
			addUnlinkedCredit("Various emblems created by: PepePateaTraseros, VainRobot, OakBlood3");
			addUnlinkedCredit("the_dirty_saltire: Icons");
		} catch (URISyntaxException ex) {
			System.out.println("Error handling URL");
		}
	}

	private void addCredit(URI link, String name, String role) {
		Hyperlink h = new Hyperlink(name);
		h.setOnAction(event -> {
			try {
				if (CommonExtensions.isWindows()) {
					Desktop.getDesktop().browse(link);
				} else if (CommonExtensions.isMac()) {
					CommonExtensions.RunTime.exec("open " + link);
				} else {
					CommonExtensions.RunTime.exec("xdg-open " + link);
				}
			} catch (IOException ex) {
			}
		});
		h.setTooltip(new Tooltip(link.toString()));
		Label l = new Label("— " + role);
		l.setWrapText(true);
		TextFlow b = new TextFlow();
		b.setTextAlignment(TextAlignment.LEFT);
		b.getChildren().addAll(h, l);
		creditBox.getChildren().add(b);
	}

	private void addUnlinkedCredit(String text) {
		Label l = new Label(text);
		l.setPadding(new Insets(4));
		l.setWrapText(true);
		HBox b = new HBox();
		b.setAlignment(Pos.CENTER_LEFT);
		b.getChildren().add(l);
		creditBox.getChildren().add(b);
	}

	private void addHistory() {
		addVersion("2.1", "2016");
		addFeature("New icons");

		addVersion("2.0.1", "2016-07-10");
		addFeature("Fixed file encoding issue");

		addVersion("2.0", "2016-05-01");
		addFeature("Linux version");
		addFeature("Mac version");
		addFeature("Installer");
		addFeature("Brand-new random flag generation engine");
		addFeature("Polish translation");
		addFeature("Can rotate rays overlay");
		addFeature("New emblems: Albania, albatross, allahu, arevakhach, arrowhead, banner (2 versions), bear, beaver, "
				+ "bee, Bhutan, bison, bottany cross, castle, compass, condor, Cyprus, dove, dragon (Wales), eagle (Iowa), "
				+ "Eritrea, Ethiopia, fasces, fern, gear (2 versions), globe, Greenland, key (2 versions), Khazak banner and "
				+ "emblem, keystone, Kosovo, Kyrgyzstan, Kuwait, laurel, Lesotho, lion, lotus, Malawi, maple triple, Mexico, "
				+ "mjolnir, Nunavut, oak, Occitania, octagram, Oman, Ontario, pine, rooster, rose, sagebrush (2 versions), "
				+ "Saskatchewan, Scotland lion, shamrock (2 versions), shield (7 versions), star (Chicago), star (Marshall "
				+ "Islands), star shadow, Sudan, Tajikistan, torch, UN, Vanuatu, Venice, vergina, wheat, zia");
		addFeature("Attribute handling overhaul");
		addFeature("Minor bug fixes");

		addVersion("1.7", "2014-08-25");
		addFeature("Random flag generation");
		addFeature("Bulk SVG / PNG export");
		addFeature("Add strokes / outlines to path overlays");
		addFeature("Toggle overlay visibility for editing purposes");
		addFeature("New overlay types: Half-ellipse, line (arbitrary positioning), quadrilateral, ring");
		addFeature("New emblems: Bolnisi cross (Georgia), fire, shield, snake, wave, yang");
		addFeature("Toggleable cloth textures");
		addFeature("Major bug fixes and under-the-hood optimization");

		addVersion("1.6", "2014-02-15");
		addFeature("Popup window for selecting overlays instead of cluttered dropdown");
		addFeature("New overlay type: Transformer (arbitrary skewing, scaling, and rotation)");
		addFeature("New overlay type: Image (insert any JPG or PNG)");
		addFeature("New overlay type: Checkerboard");
		addFeature("Triangle overlays can now be positioned anywhere (this WILL break some saved flags!)");
		addFeature("Overlay sliders retain their values when switching types");
		addFeature("Overlay sliders accept percentage and fractional inputs");
		addFeature("Updating crescent overlay rendering (this WILL break some saved flags!)");
		addFeature("Improved SVG output for smaller file sizes");
		addFeature("Overlays no longer overflow the flag work area and cover other parts of the program");
		addFeature("French language support");
		addFeature("Fixed bug with multiple custom overlays of the same type not all being rendered");
		addFeature("Fixed bug where exporting SVG with repeaters included the repeated overlay on its own");

		addVersion("1.5.1", "2014-02-05");
		addFeature("Fixed a few custom overlay bugs");
		addFeature(
				"Fixed a bug where pall overlays crashed on some cultures (the dreaded comma-as-decimal-separator bug)");
		addFeature("Reducing decimal points to 3 on SVG output");
		addFeature("Russian language support");

		addVersion("1.5", "2014-02-02");
		addFeature("Custom overlay functionality");
		addFeature(
				"Updated lines, border, cross, saltires, and fimbriations to line up with the grid (this WILL break some saved flags!)");
		addFeature("All currently-used colors (custom or not) displayed in palette");
		addFeature("Button to quickly shuffle around the colors used");
		addFeature(
				"New emblems: American eagle, anchor, angola, cedar (Lebanon), chakra (India), Communist party of the USA logo, coronet (Swedish crown), crown, Egyptian eagle, emblem of Iran, flash (lightning bolt), Forth International logo, hand, harp (Ireland), iron cross, kangaroo, kiwi, laurel wreath, Maltese cross, Mozambique hoe and rifle, olive branches (Cyprus), Papua New Guinea, parteiadler / reichsadler, shahadah, Sikh emblem, springbok, swastika, sword (Saudi Arabia), takbir, trident (Barbados), triskele, yin");
		addFeature("Support for different languages");
		addFeature("Spanish translation");
		addFeature("Fixed major bug where the program would crash on computers using comma-decimal cultures");
		addFeature("Fixed similar bug when reading .flag files");
		addFeature("Fixed bug when loading a broken flag file");
		addFeature("Fixed line rendering bugs when exporting SVG");

		addVersion("1.4.1", "2013-12-04");
		addFeature("Fixed fimbriation SVG export bug");

		addVersion("1.4", "2013-12-04");
		addFeature("Custom PNG dimensions on export");
		addFeature("More robust file handling");
		addFeature("Updated triangle overlay to have variable height");
		addFeature("Shortcut keys for new, open, save, save as");
		addFeature("New overlay type: Rays");
		addFeature("New emblems: Fleur-de-lis, eagle, sun, tree, ermine");
		addFeature("Box for editing the name for a .flag file");
		addFeature("Fixed vertical alignment on star overlays");
		addFeature("Fixed main icon");
		addFeature("Fancy new icons for overlay controls");
		addFeature("More world flag presets");

		addVersion("1.3", "2013-11-09");
		addFeature(
				"Overlay sliders dealing with vertical alignment now use the grid's vertical size for its discrete values");
		addFeature("Changed \"canton\" to \"box\"; now freely positionable");
		addFeature(
				"Changed the corner angles of saltires and fimbriations to have the same proportions as the flag itself");
		addFeature("Click-to-edit slider values for overlays");
		addFeature("Alignment grid (toggleable)");
		addFeature("New overlay type: Repeaters (lateral and radial)");
		addFeature("New overlay type: Flag (embed another FlagMaker flag into the editor)");
		addFeature("New overlay type: Half saltire (as in the United Kingdom flag)");
		addFeature("New overlay type: Border");
		addFeature("New emblems: Four-pointed star, six-pointed star, eight-pointed star");
		addFeature("Fixed a crashing bug that occurred on invalid ratio entry");
		addFeature("Lots more world flag presets");
		addFeature("Spruced up the Readme a bit");
		addFeature("Behind-the-scenes refactoring and streamlining");

		addVersion("1.2", "2013-10-07");
		addFeature("Downgraded to .NET 4.0 for increased compatibility");
		addFeature("Fixed star overlay");
		addFeature("New overlay types: Horizontal line, vertical line");
		addFeature("New emblems: Equitorial (Swiss) cross, maple leaf, pentagram, seven-pointed star, star of David");

		addVersion("1.1", "2013-09-30");
		addFeature("SVG export");
		addFeature("Blank / basic presets");
		addFeature("Grey background (for easily seeing white flags)");
		addFeature("Additional color palette");
		addFeature("Improved overlay engine");
		addFeature("New emblems: Hammer and sickle, crescent");
		addFeature("Overlay cloning");
		addFeature("Overlay slider labels");
		addFeature("Continuous overlay sliders");
		addFeature("Adjustable workspace size");
		addFeature("Icon");

		addVersion("1.0", "2013-09-09");
		addFeature("Initial release");
	}

	private void addVersion(String number, String note) {
		Label l = new Label(String.format("%s — %s", number, note));
		l.getStyleClass().add("header");
		historyBox.getChildren().add(l);
	}

	private void addFeature(String text) {
		Label l = new Label("— " + text);
		l.setWrapText(true);
		HBox b = new HBox();
		b.setAlignment(Pos.CENTER_LEFT);
		b.getChildren().addAll(l);
		historyBox.getChildren().add(b);
	}

	private void load(Stage stage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("About.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		stage.titleProperty().set(LocalizationHandler.get("About"));
		stage.getIcons().add(new Image("flagmaker/images/icon.png"));

		try {
			loader.load();
		} catch (Exception ex) {
		}
	}
}
