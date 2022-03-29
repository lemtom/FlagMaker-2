package flagmaker.Overlays.OverlayTypes.ShapeTypes;

import flagmaker.Extensions.ColorExtensions;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class OverlayEllipse extends OverlayShape
{
	public OverlayEllipse(int maximumX, int maximumY)
	{
		super("ellipse", maximumX, maximumY);
	}

	public OverlayEllipse(Color color, double x, double y, double width, double height, int maximumX, int maximumY)
	{
		super("ellipse", color, x, y, width, height, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail()
	{
		return new Shape[]
		{
			new Ellipse(15, 15, 15, 10)
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
		Ellipse ellipse = new Ellipse(left, top, width / 2, height / 2);
		ellipse.setFill(getColorAttribute("Color"));
		canvas.getChildren().add(ellipse);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		double w = width * (getDoubleAttribute("Width") / (double)maximumX);
		double h = getDoubleAttribute("Height") == 0
				? w
				: height * (getDoubleAttribute("Height") / maximumY);
		
		double x = width * (getDoubleAttribute("X") / maximumX);
		double y = height * (getDoubleAttribute("Y") / maximumY);
		
		return String.format("<ellipse cx=\"%.3f\" cy=\"%.3f\" rx=\"%.3f\" ry=\"%.3f\" %s />",
				x, y, w / 2, h / 2,
				ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
