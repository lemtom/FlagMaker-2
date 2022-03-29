package flagmaker.Overlays.Attributes;

public abstract class NumericAttribute<T> extends Attribute<T>
{
	public int maximum;
	public boolean useMaxX;
	
	public NumericAttribute(String name, int maximum, boolean useMaxX)
	{
		super(name);
		this.maximum = maximum;
		this.useMaxX = useMaxX;
	}
}
