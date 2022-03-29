package flagmaker.overlays;

import java.util.HashMap;

import flagmaker.overlays.attributes.*;
import flagmaker.overlays.overlaytypes.SpecialTypes.OverlayFlag;
import flagmaker.overlays.overlaytypes.SpecialTypes.OverlayImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Overlay
{
	public final String name;
	public boolean isEnabled;
	public Attribute[] attributes;
	protected int maximumX;
	protected int maximumY;
	
	protected abstract Shape[] thumbnail();
	
	public abstract void draw(Pane canvas);
	public abstract String exportSvg(int width, int height);
	
	protected Overlay(String name, Attribute[] attributes, int maximumX, int maximumY)
	{
		this.name = name;
		isEnabled = true;
		this.attributes = attributes;
		setMaximum(maximumX, maximumY);
	}
	
	public final void setMaximum(int newMaximumX, int newMaximumY)
	{
		maximumX = newMaximumX;
		maximumY = newMaximumY;
		
		for (Attribute a : attributes)
		{
			if (a instanceof NumericAttribute)
			{
				NumericAttribute n = (NumericAttribute)a;
				n.maximum = n.useMaxX ? maximumX : maximumY;
			}
		}
	}
	
	public Pane paneThumbnail()
	{
		Pane p = new Pane();
		p.setMinHeight(30);
		p.setMinWidth(30);
	
		for (Shape thumb : thumbnail())
		{
			thumb.setStroke(Color.BLACK);
			if (thumb.getStrokeWidth() == 1.0) thumb.setStrokeWidth(0);
			if (thumb.fillProperty().get() == null) thumb.setFill(Color.BLACK);
			p.getChildren().add(thumb);
		}
		
		return p;
	}
	
	public void setValues(HashMap<String, Object> values)
	{
		values.entrySet().stream().forEach((v) ->
		{
			String name = v.getKey();
			Object value = v.getValue();
			
			// Will fail for missing attributes
			setAttribute(name, value);
		});
	}
	
	public void setValuesFromStrings(HashMap<String, String> values)
	{
		values.entrySet().stream().forEach((v) ->
		{
			String name = v.getKey();
			String value = v.getValue();
			
			if (name.matches("size\\d"))
			{
				// Backwards-compatibility for 1.x file format
				int attributeIndex = Integer.parseInt(name.substring(4, 5));
				if (attributeIndex < attributes.length)
				{
					attributes[attributeIndex].setValue(value);
				}
			}
			else
			{
				// Will fail for missing sttributes
				for (Attribute a : attributes)
				{
					if (a.name.equalsIgnoreCase(name))
					{
						a.setValue(value);
						return;
					}
				}
			}
		});
	}
	
	public <T> void setAttribute(String name, T value)
	{
		for (Attribute a : attributes)
		{
			if (a.name.equalsIgnoreCase(name))
			{
				a.setValue(value);
				return;
			}
		}
		
		// Attribute not found
	}
	
	public Attribute getAttribute(String name)
	{
		for (Attribute a : attributes)
		{
			if (a.name.equalsIgnoreCase(name))
			{
				return a;
			}
		}
		
		// Attribute not found
		return null;
	}
	
	public double getDoubleAttribute(String name)
	{
		for (Attribute a : attributes)
		{
			if (a.name.equalsIgnoreCase(name) && a instanceof DoubleAttribute)
			{
				return ((DoubleAttribute)a).value;
			}
		}
		
		// Attribute not found
		return 0;
	}
	
	public int getIntegerAttribute(String name)
	{
		for (Attribute a : attributes)
		{
			if (a.name.equalsIgnoreCase(name) && a instanceof IntegerAttribute)
			{
				return ((IntegerAttribute)a).value;
			}
		}
		
		// Attribute not found
		return 0;
	}
	
	public boolean getBooleanAttribute(String name)
	{
		for (Attribute a : attributes)
		{
			if (a.name.equalsIgnoreCase(name) && a instanceof BooleanAttribute)
			{
				return ((BooleanAttribute)a).value;
			}
		}
		
		// Attribute not found
		return false;
	}
	
	public Color getColorAttribute(String name)
	{
		for (Attribute a : attributes)
		{
			if (a.name.equalsIgnoreCase(name) && a instanceof ColorAttribute)
			{
				return ((ColorAttribute)a).value;
			}
		}
		
		// Attribute not found
		return Color.BLACK;
	}
	
	public String exportToString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\ntype=%s\n", name));
				
		if (name.equals("flag"))
		{
			sb.append(String.format("path=%s\n", ((OverlayFlag)this).path));
		}

		if (name.equals("image"))
		{
			sb.append(String.format("path=%s\n", ((OverlayImage)this).getPath()));
		}

		for (Attribute Attribute : attributes)
		{
			sb.append(String.format("%s=%s\n", Attribute.name, Attribute.exportAsString()));
		}
		
		return sb.toString();
	}
}
