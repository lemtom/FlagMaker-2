package flagmaker.divisions;

import flagmaker.extensions.ColorExtensions;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DivisionFesses extends Division {
	public DivisionFesses(Color color1, Color color2, Color color3, int v1, int v2, int v3) {
		super(new Color[] { color1, color2, color3 }, new int[] { v1, v2, v3 });
	}

	@Override
	public String getName() {
		return "fesses";
	}

	@Override
	public void draw(Pane canvas) {
		double height = canvas.getHeight();
		double width = canvas.getWidth();

		double sizeSum = values[0] + values[1] + values[2];

		double r1Size = height * values[0] / sizeSum;
		double r2Size = height * (values[0] + values[1]) / sizeSum;

		Rectangle top = new Rectangle(width, r1Size, colors[0]);
		Rectangle middle = new Rectangle(width, r2Size, colors[1]);
		Rectangle bottom = new Rectangle(width, height, colors[2]);
		canvas.getChildren().addAll(bottom, middle, top);
	}

	@Override
	public void setColors(Color[] newColors) {
		colors[0] = newColors[0];
		colors[1] = newColors[1];
		colors[2] = newColors[2];
	}

	@Override
	public void setValues(int[] newValues) {
		values[0] = newValues[0];
		values[1] = newValues[1];
		values[2] = newValues[2];
	}

	@Override
	public String exportSvg(int width, int height) {
		StringBuilder sb = new StringBuilder();

		double sizeSum = values[0] + values[1] + values[2];
		double r1Size = height * values[0] / sizeSum;
		double r2Size = height * (values[0] + values[1]) / sizeSum;

		// Bottom
		sb.append(String.format("<rect width=\"%d\" height=\"%d\" x=\"0\" y=\"0\" %s />", width, height,
				ColorExtensions.toSvgFillWithOpacity(colors[2])));

		// Middle
		sb.append(String.format("<rect width=\"%d\" height=\"%.3f\" x=\"0\" y=\"0\" %s />", width, r2Size,
				ColorExtensions.toSvgFillWithOpacity(colors[1])));

		// Top
		sb.append(String.format("<rect width=\"%d\" height=\"%.3f\" x=\"0\" y=\"0\" %s />", width, r1Size,
				ColorExtensions.toSvgFillWithOpacity(colors[0])));

		return sb.toString();
	}
}
