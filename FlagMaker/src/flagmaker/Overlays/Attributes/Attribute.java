package flagmaker.Overlays.Attributes;

import flagmaker.Overlays.Attributes.Sliders.AttributeSlider;
import flagmaker.Overlays.OverlayControl;

public abstract class Attribute<T>
{
	public String name;
	
	public Attribute(String name)
	{
		this.name = name;
	}
	
	public abstract <T> void setValue(T value);
	public abstract <T> void setValue(String value);
	public abstract <T> T getValue();
	public abstract AttributeSlider getSlider(OverlayControl parent);
	public abstract Attribute clone();
	public abstract String exportAsString();
}
