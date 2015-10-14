package flagmaker.Overlays.OverlayTypes.PathTypes;

import flagmaker.ColorExtensions;
import flagmaker.Overlays.Attribute;
import flagmaker.Overlays.Overlay;
import flagmaker.Vector;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;

public class OverlayPath extends Overlay
{
	private final String _name;
	private final Vector _pathSize;
	private final String _path;
	
	public Color StrokeColor;
	
	public OverlayPath(String name, String path, Vector pathSize, int maximumX, int maximumY)
	{
		super(new Attribute[]
		{
			new Attribute("X", true, 1, true),
			new Attribute("Y", true, 1, true),
			new Attribute("Size", true, 1, true),
			new Attribute("Rotation", true, 0, true),
			new Attribute("Stroke", true, 0, true),
			new Attribute("StrokeCurved", true, 0, true)
		}, maximumX, maximumY);
		
		_name = name;
		_path = path;
		_pathSize = pathSize;
		StrokeColor = Color.WHITE;
	}
	
	protected OverlayPath(Color color, String name, String path, Vector pathSize, int maximumX, int maximumY)
	{
		super(new Attribute[]
		{
			new Attribute("X", true, 1, true),
			new Attribute("Y", true, 1, true),
			new Attribute("Size", true, 1, true),
			new Attribute("Rotation", true, 0, true),
			new Attribute("Stroke", true, 0, true),
			new Attribute("StrokeCurved", true, 0, true)
		}, maximumX, maximumY);
		
		_name = name;
		_path = path;
		_pathSize = pathSize;
		StrokeColor = Color.WHITE;
	}
	
	@Override
	public String Name()
	{
		return _name;
	}

	@Override
	protected Shape[] Thumbnail()
	{
		final double thumbSize = 30.0;
		double scale = thumbSize / Math.max(_pathSize.X, _pathSize.Y);
		SVGPath path = new SVGPath();
		path.setContent(_path);
		path.setScaleX(scale);
		path.setScaleY(scale);
		path.setTranslateX(thumbSize / 2);
		path.setTranslateY(thumbSize / 2);
		return new Shape[] { path };
	}

	@Override
	public void Draw(Pane canvas)
	{
		try
		{
			double xGridSize = canvas.getWidth() / MaximumX;
			double yGridSize = canvas.getHeight() / MaximumY;
			
			double x = GetAttribute("X").Value;
			double y = GetAttribute("Y").Value;
			
			Vector finalCenterPoint = new Vector(x * xGridSize, y * yGridSize);
			double scaleFactor = ScaleFactor(canvas.getWidth(), canvas.getHeight());
			
			SVGPath path = new SVGPath();
			path.setContent(_path);
			path.setFill(Color);
			path.setStroke(StrokeColor);
			path.setStrokeWidth(StrokeThickness(canvas.getWidth(), canvas.getHeight()));
			path.setStrokeLineJoin(GetAttribute("StrokeCurved").Value > MaximumX / 2.0
				? StrokeLineJoin.ROUND
				: StrokeLineJoin.MITER);
			
			path.setRotate(GetAttribute("Rotation").Value / MaximumX * 360);
			path.setScaleX(scaleFactor);
			path.setScaleY(scaleFactor);
			
			canvas.getChildren().add(path);
						
			path.setLayoutX(finalCenterPoint.X);
			path.setLayoutY(finalCenterPoint.Y);
		}
		catch (Exception e)
		{
			// Show alert
		}
	}

	@Override
	public void SetValues(Double[] values)
	{
		SetAttribute("X", values[0]);
		SetAttribute("Y", values[1]);
		SetAttribute("Size", values[2]);
		SetAttribute("Rotation", values[3]);
		SetAttribute("Stroke", values[4]);
		SetAttribute("StrokeCurved", values[5]);
	}

	@Override
	public String ExportSvg(int width, int height)
	{
		double xGridSize = (double)width / MaximumX;
		double yGridSize = (double)height / MaximumY;

		double x = GetAttribute("X").Value;
		double y = GetAttribute("Y").Value;

		Vector finalCenterPoint = new Vector(x * xGridSize, y * yGridSize);

		double idealPixelSize = GetAttribute("Size").Value / MaximumX * Math.max(width, height);
		double scaleFactor = idealPixelSize / Math.max(_pathSize.X, _pathSize.Y);
		double rotate = (GetAttribute("Rotation").Value / MaximumX) * 360;

		double strokeThickness = StrokeThickness(width, height);
		boolean strokeCurved = GetAttribute("StrokeCurved").Value > MaximumX / 2.0;

		return String.format("<g transform=\"translate(%.3f,%.3f) rotate(%.3f) scale(%.3f)\"><path d=\"%s\" %s %s /></g>",
			finalCenterPoint.X, finalCenterPoint.Y, rotate, scaleFactor, _path, ColorExtensions.ToSvgFillWithOpacity(Color),
			strokeThickness > 0
				? String.format("stroke=\"#%s\" stroke-width=\"$.3f\" stroke-linejoin=\"%s\"",
					ColorExtensions.ToHexString(StrokeColor, false), strokeThickness, strokeCurved ? "round" : "miter")
				: "");
	}
	
	private double StrokeThickness(double canvasWidth, double canvasHeight)
	{
		return canvasWidth * GetAttribute("Stroke").Value / 32 / ScaleFactor(canvasWidth, canvasHeight) / MaximumX;
	}
	
	private double ScaleFactor(double canvasWidth, double canvasHeight)
	{
		double idealPixelSize = GetAttribute("Size").Value / MaximumX * Math.max(canvasWidth, canvasHeight);
		return idealPixelSize / Math.max(_pathSize.X, _pathSize.Y);
	}
}
