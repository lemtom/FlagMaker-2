package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class OverlayTriangle extends Overlay {
	public OverlayTriangle(int maximumX, int maximumY) {
		super("triangle",
				new Attribute[] { new ColorAttribute("Color", Color.BLACK),
						new DoubleAttribute("X1", 1, maximumX, true), new DoubleAttribute("Y1", 1, maximumY, false),
						new DoubleAttribute("X2", 1, maximumX, true), new DoubleAttribute("Y2", 2, maximumY, false),
						new DoubleAttribute("X3", 2, maximumX, true), new DoubleAttribute("Y3", 1, maximumY, false) },
				maximumX, maximumY);
	}

	public OverlayTriangle(Color color, double x1, double y1, double x2, double y2, double x3, double y3, int maximumX,
			int maximumY) {
		super("triangle",
				new Attribute[] { new ColorAttribute("Color", color), new DoubleAttribute("X1", x1, maximumX, true),
						new DoubleAttribute("Y1", y1, maximumY, false), new DoubleAttribute("X2", x2, maximumX, true),
						new DoubleAttribute("Y2", y2, maximumY, false), new DoubleAttribute("X3", x3, maximumX, true),
						new DoubleAttribute("Y3", y3, maximumY, false) },
				maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		SVGPath p = new SVGPath();
		p.setContent("M 0,0 15,15 0,30 0,0");
		return new Shape[] { p };
	}

	@Override
	public void draw(Pane canvas) {
		double x1 = canvas.getWidth() * (getDoubleAttribute("X1") / maximumX);
		double y1 = canvas.getHeight() * (getDoubleAttribute("Y1") / maximumY);
		double x2 = canvas.getWidth() * (getDoubleAttribute("X2") / maximumX);
		double y2 = canvas.getHeight() * (getDoubleAttribute("Y2") / maximumY);
		double x3 = canvas.getWidth() * (getDoubleAttribute("X3") / maximumX);
		double y3 = canvas.getHeight() * (getDoubleAttribute("Y3") / maximumY);

		SVGPath path = new SVGPath();
		path.setContent(String.format(Locale.US, "M %.3f,%.3f %.3f,%.3f %.3f,%.3f", x1, y1, x2, y2, x3, y3));
		path.setFill(getColorAttribute("Color"));
		canvas.getChildren().add(path);
	}

	@Override
	public String exportSvg(int width, int height) {
		double x1 = width * (getDoubleAttribute("X1") / maximumX);
		double y1 = height * (getDoubleAttribute("Y1") / maximumY);
		double x2 = width * (getDoubleAttribute("X2") / maximumX);
		double y2 = height * (getDoubleAttribute("Y2") / maximumY);
		double x3 = width * (getDoubleAttribute("X3") / maximumX);
		double y3 = height * (getDoubleAttribute("Y3") / maximumY);

		return String.format(Locale.US, "<polygon points=\"%.3f,%.3f %.3f,%.3f %.3f,%.3f\" %s />", x1, y1, x2, y2, x3,
				y3, ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
