package flagmaker.Overlays.OverlayTypes;

import flagmaker.Extensions.ColorExtensions;
import flagmaker.Overlays.Attributes.*;
import flagmaker.Overlays.Overlay;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Shape;

public class OverlayBorder extends Overlay
{
	public OverlayBorder(int maximumX, int maximumY)
	{
		super("border", new Attribute[]
		{
			new ColorAttribute("Color", Color.BLACK),
			new DoubleAttribute("Thickness", 1, maximumX, true)
		}, maximumX, maximumY);
	}

	public OverlayBorder(Color color, double thickness, int maximumX, int maximumY)
	{
		super("border", new Attribute[]
		{
			new ColorAttribute("Color", color),
			new DoubleAttribute("Thickness", thickness, maximumX, true)
		}, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail()
	{
		return new Shape[]
		{
			new Path(new PathElement[]
			{
				new MoveTo(0, 5),
				new LineTo(30, 5),
				new LineTo(30, 25),
				new LineTo(0, 25),
				new LineTo(0, 5),
				new MoveTo(5, 10),
				new LineTo(5, 20),
				new LineTo(25, 20),
				new LineTo(25, 10),
				new LineTo(5, 10)
			})
		};
	}

	@Override
	public void draw(Pane canvas)
	{
		double thickness = canvas.getWidth() * (getDoubleAttribute("Thickness") / maximumX) / 2;

		// Prevent the border from overlapping itself
		if (canvas.getWidth() - thickness * 2 < 0)
		{
			thickness = canvas.getWidth() / 2;
		}
		if (canvas.getHeight() - thickness * 2 < 0)
		{
			thickness = canvas.getHeight() / 2;
		}

		Path path = new Path(new PathElement[]
		{
			new MoveTo(0, 0),
			new LineTo(canvas.getWidth(), 0),
			new LineTo(canvas.getWidth(), canvas.getHeight()),
			new LineTo(0, canvas.getHeight()),
			new LineTo(0, 0),
			new MoveTo(thickness, thickness),
			new LineTo(thickness, canvas.getHeight() - thickness),
			new LineTo(canvas.getWidth() - thickness, canvas.getHeight() - thickness),
			new LineTo(canvas.getWidth() - thickness, thickness),
			new LineTo(thickness, thickness)
		});
		path.setFill(getColorAttribute("Color"));
		path.setStrokeWidth(0);

		canvas.getChildren().add(path);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		double thickness = width * (getDoubleAttribute("Thickness") / maximumX) / 2;

		// Prevent the border from overlapping itself
		if (width - thickness * 2 < 0)
		{
			thickness = width / 2.0;
		}
		if (height - thickness * 2 < 0)
		{
			thickness = height / 2.0;
		}

		return String.format("<path d=\"M 0,0 %1$d,0 %1$d,%2$d 0,%2$d Z M %3$.3f,%3$.3f %4$.3f,%3$.3f %4$.3f,%5$.3f %3$.3f,%5$.3f Z\" %6$s fill-rule=\"evenodd\" />",
			width, height,
			thickness,
			width - thickness,
			height - thickness,
			ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
