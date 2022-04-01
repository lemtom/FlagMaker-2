package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.ColorAttribute;
import flagmaker.overlays.attributes.DoubleAttribute;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Locale;

public class OverlayHalfSaltire extends Overlay {
	public OverlayHalfSaltire(int maximumX, int maximumY) {
		super("half saltire", new Attribute[] { new ColorAttribute("Color", Color.BLACK),
				new DoubleAttribute("Thickness", 1, maximumX, true) }, maximumX, maximumY);
	}

	public OverlayHalfSaltire(Color color, double thickness, int maximumX, int maximumY) {
		super("half saltire", new Attribute[] { new ColorAttribute("Color", color),
				new DoubleAttribute("Thickness", thickness, maximumX, true) }, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		Line l1 = new Line(0, 5, 30, 25);
		Line l2 = new Line(30, 5, 0, 25);
		l1.setStrokeWidth(3);
		l2.setStrokeWidth(3);
		return new Shape[] { l1, l2 };
	}

	@Override
	public void draw(Pane canvas) {
		double widthX = canvas.getWidth() * (getDoubleAttribute("Thickness") / maximumX) / 4;
		double widthY = canvas.getHeight() * (getDoubleAttribute("Thickness") / maximumX) / 4;

		double centerX = canvas.getWidth() / 2;
		double centerY = canvas.getHeight() / 2;

		Path pathTopLeft = new Path(new MoveTo(0, 0), new LineTo(centerX, centerY),
				new LineTo(centerX - widthX, centerY), new LineTo(0, widthY), new LineTo(0, 0));

		Path pathTopRight = new Path(new MoveTo(centerX, centerY), new LineTo(centerX, centerY - widthY),
				new LineTo(canvas.getWidth() - widthX, 0), new LineTo(canvas.getWidth(), 0),
				new LineTo(centerX, centerY));

		Path pathBottomLeft = new Path(new MoveTo(centerX, centerY), new LineTo(centerX, centerY + widthY),
				new LineTo(widthX, canvas.getHeight()), new LineTo(0, canvas.getHeight()),
				new LineTo(centerX, centerY));

		Path pathBottomRight = new Path(new MoveTo(centerX, centerY), new LineTo(centerX + widthX, centerY),
				new LineTo(canvas.getWidth(), canvas.getHeight() - widthY),
				new LineTo(canvas.getWidth(), canvas.getHeight()), new LineTo(centerX, centerY));

		pathTopLeft.setFill(getColorAttribute("Color"));
		pathTopRight.setFill(getColorAttribute("Color"));
		pathBottomLeft.setFill(getColorAttribute("Color"));
		pathBottomRight.setFill(getColorAttribute("Color"));
		pathTopLeft.setStrokeWidth(0);
		pathTopRight.setStrokeWidth(0);
		pathBottomLeft.setStrokeWidth(0);
		pathBottomRight.setStrokeWidth(0);

		canvas.getChildren().addAll(pathBottomLeft, pathTopLeft, pathTopRight, pathBottomRight);
	}

	@Override
	public String exportSvg(int width, int height) {
		double wX = width * (getDoubleAttribute("Thickness") / maximumX) / 4;
		double wY = height * (getDoubleAttribute("Thickness") / maximumX) / 4;

		double centerX = width / 2;
		double centerY = height / 2;

		String c = ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color"));

        String sb = String.format(Locale.US, "<polygon points=\"0,0 %1$.3f,%2$.3f %3$.3f,%2$.3f 0,%4$.3f\" %5$s />",
                centerX, centerY, centerX - wX, wY, c) +
                String.format(Locale.US, "<polygon points=\"%1$.3f,%2$.3f %1$.3f,%3$.3f %4$.3f,0 %5$d,0\" %6$s />",
                        centerX, centerY, centerY - wY, width - wX, width, c) +
                String.format(Locale.US, "<polygon points=\"%1$.3f,%2$.3f %1$.3f,%3$.3f %4$.3f,%5$d 0,%5$d\" %6$s />",
                        centerX, centerY, centerY + wY, wX, height, c) +
                String.format(Locale.US,
                        "<polygon points=\"%1$.3f,%2$.3f %3$.3f,%2$.3f %4$d,%5$.3f %4$d,%6$d\" %7$s />", centerX, centerY,
                        centerX + wX, width, height - wY, height, c);
        return sb;
	}
}
