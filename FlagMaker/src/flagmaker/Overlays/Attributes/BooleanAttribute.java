package flagmaker.Overlays.Attributes;

import flagmaker.Overlays.Attributes.Sliders.AttributeSlider;
import flagmaker.Overlays.Attributes.Sliders.BooleanAttributeSlider;
import flagmaker.Overlays.OverlayControl;

public class BooleanAttribute extends Attribute<Boolean>
{
	public boolean value;
	
	private BooleanAttributeSlider slider;
	
	public BooleanAttribute(String name, boolean initialValue)
	{
		super(name);
		value = initialValue;
	}

	@Override
	public void setValue(Object newValue)
	{
		value = (boolean)newValue;
		if (slider != null)
		{
			slider.setValue(value);
		}
	}

	@Override
	public void setValue(String newValue)
	{
		setValue(Boolean.parseBoolean(newValue));
	}
	
	@Override
	public Boolean getValue()
	{
		return value;
	}

	@Override
	public AttributeSlider getSlider(OverlayControl parent)
	{
		slider = new BooleanAttributeSlider(parent, name, value);
		return slider;
	}

	@Override
	public Attribute clone()
	{
		return new BooleanAttribute(name, value);
	}

	@Override
	public String exportAsString()
	{
		return value ? "true" : "false";
	}
}
