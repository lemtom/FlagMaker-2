package flagmaker.overlays.overlaytypes.specialtypes;

import flagmaker.extensions.StringExtensions;
import flagmaker.overlays.Overlay;
import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.DoubleAttribute;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Locale;

public class OverlayImage extends Overlay
{
	private File path;
	private Image bitmap;

	public OverlayImage(int maximumX, int maximumY)
	{
		super("image", new Attribute[]
		{
			new DoubleAttribute("X", 1, maximumX, true),
			new DoubleAttribute("Y", 1, maximumY, false),
			new DoubleAttribute("Width", 1, maximumX, true),
			new DoubleAttribute("Height", 1, maximumY, false)
		}, maximumX, maximumY);
	}
	
	public OverlayImage(File path, int maximumX, int maximumY)
	{
		super("image", new Attribute[]
		{
			new DoubleAttribute("X", 1, maximumX, true),
			new DoubleAttribute("Y", 1, maximumY, false),
			new DoubleAttribute("Width", 1, maximumX, true),
			new DoubleAttribute("Height", 1, maximumY, false)
		}, maximumX, maximumY);
		setPath(path);
	}
	
	public OverlayImage(File path, double x, double y, double width, double height, int maximumX, int maximumY)
	{
		super("image", new Attribute[]
		{
			new DoubleAttribute("X", x, maximumX, true),
			new DoubleAttribute("Y", y, maximumY, false),
			new DoubleAttribute("Width", width, maximumX, true),
			new DoubleAttribute("Height", height, maximumY, false)
		}, maximumX, maximumY);
		setPath(path);
	}
	
	public File getPath()
	{
		return path;
	}
	
	public final void setPath(File value)
	{
		path = value;
		
		if (path.exists())
		{
			try
			{
				URI uri = path.toURI();
				URL url = uri.toURL();
				bitmap = new Image(url.toString());
			}
			catch (Exception e)
			{
			}
		}
	}

	@Override
	protected Shape[] thumbnail()
	{
		Rectangle border = new Rectangle(25, 30, new Color(1, 1, 0, 0));
		border.setStrokeWidth(3);
		border.setLayoutX(2.5);
		
		Rectangle square = new Rectangle(8, 8);
		square.setLayoutX(7);
		square.setLayoutY(5);
		
		Ellipse circle = new Ellipse(19, 15, 4, 4);
		Polygon triangle = new Polygon(8, 25, 12, 17, 16, 25);
		
		return new Shape[]
		{
			border,
			square,
			circle,
			triangle
		};
	}

	@Override
	public void draw(Pane canvas)
	{
		double width = canvas.getWidth() * getDoubleAttribute("Width") / maximumX;
		double height = canvas.getHeight() * getDoubleAttribute("Height") / maximumY;
		
		if (height == 0)
		{
			double ratio = bitmap.getHeight() / bitmap.getWidth();
			height = width * ratio;
		}

		Canvas c = new Canvas(width, height);
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(bitmap, 0, 0, width, height);

		c.setLayoutX((canvas.getWidth() * (getDoubleAttribute("X") / maximumX)) - width / 2);
		c.setLayoutY((canvas.getHeight() * (getDoubleAttribute("Y") / maximumY)) - height / 2);
		canvas.getChildren().add(c);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		try
		{
			double imageWidth = width * getDoubleAttribute("Width") / maximumX;
			double imageHeight = height * getDoubleAttribute("Height") / maximumY;
			if (imageHeight <= 0)
			{
				double ratio = bitmap.getHeight() / bitmap.getWidth();
				imageHeight = imageWidth * ratio;
			}
			
			byte[] bytes = Base64.getEncoder().encode(Files.readAllBytes(path.toPath()));
			String base64String = new String(bytes);
			
			return String.format(Locale.US, "<image x=\"%.3f\" y=\"%.3f\" width=\"%.3f\" height=\"%.3f\" preserveAspectRatio=\"none\" xlink:href=\"flagmaker.data:image/%s;base64,%s\" />",
					width * (getDoubleAttribute("X") / maximumX) - imageWidth / 2,
					height * (getDoubleAttribute("Y") / maximumY) - imageHeight / 2,
					imageWidth,
					imageHeight,
					StringExtensions.getFilenameExtension(path.getPath()),
					base64String);
		}
		catch (IOException ex)
		{
			return "";
		}
	}
}
