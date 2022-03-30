package test;

import flagmaker.data.Flag;
import flagmaker.data.Ratio;
import flagmaker.divisions.*;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.overlaytypes.*;
import flagmaker.overlays.overlaytypes.pathtypes.OverlayStar;
import flagmaker.overlays.overlaytypes.specialtypes.OverlayFlag;
import javafx.scene.paint.Color;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShuffleTests
{
	private final int maxX = 5;
	private final int maxY = 3;
	private final Ratio ratio = new Ratio(maxX, maxY);
	
	@Test
	public void gridTest()
	{
		Flag f = new Flag("Basic", ratio, ratio, new DivisionGrid(Color.RED, Color.WHITE, 2, 2), new Overlay[]{});
		f.shuffleColors();
		
		assertEquals(Color.WHITE, f.division.colors[0]);
		assertEquals(Color.RED, f.division.colors[1]);
	}

	@Test
	public void blankTest()
	{
		Flag f = getSampleFlag();
		f.shuffleColors();
		
		assertEquals(Color.RED, f.division.colors[0]);
	}

	@Test
	public void palesTest()
	{
		Flag f = new Flag("Basic", ratio, ratio, new DivisionPales(Color.RED, Color.WHITE, Color.BLUE, 1, 1, 1), new Overlay[]{});
		f.shuffleColors();
		
		assertEquals(Color.BLUE, f.division.colors[0]);
		assertEquals(Color.RED, f.division.colors[1]);
		assertEquals(Color.WHITE, f.division.colors[2]);
	}
	
	@Test
	public void singleOverlayTest()
	{
		Flag f = getSampleFlag(new Overlay[] { new OverlayLineHorizontal(Color.WHITE, 1.5, 1, maxX, maxY) });
		f.shuffleColors();
		
		assertEquals(Color.WHITE, f.division.colors[0]);
		assertEquals(Color.RED, f.overlays[0].getColorAttribute("Color"));
	}
	
	@Test
	public void flagOverlayTest()
	{
		Flag inner = new Flag("inner", ratio, ratio, getBlankDivision(Color.GREEN), new Overlay[] { new OverlayLineHorizontal(Color.WHITE, 1.5, 1, maxX, maxY) });
		Flag outer = getSampleFlag(new Overlay[] { new OverlayFlag(inner, null, 0, 0, maxX / 2, maxX / 2, maxX, maxY)});
		outer.shuffleColors();
		
		assertEquals(Color.GREEN, outer.division.colors[0]);
		assertEquals(Color.WHITE, inner.division.colors[0]);
		assertEquals(Color.RED, inner.overlays[0].getColorAttribute("Color"));
		assertEquals(inner, ((OverlayFlag)outer.overlays[0]).flag);
	}
	
	@Test
	public void pathOverlayTest()
	{
		Overlay o = new OverlayStar(maxX, maxY);
		o.setAttribute("Color", Color.GREEN);
		Flag f = getSampleFlag(new Overlay[] { o });
		f.shuffleColors();
		
		assertEquals(Color.GREEN, f.division.colors[0]);
		assertEquals(Color.RED, o.getColorAttribute("Color"));
		assertEquals(Color.BLACK, o.getColorAttribute("StrokeColor")); // unchanged
	}
	
	@Test
	public void pathStrokedOverlayTest()
	{
		Overlay o = new OverlayStar(maxX, maxY);
		o.setAttribute("Color", Color.GREEN);
		o.setAttribute("StrokeColor", Color.BLUE);
		o.setAttribute("Stroke", 1.0);
		
		Flag f = getSampleFlag(new Overlay[] { o });
		f.shuffleColors();
		
		assertEquals(Color.GREEN, f.division.colors[0]);
		assertEquals(Color.BLUE, o.getColorAttribute("Color"));
		assertEquals(Color.RED, o.getColorAttribute("StrokeColor"));
	}
	
	private Flag getSampleFlag()
	{
		return getSampleFlag(new Overlay[]{});
	}
	
	private Flag getSampleFlag(Overlay[] overlays)
	{
		return new Flag("Basic", ratio, ratio, getBlankDivision(Color.RED), overlays);
	}
	
	private Division getBlankDivision(Color color)
	{
		return new DivisionGrid(color, Color.TRANSPARENT, 1, 1);
	}
}
