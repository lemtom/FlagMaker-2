package flagmaker.overlays.overlaytypes;

import flagmaker.extensions.ColorExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.*;

import java.util.ArrayList;
import java.util.Locale;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class OverlayCheckerboard extends Overlay {
	public OverlayCheckerboard(int maximumX, int maximumY) {
		super("checkerboard", new Attribute[] { new ColorAttribute("Color", Color.BLACK),
				new DoubleAttribute("X", 1, maximumX, true), new DoubleAttribute("Y", 1, maximumY, false),
				new DoubleAttribute("Width", 1, maximumX, true), new DoubleAttribute("Height", 1, maximumY, false),
				new IntegerAttribute("CountX", 4, maximumX, true), new IntegerAttribute("CountY", 4, maximumX, true) },
				maximumX, maximumY);
	}

	public OverlayCheckerboard(Color color, double x, double y, double width, double height, int countX, int countY,
			int maximumX, int maximumY) {
		super("checkerboard",
				new Attribute[] { new ColorAttribute("Color", color), new DoubleAttribute("X", x, maximumX, true),
						new DoubleAttribute("Y", y, maximumY, false),
						new DoubleAttribute("Width", width, maximumX, true),
						new DoubleAttribute("Height", height, maximumY, false),
						new IntegerAttribute("CountX", countX, maximumX, true),
						new IntegerAttribute("CountY", countY, maximumX, true) },
				maximumX, maximumY);
	}

	@Override
	protected Shape[] thumbnail() {
		ArrayList<Shape> shapes = new ArrayList<>();

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				if ((x + y) % 2 == 0) {
					shapes.add(new Rectangle(2.5 + x * 5, 2.5 + y * 5, 5, 5));
				}
			}
		}

		Shape[] returnValue = new Shape[] {};
		return shapes.toArray(returnValue);
	}

	@Override
	public void draw(Pane canvas) {
		double centerX = canvas.getWidth() * (getDoubleAttribute("X") / maximumX);
		double centerY = canvas.getHeight() * (getDoubleAttribute("Y") / maximumY);
		double width = canvas.getWidth() * (getDoubleAttribute("Width") / maximumX);
		double height = canvas.getHeight() * (getDoubleAttribute("Height") / maximumY);
		if (height == 0)
			height = width;
		int countX = getIntegerAttribute("CountX");
		int countY = getIntegerAttribute("CountY");

		double left = centerX - width / 2;
		double top = centerY - height / 2;
		double blockWidth = width / countX;
		double blockHeight = height / countY;

		for (int x = 0; x < countX; x++) {
			for (int y = 0; y < countY; y++) {
				if ((x + y) % 2 != 0)
					continue;

				Rectangle rect = new Rectangle(left + x * blockWidth, top + y * blockHeight, blockWidth, blockHeight);
				rect.setFill(getColorAttribute("Color"));
				canvas.getChildren().add(rect);
			}
		}
	}

	@Override
	public String exportSvg(int width, int height) {
		double centerX = width * (getDoubleAttribute("X") / maximumX);
		double centerY = height * (getDoubleAttribute("Y") / maximumY);
		double w = width * (getDoubleAttribute("Width") / maximumX);
		double h = height * (getDoubleAttribute("Height") / maximumY);
		if (h == 0)
			h = w;
		int countX = getIntegerAttribute("CountX");
		int countY = getIntegerAttribute("CountY");

		double left = centerX - w / 2;
		double top = centerY - h / 2;
		double blockWidth = w / countX;
		double blockHeight = h / countY;

		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < countX; x++) {
			for (int y = 0; y < countY; y++) {
				if ((x + y) % 2 != 0)
					continue;

				sb.append(String.format(Locale.US, "<rect width=\"%.3f\" height=\"%.3f\" %s x=\"%.3f\" y=\"%.3f\"/>",
						blockWidth, blockHeight, ColorExtensions.toSvgFillWithOpacity(getColorAttribute("Color")),
						left + x * blockWidth, top + y * blockHeight));
			}
		}

		return sb.toString();
	}
}
