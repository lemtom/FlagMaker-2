package flagmaker;

import javafx.scene.paint.Color;
import org.junit.BeforeClass;
import org.junit.Test;

import flagmaker.data.Flag;
import flagmaker.data.Ratio;
import flagmaker.divisions.DivisionGrid;
import flagmaker.files.FileHandler;
import flagmaker.overlays.OverlayFactory;
import flagmaker.overlays.overlaytypes.pathtypes.OverlayStar;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;


public class FileTests {
	private final Ratio ratio = new Ratio(5, 3);

	@BeforeClass
	public static void setUpClass() {
		OverlayFactory.setUpTypeMap();
		OverlayFactory.fillCustomOverlays();
	}
	
	@Test
	public void loadFlagFromStringTest() {
		try {
			assertBasicFlag(FileHandler.loadFlagFromFile(saveBasicFlag()));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void loadFlagFromStringWithFlagOverlayTest() {
		File inner = saveBasicFlag();

		String sb = "name=Test\n" +
				"ratio=3:5\n" +
				"gridsize=3:5\n\n" +
				"type=grid\n" +
				"color1=ff0000\n" +
				"color2=ffffff\n" +
				"size1=2\n" +
				"size2=2\n\n" +
				"type=flag\n" +
				String .format("path=%s\n", inner.getName()) +
				"x=0\n" +
				"y=0\n" +
				"width=2.5\n" +
				"height=1.5\n";
		File file = saveFlagFile(sb);
		Flag flag = null;

		try {
			flag = FileHandler.loadFlagFromFile(file);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertEquals(flag.getRatio().getWidth(), ratio.getWidth());
		assertEquals(flag.getRatio().getHeight(), ratio.getHeight());
		assertEquals(flag.getGridSize().getWidth(), ratio.getWidth());
		assertEquals(flag.getGridSize().getHeight(), ratio.getHeight());

		assertTrue(flag.getDivision() instanceof DivisionGrid);
		assertEquals(Color.RED, flag.getDivision().colors[0]);
		assertEquals(Color.WHITE, flag.getDivision().colors[1]);
		assertEquals(2, flag.getDivision().values[0]);
		assertEquals(2, flag.getDivision().values[1]);

		assertEquals(1, flag.getOverlays().length);
		assertTrue(flag.getOverlays()[0] instanceof OverlayFlag);
		OverlayFlag f = (OverlayFlag) flag.getOverlays()[0];
		assertEquals(0, f.getDoubleAttribute("X"), 0.001);
		assertEquals(0, f.getDoubleAttribute("Y"), 0.001);
		assertEquals(2.5, f.getDoubleAttribute("Width"), 0.001);
		assertEquals(1.5, f.getDoubleAttribute("Height"), 0.001);

		assertBasicFlag(f.flag);
	}

	private void assertBasicFlag(Flag flag) {
		assertEquals(flag.getRatio().getWidth(), ratio.getWidth());
		assertEquals(flag.getRatio().getHeight(), ratio.getHeight());
		assertEquals(flag.getGridSize().getWidth(), ratio.getWidth());
		assertEquals(flag.getGridSize().getHeight(), ratio.getHeight());

		assertTrue(flag.getDivision() instanceof DivisionGrid);
		assertEquals(Color.RED, flag.getDivision().colors[0]);
		assertEquals(Color.WHITE, flag.getDivision().colors[1]);
		assertEquals(2, flag.getDivision().values[0]);
		assertEquals(2, flag.getDivision().values[1]);

		assertEquals(1, flag.getOverlays().length);
		assertTrue(flag.getOverlays()[0] instanceof OverlayStar);
		OverlayStar s = (OverlayStar) flag.getOverlays()[0];
		assertEquals(2.5, s.getDoubleAttribute("X"), 0.001);
		assertEquals(1.5, s.getDoubleAttribute("Y"), 0.001);
		assertEquals(1, s.getDoubleAttribute("Size"), 0.001);
		assertEquals(0, s.getDoubleAttribute("Rotation"), 0.001);
		assertEquals(0, s.getDoubleAttribute("Stroke"), 0.001);
		assertEquals(Color.BLACK, s.getColorAttribute("StrokeColor"));
		assertEquals(false, s.getBooleanAttribute("StrokeCurved"));
	}

	private File saveBasicFlag() {

		String sb = "name=Test\n" +
				"ratio=3:5\n" +
				"gridsize=3:5\n\n" +
				"type=grid\n" +
				"color1=ff0000\n" +
				"color2=ffffff\n" +
				"size1=2\n" +
				"size2=2\n\n" +
				"type=star\n" +
				"flagmaker.color=00ff00\n" +
				"x=2.5\n" +
				"y=1.5\n" +
				"size=1\n" +
				"rotation=0\n" +
				"stroke=0\n" +
				"strokecolor=000000\n" +
				"strokecurved=false\n";
		return saveFlagFile(sb);
	}

	private File saveFlagFile(String data) {
		try {
			File f = File.createTempFile("test", ".flag");

			try (FileWriter writer = new FileWriter(f, false); PrintWriter printLine = new PrintWriter(writer)) {
				printLine.printf(data);
			}

			return f;
		} catch (IOException ex) {
			fail();
		}

		return null;
	}
}
