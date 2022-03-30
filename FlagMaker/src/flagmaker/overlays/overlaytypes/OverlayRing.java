package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class OverlayRing extends Overlay {
	public OverlayRing(int maximumX, int maximumY) {
		super("ring",
				new Attribute[] { new ColorAttribute("Color", Color.BLACK), new DoubleAttribute("X", 1, maximumX, true),
						new DoubleAttribute("Y", 1, maximumY, false), new DoubleAttribute("Width", 1, maximumX, true),
						new DoubleAttribute("Height", 1, maximumY, false),
						new DoubleAttribute("Size", 1, maximumX, true) },
				maximumX, maximumY);
	}

	public OverlayRing(Color color, double x, double y, double width, double height, double size, int maximumX,
			int maximumY) {
		super("ring", new Attribute[] { new ColorAttribute("Color", color), new DoubleAttribute("X", x, maximumX, true),
				new DoubleAttribute("Y", y, maximumX, false), new DoubleAttribute("Width", width, maximumX, true),
				new DoubleAttribute("Height", height, maximumY, false),
				new DoubleAttribute("Size", size, maximumX, true) }, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		Ellipse e1 = new Ellipse(15, 15, 15, 15);
		Ellipse e2 = new Ellipse(15, 15, 7, 7);
		return new Shape[] { Shape.subtract(e1, e2) };
	}

	@Override
	public void draw(Pane canvas) {
		double outerDiamX = canvas.getWidth() * (getDoubleAttribute("Width") / maximumX);
		double outerDiamY = getDoubleAttribute("Height") == 0 ? outerDiamX
				: canvas.getHeight() * (getDoubleAttribute("Height") / maximumY);

		double proportion = getDoubleAttribute("Size") / maximumX;
		double innerDiamX = outerDiamX * proportion;
		double innerDiamY = outerDiamY * proportion;

		double locX = (canvas.getWidth() * (getDoubleAttribute("X") / maximumX));
		double locY = (canvas.getHeight() * (getDoubleAttribute("Y") / maximumY));

		Ellipse outer = new Ellipse(locX, locY, outerDiamX / 2, outerDiamY / 2);
		Ellipse inner = new Ellipse(locX, locY, innerDiamX / 2, innerDiamY / 2);
		Shape ring = Shape.subtract(outer, inner);
		ring.setFill(getColorAttribute("Color"));
		canvas.getChildren().add(ring);
	}

	@Override
	public String exportSvg(int width, int height) {
		double x = width * getDoubleAttribute("X") / maximumX;
		double y = height * getDoubleAttribute("Y") / maximumY;

		double outerRadX = width * getDoubleAttribute("Width") / maximumX / 2;
		double outerRadY = getDoubleAttribute("Height") == 0 ? outerRadX
				: height * getDoubleAttribute("Height") / maximumY / 2;

		double proportion = getDoubleAttribute("Size") / maximumX;
		double innerRadX = outerRadX * proportion;
		double innerRadY = outerRadY * proportion;

		return String.format(Locale.US,
				"<path d=\""
						+ "M %1$.3f,%2$.3f m -%3$.3f,0 a %3$.3f,%4$.3f 0 1,0 %5$.3f,0 a %3$.3f,%4$.3f 0 1,0 -%5$.3f,0 z"
						+ "M %1$.3f,%2$.3f m %6$.3f,0 a %6$.3f,%7$.3f 0 1,1 -%8$.3f,0 a %6$.3f,%7$.3f 0 1,1 %8$.3f,0 z"
						+ "\" %8$s />",
				x, y, outerRadX, outerRadY, 2 * outerRadX, innerRadX, innerRadY, 2 * innerRadX,
				ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
