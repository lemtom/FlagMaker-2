package flagmaker.Overlays.Attributes;

import flagmaker.Extensions.ColorExtensions;
import flagmaker.Overlays.Attributes.Sliders.AttributeSlider;
import flagmaker.Overlays.Attributes.Sliders.ColorAttributeSlider;
import flagmaker.Overlays.OverlayControl;
import javafx.scene.paint.Color;

public class ColorAttribute extends Attribute
{
	public Color value;
	
	private ColorAttributeSlider slider;
	
	public ColorAttribute(String name, Color initialValue)
	{
		super(name);
		value = initialValue;
	}
	
	@Override
	public void setValue(Object newValue)
	{
		value = (Color)newValue;
		if (slider != null)
		{
			slider.setValue(value);
		}
	}

	@Override
	public void setValue(String newValue)
	{
		setValue(ColorExtensions.parseColor(newValue));
	}
	
	@Override
	public Color getValue()
	{
		return value;
	}

	@Override
	public AttributeSlider getSlider(OverlayControl parent)
	{
		slider = new ColorAttributeSlider(parent, name, value);
		return slider;
	}

	@Override
	public Attribute clone()
	{
		return new ColorAttribute(name, value);
	}

	@Override
	public String exportAsString()
	{
		return ColorExtensions.toHexString(value, true);
	}
}
