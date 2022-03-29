package flagmaker.overlays.attributes.sliders;

import flagmaker.overlays.OverlayControl;

public abstract class NumericAttributeSlider extends AttributeSlider
{
	public final boolean useMaxX;
	
	public NumericAttributeSlider(OverlayControl parent, String name, boolean useMaxX)
	{
		super(parent, name);
		this.useMaxX = useMaxX;
	}
	
	public abstract int getMaximum();
	public abstract void setMaximum(int value);
}
