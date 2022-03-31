package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.ColorAttribute;
import flagmaker.overlays.attributes.DoubleAttribute;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Locale;

public class OverlayCross extends Overlay {
	public OverlayCross(int maximumX, int maximumY) {
		super("cross",
				new Attribute[] { new ColorAttribute("Color", Color.BLACK), new DoubleAttribute("X", 1, maximumX, true),
						new DoubleAttribute("Y", 1, maximumY, false),
						new DoubleAttribute("Thickness", 1, maximumX, true) },
				maximumX, maximumY);
	}

	public OverlayCross(Color color, double thickness, double x, double y, int maximumX, int maximumY) {
		super("cross",
				new Attribute[] { new ColorAttribute("Color", color), new DoubleAttribute("X", x, maximumX, true),
						new DoubleAttribute("Y", y, maximumY, false),
						new DoubleAttribute("Thickness", thickness, maximumX, true) },
				maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		Line l1 = new Line(10, 5, 10, 25);
		Line l2 = new Line(0, 15, 30, 15);

		l1.setStrokeWidth(5);
		l2.setStrokeWidth(5);

		return new Shape[] { l1, l2 };
	}

	@Override
	public void draw(Pane canvas) {
		double thick = canvas.getWidth() * getDoubleAttribute("Thickness") / maximumX;
		Rectangle vertical = new Rectangle(canvas.getWidth() * (getDoubleAttribute("X") / maximumX) - thick / 2, 0,
				thick, canvas.getHeight());
		Rectangle horizontal = new Rectangle(0, canvas.getHeight() * (getDoubleAttribute("Y") / maximumY) - thick / 2,
				canvas.getWidth(), thick);
		vertical.setFill(getColorAttribute("Color"));
		horizontal.setFill(getColorAttribute("Color"));
		canvas.getChildren().addAll(vertical, horizontal);
	}

	@Override
	public String exportSvg(int width, int height) {
		double thick = width * getDoubleAttribute("Thickness") / maximumX;
		double x = width * (getDoubleAttribute("X") / maximumX) - thick / 2;
		double y = height * (getDoubleAttribute("Y") / maximumY) - thick / 2;

		return String.format(Locale.US,
				"<rect width=\"%1$.3f\" height=\"%2$d\" x=\"%3$.3f\" y=\"0\" %6$s /><rect width=\"%4$d\" height=\"%1$.3f\" x=\"0\" y=\"%5$.3f\" %6$s />",
				thick, height, x, width, y, ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
