package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class OverlayLineHorizontal extends Overlay {
	public OverlayLineHorizontal(int maximumX, int maximumY) {
		super("line horizontal", new Attribute[] { new ColorAttribute("Color", Color.BLACK),
				new DoubleAttribute("Y", 1, maximumY, false), new DoubleAttribute("Thickness", 0.5, maximumY, false) },
				maximumX, maximumY);
	}

	public OverlayLineHorizontal(Color color, double y, double thickness, int maximumX, int maximumY) {
		super("line horizontal",
				new Attribute[] { new ColorAttribute("Color", color), new DoubleAttribute("Y", y, maximumY, false),
						new DoubleAttribute("Thickness", thickness, maximumY, false) },
				maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		Line l = new Line(0, 15, 30, 15);
		l.setStrokeWidth(5);
		return new Shape[] { l };
	}

	@Override
	public void draw(Pane canvas) {
		Line line = new Line(0, canvas.getHeight() * getDoubleAttribute("Y") / maximumY, canvas.getWidth(),
				canvas.getHeight() * getDoubleAttribute("Y") / maximumY);
		line.setStrokeWidth(canvas.getHeight() * (getDoubleAttribute("Thickness") / maximumY));
		line.setStroke(getColorAttribute("Color"));
		canvas.getChildren().add(line);
	}

	@Override
	public String exportSvg(int width, int height) {
		return String.format(Locale.US,
				"<line x1=\"0\" y1=\"%.3f\" x2=\"%d\" y2=\"%.3f\" stroke=\"#%s\" stroke-width=\"%.3f\" />",
				height * getDoubleAttribute("Y") / maximumY, width, height * getDoubleAttribute("Y") / maximumY,
				ColorExtensions.toHexString(getColorAttribute("Color"), false),
				height * (getDoubleAttribute("Thickness") / maximumY));
	}
}
