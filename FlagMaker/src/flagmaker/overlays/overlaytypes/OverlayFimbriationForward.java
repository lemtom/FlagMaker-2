package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Shape;

public class OverlayFimbriationForward extends Overlay {
	public OverlayFimbriationForward(int maximumX, int maximumY) {
		super("fimbriation forward", new Attribute[] { new ColorAttribute("Color", Color.BLACK),
				new DoubleAttribute("Thickness", 1, maximumX, true) }, maximumX, maximumY);
	}

	public OverlayFimbriationForward(Color color, double thickness, int maximumX, int maximumY) {
		super("fimbriation forward", new Attribute[] { new ColorAttribute("Color", color),
				new DoubleAttribute("Thickness", thickness, maximumX, true) }, maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		Line l = new Line(30, 5, 0, 25);
		l.setStrokeWidth(5);
		return new Shape[] { l };
	}

	@Override
	public void draw(Pane canvas) {
		double widthX = canvas.getWidth() * (getDoubleAttribute("Thickness") / maximumX) / 2;
		double widthY = canvas.getHeight() * (getDoubleAttribute("Thickness") / maximumX) / 2;

		Path path = new Path(new MoveTo(canvas.getWidth() - widthX, 0), new LineTo(canvas.getWidth(), 0),
				new LineTo(canvas.getWidth(), widthY), new LineTo(widthX, canvas.getHeight()),
				new LineTo(0, canvas.getHeight()), new LineTo(0, canvas.getHeight() - widthY),
				new LineTo(canvas.getWidth() - widthX, 0));
		path.setFill(getColorAttribute("Color"));
		path.setStrokeWidth(0);
		canvas.getChildren().add(path);
	}

	@Override
	public String exportSvg(int width, int height) {
		double wX = width * (getDoubleAttribute("Thickness") / maximumX) / 2;
		double wY = height * (getDoubleAttribute("Thickness") / maximumX) / 2;

		return String.format(Locale.US,
				"<polygon points=\"%1$.3f,0 %2$d,0 %2$d,%6$.3f %3$.3f,%4$d 0,%4$d 0,%5$.3f %1$.3f,0\" %7$s />",
				width - wX, width, wX, height, height - wY, wY,
				ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")));
	}
}
