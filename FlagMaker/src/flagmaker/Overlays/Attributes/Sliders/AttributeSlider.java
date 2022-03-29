package flagmaker.Overlays.Attributes.Sliders;

import flagmaker.Overlays.OverlayControl;
import javafx.scene.layout.HBox;

public abstract class AttributeSlider extends HBox
{
	public String name;
	
	private final OverlayControl parent;
	protected boolean triggeredByUser;
	
	protected AttributeSlider(OverlayControl parent, String name)
	{
		this.parent = parent;
		this.name = name;
		triggeredByUser = true;
	}
	
	protected void valueChanged()
	{
		parent.overlaySliderChanged(triggeredByUser);
	}
	
	public abstract Object getValue();
	public abstract void setValue(Object value);
}
