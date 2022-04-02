package flagmaker.overlays.overlaytypes.pathtypes;

import flagmaker.data.Vector;
import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.BooleanAttribute;
import flagmaker.overlays.attributes.ColorAttribute;
import flagmaker.overlays.attributes.DoubleAttribute;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.util.Locale;

public class OverlayPath extends Overlay {
	private Vector pathSize;
	private String path;

	public OverlayPath(String name, int maximumX, int maximumY) {
		super(name,
				new Attribute[] { new ColorAttribute("Color", Color.BLACK), new DoubleAttribute("X", 1, maximumX, true),
						new DoubleAttribute("Y", 1, maximumY, false), new DoubleAttribute("Size", 1, maximumX, true),
						new DoubleAttribute("Rotation", 0, maximumX, true),
						new DoubleAttribute("Stroke", 0, maximumX, true),
						new ColorAttribute("StrokeColor", Color.BLACK), new BooleanAttribute("StrokeCurved", false) },
				maximumX, maximumY);
	}

	public OverlayPath(String name, String path, Vector pathSize) {
		super(name,
				new Attribute[] { new ColorAttribute("Color", Color.BLACK), new DoubleAttribute("X", 1, 1, true),
						new DoubleAttribute("Y", 1, 1, false), new DoubleAttribute("Size", 1, 1, true),
						new DoubleAttribute("Rotation", 0, 1, true), new DoubleAttribute("Stroke", 0, 1, true),
						new ColorAttribute("StrokeColor", Color.BLACK), new BooleanAttribute("StrokeCurved", false) },
				1, 1);

		this.path = path;
		this.pathSize = pathSize;
	}

	public OverlayPath(String name, String path, Vector pathSize, int maximumX, int maximumY) {
		super(name,
				new Attribute[] { new ColorAttribute("Color", Color.BLACK), new DoubleAttribute("X", 1, 1, true),
						new DoubleAttribute("Y", 1, 1, false), new DoubleAttribute("Size", 1, 1, true),
						new DoubleAttribute("Rotation", 0, 1, true), new DoubleAttribute("Stroke", 0, 1, true),
						new ColorAttribute("StrokeColor", Color.BLACK), new BooleanAttribute("StrokeCurved", false) },
				maximumX, maximumY);

		this.path = path;
		this.pathSize = pathSize;
	}

	protected void constructor(String path, Vector pathSize) {
		this.path = path;
		this.pathSize = pathSize;
	}

	@Override
	protected Shape[] thumbnail() {
		final double thumbSize = 30.0;
		double scaleFactor = thumbSize / Math.max(pathSize.getX(), pathSize.getY());
		SVGPath path = new SVGPath();
		path.setContent(this.path);

		Translate translate = new Translate();
		translate.setX(thumbSize / 2);
		translate.setY(thumbSize / 2);

		Scale scale = new Scale();
		scale.setX(scaleFactor);
		scale.setY(scaleFactor);
		scale.setPivotX(0);
		scale.setPivotY(0);

		path.getTransforms().add(translate);
		path.getTransforms().add(scale);

		return new Shape[] { path };
	}

	@Override
	public void draw(Pane canvas) {
		try {
			double xGridSize = canvas.getWidth() / maximumX;
			double yGridSize = canvas.getHeight() / maximumY;

			double x = getDoubleAttribute("X");
			double y = getDoubleAttribute("Y");

			Vector finalCenterPoint = new Vector(x * xGridSize, y * yGridSize);
			double scaleFactor = scaleFactor(canvas.getWidth(), canvas.getHeight());

			SVGPath path = new SVGPath();
			path.setContent(this.path);
			path.setFill(getColorAttribute("Color"));
			path.setStroke(getColorAttribute("StrokeColor"));
			path.setStrokeWidth(strokeThickness(canvas.getWidth(), canvas.getHeight()));
			path.setStrokeLineJoin(getBooleanAttribute("StrokeCurved") ? StrokeLineJoin.ROUND : StrokeLineJoin.MITER);

			Rotate rotate = new Rotate();
			rotate.setAngle(getDoubleAttribute("Rotation") / maximumX * 360);
			rotate.setPivotX(0);
			rotate.setPivotY(0);

			Translate translate = new Translate();
			translate.setX(finalCenterPoint.getX());
			translate.setY(finalCenterPoint.getY());

			Scale scale = new Scale();
			scale.setX(scaleFactor);
			scale.setY(scaleFactor);
			scale.setPivotX(0);
			scale.setPivotY(0);

			path.getTransforms().add(translate);
			path.getTransforms().add(scale);
			path.getTransforms().add(rotate);

			canvas.getChildren().add(path);
		} catch (Exception e) {
			// Show alert
		}
	}

	@Override
	public String exportSvg(int width, int height) {
		double xGridSize = (double) width / maximumX;
		double yGridSize = (double) height / maximumY;

		double x = getDoubleAttribute("X");
		double y = getDoubleAttribute("Y");

		Vector finalCenterPoint = new Vector(x * xGridSize, y * yGridSize);
		double rotate = (getDoubleAttribute("Rotation") / maximumX) * 360;

		double strokeThickness = strokeThickness(width, height);
		boolean strokeCurved = getBooleanAttribute("StrokeCurved");

		return String.format(Locale.US,
				"<g transform=\"translate(%.3f,%.3f) rotate(%.3f) scale(%.3f)\"><path d=\"%s\" %s %s /></g>",
				finalCenterPoint.getX(), finalCenterPoint.getY(), rotate, scaleFactor(width, height), path,
				ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")),
				strokeThickness > 0
						? String.format(Locale.US, "stroke=\"#%s\" stroke-width=\"$.3f\" stroke-linejoin=\"%s\"",
								ColorExtensions.toHexString(getColorAttribute("StrokeColor"), false), strokeThickness,
								strokeCurved ? "round" : "miter")
						: "");
	}

	public OverlayPath copy() {
		return new OverlayPath(name, path, pathSize, maximumX, maximumY);
	}

	private double strokeThickness(double canvasWidth, double canvasHeight) {
		return canvasWidth * getDoubleAttribute("Stroke") / 32 / scaleFactor(canvasWidth, canvasHeight) / maximumX;
	}

	private double scaleFactor(double canvasWidth, double canvasHeight) {
		double idealPixelSize = getDoubleAttribute("Size") / maximumX * Math.max(canvasWidth, canvasHeight);
		return idealPixelSize / Math.max(pathSize.getX(), pathSize.getY());
	}
}
