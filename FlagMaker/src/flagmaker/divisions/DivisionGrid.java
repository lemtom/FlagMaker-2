package flagmaker.divisions;

import flagmaker.extensions.ColorExtensions;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DivisionGrid extends Division
{
	public DivisionGrid(Color color1, Color color2, int horizontalCount, int verticalCount)
	{
		super(new Color[] { color1, color2 }, new int[] { horizontalCount, verticalCount });
	}

	@Override
	public String getName()
	{
		return "grid";
	}

	@Override
	public void draw(Pane canvas)
	{
		double height = canvas.getHeight();
		double width = canvas.getWidth();
		
		canvas.getChildren().add(new Rectangle(width, height, colors[0]));
		
		double gHeight = height / values[1];
		double gWidth = width / values[0];
		
		for (int x = 0; x < values[0]; x++)
		{
			for (int y = 0; y < values[1]; y++)
			{
				if ((x + y) % 2 == 0) continue;
				
				Rectangle r = new Rectangle(gWidth, gHeight, colors[1]);
				r.setX(x * gWidth);
				r.setY(y * gHeight);
				canvas.getChildren().add(r);
			}
		}
	}

	@Override
	public void setColors(Color[] newColors)
	{
		colors[0] = newColors[0];
		colors[1] = newColors[1];
	}

	@Override
	public void setValues(int[] newValues)
	{
		values[0] = newValues[0];
		values[1] = newValues[1];
	}

	@Override
	public String exportSvg(int width, int height)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<rect width=\"%d\" height=\"%d\" x=\"0\" y=\"0\" %s />",
				width,
				height,
				ColorExtensions.toSvgFillWithOpacity(colors[0])));
		
		double h = height / (double)values[1];
		double w = width / (double)values[0];
			
		for	(int x = 0; x < values[0]; x++)
		{
			for (int y = 0; y < values[1]; y++)
			{
				sb.append(String.format("<rect width=\"%.3f\" height=\"%.3f\" x=\"%.3f\" y=\"%.3f\" %s />",
					w, h, x * w, y * h,
					ColorExtensions.toSvgFillWithOpacity((x + y) % 2 == 0 ? colors[0] : colors[1])));
			}
		}
		
		return sb.toString();
	}
}
