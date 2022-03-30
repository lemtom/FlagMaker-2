package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class OverlayPall extends Overlay {
	public OverlayPall(int maximumX, int maximumY) {
		super("pall", new Attribute[] { new ColorAttribute("Color", Color.BLACK),
				new DoubleAttribute("X", 1, maximumX, true), new DoubleAttribute("Width", 1, maximumX, true) },
				maximumX, maximumY);
	}

	public OverlayPall(Color color, double x, double width, int maximumX, int maximumY) {
		super("pall", new Attribute[] { new ColorAttribute("Color", color), new DoubleAttribute("X", x, maximumX, true),
				new DoubleAttribute("Width", width, maximumX, true) }, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		Line l1 = new Line(0, 5, 15, 15);
		Line l2 = new Line(0, 25, 15, 15);
		Line l3 = new Line(15, 15, 30, 15);
		l1.setStrokeWidth(5);
		l2.setStrokeWidth(5);
		l3.setStrokeWidth(5);

		return new Shape[] { l1, l2, l3 };
	}

	@Override
	public void draw(Pane canvas) {
		double theWidth = getDoubleAttribute("Width") / maximumX * canvas.getWidth() / 2;
		double x = canvas.getWidth() * (getDoubleAttribute("X") / maximumX);

		SVGPath p = new SVGPath();
		p.setContent(String.format(Locale.US,
				"M 0,0 %1$.3f,0 %2$.3f,%3$.3f %4$.3f,%3$.3f %4$.3f,%6$.3f %2$.3f,%6$.3f %1$.3f,%5$.3f 0,%5$.3f 0,%7$.3f %9$.3f,%8$.3f 0,%1$.3f",
				theWidth / 2, x + theWidth / 3, canvas.getHeight() / 2 - theWidth / 3, canvas.getWidth(),
				canvas.getHeight(), canvas.getHeight() / 2 + theWidth / 3, canvas.getHeight() - theWidth / 2,
				canvas.getHeight() / 2, x - theWidth / 3));
		p.setFill(getColorAttribute("Color"));

		canvas.getChildren().add(p);
	}

	@Override
	public String exportSvg(int width, int height) {
		double theWidth = getDoubleAttribute("Width") / maximumX * width / 2;
		double x = width * (getDoubleAttribute("X") / maximumX);

		return String.format(Locale.US,
				"<path d=\"M 0,0 %1$.3f,0 %2$.3f,%3$.3f %4$d,%3$.3f %4$d,%6$.3f %2$.3f,%6$.3f %1$.3f,%5$d 0,%5$d 0,%7$.3f %9$.3f,%8$.3f 0,%1$.3f\" %10$s />",
				theWidth / 2, x + theWidth / 3, height / 2 - theWidth / 3, width, height, height / 2 + theWidth / 3,
				height - theWidth / 2, height / 2.0, x - theWidth / 3,
				ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
