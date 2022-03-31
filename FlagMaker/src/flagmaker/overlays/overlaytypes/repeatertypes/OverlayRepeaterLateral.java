package flagmaker.overlays.overlaytypes.repeatertypes;

import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.DoubleAttribute;
import flagmaker.overlays.attributes.IntegerAttribute;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

import java.util.Locale;
import java.util.UUID;

public class OverlayRepeaterLateral extends OverlayRepeater {
	public OverlayRepeaterLateral(int maximumX, int maximumY) {
		super("repeater lateral", new Attribute[] { new DoubleAttribute("X", 1, maximumX, true),
				new DoubleAttribute("Y", 1, maximumY, false), new DoubleAttribute("Width", 1, maximumX, true),
				new DoubleAttribute("Height", 1, maximumY, false), new IntegerAttribute("CountX", 1, maximumX, true),
				new IntegerAttribute("CountY", 1, maximumY, false) }, maximumX, maximumY);
	}

	public OverlayRepeaterLateral(double x, double y, double width, double height, int countX, int countY, int maximumX,
			int maximumY) {
		super("repeater lateral", new Attribute[] { new DoubleAttribute("X", x, maximumX, true),
				new DoubleAttribute("Y", y, maximumY, false), new DoubleAttribute("Width", width, maximumX, true),
				new DoubleAttribute("Height", height, maximumY, false),
				new IntegerAttribute("CountX", countX, maximumX, true),
				new IntegerAttribute("CountY", countY, maximumY, false) }, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		return new Shape[] { new Ellipse(5, 15, 2.5, 2.5), new Ellipse(15, 15, 2.5, 2.5),
				new Ellipse(25, 15, 2.5, 2.5) };
	}

	@Override
	public void draw(Pane canvas) {
		if (overlay == null || !overlay.isEnabled)
			return;

		int countX = getIntegerAttribute("CountX");
		int countY = getIntegerAttribute("CountY");
		double width = canvas.getWidth() * (getDoubleAttribute("Width") / maximumX);
		double height = canvas.getHeight() * (getDoubleAttribute("Height") / maximumY);

		double locX = canvas.getWidth() * (getDoubleAttribute("X") / maximumX) - width / 2;
		double locY = canvas.getHeight() * (getDoubleAttribute("Y") / maximumY) - height / 2;

		double intervalX = width / (countX > 1 ? countX - 1 : countX);
		double intervalY = height / (countY > 1 ? countY - 1 : countY);

		Pane repeaterCanvas = new Pane();
		repeaterCanvas.setLayoutX(locX);
		repeaterCanvas.setLayoutY(locY);
		repeaterCanvas.setBackground(Background.EMPTY);

		for (int x = 0; x < countX; x++) {
			for (int y = 0; y < countY; y++) {
				AnchorPane a = new AnchorPane();
				a.setBackground(Background.EMPTY);
				Scene s = new Scene(a, width, height);
				Pane p = new Pane();
				p.setBackground(Background.EMPTY);
				s.setRoot(p);

				overlay.draw(p);
				repeaterCanvas.getChildren().add(p);

				p.setLayoutX(x * intervalX);
				p.setLayoutY(y * intervalY);
			}
		}

		canvas.getChildren().add(repeaterCanvas);
	}

	@Override
	public String exportSvg(int width, int height) {
		if (overlay == null)
			return "";
		if (!overlay.isEnabled)
			return "";

		int countX = getIntegerAttribute("CountX");
		int countY = getIntegerAttribute("CountY");
		double w = width * (getDoubleAttribute("Width") / maximumX);
		double h = height * (getDoubleAttribute("Height") / maximumY);

		double locX = width * (getDoubleAttribute("X") / maximumX) - w / 2;
		double locY = height * (getDoubleAttribute("Y") / maximumY) - h / 2;

		double intervalX = w / (countX > 1 ? countX - 1 : countX);
		double intervalY = h / (countY > 1 ? countY - 1 : countY);

		UUID id = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();

		sb.append(String.format(Locale.US, "<defs><g id=\"%s\">%s</g></defs>\n", id.toString(),
				overlay.exportSvg((int) w, (int) h)));

		for (int x = 0; x < countX; x++) {
			for (int y = 0; y < countY; y++) {
				sb.append(String.format(Locale.US, "<g transform=\"translate(%.3f,%.3f)\">\n", locX + x * intervalX,
						locY + y * intervalY));
				sb.append(String.format(Locale.US, "<use xlink:href=\"#%s\" />\n", id.toString()));
				sb.append("</g>\n");
			}
		}

		return sb.toString();
	}
}
