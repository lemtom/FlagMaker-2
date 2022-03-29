package flagmaker.Overlays.OverlayTypes.SpecialTypes;

import flagmaker.Data.Flag;
import flagmaker.Overlays.OverlayTypes.RepeaterTypes.OverlayRepeater;
import flagmaker.Files.FileHandler;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class OverlayFlag extends OverlaySpecial
{
	public Flag flag;
	public File path;

	public OverlayFlag(int maximumX, int maximumY)
	{
		super("flag", maximumX, maximumY);
	}
	
	public OverlayFlag(Flag flag, File path, int maximumX, int maximumY)
	{
		super("flag", maximumX, maximumY);
		this.flag = flag;
		this.path = path;
	}
	
	public OverlayFlag(Flag flag, File path, double x, double y, double width, double height, int maximumX, int maximumY)
	{
		super("flag", x, y, width, height, maximumX, maximumY);
		this.flag = flag;
		this.path = path;
	}

	@Override
	protected Shape[] thumbnail()
	{
		Rectangle base = new Rectangle(30, 20, new Color(0, 0, 0, 0));
		base.setLayoutY(5);
		base.setStrokeWidth(3);
		
		Line l1 = new Line(10, 7, 10, 23);
		l1.setStrokeWidth(5);
		
		Line l2 = new Line(2, 15, 28, 15);
		l2.setStrokeWidth(5);
		
		return new Shape[] { base, l1, l2 };
	}

	@Override
	public void draw(Pane canvas)
	{
		if (flag == null && path == null)
		{
			return;
		}
		else if (flag == null && path != null)
		{
			try
			{
				flag = FileHandler.loadFlagFromFile(path);
			}
			catch (Exception ex)
			{
				return;
			}
		}
		
		double canvasWidth = canvas.getWidth() * getDoubleAttribute("Width") / maximumX;
		double canvasHeight = canvas.getHeight() * getDoubleAttribute("Height") / maximumY;
		
		AnchorPane a = new AnchorPane();
		Scene s = new Scene(a, canvasWidth, canvasHeight);
		Rectangle clip = new Rectangle(canvasWidth, canvasHeight);
		Pane p = new Pane();
		p.setBackground(Background.EMPTY);
		p.setClip(clip);
		s.setRoot(p);
		flag.draw(p);
		
		p.setLayoutX(canvas.getWidth() * getDoubleAttribute("X") / maximumX);
		p.setLayoutY(canvas.getHeight() * getDoubleAttribute("Y") / maximumX);
		canvas.getChildren().add(p);
	}

	@Override
	public String exportSvg(int width, int height)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("<g transform=\"translate(%.3f,%.3f) scale(%.3f %.3f)\">\n",
			width * (getDoubleAttribute("X") / maximumX),
			height * (getDoubleAttribute("Y") / maximumY),
			getDoubleAttribute("Width") / maximumX,
			getDoubleAttribute("Height") / maximumY));

		sb.append(flag.division.exportSvg(width, height));

		for (int i = 0; i < flag.overlays.length; i++)
		{
			if (i == 0 || !(flag.overlays[i - 1] instanceof OverlayRepeater))
			{
				sb.append(flag.overlays[i].exportSvg(width, height));
			}
		}

		sb.append("</g>\n");

		return sb.toString();
	}
}
