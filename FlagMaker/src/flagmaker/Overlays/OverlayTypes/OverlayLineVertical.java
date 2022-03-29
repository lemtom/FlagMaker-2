package flagmaker.Overlays.OverlayTypes;

import flagmaker.Extensions.ColorExtensions;
import flagmaker.Overlays.Attributes.*;
import flagmaker.Overlays.Overlay;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class OverlayLineVertical extends Overlay
{
	public OverlayLineVertical(int maximumX, int maximumY)
	{
		super("line vertical", new Attribute[]
		{
			new ColorAttribute("Color", Color.BLACK),
			new DoubleAttribute("X", 1, maximumX, true),
			new DoubleAttribute("Thickness", 0.5, maximumX, false)
		}, maximumX, maximumY);
	}

	public OverlayLineVertical(Color color, double x, double thickness, int maximumX, int maximumY)
	{
		super("line vertical", new Attribute[]
		{
			new ColorAttribute("Color", color),
			new DoubleAttribute("X", x, maximumX, true),
			new DoubleAttribute("Thickness", thickness, maximumX, false)
		}, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail()
	{
		Line l = new Line(15, 5, 15, 25);
		l.setStrokeWidth(5);
		return new Shape[] { l };
	}

	@Override
	public void draw(Pane canvas)
	{
		Line line = new Line(
				canvas.getWidth() * getDoubleAttribute("X") / maximumX,
				0,
				canvas.getWidth() * getDoubleAttribute("X") / maximumX,
				canvas.getHeight());
		line.setStrokeWidth(canvas.getWidth() * (getDoubleAttribute("Thickness") / maximumX));
		line.setStroke(getColorAttribute("Color"));
		canvas.getChildren().add(line);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		return String.format("<line x1=\"%.3f\" y1=\"0\" x2=\"%3f\" y2=\"%d\" stroke=\"#%s\" stroke-width=\"%.3f\" />",
			width * getDoubleAttribute("X") / maximumX,
			width * getDoubleAttribute("X") / maximumX,
			height,
			ColorExtensions.toHexString(getColorAttribute("Color"), false),
			width * (getDoubleAttribute("Thickness") / maximumX));
	}
}
