package flagmaker.divisions;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class Division {
	public Color[] colors;
	public int[] values;

	protected Division(Color[] colors, int[] values) {
		this.colors = colors;
		this.values = values;
	}

	public abstract String getName();

	public abstract void draw(Pane canvas);

	public abstract void setColors(Color[] colors);

	public abstract void setValues(int[] values);

	public abstract String exportSvg(int width, int height);
}
