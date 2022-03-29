package flagmaker.Divisions;

import flagmaker.Extensions.ColorExtensions;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DivisionPales extends Division 
{
	public DivisionPales(Color color1, Color color2, Color color3, int v1, int v2, int v3)
	{
		super(new Color[] { color1, color2, color3 }, new int[] { v1, v2, v3 });
	}

	@Override
	public String getName()
	{
		return "pales";
	}
	
	@Override
	public void draw(Pane canvas)
	{
		double height = canvas.getHeight();
		double width = canvas.getWidth();
		
		double sizeSum = values[0] + values[1] + values[2];
		
		double r1Size = width * values[0] / sizeSum;
		double r2Size = width * (values[0] + values[1]) / sizeSum;
				
		Rectangle left = new Rectangle(r1Size, height, colors[0]);
		Rectangle center = new Rectangle(r2Size, height, colors[1]);
		Rectangle right = new Rectangle(width, height, colors[2]);
		canvas.getChildren().addAll(right, center, left);
	}
	
	@Override
	public void setColors(Color[] newColors)
	{
		colors[0] = newColors[0];
		colors[1] = newColors[1];
		colors[2] = newColors[2];
	}
	
	@Override
	public void setValues(int[] newValues)
	{
		values[0] = newValues[0];
		values[1] = newValues[1];
		values[2] = newValues[2];
	}
	
	@Override
	public String exportSvg(int width, int height)
	{
		StringBuilder sb = new StringBuilder();

		double sizeSum = values[0] + values[1] + values[2];
		double r1Size = width * values[0] / sizeSum;
		double r2Size = width * (values[0] + values[1]) / sizeSum;
			
		// Bottom
		sb.append(String.format("<rect width=\"%d\" height=\"%d\" x=\"0\" y=\"0\" %s />",
				width,
				height,
				ColorExtensions.toSvgFillWithOpacity(colors[2])));
		
		// Middle
		sb.append(String.format("<rect width=\"%.3f\" height=\"%d\" x=\"0\" y=\"0\" %s />",
				r2Size,
				height,
				ColorExtensions.toSvgFillWithOpacity(colors[1])));
		
		// Top
		sb.append(String.format("<rect width=\"%.3f\" height=\"%d\" x=\"0\" y=\"0\" %s />",
				r1Size,
				height,
				ColorExtensions.toSvgFillWithOpacity(colors[0])));
		
		return sb.toString();
	}
}
