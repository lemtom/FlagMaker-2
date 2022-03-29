package flagmaker.Overlays.OverlayTypes;

import flagmaker.Extensions.ColorExtensions;
import flagmaker.Overlays.Attributes.*;
import flagmaker.Overlays.Overlay;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class OverlaySaltire extends Overlay
{
	public OverlaySaltire(int maximumX, int maximumY)
	{
		super("saltire", new Attribute[]
		{
			new ColorAttribute("Color", Color.BLACK),
			new DoubleAttribute("Thickness", 1, maximumX, true)
		}, maximumX, maximumY);
	}

	public OverlaySaltire(Color color, double thickness, int maximumX, int maximumY)
	{
		super("saltire", new Attribute[]
		{
			new ColorAttribute("Color", color),
			new DoubleAttribute("Thickness", thickness, maximumX, true)
		}, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail()
	{
		Line l1 = new Line(0, 5, 30, 25);
		Line l2 = new Line(30, 5, 0, 25);
		l1.setStrokeWidth(5);
		l2.setStrokeWidth(5);
		return new Shape[] { l1, l2 };
	}

	@Override
	public void draw(Pane canvas)
	{
		double widthX = canvas.getWidth() * (getDoubleAttribute("Thickness") / maximumX) / 2;
		double widthY = canvas.getHeight() * (getDoubleAttribute("Thickness") / maximumX) / 2;

		SVGPath path1 = new SVGPath();
		SVGPath path2 = new SVGPath();
		
		path1.setContent(String.format("M %1$.3f,0 0,0 0,%2$.3f %3$.3f,%4$.3f %5$.3f,%4$.3f %5$.3f,%6$.3f %1$.3f,0",
				widthX, widthY, canvas.getWidth() - widthX, canvas.getHeight(), canvas.getWidth(), canvas.getHeight() - widthY));
		path2.setContent(String.format("M %1$.3f,0 %2$.3f,0 %2$.3f,%6$.3f %3$.3f,%4$.3f 0,%4$.3f 0,%5$.3f %1$.3f,0",
				canvas.getWidth() - widthX, canvas.getWidth(), widthX, canvas.getHeight(), canvas.getHeight() - widthY, widthY));

		path1.setFill(getColorAttribute("Color"));
		path2.setFill(getColorAttribute("Color"));
		
		canvas.getChildren().addAll(path1, path2);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		double wX = width * (getDoubleAttribute("Thickness") / maximumX) / 2;
		double wY = height * (getDoubleAttribute("Thickness") / maximumX) / 2;

		return String.format("<polygon points=\"%1$.3f,0 0,0 0,%6$.3f %2$.3f,%3$d %4$d,%3$d %4$d,%5$.3f %1$.3f,0\" %7$s /><polygon points=\"%2$.3f,0 %4$d,0 %4$d,%6$.3f %1$.3f,%3$d 0,%3$d 0,%5$.3f %2$.3f,0\" %7$s />",
			wX, width - wX, height, width, height - wY, wY, ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
