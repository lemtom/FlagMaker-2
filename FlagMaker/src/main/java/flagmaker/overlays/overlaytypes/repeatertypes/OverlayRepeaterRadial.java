package flagmaker.overlays.overlaytypes.repeatertypes;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

import java.util.Locale;
import java.util.UUID;

import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.BooleanAttribute;
import flagmaker.overlays.attributes.DoubleAttribute;
import flagmaker.overlays.attributes.IntegerAttribute;

public class OverlayRepeaterRadial extends OverlayRepeater {
	public OverlayRepeaterRadial(int maximumX, int maximumY) {
		super("repeater radial",
				new Attribute[] { new DoubleAttribute("X", 1, maximumX, true),
						new DoubleAttribute("Y", 1, maximumY, false), new DoubleAttribute("Radius", 1, maximumX, true),
						new IntegerAttribute("Count", 1, maximumX, true), new BooleanAttribute("Rotate", true) },
				maximumX, maximumY);
	}

	public OverlayRepeaterRadial(double x, double y, double radius, int count, boolean rotate, int maximumX,
			int maximumY) {
		super("repeater radial", new Attribute[] { new DoubleAttribute("X", x, maximumX, true),
				new DoubleAttribute("Y", y, maximumY, false), new DoubleAttribute("Radius", radius, maximumX, true),
				new IntegerAttribute("Count", count, maximumX, true), new BooleanAttribute("Rotate", rotate) },
				maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		final int count = 7;
		final int radius = 10;
		final double interval = 2 * Math.PI / count;
		Shape[] shapes = new Shape[count];

		for (int i = 0; i < count; i++) {
			double left = Math.cos(i * interval) * radius + 15;
			double top = Math.sin(i * interval) * radius + 15;
			shapes[i] = new Ellipse(left, top, 2.5, 2.5);
		}

		return shapes;
	}

	@Override
	public void draw(Pane canvas) {
		if (overlay == null || !overlay.isEnabled)
			return;

		double locX = canvas.getWidth() * (getDoubleAttribute("X") / maximumX);
		double locY = canvas.getHeight() * (getDoubleAttribute("Y") / maximumY);
		double radius = canvas.getWidth() * (getDoubleAttribute("Radius") / maximumX);
		double interval = 2 * Math.PI / getIntegerAttribute("Count");
		boolean rotate = getBooleanAttribute("Rotate");

		for (int i = 0; i < getIntegerAttribute("Count"); i++) {
			AnchorPane a = new AnchorPane();
			a.setBackground(Background.EMPTY);
			Scene s = new Scene(a, radius, radius);
			Pane p = new Pane();
			p.setBackground(Background.EMPTY);
			s.setRoot(p);

			double x = locX + Math.cos(i * interval - Math.PI / 2) * radius;
			double y = locY + Math.sin(i * interval - Math.PI / 2) * radius;

			if (rotate) {
				p.getTransforms().add(new Rotate(i * 360 / getIntegerAttribute("Count"), 0, 0));
			}

			overlay.draw(p);
			canvas.getChildren().add(p);

			p.setLayoutX(x);
			p.setLayoutY(y);
		}
	}

	@Override
	public String exportSvg(int width, int height) {
		if (overlay == null)
			return "";
		if (!overlay.isEnabled)
			return "";

		double locX = width * (getDoubleAttribute("X") / maximumX);
		double locY = height * (getDoubleAttribute("Y") / maximumY);
		double radius = width * (getDoubleAttribute("Radius") / maximumX);
		double interval = 2 * Math.PI / getIntegerAttribute("Count");
		boolean rotate = getBooleanAttribute("Rotate");

		UUID id = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();

		sb.append(String.format(Locale.US, "<defs><g id=\"%s\">%s</g></defs>", id.toString(),
				overlay.exportSvg((int) radius, (int) radius)));

		for (int i = 0; i < getIntegerAttribute("Count"); i++) {
			sb.append(String.format(Locale.US, "<g transform=\"translate(%.3f,%.3f)%s\">\n",
					locX + Math.cos(i * interval - Math.PI / 2) * radius,
					locY + Math.sin(i * interval - Math.PI / 2) * radius,
					rotate ? String.format(Locale.US, "rotate(%.3f)", i * 360.0 / getIntegerAttribute("Count")) : ""));
			sb.append(String.format(Locale.US, "<use xlink:href=\"#%s\" />\n", id.toString()));
			sb.append("</g>\n");
		}

		return sb.toString();
	}
}
