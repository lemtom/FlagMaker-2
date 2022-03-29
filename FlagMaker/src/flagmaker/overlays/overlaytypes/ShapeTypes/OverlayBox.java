package flagmaker.overlays.overlaytypes.ShapeTypes;

import java.util.Locale;

import flagmaker.extensions.ColorExtensions;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class OverlayBox extends OverlayShape
{
	public OverlayBox(int maximumX, int maximumY)
	{
		super("box", maximumX, maximumY);
	}

	public OverlayBox(Color color, double x, double y, double width, double height, int maximumX, int maximumY)
	{
		super("box", color, x, y, width, height, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail()
	{
		return new Shape[]
		{
			new Rectangle(5, 7.5, 20, 15)
		};
	}

	@Override
	public void draw(Pane canvas)
	{
		double width = canvas.getWidth() * (getDoubleAttribute("Width") / (double)maximumX);
		double height = getDoubleAttribute("Height") == 0
				? width
				: canvas.getHeight() * (getDoubleAttribute("Height") / maximumY);
		double left = canvas.getWidth() * (getDoubleAttribute("X") / maximumX);
		double top = canvas.getHeight() * (getDoubleAttribute("Y") / maximumY);
		Rectangle rect = new Rectangle(left, top, width, height);
		rect.setFill(getColorAttribute("Color"));
		canvas.getChildren().add(rect);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		double w = width * (getDoubleAttribute("Width") / (double)maximumX);
		double h = getDoubleAttribute("Height") == 0
				? w
				: height * (getDoubleAttribute("Height") / maximumY);
		return String.format(Locale.US, "<rect width=\"%.3f\" height=\"%.3f\" x=\"%.3f\" y=\"%.3f\" %s />",
				w,
				h,
				width * (getDoubleAttribute("X") / maximumX),
				height * (getDoubleAttribute("Y") / maximumY),
				ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
