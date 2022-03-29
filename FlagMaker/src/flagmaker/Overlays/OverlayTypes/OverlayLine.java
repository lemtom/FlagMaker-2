package flagmaker.Overlays.OverlayTypes;

import flagmaker.Extensions.ColorExtensions;
import flagmaker.Overlays.Attributes.*;
import flagmaker.Overlays.Overlay;
import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class OverlayLine extends Overlay
{
	public OverlayLine(int maximumX, int maximumY)
	{
		super("line", new Attribute[]
		{
			new ColorAttribute("Color", Color.BLACK),
			new DoubleAttribute("X1", 1, maximumX, true),
			new DoubleAttribute("Y1", 1, maximumY, false),
			new DoubleAttribute("X2", 2, maximumX, true),
			new DoubleAttribute("Y2", 2, maximumY, false),
			new DoubleAttribute("Thickness", 0.5, maximumX, true)
		}, maximumX, maximumY);
	}

	public OverlayLine(Color color, double x1, double y1, double x2, double y2, double thickness, int maximumX, int maximumY)
	{
		super("line", new Attribute[]
		{
			new ColorAttribute("Color", color),
			new DoubleAttribute("X1", x1, maximumX, true),
			new DoubleAttribute("Y1", y1, maximumY, false),
			new DoubleAttribute("X2", x2, maximumX, true),
			new DoubleAttribute("Y2", y2, maximumY, false),
			new DoubleAttribute("Thickness", thickness, maximumX, true)
		}, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail()
	{
		Line l = new Line(10, 5, 20, 25);
		l.setStrokeWidth(5);
		return new Shape[] { l };
	}

	@Override
	public void draw(Pane canvas)
	{
		Line line = new Line(
				canvas.getWidth() * getDoubleAttribute("X1") / maximumX,
				canvas.getHeight() * getDoubleAttribute("Y1") / maximumY,
				canvas.getWidth() * getDoubleAttribute("X2") / maximumX,
				canvas.getHeight() * getDoubleAttribute("Y2") / maximumY);
		line.setStrokeWidth(canvas.getWidth() * (getDoubleAttribute("Thickness") / maximumX));
		line.setStroke(getColorAttribute("Color"));
		canvas.getChildren().add(line);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		return String.format(Locale.US, "<line x1=\"%.3f\" y1=\"%.3f\" x2=\"%.3f\" y2=\"%.3f\" stroke=\"#%s\" stroke-width=\"%.3f\" />",
			width * getDoubleAttribute("X1") / maximumX,
			height * getDoubleAttribute("Y1") / maximumY,
			width * getDoubleAttribute("X2") / maximumX,
			height * getDoubleAttribute("Y2") / maximumY,
			ColorExtensions.toHexString(getColorAttribute("Color"), false),
			width * (getDoubleAttribute("Thickness") / maximumX));
	}
}
