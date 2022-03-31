package test;

import flagmaker.data.Flag;
import flagmaker.data.Ratio;
import flagmaker.divisions.Division;
import flagmaker.divisions.DivisionGrid;
import flagmaker.divisions.DivisionPales;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.overlaytypes.OverlayLineHorizontal;
import flagmaker.overlays.overlaytypes.pathtypes.OverlayStar;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShuffleTests
{
	private static final int MAX_X = 5;
	private static final int MAX_Y = 3;
	private static final Ratio RATIO = new Ratio(MAX_X, MAX_Y);
	
	@Test
	public void gridTest()
	{
		Flag f = new Flag("Basic", RATIO, RATIO, new DivisionGrid(Color.RED, Color.WHITE, 2, 2), new Overlay[]{});
		f.shuffleColors();
		
		assertEquals(Color.WHITE, f.getDivision().colors[0]);
		assertEquals(Color.RED, f.getDivision().colors[1]);
	}

	@Test
	public void blankTest()
	{
		Flag f = getSampleFlag();
		f.shuffleColors();
		
		assertEquals(Color.RED, f.getDivision().colors[0]);
	}

	@Test
	public void palesTest()
	{
		Flag f = new Flag("Basic", RATIO, RATIO, new DivisionPales(Color.RED, Color.WHITE, Color.BLUE, 1, 1, 1), new Overlay[]{});
		f.shuffleColors();
		
		assertEquals(Color.BLUE, f.getDivision().colors[0]);
		assertEquals(Color.RED, f.getDivision().colors[1]);
		assertEquals(Color.WHITE, f.getDivision().colors[2]);
	}
	
	@Test
	public void singleOverlayTest()
	{
		Flag f = getSampleFlag(new Overlay[] { new OverlayLineHorizontal(Color.WHITE, 1.5, 1, MAX_X, MAX_Y) });
		f.shuffleColors();
		
		assertEquals(Color.WHITE, f.getDivision().colors[0]);
		assertEquals(Color.RED, f.getOverlays()[0].getColorAttribute("Color"));
	}
	
	@Test
	public void flagOverlayTest()
	{
		Flag inner = new Flag("inner", RATIO, RATIO, getBlankDivision(Color.GREEN), new Overlay[] { new OverlayLineHorizontal(Color.WHITE, 1.5, 1, MAX_X, MAX_Y) });
		Flag outer = getSampleFlag(new Overlay[] { new OverlayFlag(inner, null, 0, 0, MAX_X / 2, MAX_X / 2, MAX_X, MAX_Y)});
		outer.shuffleColors();
		
		assertEquals(Color.GREEN, outer.getDivision().colors[0]);
		assertEquals(Color.WHITE, inner.getDivision().colors[0]);
		assertEquals(Color.RED, inner.getOverlays()[0].getColorAttribute("Color"));
		assertEquals(inner, ((OverlayFlag)outer.getOverlays()[0]).flag);
	}
	
	@Test
	public void pathOverlayTest()
	{
		Overlay o = new OverlayStar(MAX_X, MAX_Y);
		o.setAttribute("Color", Color.GREEN);
		Flag f = getSampleFlag(new Overlay[] { o });
		f.shuffleColors();
		
		assertEquals(Color.GREEN, f.getDivision().colors[0]);
		assertEquals(Color.RED, o.getColorAttribute("Color"));
		assertEquals(Color.BLACK, o.getColorAttribute("StrokeColor")); // unchanged
	}
	
	@Test
	public void pathStrokedOverlayTest()
	{
		Overlay o = new OverlayStar(MAX_X, MAX_Y);
		o.setAttribute("Color", Color.GREEN);
		o.setAttribute("StrokeColor", Color.BLUE);
		o.setAttribute("Stroke", 1.0);
		
		Flag f = getSampleFlag(new Overlay[] { o });
		f.shuffleColors();
		
		assertEquals(Color.GREEN, f.getDivision().colors[0]);
		assertEquals(Color.BLUE, o.getColorAttribute("Color"));
		assertEquals(Color.RED, o.getColorAttribute("StrokeColor"));
	}
	
	private Flag getSampleFlag()
	{
		return getSampleFlag(new Overlay[]{});
	}
	
	private Flag getSampleFlag(Overlay[] overlays)
	{
		return new Flag("Basic", RATIO, RATIO, getBlankDivision(Color.RED), overlays);
	}
	
	private Division getBlankDivision(Color color)
	{
		return new DivisionGrid(color, Color.TRANSPARENT, 1, 1);
	}
}
