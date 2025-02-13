package flagmaker.divisions;

import flagmaker.extensions.ColorExtensions;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class DivisionX extends Division {
	public DivisionX(Color color1, Color color2) {
		super(new Color[] { color1, color2 }, new int[] {});
	}

	@Override
	public String getName() {
		return "bends both";
	}

	@Override
	public void draw(Pane canvas) {
		double height = canvas.getHeight();
		double width = canvas.getWidth();

		canvas.getChildren().add(new Rectangle(width, height, colors[0]));

		Path p = new Path(new MoveTo(0, height), new LineTo(width, 0), new LineTo(width, height),
				new LineTo(0, 0));
		p.fillProperty().set(colors[1]);
		p.strokeWidthProperty().set(0);
		canvas.getChildren().add(p);
	}

	@Override
	public void setColors(Color[] newColors) {
		colors[0] = newColors[0];
		colors[1] = newColors[1];
	}

	@Override
	public void setValues(int[] newValues) {
	}

	@Override
	public String exportSvg(int width, int height) {
        return String.format("<rect width=\"%d\" height=\"%d\" x=\"0\" y=\"0\" %s />", width, height,
                ColorExtensions.toSvgFillWithOpacity(colors[0])) +
                String.format("<polygon points=\"0,0 %1$d,%2$d %1$d,0 0,%2$d\" %3$s />", width, height,
                        ColorExtensions.toSvgFillWithOpacity(colors[1]));
	}

}
