package test;

import flagmaker.data.Flag;
import flagmaker.data.Ratio;
import flagmaker.divisions.*;
import flagmaker.files.FileHandler;
import flagmaker.overlays.OverlayFactory;
import flagmaker.overlays.overlaytypes.pathtypes.OverlayStar;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

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

		StringBuilder sb = new StringBuilder();
		sb.append("name=Test\n");
		sb.append("ratio=3:5\n");
		sb.append("gridsize=3:5\n\n");

		sb.append("type=grid\n");
		sb.append("color1=ff0000\n");
		sb.append("color2=ffffff\n");
		sb.append("size1=2\n");
		sb.append("size2=2\n\n");

		sb.append("type=flag\n");
		sb.append(String.format("path=%s\n", inner.getName()));
		sb.append("x=0\n");
		sb.append("y=0\n");
		sb.append("width=2.5\n");
		sb.append("height=1.5\n");

		File file = saveFlagFile(sb.toString());
		Flag flag = null;

		try {
			flag = FileHandler.loadFlagFromFile(file);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertEquals(flag.ratio.width, ratio.width);
		assertEquals(flag.ratio.height, ratio.height);
		assertEquals(flag.gridSize.width, ratio.width);
		assertEquals(flag.gridSize.height, ratio.height);

		assertTrue(flag.division instanceof DivisionGrid);
		assertEquals(Color.RED, flag.division.colors[0]);
		assertEquals(Color.WHITE, flag.division.colors[1]);
		assertEquals(2, flag.division.values[0]);
		assertEquals(2, flag.division.values[1]);

		assertEquals(1, flag.overlays.length);
		assertTrue(flag.overlays[0] instanceof OverlayFlag);
		OverlayFlag f = (OverlayFlag) flag.overlays[0];
		assertEquals(0, f.getDoubleAttribute("X"), 0.001);
		assertEquals(0, f.getDoubleAttribute("Y"), 0.001);
		assertEquals(2.5, f.getDoubleAttribute("Width"), 0.001);
		assertEquals(1.5, f.getDoubleAttribute("Height"), 0.001);

		assertBasicFlag(f.flag);
	}

	private void assertBasicFlag(Flag flag) {
		assertEquals(flag.ratio.width, ratio.width);
		assertEquals(flag.ratio.height, ratio.height);
		assertEquals(flag.gridSize.width, ratio.width);
		assertEquals(flag.gridSize.height, ratio.height);

		assertTrue(flag.division instanceof DivisionGrid);
		assertEquals(Color.RED, flag.division.colors[0]);
		assertEquals(Color.WHITE, flag.division.colors[1]);
		assertEquals(2, flag.division.values[0]);
		assertEquals(2, flag.division.values[1]);

		assertEquals(1, flag.overlays.length);
		assertTrue(flag.overlays[0] instanceof OverlayStar);
		OverlayStar s = (OverlayStar) flag.overlays[0];
		assertEquals(2.5, s.getDoubleAttribute("X"), 0.001);
		assertEquals(1.5, s.getDoubleAttribute("Y"), 0.001);
		assertEquals(1, s.getDoubleAttribute("Size"), 0.001);
		assertEquals(0, s.getDoubleAttribute("Rotation"), 0.001);
		assertEquals(0, s.getDoubleAttribute("Stroke"), 0.001);
		assertEquals(Color.BLACK, s.getColorAttribute("StrokeColor"));
		assertEquals(false, s.getBooleanAttribute("StrokeCurved"));
	}

	private File saveBasicFlag() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=Test\n");
		sb.append("ratio=3:5\n");
		sb.append("gridsize=3:5\n\n");

		sb.append("type=grid\n");
		sb.append("color1=ff0000\n");
		sb.append("color2=ffffff\n");
		sb.append("size1=2\n");
		sb.append("size2=2\n\n");

		sb.append("type=star\n");
		sb.append("color=00ff00\n");
		sb.append("x=2.5\n");
		sb.append("y=1.5\n");
		sb.append("size=1\n");
		sb.append("rotation=0\n");
		sb.append("stroke=0\n");
		sb.append("strokecolor=000000\n");
		sb.append("strokecurved=false\n");
		
		return saveFlagFile(sb.toString());
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
