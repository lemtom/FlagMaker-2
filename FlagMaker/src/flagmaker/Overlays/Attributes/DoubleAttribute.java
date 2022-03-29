package flagmaker.Overlays.Attributes;

import flagmaker.Overlays.Attributes.Sliders.AttributeSlider;
import flagmaker.Overlays.Attributes.Sliders.DoubleAttributeSlider;
import flagmaker.Overlays.OverlayControl;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DoubleAttribute extends NumericAttribute<Double>
{
	public boolean isDiscrete;
	public double value;
	
	private DoubleAttributeSlider sslider;
	
	public DoubleAttribute(String name, double initialValue, int maximum, boolean useMaxX)
	{
		super(name, maximum, useMaxX);
		isDiscrete = initialValue % 1 == 0;
		value = initialValue;
	}

	@Override
	public void setValue(Object newValue)
	{
		value = (double)newValue;
		if (sslider != null)
		{
			sslider.setValue(value);
		}
	}

	@Override
	public void setValue(String newValue)
	{
		setValue(Double.parseDouble(newValue.replace(",", ".")));
	}
	
	@Override
	public Double getValue()
	{
		return value;
	}

	@Override
	public AttributeSlider getSlider(OverlayControl parent)
	{
		sslider = new DoubleAttributeSlider(parent, name, isDiscrete, value, maximum, useMaxX);
		return sslider;
	}

	@Override
	public Attribute clone()
	{
		return new DoubleAttribute(name, value, maximum, useMaxX);
	}

	@Override
	public String exportAsString()
	{
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("0.##", decimalFormatSymbols);
		return decimalFormat.format(value);
	}
}
