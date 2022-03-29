package flagmaker.overlays.overlaytypes.RepeaterTypes;

import java.util.Locale;

import flagmaker.overlays.attributes.Attribute;
import flagmaker.overlays.attributes.DoubleAttribute;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Transform;

public class OverlayTransformer extends OverlayRepeater
{
    public OverlayTransformer(int maximumX, int maximumY)
    {
	super("transformer", new Attribute[]
	{
	    new DoubleAttribute("X", 1, maximumX, true),
	    new DoubleAttribute("Y", 1, maximumY, false),
	    new DoubleAttribute("SkewX", maximumX / 2.0, maximumX, true),
	    new DoubleAttribute("SkewY", maximumY / 2.0, maximumY, false),
	    new DoubleAttribute("Width", 1, maximumX, true),
	    new DoubleAttribute("Height", 1, maximumY, false),
	    new DoubleAttribute("Rotation", 0, maximumX, true),
	}, maximumX, maximumY);
    }

    @Override
    protected Shape[] thumbnail()
    {
	return new Shape[]
	{
	    new Polygon(10, 10, 25, 10, 20, 20, 5, 20)
	};
    }

    @Override
    public void draw(Pane canvas)
    {
		if (overlay == null) return;
		if (!overlay.isEnabled) return;
		
		AnchorPane a = new AnchorPane();
		a.setBackground(Background.EMPTY);
		Scene s = new Scene(a, canvas.getWidth(), canvas.getHeight());
		Pane p = new Pane();
		p.setBackground(Background.EMPTY);
		s.setRoot(p);
		
		p.getTransforms().add(getTransformation((int)canvas.getWidth(), (int)canvas.getHeight()));

		overlay.draw(p);
		canvas.getChildren().add(p);
    }

    @Override
    public String exportSvg(int width, int height)
    {
		if (overlay == null) return "";
		if (!overlay.isEnabled) return "";

		Transform matrix = getTransformation(width, height);

		return String.format(Locale.US, "<g transform=\"matrix(%.3f,%.3f,%.3f,%.3f,%.3f,%.3f)\">%s</g>",
			matrix.getMxx(),
			matrix.getMxy(),
			matrix.getMyx(),
			matrix.getMyy(),
			matrix.getTx(),
			matrix.getTy(),
			overlay.exportSvg(width, height));
    }
	
	private Transform getTransformation(int width, int height)
	{
		double centerX = width * getDoubleAttribute("X") / maximumX;
		double centerY = height * getDoubleAttribute("Y") / maximumY;

		double skewX = 90 * (getDoubleAttribute("SkewX") - maximumX / 2.0) / maximumX;
		double skewY = 90 * (getDoubleAttribute("SkewY") - maximumY / 2.0) / maximumY;

		double scaleX = getDoubleAttribute("Width");
		double scaleY = getDoubleAttribute("Height");

		double rotation = (getDoubleAttribute("Rotation") / maximumX) * 360;
		
		Shear skewTransform = new Shear(skewX, skewY, centerX, centerY);
		Rotate rotateTransform = new Rotate(rotation, centerX, centerY);
		Scale scaleTransform = new Scale(scaleX, scaleY, centerX, centerY);

		return rotateTransform.createConcatenation(scaleTransform).createConcatenation(skewTransform);
	}
}
