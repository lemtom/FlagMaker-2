package flagmaker.overlays.overlaytypes;

import flagmaker.data.Vector;
import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

import java.util.ArrayList;
import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class OverlayRays extends Overlay
{
	public OverlayRays(int maximumX, int maximumY)
	{
		super("rays", new Attribute[]
		{
			new ColorAttribute("Color", Color.BLACK),
			new DoubleAttribute("X", 1, maximumX, true),
			new DoubleAttribute("Y", 1, maximumY, false),
			new IntegerAttribute("Count", 4, maximumX, true),
			new DoubleAttribute("Rotation", 0, maximumX, true),
		}, maximumX, maximumY);
	}
	
	public OverlayRays(Color color, double x, double y, int count, double rotation, int maximumX, int maximumY)
	{
		super("rays", new Attribute[]
		{
			new ColorAttribute("Color", color),
			new DoubleAttribute("X", x, maximumX, true),
			new DoubleAttribute("Y", y, maximumY, false),
			new IntegerAttribute("Count", count, maximumX, true),
			new DoubleAttribute("Rotation", rotation, maximumX, true),
		}, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail()
	{
		SVGPath path = new SVGPath();
		path.setContent("M 15,10 18,0 12,0 Z M 15,10 0,8 0,12 Z M 15,10 18,20 12,20 Z M 15,10 30,8 30,12 Z" +
						"M 15,10 6,0 0,0 0,3 Z M 15,10 24,0 30,0 30,3 Z M 15,10 24,20 30,20 30,17 Z M 15,10 6,20 0,20 0,17 Z");
		path.setLayoutY(5);
		return new Shape[] { path };
	}

	@Override
	public void draw(Pane canvas)
	{
		for (String path : getPaths(canvas.getWidth(), canvas.getHeight()))
		{
			SVGPath p = new SVGPath();
			p.setContent(path);
			p.setFill(getColorAttribute("Color"));
			canvas.getChildren().add(p);
		}
	}

	@Override
	public String exportSvg(int width, int height)
	{
		StringBuilder sb = new StringBuilder();

		for (String path : getPaths(width, height))
		{
			sb.append(String.format(Locale.US, "<path d=\"%s\" %s />",
				path, ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color"))));
		}

		return sb.toString();
	}
	
	private String[] getPaths(double width, double height)
	{
		double centerX = width * (getDoubleAttribute("X") / maximumX);
		double centerY = height * (getDoubleAttribute("Y") / maximumY);
		int count = getIntegerAttribute("Count");
		double rotation = getDoubleAttribute("Rotation") / maximumX;
		double rotationOffset = rotation * Math.PI * 2 / count;
		double angularInterval = Math.PI / count;
		
		ArrayList<String> returnValue = new ArrayList<>();

		for (int i = 0; i < count; i++)
		{
			Vector point1 = borderIntersection(centerX, centerY, angularInterval * 2 * i + rotationOffset, width, height);
			Vector point2 = borderIntersection(centerX, centerY, angularInterval * (2 * i + 1) + rotationOffset, width, height);

			// If points lie on different sides, add corner
			String point3 = "";
			if (point1.x != point2.x && point1.y != point2.y)
			{
				if (point1.y == 0)
				{
					point3 = "0,0 ";
				}
				else if (point1.x == 0)
				{
					point3 = String.format(Locale.US, "0,%.3f ", height);
				}
				else if (point1.y == height)
				{
					point3 = String.format(Locale.US, "%.3f,%.3f ", width, height);
				}
				else if (point1.x == width)
				{
					point3 = String.format(Locale.US, "%.3f,0 ", width);
				}
			}

			returnValue.add(String.format(Locale.US, "M %.3f,%.3f %.3f,%.3f %s%.3f,%.3f Z",
				centerX, centerY,
				point1.x, point1.y,
				point3,
				point2.x, point2.y));
		}
		
		String[] rv = new String[]{};
		return returnValue.toArray(rv);
	}

	private static Vector borderIntersection(double centerX, double centerY, double angle, double width, double height)
	{
		ArrayList<Vector> possiblePoints = new ArrayList<>();

		if (angle > 0 && angle < Math.PI)
		{
			// Check intersection with top border
			double tX = centerY / Math.tan(angle);
			possiblePoints.add(new Vector(centerX + tX, 0));
		}
		if (angle > Math.PI / 2 && angle < 3 * Math.PI / 2)
		{
			// Check intersection with left border
			double tY = centerX * Math.tan(2 * Math.PI - angle);
			possiblePoints.add(new Vector(0, centerY - tY));
		}
		if (angle > Math.PI && angle < 2 * Math.PI)
		{
			// Check intersection with bottom border
			double tX = Math.tan(3 * Math.PI / 2 - angle) * (height - centerY);
			possiblePoints.add(new Vector(centerX - tX, height));
		}
		if (angle < Math.PI / 2)
		{
			// Check intersection with right border
			double tY = Math.tan(angle) * (width - centerX);
			possiblePoints.add(new Vector(width, centerY - tY));
		}
		if (angle > 3 * Math.PI / 2)
		{
			// Check intersection with right border
			double tY = Math.tan(2 * Math.PI - angle) * (width - centerX);
			possiblePoints.add(new Vector(width, centerY + tY));
		}
		
		possiblePoints.sort((Vector o1, Vector o2) ->
		{
			Double l1 = length(o1, new Vector(centerX, centerY));
			Double l2 = length(o2, new Vector(centerX, centerY));
			return l1.compareTo(l2);
		});

		return !possiblePoints.isEmpty()
			? possiblePoints.get(0)
			: new Vector(centerX, centerY);
	}

	private static double length(Vector p1, Vector p2)
	{
		return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
}
