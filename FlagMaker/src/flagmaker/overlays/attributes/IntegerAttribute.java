package flagmaker.overlays.attributes;

import flagmaker.overlays.OverlayControl;
import flagmaker.overlays.attributes.sliders.AttributeSlider;
import flagmaker.overlays.attributes.sliders.IntegerAttributeSlider;

public class IntegerAttribute extends NumericAttribute<Integer>
{
	public int value;
	
	private IntegerAttributeSlider slider;
	
	public IntegerAttribute(String name, int initialValue, int maximum, boolean useMaxX)
	{
		super(name, maximum, useMaxX);
		value = initialValue;
	}

	@Override
	public void setValue(Object newValue)
	{
		value = (int)newValue;
		if (slider != null)
		{
			slider.setValue(value);
		}
	}

	@Override
	public void setValue(String newValue)
	{
		setValue(Integer.parseInt(newValue));
	}
	
	@Override
	public Integer getValue()
	{
		return value;
	}

	@Override
	public AttributeSlider getSlider(OverlayControl parent)
	{
		slider = new IntegerAttributeSlider(parent, name, value, maximum, useMaxX);
		return slider;
	}

	@Override
	public Attribute clone()
	{
		return new IntegerAttribute(name, value, maximum, useMaxX);
	}

	@Override
	public String exportAsString()
	{
		return String.format("%d", value);
	}
}
